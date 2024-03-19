package com.adesso.bank.utils.timelogging;

import ch.qos.logback.classic.LoggerContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TimeLoggingWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLoggingWorker.class);

    @Autowired
    TimeLoggingConfig timeLoggingConfig;
    public static TimeLoggingMemoryAppender memoryAppender;
    public static final String LOGGER_NAME = "com.adesso.bank";
    public static final String MSG = "Time Taken";

    public TimeLoggingWorker() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new TimeLoggingMemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(ch.qos.logback.classic.Level.WARN);

//        logger.setLevel(ch.qos.logback.classic.Level.convertAnSLF4JLevel(timeLoggingConfig.getLevel()));<
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }
    public static void stopMemoryAppender() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return trackTime(joinPoint, Boolean.TRUE, "" );
    }

    Object trackTime(ProceedingJoinPoint joinPoint, Boolean enabled, String executionMethod) throws Throwable {
        if (!enabled){
            return joinPoint.proceed();
        }
        Level level = timeLoggingConfig.getLevel();
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        LOGGER.atLevel(level).log("Time Taken with {} by {} is {}", executionMethod, joinPoint, timeTaken);

        if (timeTaken > 100){
            printLogger();
        }

        return object;
    }

    void printLogger(){
        String fileName = generateFileNameWithTimestamp("output", ".txt");

//        memoryAppender.getLoggedEvents().stream().forEach(System.out::println);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Stream aus der Liste erstellen und in die Datei schreiben
            memoryAppender.getLoggedEvents().stream().forEach(line -> {
                try {
                    writer.write(String.valueOf(line));
                    writer.newLine(); // Neue Zeile einfügen
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Daten wurden erfolgreich in die Datei geschrieben: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static String generateFileNameWithTimestamp(String prefix, String suffix) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSSSSS").format(new Date());
        return prefix + "_" + timestamp + suffix;
    }

}

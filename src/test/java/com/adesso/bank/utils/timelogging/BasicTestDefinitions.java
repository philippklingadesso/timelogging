package com.adesso.bank.utils.timelogging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.adesso.bank.utils.timelogging.business.MemoryAppender;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BasicTestDefinitions {

    public static MemoryAppender memoryAppender;
    public static final String LOGGER_NAME = "com.adesso.bank";
    public static final String MSG = "Time Taken";

    public static void setAndStartMemoryAppender(Level loggingLevel) {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(loggingLevel);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

    }

    public static void stopMemoryAppender() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

}

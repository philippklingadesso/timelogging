package com.adesso.bank.utils.timelogging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeLoggingWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLoggingWorker.class);

    @Autowired
    TimeLoggingConfig timeLoggingConfig;

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
        return object;
    }

}

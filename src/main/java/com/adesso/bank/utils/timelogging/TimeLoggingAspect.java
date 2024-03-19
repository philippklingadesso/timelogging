package com.adesso.bank.utils.timelogging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

//AOP
@Aspect
@ConditionalOnProperty(
        prefix = "com.adesso.utils.timelogging", name = "active",
        havingValue = "true",
        matchIfMissing = true
)
@Component
public class TimeLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLoggingAspect.class);

    @Autowired
    TimeLoggingConfig timeLoggingConfig;
    @Autowired
    TimeLoggingWorker timeLoggingWorker;

    public TimeLoggingAspect() {
        LOGGER.info("TimeLoggingAspect Enabled");
    }


    @Around("com.adesso.bank.utils.timelogging.CommonJoinPontConfig.jpaExecution()")
    public Object jpaExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return timeLoggingWorker.trackTime(joinPoint,timeLoggingConfig.getJpaExecution(), "jpaExecution");
    }

    @Around("com.adesso.bank.utils.timelogging.CommonJoinPontConfig.repositoryExecution()")
    public Object repositoryExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return timeLoggingWorker.trackTime(joinPoint,timeLoggingConfig.getRepository(), "repositoryExecution");
    }
    @Around("com.adesso.bank.utils.timelogging.CommonJoinPontConfig.dataLayerExecution()")
    public Object dataLayerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return timeLoggingWorker.trackTime(joinPoint,timeLoggingConfig.getData(), "dataLayerExecution");
    }
    @Around("com.adesso.bank.utils.timelogging.CommonJoinPontConfig.trackTimeAnnotation()")
    public Object trackTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        return timeLoggingWorker.trackTime(joinPoint,timeLoggingConfig.getTracktime(), "trackTimeAnnotation");
    }


    @AfterThrowing("com.adesso.bank.utils.timelogging.CommonJoinPontConfig.dataLayerExecution()")
    public void printLogger() {
        timeLoggingWorker.printLogger();
    }


}


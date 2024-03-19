package com.adesso.bank.utils.timelogging;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;

public class CommonJoinPontConfig {

    @Pointcut("within(com.adesso.bank.data.model.data..*)")
    public void dataLayerExecution() {
    }

    @Pointcut("@annotation(com.adesso.bank.utils.timelogging.TrackTime)")
    public void trackTimeAnnotation() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryExecution() {
    }

    @Pointcut("execution(public * org.springframework.data.repository.CrudRepository..*(..))")
    public void jpaExecution() {
    }

}

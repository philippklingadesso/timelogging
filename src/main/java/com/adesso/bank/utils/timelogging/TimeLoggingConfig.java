package com.adesso.bank.utils.timelogging;

import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "com.adesso.utils.timelogging")
public class TimeLoggingConfig {

    private Boolean active = true;
    private Level level = Level.TRACE;
    private Boolean repository = true;
    private Boolean data = true;
    private Boolean tracktime = true;
    private Boolean jpaExecution = true;
    public TimeLoggingConfig() {
    }
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Boolean getRepository() {
        return repository;
    }

    public void setRepository(Boolean repository) {
        this.repository = repository;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    public Boolean getTracktime() {
        return tracktime;
    }

    public void setTracktime(Boolean tracktime) {
        this.tracktime = tracktime;
    }

    public Boolean getJpaExecution() {
        return jpaExecution;
    }

    public void setJpaExecution(Boolean jpaExecution) {
        this.jpaExecution = jpaExecution;
    }
}

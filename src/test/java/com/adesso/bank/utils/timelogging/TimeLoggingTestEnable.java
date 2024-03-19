package com.adesso.bank.utils.timelogging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.adesso.bank.utils.timelogging.business.BusinessLogicTest;
import com.adesso.bank.utils.timelogging.business.MemoryAppender;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.adesso.bank.utils.timelogging.BasicTestDefinitions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        properties = {
                "com.adesso.utils.timelogging.active=true",
                "com.adesso.utils.timelogging.level=trace"
        })
@EnableConfigurationProperties(value = TimeLoggingConfig.class)
@ContextConfiguration(
        classes = {
                TimeLoggingConfig.class,
                TimeLoggingAspect.class,
                TimeLoggingWorker.class,
                BusinessLogicTest.class
        })
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableAspectJAutoProxy
class TimeLoggingTestEnable {

    @Autowired
    private BusinessLogicTest businessLogicTest;
    @Autowired
    private TimeLoggingConfig timeLoggingConfig;
    private Level loggingLevel;

    @BeforeEach
    public void setup() {
        loggingLevel = Level.convertAnSLF4JLevel(timeLoggingConfig.getLevel());
        setAndStartMemoryAppender(loggingLevel);
    }

    @AfterEach
    public void cleanUp() {
        stopMemoryAppender();
    }

    @Test
    public void timeToggingAnnotationsTest() {
        businessLogicTest.loopMethod(MSG);

        System.out.println("loggingLevel config:" + loggingLevel);

        // I look for a specific message at a specific level, and I only have 1
        assertThat(memoryAppender.search(MSG, loggingLevel).size()).isEqualTo(1);
        // I check that the entry that is not present is the DEBUG level
        assertThat(memoryAppender.contains(MSG, loggingLevel)).isTrue();
    }

}

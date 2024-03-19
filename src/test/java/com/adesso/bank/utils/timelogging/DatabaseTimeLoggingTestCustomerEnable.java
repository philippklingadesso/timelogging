package com.adesso.bank.utils.timelogging;

import ch.qos.logback.classic.Level;
import com.adesso.bank.data.model.data.TestRepository;
import com.adesso.bank.data.model.data.TestService;
import com.adesso.bank.data.model.data.customer.CustomerRepository;
import com.adesso.bank.data.model.data.customer.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.adesso.bank.utils.timelogging.BasicTestDefinitions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        properties = {
                "com.adesso.utils.timelogging.active=true",
                "com.adesso.utils.timelogging.level=trace",
                "com.adesso.utils.timelogging.repository=true"
        })
@EnableConfigurationProperties(value = TimeLoggingConfig.class)
@ContextConfiguration(
        classes = {
                TimeLoggingConfig.class,
                TimeLoggingAspect.class,
                TimeLoggingWorker.class,
                CustomerService.class,
                CustomerRepository.class
        })
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableAspectJAutoProxy
class DatabaseTimeLoggingTestCustomerEnable {

    @Autowired
    private CustomerService customerService;
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
    public void TestTimeLoggingEnableDatabaseRetrieveOne() {
        String expected = "CustomerData";

        String result = customerService.readOneCustomer();

        // I look for a specific message at a specific level, and I only have 3
        assertThat(memoryAppender.search(MSG, loggingLevel).size()).isEqualTo(3);
        // I check that the entry that is not present is the DEBUG level
        assertThat(memoryAppender.contains(MSG, loggingLevel)).isTrue();
        // check if method return something
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void TestTimeLoggingEnableDatabaseRetrieveMore() {
        List<String> expected = Arrays.asList("CustomerData1", "CustomerData2", "CustomerData3");

        List<String> result = customerService.readMoreCustomer();

        // I look for a specific message at a specific level, and I only have 3
        assertThat(memoryAppender.search(MSG, loggingLevel).size()).isEqualTo(3);
        // I check that the entry that is not present is the DEBUG level
        assertThat(memoryAppender.contains(MSG, loggingLevel)).isTrue();
        // check if method return something
        assertThat(result).isEqualTo(expected);

    }

}

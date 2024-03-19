package com.adesso.bank.data.model.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public class TestRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRepository.class);

    public List<String> retrieveAll() {
        LOGGER.info("read 'All' from database");
        for (int i = 0; i < 1000; i++) {
            //do nothing, just use a bit of time
        }
        return Arrays.asList("data1", "data2", "data3");
    }

    public String retrieveOne() {
        LOGGER.info("read 'One' from database");
        for (int i = 0; i < 1000; i++) {
            //do nothing, just use a bit of time
        }
        return "data1";
    }

    public String retrieveOneAndThrow() throws RuntimeException {
        LOGGER.info("read 'One' from database");
//        try {
            for (int i = 0; i < 1000; i++) {
                if (i == 10) {
                    throw new RuntimeException();
                }
                //do nothing, just use a bit of time
            }
//        } catch (RuntimeException ex) {
//            do nothing
//        }
        return "data1";
    }

    public String retrieveOneAndWait() {
        LOGGER.info("read 'One' from database for a long time");
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "data1";
    }

}

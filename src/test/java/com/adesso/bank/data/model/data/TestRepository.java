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

}

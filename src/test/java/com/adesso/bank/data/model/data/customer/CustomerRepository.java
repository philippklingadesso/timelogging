//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.adesso.bank.data.model.data.customer;

import com.adesso.bank.data.model.data.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CustomerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRepository.class);

    public String retrieveOneCustomer() {
        LOGGER.info("read 'One Customer' from database");
        for (int i = 0; i < 1000; i++) {
            //do nothing, just use a bit of time
        }
        return "CustomerData";
    }

    public List<String> retrieveMoreCustomer() {
        LOGGER.info("read 'More Customer' from database");
        for (int i = 0; i < 1000; i++) {
            //do nothing, just use a bit of time
        }
        return Arrays.asList("CustomerData1", "CustomerData2", "CustomerData3");
    }


}

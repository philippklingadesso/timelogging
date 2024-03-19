package com.adesso.bank.utils.timelogging.business;

import com.adesso.bank.utils.timelogging.TrackTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessLogicTest.class);

    @TrackTime
    public void loopMethod(String MSG) {
        for (int i = 0; i < 1000; i++) {
            LOGGER.info("#" + i + " just some logs...");
            //do nothing, just use a bit of time
        }
    }
}

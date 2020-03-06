package com.example.springexamples.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author VKT-HALILU
 * @date 3/6/2020
 */
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }

}

package com.example.springexamples.messaging;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author VKT-HALILU
 * @date 3/6/2020
 */
@Component
public class ReceiverRabbitMq {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}

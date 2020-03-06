package com.example.springexamples;

import com.example.springexamples.messaging.ReceiverRabbitMq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author VKT-HALILU
 * @date 3/6/2020
 */
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final ReceiverRabbitMq receiverRabbitMq;

    public Runner(ReceiverRabbitMq receiverRabbitMq, RabbitTemplate rabbitTemplate) {
        this.receiverRabbitMq = receiverRabbitMq;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message through Rabbit MQ...");
        rabbitTemplate.convertAndSend(SpringExamplesMain.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        receiverRabbitMq.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}

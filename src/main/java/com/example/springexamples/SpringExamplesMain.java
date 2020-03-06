package com.example.springexamples;

/**
 * @author VKT-HALILU
 * @date 3/5/2020
 */

import com.example.springexamples.messaging.Receiver;
import com.example.springexamples.properties.StorageProperties;
import com.example.springexamples.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class SpringExamplesMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringExamplesMain.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }


    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(SpringExamplesMain.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        while (receiver.getCount() == 0) {
            LOGGER.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
            Thread.sleep(500L);
        }

        System.exit(0);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args -> {
            storageService.deleteAll();
            storageService.init();
        });
    }


}
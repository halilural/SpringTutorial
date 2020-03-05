package com.example.springexamples;

/**
 * @author VKT-HALILU
 * @date 3/5/2020
 */

import com.example.springexamples.properties.StorageProperties;
import com.example.springexamples.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class SpringExamplesMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringExamplesMain.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args -> {
            storageService.deleteAll();
            storageService.init();
        });
    }

}
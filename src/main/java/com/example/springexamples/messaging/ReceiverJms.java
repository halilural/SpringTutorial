package com.example.springexamples.messaging;

import com.example.springexamples.model.Email;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author VKT-HALILU
 * @date 3/6/2020
 */
@Component
public class ReceiverJms {
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }

}

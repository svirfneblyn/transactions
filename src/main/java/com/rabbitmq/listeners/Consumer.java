package com.rabbitmq.listeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements MessageListener {
    Logger logger = LogManager.getRootLogger();

 /*   @Autowired
    JsonParser parser;*/

    @Override
    public void onMessage(Message message) {

        logger.info(" message: -> " + new String(message.getBody()) + " <-");

    }

}

package com.rest.controller;

import com.rabbitmq.send.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class CustomController {
    @Autowired
    private Producer producer;
    @RequestMapping(method = RequestMethod.GET)
    public void custom() {
         producer.produceQue();
    }
}

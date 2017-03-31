package com.rabbitmq.send;

import com.rest.entity.Employee;
import com.rest.repositoryservices.EmployeeService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Producer {
    private static Logger logger = LogManager.getRootLogger();
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void produceQue() {
        AtomicInteger counter = new AtomicInteger();
        for (int i = 0; i < 5; i++) {
            logger.info("sending new message ...");
            counter.incrementAndGet();
            Employee employee = employeeService.getEmployeById(counter.longValue());
            rabbitTemplate.convertAndSend(employee);

        }
    }
}

package com.example.second_instance.service;

import com.example.second_instance.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${check.rabbitmq.exchange}")
    private String exchange;

    @Value("${check.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Employee company) {
        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg instance 2 = " + company);

    }

}
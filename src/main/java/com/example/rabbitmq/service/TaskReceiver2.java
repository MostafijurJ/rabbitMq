package com.example.rabbitmq.service;

import com.example.rabbitmq.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;


@Service
public class TaskReceiver2 {

    @RabbitListener(queues = "${check.rabbitmq.queue}")
    private void getMessage(Employee employee) throws InterruptedException {
        System.out.println(" Consumed By Instance 1");
        System.out.println(" Received By Task 2:  '");
        receive(employee);
    }

    @RabbitHandler
    public void receive(Employee employee) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(" Employee Name '" + employee.getEmpName() + "'"+" ID: "+ employee.getEmpId());
        watch.stop();
        System.out.println(" Instance 1 Task 2  Done in " + watch.getTotalTimeSeconds() + "s");
    }




}
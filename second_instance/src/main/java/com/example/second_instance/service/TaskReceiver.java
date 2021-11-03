package com.example.second_instance.service;

import com.example.second_instance.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;


@Service
public class TaskReceiver {

    @RabbitListener(queues = "${check.rabbitmq.queue}")
    private void getMessage(Employee employee) throws InterruptedException {
        System.out.println(" Consumed By Instance 2:  '");
        System.out.println(" Received By Task 1:  '");
        receive(employee);
    }

    @RabbitHandler
    public void receive(Employee employee) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("Employee Name '" + employee.getEmpName() + "'"+" ID: "+ employee.getEmpId());
        watch.stop();
        System.out.println("Instance 2 Task 1: Done in " + watch.getTotalTimeSeconds() + "s");
    }
}
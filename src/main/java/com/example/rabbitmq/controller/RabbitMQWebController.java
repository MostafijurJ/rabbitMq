package com.example.rabbitmq.controller;

import com.example.rabbitmq.service.RabbitMQSender;
import com.example.rabbitmq.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/check/")
public class RabbitMQWebController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    /**
     * check/producer?empName=emp1&empId=emp001
     *
     * @param empName
     * @param empId
     * @return
     */
    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {

        Employee emp=new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitMQSender.send(emp);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}


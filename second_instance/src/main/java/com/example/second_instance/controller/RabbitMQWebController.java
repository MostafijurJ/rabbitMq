package com.example.second_instance.controller;

import com.example.second_instance.model.Employee;
import com.example.second_instance.service.RabbitMQSender;
import com.example.second_instance.service.TaskReceiver;
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

    @Autowired
    TaskReceiver taskReceiver;

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
        emp.setEmpId(empId+" Sent from Instance 2");
        emp.setEmpName(empName);
        rabbitMQSender.send(emp);
        return "Message sent to the RabbitMQ JavaInUse Successfully \n Name: "+empName+"\n Id: "+empId;
    }


    /**
     * check/consume?empName=emp1
     * @param empName
     * @param empId
     * @return
     */

//    @GetMapping(value = "/consume")
//    public void consume(@RequestParam("empName") String empName) throws InterruptedException {
//
//         taskReceiver.receive(empName);
//    }
}


package com.smaz.ms.controller;

import com.smaz.ms.model.User;
import com.smaz.ms.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {

    Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

    @Autowired
    ProducerService producerService;


    @GetMapping("/send")
    public void sendMessage(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age){
        logger.info("name is :" +name + " and age of :" + age);
        //producerService.sendMessage(new User(name, age));
        IntStream.range(1, 1000).forEach(i -> producerService.sendMessage(new User(name+i, age+i)));
        //producerService.sendMessage(new UserModel(name, age));
    }

}

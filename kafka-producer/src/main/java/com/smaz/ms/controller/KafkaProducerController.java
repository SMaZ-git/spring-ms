package com.smaz.ms.controller;

import com.smaz.ms.model.ResponseModel;
import com.smaz.ms.model.User;
import com.smaz.ms.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {

    Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

    @Autowired
    ProducerService producerService;


    @GetMapping("/send")
    public Mono<ResponseModel> sendMessage(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        long start = System.currentTimeMillis();
        logger.info(String.format(Thread.currentThread().getName()));
        logger.info("name is :" + name + " and age of :" + age);

        Mono.just(new User(name, age))
                .log()
                .subscribeOn(Schedulers.parallel())
                .subscribe(user -> producerService.sendMessage(user));
        logger.info(String.format(" time taken %s", (System.currentTimeMillis() - start)));
        return Mono.just(new ResponseModel("Success", "Request submitted successfully"));
    }

}

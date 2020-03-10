package com.smaz.ms.service;

import com.smaz.ms.model.User;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@CommonsLog
public class ProducerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendMessage(User user){

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("user", user.getName(), user);
        //ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("user", user.toString());

        logger.info(future.toString());

        // Test the theory with delay of 5 seconds, Http should get response immediately
        /*try {
            log.info(String.format("Thread name %s", Thread.currentThread().getName()));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        
        // register a callback with the listener to receive the result of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("sent message='{}' with offset={}", user.toString(),
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("unable to send message='{}'", user.toString(), ex);
            }
        });
    }
}

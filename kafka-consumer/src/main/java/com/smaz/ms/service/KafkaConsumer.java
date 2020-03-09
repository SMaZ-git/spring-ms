package com.smaz.ms.service;

import com.smaz.ms.model.User;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Consumer Logger")
public class KafkaConsumer {

    @KafkaListener(topics = "user", groupId = "group_id")
    public void consumer(ConsumerRecord<String, User> message){

        log.info(message);
    }
}

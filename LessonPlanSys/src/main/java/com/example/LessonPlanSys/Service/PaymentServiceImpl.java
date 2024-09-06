package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    //In this class we want to send off some message to our Kafka Topic (topic is a collection of events)
    //To do this we need to add in a KafkaTemplate

    //We'll do some field injection
    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    //we'll make a method that sends a message to out Kafka Topic

    public void sendPayment(Payment payment){
        logger.info("Sent a new payment!");

        //We can send the payment like we did last time, but we can also use the MassageBuilder API to
        //send a better message if we desire
        Message<Payment> message = MessageBuilder
                .withPayload(payment)
                .setHeader(KafkaHeaders.TOPIC,"payment")
                .build();

        this.kafkaTemplate.send(message);
    }
}

// package com.example.LessonPlanSys.Service;

// import com.example.LessonPlanSys.Model.Payment;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// @Service
// public class InvoiceServiceImpl implements InvoiceService {
//     private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

//     @KafkaListener(topics = "payment",groupId = "payment-listeners-3")
//     public void createInvoice(Payment payment){
//         logger.info("Invoice created: Payment received and is ->"+(payment.getAmount()));
//     }
// }

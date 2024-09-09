// package com.example.LessonPlanSys.Controller;

// import com.example.LessonPlanSys.Model.Payment;
// import com.example.LessonPlanSys.Service.PaymentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/payments")
// public class PaymentController {
//     @Autowired
//     private PaymentService paymentService;

//     @PostMapping
//     public void createPayment(@RequestBody Payment payment){

//         //let's print the message to the console for now
//         System.out.println(payment);
//         paymentService.sendPayment(payment);
//     }
// }

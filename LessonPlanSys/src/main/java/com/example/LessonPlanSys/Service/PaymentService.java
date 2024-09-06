package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Payment;
import org.springframework.stereotype.Service;

public interface PaymentService {
    void sendPayment(Payment payment);

}
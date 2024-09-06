package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Payment;
import org.springframework.stereotype.Service;

public interface InvoiceService {
    public void createInvoice(Payment payment);
}

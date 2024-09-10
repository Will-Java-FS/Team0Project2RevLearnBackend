package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final EnrollmentsService enrollmentsService;

    // Constructor injection
    public InvoiceServiceImpl(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;    }

    @KafkaListener(topics = "payment", groupId = "payment-listeners-3")
    public void createInvoice(Payment payment) {
        Integer userId = payment.getUserId();
        Integer courseId = payment.getCourseId();

        // Call the service method to update enrollment status
        int updatedRows = enrollmentsService.updateEnrollmentStatusToPaid(userId, courseId);

        if (updatedRows > 0) {
            logger.info("Invoice created: Payment received and enrollment status updated to 'paid' for student ID {} and course ID {}", userId, courseId);
        } else {
            logger.warn("Invoice created: Payment received but no enrollment found for student ID {} and course ID {}", userId, courseId);
        }
    }
}

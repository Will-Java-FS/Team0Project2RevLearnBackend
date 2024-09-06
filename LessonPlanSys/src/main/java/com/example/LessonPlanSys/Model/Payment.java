package com.example.LessonPlanSys.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id", updatable = false)
    private int id;

//    TODO: implement Foreign key
    private int userId;  // Link to the user making the payment
//    TODO: implement Foreign key
    private int courseId; // Link to the course being paid for
    private BigDecimal amount;
//    private LocalDateTime paymentDate;
}

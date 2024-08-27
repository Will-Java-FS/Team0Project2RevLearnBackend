package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

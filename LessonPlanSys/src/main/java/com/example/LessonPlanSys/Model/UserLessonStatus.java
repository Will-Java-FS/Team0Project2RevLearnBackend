package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="userLessonStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLessonStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_plan_id")
    private LessonPlan lessonPlan;

    @Column(name = "complete")
    private Boolean isComplete;
}

package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="userLessonStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLessonStatus {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_lesson_id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_plan_id")
    private LessonPlan lessonPlan;

    @Getter
    @Setter
    @Column(name = "complete")
    private boolean isComplete;
}

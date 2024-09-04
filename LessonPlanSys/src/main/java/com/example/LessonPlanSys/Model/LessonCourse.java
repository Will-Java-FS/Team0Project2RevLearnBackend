package com.example.LessonPlanSys.Model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_plan_course")
public class LessonCourse {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_course_id", updatable = false)
    private int lesson_course_id;
  
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "lesson_plan_id")
    private LessonPlan lessonPlan;


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
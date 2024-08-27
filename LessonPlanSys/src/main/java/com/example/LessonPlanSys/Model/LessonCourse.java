package com.example.LessonPlanSys.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessonplan_course")
public class LessonCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lesson_course_id;

    @ManyToOne
    @JoinColumn(name = "lesson_plan_id")
    private LessonPlan lessonPlan;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
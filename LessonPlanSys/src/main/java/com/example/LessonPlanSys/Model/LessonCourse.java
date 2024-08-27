package com.example.LessonPlanSys.Model;

import com.example.Model.LessonPlan;
import com.example.Model.Course;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LessonCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_course_id")
    private int lesson_course_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lessonplans",
            joinColumns = @JoinColumn(name = "lesson_plan_id"))
    LessonPlan lessonPlan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "courses",
     joinColumns = @JoinColumn(name = "course_id"))
    Course course;

}
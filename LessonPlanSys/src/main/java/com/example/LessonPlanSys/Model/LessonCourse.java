package com.example.LessonPlanSys.Model;
import jakarta.persistence.*;
import lombok.Data;
import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonPlan;



@Data
@Entity
public class LessonCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_course_id")
    private int id;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "lessonplans",
//            joinColumns = @JoinColumn(name = "lesson_plan_id"))
//    LessonPlan lessonPlan;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "courses",
//     joinColumns = @JoinColumn(name = "course_id"))
//    Course course;

}
package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LessonCourse {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lessonplans",
            joinColumns = @JoinColumn(name = "lesson_plan_id"))
    LessonPlan lessonPlan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "courses",
            joinColumns = @JoinColumn(name = "course_id"))
    Course course;

}
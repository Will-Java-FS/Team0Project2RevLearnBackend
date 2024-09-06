package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import com.example.LessonPlanSys.Repo.LessonCourseRepo;
import com.example.LessonPlanSys.Repo.LessonPlanRepo;
import com.example.LessonPlanSys.Repo.UserLessonStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LessonPlanServiceImp implements LessonPlanService {
    LessonPlanRepo lessonPlanRepo;
    LessonCourseRepo lessonCourseRepo;
    UserLessonStatusRepo userLessonStatusRepo;

    @Autowired
    public LessonPlanServiceImp(LessonPlanRepo lessonPlanRepo, LessonCourseRepo lessonCourseRepo,
                                UserLessonStatusRepo userLessonStatusRepo) {
        this.lessonPlanRepo = lessonPlanRepo;
        this.lessonCourseRepo = lessonCourseRepo;
        this.userLessonStatusRepo = userLessonStatusRepo;
    }

    @Override
    public List<LessonPlan> getAll() {
        return lessonPlanRepo.findAll();
    }

    @Override
    public LessonPlan getById(int id) {
        return lessonPlanRepo.findById(id).orElse(null);
    }

    @Override
    public LessonPlan addLessonPlan(LessonPlan nlp) {
        nlp.setLp_updated_at(new Timestamp(System.currentTimeMillis()));
        nlp.setLp_created_at(new Timestamp(System.currentTimeMillis()));
        return lessonPlanRepo.save(nlp);
    }

    @Override
    public void deleteLessonPlan(int lesson_plan_id) {
        // exist ? continue: skip
        LessonPlan exists = getById(lesson_plan_id);
        if (exists == null) {
            return;
        }

        // Remove lesson from courses
        List<LessonCourse> lessonCourses = lessonCourseRepo.findAll()
                .stream()
                .filter(lc -> lc.getLessonPlan().getLesson_plan_id() == lesson_plan_id)
                .toList();
        lessonCourses.forEach(lc ->
                lessonCourseRepo.deleteById(lc.getLesson_course_id())
        );

        // Remove lesson from user progress
        List<UserLessonStatus> userLessonStatuses = userLessonStatusRepo.findAll()
                .stream()
                .filter(ul -> ul.getLessonPlan().getLesson_plan_id() == lesson_plan_id)
                .toList();
        userLessonStatuses.forEach(ul ->
                userLessonStatusRepo.deleteById(ul.getUser_lesson_id()));

        // Remove lesson
        lessonPlanRepo.deleteById(lesson_plan_id);
    }

    @Override
    public LessonPlan updateLessonPlan(int id, LessonPlan updatedLessonPlan) {
        LessonPlan lessonPlan = getById(id);
        if (lessonPlan == null || id != updatedLessonPlan.getLesson_plan_id()) {
            return null;
        }

        lessonPlan.setContent(updatedLessonPlan.getContent());
        lessonPlan.setTitle(updatedLessonPlan.getTitle());
        lessonPlan.setLp_updated_at(new Timestamp(System.currentTimeMillis()));

        return lessonPlanRepo.save(lessonPlan);
    }
}

package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Repo.LessonPlanRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LessonPlanServiceImp implements LessonPlanService{
    LessonPlanRepo lessonPlanRepo;
    @Autowired
    public LessonPlanServiceImp(LessonPlanRepo lessonPlanRepo)
    {
        this.lessonPlanRepo = lessonPlanRepo;
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
    public void deleteLessonPlan(int id) {
         lessonPlanRepo.deleteById(id);
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

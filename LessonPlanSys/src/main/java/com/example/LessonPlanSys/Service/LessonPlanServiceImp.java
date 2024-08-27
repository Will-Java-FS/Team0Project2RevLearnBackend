package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Repo.LessonPlanRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return lessonPlanRepo.getAllLessonPlans();
    }

    @Override
    public LessonPlan getById(int id) {
        return lessonPlanRepo.getbyLPID(id);
    }

    @Override
    public LessonPlan addLessonPlan(LessonPlan nlp) {
        return lessonPlanRepo.save(nlp);
    }

    @Override
    public void deleteLessonPlan(int id) {
         lessonPlanRepo.deleteById(id);
    }

    @Override
    @Transactional
    public LessonPlan updateLessonPlan(LessonPlan nlp, int id) {
        LessonPlan olp = lessonPlanRepo.getbyLPID(id);
        if(olp != null)
        {
            lessonPlanRepo.setLPInfoById(nlp.getTitle(), nlp.getContent(), nlp.getLp_updated_at(), id);
            return getById(id);
        }
        return null;
    }
}

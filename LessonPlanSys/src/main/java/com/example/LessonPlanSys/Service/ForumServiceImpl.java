package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Forum;
import com.example.LessonPlanSys.Repo.ForumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService{

    @Autowired
    ForumRepo fr;

    public ForumServiceImpl(ForumRepo fr){
        this.fr = fr;
    }

    @Override
    public List<Forum> getAllForums() {
        return fr.findAll();
    }

    @Override
    public Forum getForum(Integer id) {
        return fr.findById(id).orElse(null);
    }

    @Override
    public Integer deleteForum(Integer id) {
        if(fr.existsById(id)){
            fr.deleteById(id);
            return 1;
        }
        return null;
    }

    @Override
    public Forum updateForum(Integer id, Forum newForum) {
        if(fr.existsById(id)){
            Forum forum = fr.findById(id).get();
            forum.setForum_updated_at(Timestamp.valueOf(LocalDateTime.now()));
            forum.setTitle(newForum.getTitle());
            return fr.save(forum);
        }
        return null;
    }

    @Override
    public Forum addForum(Integer courseId, Forum newForum) {
        if(fr.courseExists(courseId) == 1){
            newForum.setForum_created_at(Timestamp.valueOf(LocalDateTime.now()));
            newForum.setForum_updated_at(Timestamp.valueOf(LocalDateTime.now()));
            return fr.save(newForum);
        }
        return null;
    }


}

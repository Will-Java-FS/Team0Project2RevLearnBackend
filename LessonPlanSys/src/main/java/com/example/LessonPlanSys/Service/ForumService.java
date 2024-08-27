package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Forum;

import java.util.List;

public interface ForumService
{
//    GetAll, GetById, Add, Delete, Update
    public List<Forum> getAllForums();

    public Forum getForum(Integer id);

    public Integer deleteForum(Integer id);

    public Forum updateForum(Integer id, Forum newForum);

    public Forum addForum(Integer courseId, Forum newForum);
}

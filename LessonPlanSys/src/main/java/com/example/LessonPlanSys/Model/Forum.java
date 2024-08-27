package com.example.LessonPlanSys.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "discussionforums")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Forum
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id", updatable = false)
    private int forum_id;

    @OneToOne//(mappedBy = "discussionforums", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id", updatable = false)
    private Course course;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Timestamp created_at;

    @Column(nullable = false)
    private Timestamp updated_at;

//    @OneToMany//(mappedBy = "discussionforums", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    @JoinColumn(name = "forumpost_id", updatable = false)
//    private List<ForumPost> posts;//ForumPosts
}

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
    @Column(name = "id", updatable = false)
    private int id;

    @OneToOne(mappedBy = "discussionforums", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "course_id", updatable = false)
    private int course_id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Timestamp created_at;

    @Column(nullable = false)
    private Timestamp updated_at;

    @OneToMany(mappedBy = "discussionforums", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<?> posts;//ForumPosts
}

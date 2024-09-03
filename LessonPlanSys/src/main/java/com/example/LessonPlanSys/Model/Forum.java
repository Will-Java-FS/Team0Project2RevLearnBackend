package com.example.LessonPlanSys.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id", updatable = false)
    private int forum_id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id", updatable = false)
    private Course course;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String title;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp forum_created_at;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp forum_updated_at;

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JoinColumn(name = "forumpost_id", updatable = false)
    private List<ForumPost> posts;//ForumPosts*/
}

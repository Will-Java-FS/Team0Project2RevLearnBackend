package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "forum_posts")
public class forumpost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int forumpost_id;

    @ManyToOne
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp created_at;

    @Column(name="updated_at", nullable = false)
    private Timestamp updated_at;

    public forumpost(int forumpost_id, Forum forum, User user, Timestamp created_at, Timestamp updated_at) {
        this.forumpost_id = forumpost_id;
        this.forum = forum;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}

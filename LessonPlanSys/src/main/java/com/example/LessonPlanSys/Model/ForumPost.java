package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forum_posts")
public class ForumPost {
    @Getter
    @Id
    @Column(name = "forum_post_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int forumpost_id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum_id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp post_created_at;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private Timestamp post_updated_at;

}
package com.example.LessonPlanSys.Model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discussionforums", schema = "project2") // Adjust table name and schema as necessary
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id", updatable = false)
    private Integer forumId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "forum_created_at", nullable = false)
    private Timestamp forumCreatedAt;

    @Column(name = "forum_updated_at")
    private Timestamp forumUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
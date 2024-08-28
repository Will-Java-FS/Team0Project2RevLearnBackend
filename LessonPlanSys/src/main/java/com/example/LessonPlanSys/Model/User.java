package com.example.LessonPlanSys.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int user_id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;


    @Column(nullable = false)
    private ZonedDateTime userCreatedAt;

    @Column(nullable = false)
    private ZonedDateTime userUpdatedAt;

    @OneToMany//(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", updatable = false)
    private List<Course> courses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Program program;

}

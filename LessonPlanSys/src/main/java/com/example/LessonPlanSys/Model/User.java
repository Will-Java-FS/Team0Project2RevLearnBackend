package com.example.LessonPlanSys.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private int user_id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Getter
    @Setter
    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Getter
    @Setter
    @Column(nullable = false, length = 255)
    private String firstName;

    @Getter
    @Setter
    @Column(nullable = false, length = 255)
    private String lastName;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp userCreatedAt;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp userUpdatedAt;

    /*@Getter
    @OneToOne//(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "program_id", updatable = false)
    private Program program;*/

}

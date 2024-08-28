package com.example.LessonPlanSys.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="programs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="program_id", updatable = false)
    private int program_id;

    @Column(nullable = false)
    private String program_name;

    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();
}

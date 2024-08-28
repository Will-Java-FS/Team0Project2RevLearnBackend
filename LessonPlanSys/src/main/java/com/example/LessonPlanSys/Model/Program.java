package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="programs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="program_id", updatable = false)
    private int program_id;


    @Getter
    @Setter
    @Column(name = "program_name", nullable = false)
    private String program_name;



}

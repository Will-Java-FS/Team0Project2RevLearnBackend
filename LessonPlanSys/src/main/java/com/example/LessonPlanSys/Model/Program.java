package com.example.LessonPlanSys.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //TODO: Uncomment when Course model is done.
/*    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Course> courses;*/

}

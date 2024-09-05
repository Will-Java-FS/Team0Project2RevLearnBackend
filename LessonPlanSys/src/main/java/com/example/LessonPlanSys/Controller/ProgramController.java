package com.example.LessonPlanSys.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Service.CourseService;
import com.example.LessonPlanSys.Service.ProgramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/programs")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor // Generates a constructor with all the fields
public class ProgramController {
    private final ProgramService programService;
    private final CourseService courseService;

    // GET Program by ID
    @GetMapping("/{program_id}")
    public ResponseEntity<Program> getProgram(@PathVariable("program_id") int id) {
        return programService.getProgram(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add program
    @PostMapping
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        Program savedProgram = programService.addProgram(program);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProgram);
    }

    // Update program
    @PutMapping("/{program_id}")
    public ResponseEntity<Program> updateProgram(@PathVariable("program_id") int id, @RequestBody Program program) {
        return programService.updateProgram(id, program)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete program
    @DeleteMapping("/{program_id}")
    public ResponseEntity<Program> deleteProgram(@PathVariable("id") int id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }

    // Get all programs
    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    // Get all associated courses
    @GetMapping ("/{program_id}/courses")
    ResponseEntity<List<Course>> getAllCoursesByProgramId(@PathVariable("program_id") int id) {
        return courseService.getCoursesByProgramId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

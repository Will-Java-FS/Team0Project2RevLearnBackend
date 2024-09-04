package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Service.CourseService;
import com.example.LessonPlanSys.Service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor    // Generates a constructor with all the fields
public class ProgramController {
    private final ProgramService programService;
    private final CourseService courseService;

    // GET Program by ID
    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgram(@PathVariable("id") int id) {
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
    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable("id") int id, @RequestBody Program program) {
        return programService.updateProgram(id, program)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete program
    @DeleteMapping("/{id}")
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
    @GetMapping ("/{id}/courses")
    ResponseEntity<List<Course>> getAllCoursesByProgramId(@PathVariable("id") int id) {
        return courseService.getCoursesByProgramId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

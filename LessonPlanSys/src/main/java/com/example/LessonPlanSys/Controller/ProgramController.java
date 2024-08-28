package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Service.CourseService;
import com.example.LessonPlanSys.Service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;
    private final CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgram(@PathVariable("id") int id) {
        return programService.getProgram(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        Program savedProgram = programService.addProgram(program);
        return ResponseEntity.ok(savedProgram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable("id") int id, @RequestBody Program program) {
        return programService.updateProgram(id, program)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Program> deleteProgram(@PathVariable("id") int id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    @GetMapping ("/{id}/courses")
    ResponseEntity<List<Course>> getAllCoursesByProgramId(@PathVariable("id") int id) {
        return courseService.getCoursesByProgramId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

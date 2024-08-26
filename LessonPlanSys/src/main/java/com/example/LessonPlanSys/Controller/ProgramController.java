package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
@CrossOrigin
public class ProgramController {
    @Autowired
    ProgramService ps;

    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgram(@PathVariable("id") int id) {
        Program program = ps.getProgram(id);

        return program != null
                ? new ResponseEntity<>(program, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        Program savedProgram = ps.addProgram(program);
        return (savedProgram != null)
                ? new ResponseEntity<>(savedProgram, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> updateAccount(@PathVariable("id") int id, @RequestBody Program program) {
        Program updatedProgram = ps.updateProgram(id, program);
        return (updatedProgram != null)
                ? new ResponseEntity<>(updatedProgram, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Program> deleteAccount(@PathVariable("id") int id) {
        ps.deleteProgram(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        return new ResponseEntity<>(ps.getAllPrograms(), HttpStatus.OK);
    }

}

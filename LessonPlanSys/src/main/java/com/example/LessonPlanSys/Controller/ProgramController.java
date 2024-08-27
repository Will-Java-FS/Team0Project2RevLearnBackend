//package com.example.LessonPlanSys.Controller;
//
//import com.example.LessonPlanSys.Model.Program;
//import com.example.LessonPlanSys.Service.ProgramService;
//import com.example.LessonPlanSys.Service.ProgramServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/programs")
//@CrossOrigin
//public class ProgramController {
//
//    public ProgramServiceImpl programService;
//
//    @Autowired
//    public ProgramController(ProgramServiceImpl programService)
//    {
//        this.programService = programService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Program> getProgram(@PathVariable("id") int id) {
//        Program program = programService.getProgram(id);
//
//        return program != null
//                ? new ResponseEntity<>(program, HttpStatus.OK)
//                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
//        Program savedProgram = programService.addProgram(program);
//        return (savedProgram != null)
//                ? new ResponseEntity<>(savedProgram, HttpStatus.OK)
//                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Program> updateAccount(@PathVariable("id") int id, @RequestBody Program program) {
//        Program updatedProgram = programService.updateProgram(id, program);
//        return (updatedProgram != null)
//                ? new ResponseEntity<>(updatedProgram, HttpStatus.OK)
//                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Program> deleteAccount(@PathVariable("id") int id) {
//        programService.deleteProgram(id);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Program>> getAllPrograms() {
//        return new ResponseEntity<>(programService.getAllPrograms(), HttpStatus.OK);
//    }
//
//}

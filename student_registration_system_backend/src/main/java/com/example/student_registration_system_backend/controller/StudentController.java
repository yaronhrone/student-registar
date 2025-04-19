package com.example.student_registration_system_backend.controller;

import com.example.student_registration_system_backend.model.Student;
import com.example.student_registration_system_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody Student student, @RequestParam Integer courseId){
        try{
            String result = studentService.register(student, courseId);
            if(result.contains("successfully")){
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) Integer courseId){
        try{
            if(courseId == null){
                return studentService.getAll();
            } else {
                return studentService.getByCourseId(courseId);
            }
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

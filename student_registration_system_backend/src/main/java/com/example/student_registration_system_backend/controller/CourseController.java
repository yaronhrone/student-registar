package com.example.student_registration_system_backend.controller;

import com.example.student_registration_system_backend.model.Course;
import com.example.student_registration_system_backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(@RequestParam(required = false) String numberOfStudent){
        try{
            if (numberOfStudent != null){
                int min;
                int max;
                switch (numberOfStudent){
                    case "0":
                        min= 0;
                        max = 0;
                        break;
                    case "1 to 10":
                        min = 1;
                        max = 10;
                        break;
                    case "11 to 30":
                        min = 11;
                        max = 30;
                        break;
                    case "over 30":
                        min = 31;
                        max = Integer.MAX_VALUE;
                        break;
                    default:
                        return new ResponseEntity("The number of student: "+ numberOfStudent+ "is not exies" ,HttpStatus.BAD_REQUEST);

                }
                return new ResponseEntity<>(courseService.findByStudentCountRange(min,max),HttpStatus.OK);
            }
            return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@GetMapping("/registrants-count")
    public ResponseEntity<List<Integer>> findRegistrantsCountPerCourse(){
        try {
            return new ResponseEntity<>(courseService.findRegistrantsCountPerCourse(),HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}

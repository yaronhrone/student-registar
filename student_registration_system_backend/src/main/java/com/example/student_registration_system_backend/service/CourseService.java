package com.example.student_registration_system_backend.service;

import com.example.student_registration_system_backend.model.Course;
import com.example.student_registration_system_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public List<Integer> findRegistrantsCountPerCourse(){
        return courseRepository.findRegistrantsCountPerCourse();
    }
    public List<Course>  findByStudentCountRange(int min, int max){
        return courseRepository.findByStudentCountRange(min,max);
    }


}

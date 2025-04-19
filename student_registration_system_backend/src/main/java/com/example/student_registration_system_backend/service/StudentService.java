package com.example.student_registration_system_backend.service;

import com.example.student_registration_system_backend.model.Student;
import com.example.student_registration_system_backend.repository.CourseRepository;
import com.example.student_registration_system_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public String register(Student student, Integer courseId){
        if(student.getName() == null || student.getName().trim().isEmpty()
        || student.getEmail() == null || student.getEmail().trim().isEmpty() || !student.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            return "The student is not registered. the name and valid email are required";
        }
        if(courseRepository.getCourseByIdHelper(courseId) == null){
            return "The student is not registered. The course with id: " + courseId + " does not exist";
        }
        int studentId = studentRepository.getStudentIdByEmailHelper(student.getEmail());
        if(studentId != -1){
            if(studentRepository.isStudentRegisteredToCourseHelper(studentId, courseId).equals("Yes")){
                return "The student already registered to course with id: " + courseId;
            } else {
                return studentRepository.registerToCourse(studentId, courseId);
            }
        }
        return studentRepository.saveStudent(student, courseId);
    }

    public ResponseEntity<List<Student>> getAll(){
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getByCourseId(Integer courseId){
        if(courseRepository.getCourseByIdHelper(courseId) == null){
            return new ResponseEntity("The course with id: " + courseId + " does not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentRepository.findByCourseId(courseId), HttpStatus.OK);
    }
}

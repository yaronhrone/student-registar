package com.example.student_registration_system_backend.repository;

import com.example.student_registration_system_backend.model.Student;
import com.example.student_registration_system_backend.repository.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String STUDENTS_TABLE = "students";
    private final static String STUDENT_COURSE_TABLE = "student_course";

    public String saveStudent(Student student, int courseId){
        String sql = String.format("INSERT INTO %s (name, age, email, phone) VALUES(?, ?, ?, ?)", STUDENTS_TABLE);
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getEmail(), student.getPhone());
        String result = registerToCourse(getStudentIdByEmailHelper(student.getEmail()), courseId);
        return "The student saved in the system successfully, and " + result;
    }

    public String registerToCourse(int studentId, int courseId){
        String sql = String.format("INSERT INTO %s (student_id, course_id) VALUES(?, ?)", STUDENT_COURSE_TABLE);
        jdbcTemplate.update(sql, studentId, courseId);
        return "The student with id: " + studentId + " registered to course with id: " + courseId + " successfully";
    }

    public List<Student> findAll(){
        String sql = String.format("SELECT * FROM %s", STUDENTS_TABLE);
        return jdbcTemplate.query(sql, new StudentMapper());
    }

    public List<Student> findByCourseId(int courseId){
        String sql = String.format("SELECT * FROM %s s INNER JOIN %s sc ON s.id = sc.student_id WHERE sc.course_id = ?", STUDENTS_TABLE, STUDENT_COURSE_TABLE);
        return jdbcTemplate.query(sql, new StudentMapper(), courseId);
    }

    public int getStudentIdByEmailHelper(String email){
        try {
            String sql = String.format("SELECT id FROM %s WHERE email = ?", STUDENTS_TABLE);
            return jdbcTemplate.queryForObject(sql, Integer.class, email);
        } catch (Exception e){
            return -1;
        }
    }

    public String isStudentRegisteredToCourseHelper(int studentId, int courseId){
        String sql = String.format("SELECT CASE " +
                "WHEN EXISTS(SELECT 1 FROM %s WHERE student_id = ? AND course_id = ?) " +
                "THEN 'Yes' " +
                "ELSE 'No' " +
                "END AS is_registered", STUDENT_COURSE_TABLE);
        return jdbcTemplate.queryForObject(sql, String.class, studentId, courseId);
    }
}

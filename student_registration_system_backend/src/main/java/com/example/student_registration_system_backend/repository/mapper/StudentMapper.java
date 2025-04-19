package com.example.student_registration_system_backend.repository.mapper;

import com.example.student_registration_system_backend.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        return student;
    }
}

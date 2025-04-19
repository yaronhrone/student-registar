package com.example.student_registration_system_backend.repository.mapper;

import com.example.student_registration_system_backend.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setStartDate(rs.getDate("start_date").toLocalDate());
        return course;
    }
}

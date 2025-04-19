package com.example.student_registration_system_backend.repository;

import com.example.student_registration_system_backend.model.Course;
import com.example.student_registration_system_backend.repository.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String COURSES_TABLE = "courses";
    private final static String STUDENT_COURSE_TABLE = "student_course";

    public List<Course> findAll(){
        String sql = String.format("SELECT * FROM %s", COURSES_TABLE);
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    public List<Integer> findRegistrantsCountPerCourse(){
        String sql = String.format("SELECT COUNT(sc.student_id) AS registrants_count FROM %s c " +
                "LEFT JOIN %S sc " +
                "ON c.id = sc.course_id " +
                "GROUP BY c.id " +
                " ORDER BY c.id",COURSES_TABLE, STUDENT_COURSE_TABLE);
    return  jdbcTemplate.queryForList(sql, Integer.class);
    }
    public List<Course> findByStudentCountRange(int min, int max){
        String sql = String.format("SELECT c.id,c.name,c.start_date FROM %s c " +
                "LEFT JOIN %s sc " +
                "ON c.id = sc.course_id " +
                "GROUP BY c.id,c.name,c.start_date " +
                "HAVING COUNT(sc.student_id) BETWEEN ? AND ?"
                ,COURSES_TABLE,STUDENT_COURSE_TABLE);
        return jdbcTemplate.query(sql,new CourseMapper(),min,max);
    }

    public Course getCourseByIdHelper(int courseId){
        try{
            String sql = String.format("SELECT * FROM %s WHERE id = ?", COURSES_TABLE);
            return jdbcTemplate.queryForObject(sql, new CourseMapper(), courseId);
        } catch (Exception e){
            return null;
        }
    }
}

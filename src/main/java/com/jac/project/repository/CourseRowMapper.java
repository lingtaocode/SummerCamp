package com.jac.project.repository;
import com.jac.project.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();


        course.setCcid(rs.getInt("ccid"));
        course.setCid(rs.getInt("cid"));
        course.setIid(rs.getInt("iid"));
        course.setCourse(rs.getString("course"));
        course.setComments(rs.getString("comments"));

        return course;

    }
}

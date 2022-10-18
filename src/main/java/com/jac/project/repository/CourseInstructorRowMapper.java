package com.jac.project.repository;
import com.jac.project.repository.model.CourseInstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseInstructorRowMapper implements RowMapper<CourseInstructor> {
    @Override
    public CourseInstructor mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseInstructor courseInstructor = new CourseInstructor();


        courseInstructor.setCcid(rs.getInt("ccid"));
        courseInstructor.setCid(rs.getInt("cid"));
        courseInstructor.setIid(rs.getInt("iid"));
        courseInstructor.setCourse(rs.getString("course"));
        courseInstructor.setComments(rs.getString("comments"));
        courseInstructor.setName(rs.getString("name"));
        return courseInstructor;

    }
}

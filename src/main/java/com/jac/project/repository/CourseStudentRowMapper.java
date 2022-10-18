package com.jac.project.repository;
import com.jac.project.model.CourseStudent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentRowMapper implements RowMapper<CourseStudent> {
    @Override
    public CourseStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseStudent courseStudent = new CourseStudent();


        courseStudent.setCcid(rs.getInt("ccid"));
        courseStudent.setSid(rs.getInt("sid"));
        courseStudent.setStatus(rs.getInt("status"));

        return courseStudent;

    }
}

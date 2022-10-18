package com.jac.project.repository.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentClubRowMapper implements RowMapper<CourseStudentClub> {
    @Override
    public CourseStudentClub mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseStudentClub courseStudent = new CourseStudentClub();
        //course_student
        courseStudent.setCcid(rs.getInt("ccid"));
        courseStudent.setSid(rs.getInt(("sid")));
        courseStudent.setStatus(rs.getInt("status"));

        //course
        courseStudent.setCid(rs.getInt("cid"));
        courseStudent.setIid(rs.getInt("iid"));
        courseStudent.setCourse(rs.getString("course"));
        courseStudent.setComments(rs.getString("comments"));

        //student
        courseStudent.setFname(rs.getString("fname"));
        courseStudent.setLname(rs.getString("lname"));
        courseStudent.setAge(rs.getInt("age"));
        courseStudent.setEmail(rs.getString("email"));

        //club
        courseStudent.setTitle(rs.getString("title"));
        courseStudent.setSdate(rs.getDate("sdate"));

        //instructor
        courseStudent.setName(rs.getString("name"));

        return courseStudent;


    }
}

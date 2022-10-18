package com.jac.project.repository;
import com.jac.project.model.Student;
import com.jac.project.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();


        student.setSid(rs.getInt(("sid")));
        student.setFname(rs.getString("fname"));
        student.setLname(rs.getString("lname"));
        student.setAge(rs.getInt("age"));
        student.setAddress(rs.getString("address"));
        student.setPhone(rs.getString("phone"));
        student.setEmail(rs.getString("email"));

        return student;


    }
}

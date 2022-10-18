package com.jac.project.repository;
import com.jac.project.model.Instructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorRowMapper implements RowMapper<Instructor> {
    @Override
    public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Instructor instructor = new Instructor();

        instructor.setIid(rs.getInt("iid"));
        instructor.setName(rs.getString("name"));
        instructor.setEmail(rs.getString("email"));
        instructor.setPhone(rs.getString("phone"));

        return instructor;

    }
}

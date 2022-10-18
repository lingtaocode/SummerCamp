package com.jac.project.repository;
import com.jac.project.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPword(rs.getString("pword"));
        user.setRole(rs.getInt("flag"));

        return user;
    }
}

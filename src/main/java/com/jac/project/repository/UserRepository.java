package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.UserNotFoundException;
import com.jac.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public User newUser(User user){
        try {
            jdbcTemplate.update("insert into user (email, pword, flag) values(?,?,?)", user.getEmail(), user.getPword(), user.getRole());
        } catch (Exception e){
            throw new DatabaseException("The email has been used, please change an other one.");
        }
        return user;
    }

    public User updateUser(User user){
        try {
            jdbcTemplate.update("update user set pword = ? where email = ?", user.getEmail());
        } catch (Exception e){
            throw new DatabaseException("updateUser() is wrong.");
        }
        return user;
    }

    public User getUserByEmail(String email){
        List userList = new ArrayList<User>();
        User user = null;
        try {
            String sql = "select * from user where email = ?";
            userList = jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper());

            if(userList.size() > 0){
                user = (User)userList.get(0);
            }

        } catch (Exception e){
            throw new DatabaseException("getUser() is wrong.");
        }

        return user;
    }


}

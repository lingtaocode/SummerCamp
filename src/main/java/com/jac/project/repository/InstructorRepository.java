package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.InstructorNotFoundException;
import com.jac.project.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Instructor newInstructor(Instructor instructor){

        try {
            jdbcTemplate.update("insert into instructor (name, phone, email) values(?,?,?)",
                    instructor.getName(), instructor.getPhone(), instructor.getEmail());
        } catch (Exception e){
            throw new DatabaseException("newInstructor() is wrong.");
        }
        return instructor;
    }

    public Instructor updateInstructor(Instructor instructor){
        try {
            jdbcTemplate.update("update instructor set name=?, phone=?, email=? where iid = ?",
                    instructor.getName(), instructor.getPhone(), instructor.getEmail(), instructor.getIid());
        } catch (Exception e){
            throw new InstructorNotFoundException("Instructor not found. ID = " + instructor.getIid());
        }
        return instructor;
    }

    public Instructor getInstructorByIid(int iid){
        try {
            String sql = "select * from instructor where iid = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{iid}, new InstructorRowMapper());
        } catch (Exception e){
            throw new InstructorNotFoundException("Instructor not found. ID = " + iid);
        }
    }

    public List<Instructor> getAllInstructor(){
        List instructorList = new ArrayList<Instructor>();
        try {
            String sql = "select * from instructor";
            instructorList = jdbcTemplate.query(sql, new InstructorRowMapper());
        } catch (Exception e){
            throw new DatabaseException("getAllInstructor() is wrong.");
        }
        return instructorList;
    }


}

package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.UserNotFoundException;
import com.jac.project.model.Student;
import com.jac.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Student newStudent(Student student){
        try {
            jdbcTemplate.update("insert into student (fname, lname, age, address, phone, email) values(?,?,?,?,?,?)",
                    student.getFname(), student.getLname(), student.getAge(), student.getAddress(), student.getPhone(), student.getEmail());
        } catch (Exception e){
            throw new DatabaseException("Student email has already been used.");
        }
        return student;
    }

    public Student updateStudent(Student student){
        try {
            jdbcTemplate.update("update student set fname=?, lname=?, age=?, address=?, phone=? where email = ?",
                    student.getFname(), student.getLname(), student.getAge(), student.getAddress(), student.getPhone(), student.getEmail());
        } catch (Exception e){
            throw new DatabaseException("updateStudent() is wrong.");
        }
        return student;
    }

    public Student getStudentByEmail(String email){
        List studentList = new ArrayList<Student>();
        Student student = null;
        try {
            String sql = "select * from student where email = ?";
            studentList = jdbcTemplate.query(sql, new Object[]{email}, new StudentRowMapper());

            if(studentList.size() > 0){
                student = (Student)studentList.get(0);
            }

        } catch (DatabaseException e){
            throw new DatabaseException("getStudentByEmail() is wrong.");
        }

        return student;
    }

    public List<Student> getAllStudent(){
        List studentList = new ArrayList<Student>();
        try {
            String sql = "select * from student order by fname";
            studentList = jdbcTemplate.query(sql, new StudentRowMapper());

        } catch (DatabaseException e){
            throw new DatabaseException("getAllStudent() is wrong.");
        }

        return studentList;
    }


}

package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Course;
import com.jac.project.model.CourseStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseStudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public CourseStudent newCourseStudent(CourseStudent courseStudent){

        try {
            jdbcTemplate.update("insert into course_student (ccid, sid, status) values(?,?,?)",
                    courseStudent.getCcid(), courseStudent.getSid(), courseStudent.getStatus());
        } catch (Exception e){
            throw new DatabaseException("You subscribe to the course repeatedly. Please check your account for details.");
        }
        return courseStudent;
    }

    public CourseStudent updateCourseStudent(CourseStudent courseStudent){
        try {
            jdbcTemplate.update("update course_student set status=? where ccid=? and sid=?",
                    courseStudent.getStatus(), courseStudent.getCcid(), courseStudent.getSid());
        } catch (DatabaseException e){
            throw new DatabaseException("updateCourseStudent() is wrong.");
        }
        return courseStudent;
    }

    public CourseStudent deleteCourse(CourseStudent courseStudent){
        try {
            jdbcTemplate.update("delete from course_student where sid=? and ccid=? and status=?",
                    courseStudent.getSid(), courseStudent.getCcid(), courseStudent.getStatus());
        } catch (DatabaseException e){
            throw new DatabaseException("deleteCourse() is wrong.");
        }
        return courseStudent;
    }




}

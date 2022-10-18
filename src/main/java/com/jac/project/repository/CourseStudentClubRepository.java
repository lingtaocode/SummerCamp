package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;

import com.jac.project.repository.model.CourseStudentClub;
import com.jac.project.repository.model.CourseStudentClubRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseStudentClubRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CourseStudentClub> getCSCIBySid(int sid){
        try {
            String sql = "SELECT course_student.ccid," +
                    "course_student.sid," +
                    "course_student.status," +
                    "club_course.cid," +
                    "club_course.iid," +
                    "club_course.course," +
                    "club_course.comments," +
                    "student.fname," +
                    "student.lname," +
                    "student.age," +
                    "student.email," +
                    "club.title," +
                    "club.sdate," +
                    "instructor.name " +
                    "FROM course_student " +
                    "INNER JOIN club_course ON course_student.ccid = club_course.ccid " +
                    "INNER JOIN club ON club.cid = club_course.cid " +
                    "INNER JOIN student on course_student.sid = student.sid " +
                    "INNER JOIN instructor on club_course.iid = instructor.iid " +
                    "where student.sid=? order by course_student.ccid desc";

            return jdbcTemplate.query(sql, new Object[]{sid}, new CourseStudentClubRowMapper());
        } catch (Exception e){
            throw new DatabaseException("getCSCIBySid() is wrong.");
        }
    }


    public List<CourseStudentClub> getCSCI(){
        try {
            String sql = "SELECT club_course.ccid," +
                    "course_student.sid," +
                    "course_student.status," +
                    "club_course.cid," +
                    "club_course.iid," +
                    "club_course.course," +
                    "club_course.comments," +
                    "student.fname," +
                    "student.lname," +
                    "student.age," +
                    "student.email," +
                    "club.title," +
                    "club.sdate," +
                    "instructor.name " +
                    "FROM club_course " +
                    "left JOIN course_student ON course_student.ccid = club_course.ccid " +
                    "left JOIN club ON club.cid = club_course.cid " +
                    "left JOIN student on course_student.sid = student.sid " +
                    "left JOIN instructor on club_course.iid = instructor.iid " +
                    "order by club_course.cid asc";

            return jdbcTemplate.query(sql, new CourseStudentClubRowMapper());
        } catch (Exception e){
            throw new DatabaseException("getCSCI() is wrong.");
        }
    }

    public List<CourseStudentClub> getCSCIBy(String key, String value){

        try {
            String sql = "SELECT course_student.ccid," +
                    "course_student.sid," +
                    "course_student.status," +
                    "club_course.cid," +
                    "club_course.iid," +
                    "club_course.course," +
                    "club_course.comments," +
                    "student.fname," +
                    "student.lname," +
                    "student.age," +
                    "student.email," +
                    "club.title," +
                    "club.sdate," +
                    "instructor.name " +
                    "FROM club_course " +
                    "left JOIN course_student ON course_student.ccid = club_course.ccid " +
                    "left JOIN club ON club.cid = club_course.cid " +
                    "left JOIN student on course_student.sid = student.sid " +
                    "left JOIN instructor on club_course.iid = instructor.iid ";

            sql = sql + "where " + key + "='" + value + "'";

            return jdbcTemplate.query(sql, new CourseStudentClubRowMapper());
        } catch (Exception e){
            throw new DatabaseException("getCSCIBy() is wrong.");
        }
    }




}

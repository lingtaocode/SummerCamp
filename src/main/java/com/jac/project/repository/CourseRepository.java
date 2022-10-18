package com.jac.project.repository;

import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Course;
import com.jac.project.repository.model.CourseInstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Course newCourse(Course course){

        try {
            jdbcTemplate.update("insert into club_course (cid, iid, course, comments) values(?,?,?,?)",
                    course.getCid(), course.getIid(), course.getCourse(), course.getComments());
        } catch (Exception e){
            throw new DatabaseException("newCourse() is wrong.");
        }
        return course;
    }

    public Course updateCourse(Course course){
        try {
            jdbcTemplate.update("update club_course set cid=?, iid=?, course=?, comments=? where ccid = ?",
                    course.getCid(), course.getIid(), course.getCourse(), course.getComments(), course.getCcid());
        } catch (Exception e){
            throw new DatabaseException("updateCourse() is wrong.");
        }
        return course;
    }

    public Course getCourseByCcid(int ccid){
        try {
            String sql = "select * from club_course where ccid = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{ccid}, new CourseRowMapper());
        } catch (Exception e){
            throw new DatabaseException("getCourseByCcid() is wrong.");
        }
    }

    public String deleteCourseByCcid(int ccid){
        try {
            String sql = "delete from club_course where ccid = ?";
            jdbcTemplate.update(sql, new Object[]{ccid});
            return "The course deleted successfully.";

        } catch (Exception e){
            throw new DatabaseException("This course is already in effect. Can not be deleted.");
        }
    }

    public List<Course> getAllCourse(){
        List courseList = new ArrayList<Course>();
        try {
            String sql = "select * from club_course";
            courseList = jdbcTemplate.query(sql, new CourseRowMapper());

        } catch (Exception e){
            throw new DatabaseException("getAllCourse() is wrong.");
        }

        return courseList;
    }

    public List<CourseInstructor> getCourseListByCid(int cid){
        List courseList = new ArrayList<CourseInstructor>();
        try {
            String sql = "select ccid, cid, club_course.iid, course, comments, name " +
                    "from club_course, instructor where club_course.iid = instructor.iid and cid=?";
            courseList = jdbcTemplate.query(sql, new Object[]{cid}, new CourseInstructorRowMapper());

        } catch (Exception e){
            throw new DatabaseException("getCourseListByCid() is wrong.");
        }

        return courseList;
    }

    public List<Course> getCourseListByCidSid(int cid, int sid){
        List courseList = new ArrayList<Course>();
        try {
            String sql ="select * from club_course " +
                    "where ccid not in(select ccid from course_student where sid=?) " +
                    "and cid=?";
            courseList = jdbcTemplate.query(sql, new Object[]{sid, cid}, new CourseRowMapper());
        } catch (Exception e){
            throw new DatabaseException("getCourseListByCidSid() is wrong.");
        }

        return courseList;
    }


}

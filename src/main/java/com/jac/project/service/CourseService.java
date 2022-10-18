package com.jac.project.service;


import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Course;
import com.jac.project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jac.project.repository.model.CourseInstructor;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course getCourseByCcid(int ccid){
        try {
            return courseRepository.getCourseByCcid(ccid);

        }catch (DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Course newCourse (Course course){
        try{
            return courseRepository.newCourse(course);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Course updateCourse(Course course){

        try{
            return courseRepository.updateCourse(course);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public String deleteCourse(int ccid){
        try{
            return courseRepository.deleteCourseByCcid(ccid);

        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }

    }
    public List<Course> getAllCourse(){
        try{
            return courseRepository.getAllCourse();
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<CourseInstructor> getCourseListByCid(int cid){
        try{
            return courseRepository.getCourseListByCid(cid);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Course> getCourseListByCidSid(int cid, int sid){
        try{
            return courseRepository.getCourseListByCidSid(cid, sid);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }











}

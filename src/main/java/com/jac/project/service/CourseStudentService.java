package com.jac.project.service;


import com.jac.project.exception.DatabaseException;

import com.jac.project.model.CourseStudent;

import com.jac.project.repository.CourseStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseStudentService {
    @Autowired
    private CourseStudentRepository courseStudentRepository;

    public CourseStudent newCourseStudent (CourseStudent courseStudent){
        try{
            return courseStudentRepository.newCourseStudent(courseStudent);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public CourseStudent confirmCourse(CourseStudent courseStudent){

        try{
            courseStudent.setStatus(1);
            return courseStudentRepository.updateCourseStudent(courseStudent);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }


    public CourseStudent deleteCourse(CourseStudent courseStudent){
        try{
            return courseStudentRepository.deleteCourse(courseStudent);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }















}

package com.jac.project.controller;

import com.jac.project.exception.ClubNotFoundException;
import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Course;
import com.jac.project.model.CourseStudent;
import com.jac.project.service.CourseService;
import com.jac.project.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/coursestudent")
public class CourseStudentController {

    @Autowired
    private CourseStudentService courseStudentService;

    @PostMapping("/new")
    public ResponseEntity<CourseStudent> newCourseStudent(@RequestBody CourseStudent courseStudent){
        try {
            return new ResponseEntity<>(courseStudentService.newCourseStudent(courseStudent), HttpStatus.CREATED);
        }catch (DatabaseException exc){
            return new ResponseEntity(exc.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/confirm")
    public ResponseEntity<CourseStudent> confirmCourse(@RequestBody CourseStudent courseStudent){
        return new ResponseEntity<>(courseStudentService.confirmCourse(courseStudent), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CourseStudent> deleteCourse(@RequestBody CourseStudent courseStudent){
        return new ResponseEntity<>(courseStudentService.deleteCourse(courseStudent), HttpStatus.OK);
    }




}

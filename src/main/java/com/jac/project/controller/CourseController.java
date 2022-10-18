package com.jac.project.controller;

import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Course;
import com.jac.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<Course> newCourse(@RequestBody Course course){
        try{
            return new ResponseEntity<>(courseService.newCourse(course), HttpStatus.CREATED);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @GetMapping("/ccid/{ccid}")
    public ResponseEntity<Course> getCourseByCcid(@PathVariable int ccid) {
        try{
            return new ResponseEntity<>(courseService.getCourseByCcid(ccid), HttpStatus.OK);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List> getAllCourse(){
        try{
            return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List> getCourseListByCid(@PathVariable int cid){
        try{
            return new ResponseEntity<>(courseService.getCourseListByCid(cid), HttpStatus.OK);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @GetMapping("/cid/{cid}/{sid}")
    public ResponseEntity<List> getCourseListByCidSid(@PathVariable int cid, @PathVariable int sid){
        try{
            return new ResponseEntity<>(courseService.getCourseListByCidSid(cid, sid), HttpStatus.OK);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        try{
            return new ResponseEntity<>(courseService.updateCourse(course), HttpStatus.OK);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{ccid}")
    public String deleteCourseByCcid(@PathVariable int ccid){
        try{
            return courseService.deleteCourse(ccid);
        } catch(Exception e){
            return (new DatabaseException(e.getMessage())).toString();
        }

    }



}

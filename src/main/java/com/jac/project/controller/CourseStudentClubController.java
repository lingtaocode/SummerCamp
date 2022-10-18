package com.jac.project.controller;


import com.jac.project.exception.DatabaseException;
import com.jac.project.service.CourseStudentClubService;
import com.jac.project.repository.model.CourseStudentClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/csci")
public class CourseStudentClubController {

    @Autowired
    private CourseStudentClubService courseStudentClubService;

    @GetMapping("/sid/{sid}")
    public List<CourseStudentClub> getCSCIBySid(@PathVariable int sid) {
        try {
            return courseStudentClubService.getCSCIBySid(sid);
        }
        catch (DatabaseException exc){
            return null;
        }
    }

    @GetMapping("/")
    public List<CourseStudentClub> getCSCI() {
        try {
            return courseStudentClubService.getCSCI();
        }
        catch (Exception exc){
            return null;
        }
    }

    @GetMapping("/{key}/{value}")
    public List<CourseStudentClub> getCSCIBy(@PathVariable String key, @PathVariable String value) {
        try {
            return courseStudentClubService.getCSCI(key, value);
        }
        catch (Exception exc){
            return null;
        }
    }


}

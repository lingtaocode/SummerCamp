package com.jac.project.controller;


import com.jac.project.exception.ResourceNotFoundException;
import com.jac.project.model.Aclub;
import com.jac.project.model.Acourse;
import com.jac.project.model.Ainstructor;

import com.jac.project.model.Astudent;
import com.jac.project.repository.AclubRepository;
import com.jac.project.repository.AcourseRepository;
import com.jac.project.repository.AinstructorRepository;
import com.jac.project.repository.AstudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/acourse")
public class AcourseController {

    @Autowired
    AcourseRepository acourseRepository;

    @Autowired
    AstudentRepository astudentRepository;

    @Autowired
    AclubRepository aclubRepository;
    @Autowired
    AinstructorRepository ainstructorRepository;

 //
    @GetMapping("/acourse")
    public ResponseEntity<List<Acourse>> getAllAcourse() {
        List<Acourse> acourses = new ArrayList<Acourse>();
        acourses = acourseRepository.findAll();
        if (acourses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(acourses, HttpStatus.OK);
    }

    // add a course as admin account
    @PostMapping("/acourse/{ainstructor_id}/{aclub_id}")
    public ResponseEntity<Acourse> createAcourse(@PathVariable("ainstructor_id") Long ainstructor_id,
                                                 @PathVariable("aclub_id") Long aclub_id,
                                                 @RequestBody Acourse acourse) {
        Ainstructor ainstructor = ainstructorRepository.findById(ainstructor_id).orElseThrow(() -> new ResourceNotFoundException("Not found instructor with id = " + ainstructor_id));
        Aclub aclub = aclubRepository.findById(aclub_id).orElseThrow(() -> new ResourceNotFoundException("Not found club with id = " + aclub_id));

        Acourse acourse1 = acourseRepository.save(new Acourse(acourse.getName(), acourse.getComment(), ainstructor, aclub));
        return new ResponseEntity<>(acourse1, HttpStatus.CREATED);
    }

  // register a course as a student account
    @PostMapping("/astudent/{astudentId}/acourse")
    public ResponseEntity<Acourse> addAcourse(@PathVariable(value = "astudentId") Long astudentId,
                                               @RequestBody Acourse acourseRequest
                                               ) {
        Astudent astudent= astudentRepository.findById(astudentId).orElseThrow(() -> new ResourceNotFoundException("Not found student with id = " + astudentId));
        long acourseId = acourseRequest.getId();
        Acourse acourse = acourseRepository.findById(acourseId).orElseThrow(() -> new ResourceNotFoundException("Not found student with id = " + astudentId));
        astudent.addAcourse(acourseRequest);
        astudentRepository.save(astudent);

        return new ResponseEntity<>(acourse, HttpStatus.CREATED);
    }


}

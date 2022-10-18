package com.jac.project.controller;

import com.jac.project.model.Aclub;

import com.jac.project.model.Ainstructor;
import com.jac.project.repository.AinstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/aclub")
public class AinstructorController {
    @Autowired
    AinstructorRepository ainstructorRepository;


    @GetMapping("/ainstructor")
    public ResponseEntity<List<Ainstructor>> getAllAinstructor() {
        List<Ainstructor> ainstructors = new ArrayList<Ainstructor>();
        ainstructorRepository.findAll().forEach(ainstructors::add);
        if (ainstructors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ainstructors, HttpStatus.OK);
    }

    @PostMapping("/ainstructors")
    public ResponseEntity<Ainstructor> createAinstructor(@RequestBody Ainstructor ainstructor) {
        Ainstructor ainstructor1 = ainstructorRepository.save(ainstructor);
        return new ResponseEntity<>(ainstructor1, HttpStatus.CREATED);
    }
}
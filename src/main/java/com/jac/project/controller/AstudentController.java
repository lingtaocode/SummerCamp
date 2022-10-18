package com.jac.project.controller;



import com.jac.project.model.Astudent;

import com.jac.project.repository.AstudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/astudent")
public class AstudentController {

    @Autowired
    AstudentRepository astudentRepository;


    @GetMapping("/astudent")
    public ResponseEntity<List<Astudent>> getAllAstudent() {
        List<Astudent> astudents = new ArrayList<Astudent>();
        astudentRepository.findAll().forEach(astudents::add);
        if (astudents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(astudents, HttpStatus.OK);
    }

    @PostMapping("/astudent")
    public ResponseEntity<Astudent> createAstudent(@RequestBody Astudent astudent) {
        Astudent astudent1 = astudentRepository.save(new Astudent(astudent.getFname(),astudent.getLname(),astudent.getAge(),astudent.getAddress(), astudent.getPhone(), astudent.getEmail()));
        return new ResponseEntity<>(astudent1, HttpStatus.CREATED);
    }




}

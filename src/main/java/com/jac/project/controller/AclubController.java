package com.jac.project.controller;

import com.jac.project.model.Aclub;
import com.jac.project.model.Astudent;
import com.jac.project.repository.AclubRepository;
import com.jac.project.repository.AstudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aclub")
public class AclubController {
    @Autowired
    AclubRepository aclubRepository;


    @GetMapping("/aclub")
    public ResponseEntity<List<Aclub>> getAllAclub() {
        List<Aclub> aclubs = new ArrayList<Aclub>();
        aclubRepository.findAll().forEach(aclubs::add);
        if (aclubs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(aclubs, HttpStatus.OK);
    }

    @PostMapping("/aclub")
    public ResponseEntity<Aclub> createAclub(@RequestBody Aclub aclub) {
        Aclub aclub1 = aclubRepository.save(aclub);
        return new ResponseEntity<>(aclub1, HttpStatus.CREATED);
    }
}

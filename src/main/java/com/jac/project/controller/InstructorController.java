package com.jac.project.controller;

import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.InstructorNotFoundException;
import com.jac.project.model.Instructor;
import com.jac.project.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/new")
    public ResponseEntity<Instructor> newInstructor(@RequestBody Instructor instructor){
        try{
            return new ResponseEntity<>(instructorService.newInstructor(instructor), HttpStatus.CREATED);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @GetMapping("/iid/{iid}")
    public ResponseEntity<Instructor> getInstructorByIid(@PathVariable int iid) {
        try {
            return new ResponseEntity<>(instructorService.getInstructorByIid(iid), HttpStatus.OK);
        }catch (InstructorNotFoundException e){
            throw new InstructorNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List> getAllInstructor(){
        try{
            return new ResponseEntity<>(instructorService.getAllInstructor(), HttpStatus.OK);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Instructor> updateInstructor(@RequestBody Instructor instructor){
        try{
            return new ResponseEntity<>(instructorService.updateInstructor(instructor), HttpStatus.OK);
        } catch(InstructorNotFoundException e){
            throw new InstructorNotFoundException(e.getMessage());
        }

    }




}

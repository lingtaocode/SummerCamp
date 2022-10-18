package com.jac.project.controller;


import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.StudentNotFoundException;
import com.jac.project.model.Student;
import com.jac.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/new")
    public ResponseEntity<Student> newStudent(@RequestBody Student student){
        try{
            return new ResponseEntity<>(studentService.newStudent(student), HttpStatus.CREATED);
        }catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        try{
            return new ResponseEntity<>(studentService.getStudentByEmail(email), HttpStatus.OK);
        }catch (StudentNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List> getAllStudent(){
        try{
            return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);
        }catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        try{
            return new ResponseEntity<>(studentService.updateStudent(student), HttpStatus.OK);
        }catch (StudentNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




}

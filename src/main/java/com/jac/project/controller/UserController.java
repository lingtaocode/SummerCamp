package com.jac.project.controller;


import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.UserNotFoundException;
import com.jac.project.model.ResponseData;
import com.jac.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jac.project.service.UserService;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<User> newUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userService.newUser(user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody User user) {
        try{
            return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try{
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}

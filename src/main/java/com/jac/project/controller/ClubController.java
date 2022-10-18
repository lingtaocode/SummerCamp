package com.jac.project.controller;

import com.jac.project.exception.ClubNotFoundException;
import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Club;
import com.jac.project.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 45000)
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping("/new")
    public ResponseEntity<Club> newClub(@RequestBody Club club){
        try{
            return new ResponseEntity<>(clubService.newClub(club), HttpStatus.CREATED);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @GetMapping("/cid/{cid}")
    public ResponseEntity<Club> getClubByCid(@PathVariable int cid) {
        try {
            return new ResponseEntity<>(clubService.getClubByCid(cid), HttpStatus.OK);
        }
        catch (ClubNotFoundException exc){
            return new ResponseEntity(exc.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List> getAllClub(){
        return new ResponseEntity<>(clubService.getAllClub(), HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<Club> updateClub(@RequestBody Club club){

        return new ResponseEntity<>(clubService.updateClub(club), HttpStatus.OK);

    }




}

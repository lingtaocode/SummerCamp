package com.jac.project.service;


import com.jac.project.exception.ClubNotFoundException;
import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Club;
import com.jac.project.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public Club getClubByCid(int cid){
        try {
            return clubRepository.getClubByCid(cid);

        }catch (DatabaseException e){
            throw new ClubNotFoundException(e.getMessage());
        }
    }

    public Club newClub (Club club){
        try{
            return clubRepository.newClub(club);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Club updateClub(Club club){

        try{
            return clubRepository.updateClub(club);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Club> getAllClub(){
        try{
            return clubRepository.getAllClub();
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }













}

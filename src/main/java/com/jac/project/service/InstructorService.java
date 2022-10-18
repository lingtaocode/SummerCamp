package com.jac.project.service;


import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.InstructorNotFoundException;
import com.jac.project.model.Instructor;
import com.jac.project.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor getInstructorByIid(int iid){
        try {
            return instructorRepository.getInstructorByIid(iid);
        }catch (InstructorNotFoundException e){
            throw new InstructorNotFoundException(e.getMessage());
        }
    }

    public Instructor newInstructor (Instructor instructor){
        try{
            return instructorRepository.newInstructor(instructor);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Instructor updateInstructor(Instructor instructor){

        try{
            return instructorRepository.updateInstructor(instructor);
        } catch(InstructorNotFoundException e){
            throw new InstructorNotFoundException(e.getMessage());
        }
    }

    public List<Instructor> getAllInstructor(){
        try{
            return instructorRepository.getAllInstructor();
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }













}

package com.jac.project.service;



import com.jac.project.exception.DatabaseException;
import com.jac.project.repository.CourseStudentClubRepository;
import com.jac.project.repository.model.CourseStudentClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseStudentClubService {
    @Autowired
    private CourseStudentClubRepository courseStudentClubRepository;

    public List<CourseStudentClub> getCSCIBySid(int sid){
        try {
            return courseStudentClubRepository.getCSCIBySid(sid);

        }catch (DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<CourseStudentClub> getCSCI(){
        try {
            return courseStudentClubRepository.getCSCI();

        }catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<CourseStudentClub> getCSCI(String key, String value){
        try {
            return courseStudentClubRepository.getCSCIBy(key, value);

        }catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }



}

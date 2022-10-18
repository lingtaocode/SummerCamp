package com.jac.project.service;


import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.StudentNotFoundException;

import com.jac.project.model.Student;

import com.jac.project.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentByEmail(String email){
        try {
            Student student = studentRepository.getStudentByEmail(email);
            if(student == null){
                throw new StudentNotFoundException("Student not found. email = " + email);
            }else{
                return student;
            }

        }catch (DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Student newStudent(Student student){
        try{
            return studentRepository.newStudent(student);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Student updateStudent(Student student){

        try{
            String email = student.getEmail();
            Student stuDB = getStudentByEmail(email);
            if(stuDB==null){
                throw new StudentNotFoundException("Student not found. email = " + email);
            }else{
                return studentRepository.updateStudent(student);
            }

        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Student> getAllStudent(){
        try{
            return studentRepository.getAllStudent();
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }













}

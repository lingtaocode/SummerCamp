package com.jac.project.service;



import com.jac.project.exception.DatabaseException;
import com.jac.project.exception.UserNotFoundException;
import com.jac.project.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import com.jac.project.model.User;
import com.jac.project.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email){
        try {
            return userRepository.getUserByEmail(email);
        }catch (DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public ResponseData login(User user){
        ResponseData responseData = new ResponseData();
        try {
            User userDB = userRepository.getUserByEmail(user.getEmail());
            if(userDB == null){
                //User not found.
                responseData.setMessage("0");
            }else if(user.getPword().equals(userDB.getPword())){
                //Welcome:
                responseData.setMessage("1");
                responseData.setObject(userDB);
            }else{
                //Password is incorrect.
                responseData.setMessage("2");
            }

        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }

        return responseData;
    }


    public User newUser(User user){
        try{
            return userRepository.newUser(user);
        } catch(Exception e){
            throw new DatabaseException(e.getMessage());
        }
    }
    public User updateUser(User user){
        try{
            return userRepository.updateUser(user);
        } catch(DatabaseException e){
            throw new DatabaseException(e.getMessage());
        }
    }













}

package com.jac.project.model;
import lombok.*;

/*
* create a class User
* */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String email;
    private String pword;
    private int role;
}

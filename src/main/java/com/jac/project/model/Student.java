package com.jac.project.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private int sid;
    private String fname;
    private String lname;
    private int age;
    private String address;
    private String phone;
    private String email;
}

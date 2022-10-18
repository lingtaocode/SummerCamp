package com.jac.project.repository.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseStudentClub {
//course_student
    private int ccid;
    private int sid;
    private int status;
//course
    private int cid;
    private int iid;
    private String course;
    private String comments;
//student
    private String fname;
    private String lname;
    private int age;
    private String email;
//club
    private String title;
    private Date sdate;
//instructor
    private String name;
}

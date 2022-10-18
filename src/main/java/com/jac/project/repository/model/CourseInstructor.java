package com.jac.project.repository.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseInstructor {
    private int ccid;
    private int cid;
    private int iid;
    private String course;
    private String comments;
    private String name;
}

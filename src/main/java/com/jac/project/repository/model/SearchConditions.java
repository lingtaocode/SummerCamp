package com.jac.project.repository.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchConditions {
    private int ccid;
    private int cid;
    private int sid;
    private String course;
    private String fname;
    private String email;
}

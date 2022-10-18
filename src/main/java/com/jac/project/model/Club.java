package com.jac.project.model;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Club {
    private int cid;
    private String title;
    private Date sdate;
    private String pfile;
    private String comments;
}

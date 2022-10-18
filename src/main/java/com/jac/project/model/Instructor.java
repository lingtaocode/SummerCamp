package com.jac.project.model;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Instructor {
    private int iid;
    private String name;
    private String phone;
    private String email;
}

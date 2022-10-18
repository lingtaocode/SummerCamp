package com.jac.project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ainstructor")
public class Ainstructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    private String name;
    private String phone;
    private String email;
}

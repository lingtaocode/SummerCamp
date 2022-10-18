package com.jac.project.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "astudent")
public class Astudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fname;
    private String lname;
    private int age;
    private String address;
    private String phone;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "astudent_acourse",
            joinColumns = @JoinColumn(name = "astudent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "acourse_id", referencedColumnName = "id")
    )
    private Set<Acourse> acourses = new HashSet<>();

    public Astudent(String fname, String lname, int age, String address, String phone, String email){
    this.fname=fname;
    this.lname=lname;
    this.age=age;
    this.address=address;
    this.phone=phone;
    this.email=email;
    }

    public void addAcourse(Acourse acourse) {
        this.acourses.add(acourse);
        acourse.getAstudents().add(this);
    }


    public void removeAcourse(long acourseId) {
        Acourse acourse = this.acourses.stream().filter(t -> t.getId() == acourseId).findFirst().orElse(null);
        if (acourse != null) {
            this.acourses.remove(acourse);
            acourse.getAstudents().remove(this);
        }
    }


}

package com.jac.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "acourse")
public class Acourse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String comment;

    @ManyToMany(mappedBy = "acourses",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Astudent> astudents = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ainstructor_id")
    private Ainstructor ainstructor;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "aclub_id")
    private Aclub aclub;

    public Acourse(String name, String comment, Ainstructor ainstructor, Aclub aclub){
     this.name = name;
     this.comment= comment;
     this.aclub = aclub;
     this.ainstructor=ainstructor;
    }

    public void addAstudent(Astudent astudent) {
        astudents.add(astudent);
    }

    public void deleteAstudent(Astudent astudent) {
        astudents.remove(astudent);
    }
}

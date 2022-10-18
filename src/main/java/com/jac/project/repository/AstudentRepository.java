package com.jac.project.repository;

import com.jac.project.model.Astudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AstudentRepository extends JpaRepository<Astudent, Long> {

   // List<Astudent> findAstudentByAcourseId(Long acourseId);
}

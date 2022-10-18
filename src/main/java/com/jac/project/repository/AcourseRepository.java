package com.jac.project.repository;

import com.jac.project.model.Acourse;

import com.jac.project.model.Astudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AcourseRepository extends JpaRepository<Acourse, Long> {

   // List<Acourse> findAcourseByAstudentId(Long astudentId);
}

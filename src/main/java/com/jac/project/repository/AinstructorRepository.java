package com.jac.project.repository;


import com.jac.project.model.Ainstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AinstructorRepository extends JpaRepository<Ainstructor, Long> {
}

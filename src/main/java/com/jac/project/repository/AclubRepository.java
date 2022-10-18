package com.jac.project.repository;

import com.jac.project.model.Aclub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AclubRepository extends JpaRepository<Aclub, Long> {
}

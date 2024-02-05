package com.dncoyote.schoolmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dncoyote.schoolmgmt.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    
}

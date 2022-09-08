package com.digitalbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbook.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

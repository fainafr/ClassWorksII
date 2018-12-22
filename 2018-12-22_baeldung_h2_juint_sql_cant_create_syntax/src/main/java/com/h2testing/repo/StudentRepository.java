package com.h2testing.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.h2testing.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

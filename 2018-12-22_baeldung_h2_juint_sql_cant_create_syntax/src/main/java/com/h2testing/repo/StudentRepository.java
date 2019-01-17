package com.h2testing.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.h2testing.entity.Student;

@Repository	
public interface StudentRepository extends CrudRepository<Student, Long> {

}

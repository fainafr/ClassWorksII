package com.h2testing.repo;

import com.h2testing.configuration.StudentConfiguration;
import com.h2testing.entity.Student;
import com.h2testing.repo.StudentRepository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = { StudentConfiguration.class }, 
  loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
public class InMemoryDBTest {
     
    @Resource
    private StudentRepository studentRepository;

    private static final long ID = 1;
    private static final String NAME = "john";
    

    @Test
    public void givenStudent_whenSave_thenGetOk() {
    	Student student = new Student(ID, NAME);
        studentRepository.save(student);

     //   Student student2 = studentRepository.findById(ID).get();
       // assertEquals("name incorrect", NAME, student2.getName());
    }


}
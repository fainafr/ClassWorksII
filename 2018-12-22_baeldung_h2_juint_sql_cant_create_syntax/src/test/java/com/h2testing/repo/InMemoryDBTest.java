package com.h2testing.repo;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.h2testing.configuration.StudentConfiguration;
import com.h2testing.entity.Student;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = { StudentConfiguration.class }, 
  loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
public class InMemoryDBTest {
	
	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

     
    @Resource
    private StudentRepository studentRepository;

    private static final Long ID = 1l;
    private static final String NAME = "john";
    

    @Test
    public void givenStudent_whenSave_thenGetOk() {
    	Student student = new Student(ID, NAME);
        studentRepository.save(student);

     //   Student student2 = studentRepository.findById(ID).get();
       // assertEquals("name incorrect", NAME, student2.getName());
    }
    
    @Test
    @Commit
	public void emPersistFind() {

    	Student student = new Student(ID, NAME);
    	em.persist(student);
		

    	Student persistentStudent = em.find(Student.class, ID);

		assertTrue(persistentStudent == student);
		assertTrue(persistentStudent.equals(student));
		assertTrue(persistentStudent.getId().equals(student.getId()));
		
	}


}
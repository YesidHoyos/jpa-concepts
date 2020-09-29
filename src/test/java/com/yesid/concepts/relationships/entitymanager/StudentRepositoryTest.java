package com.yesid.concepts.relationships.entitymanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.DatabaseApplicationJPARelationships;
import com.yesid.concepts.jpa.relationships.entity.Address;
import com.yesid.concepts.jpa.relationships.entity.Student;
import com.yesid.concepts.jpa.relationships.entitymanager.repository.StudentRepository;

@SpringBootTest(classes = DatabaseApplicationJPARelationships.class)
public class StudentRepositoryTest {

	private static final String STUDENT_NOT_FOUND = "Student not found";
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	void findByNullId() {
		//arrange
		
		//act
		try {
		studentRepository.findById(null)
			.orElseThrow(()-> new RuntimeException(STUDENT_NOT_FOUND));
		
		fail();
		
		} catch (Exception e){
			//assert
			assertEquals(STUDENT_NOT_FOUND, e.getMessage());
		}
	}
	
	@Test
	void findByIdWhenStudentDoesNotExist() {
		//arrange
		
		//act
		try {
		studentRepository.findById(999L)
			.orElseThrow(()-> new RuntimeException(STUDENT_NOT_FOUND));
		
		fail();
		
		} catch (Exception e){
			//assert
			assertEquals(STUDENT_NOT_FOUND, e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void setAddressDetails() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("No 101", "Some Street", "Some city"));
		em.flush();
	}
}

package com.yesid.concepts.jpa.relationships.entitymanager.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.jpa.relationships.entity.Course;
import com.yesid.concepts.jpa.relationships.entity.Passport;
import com.yesid.concepts.jpa.relationships.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private final EntityManager entityManager;

	public StudentRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<Student> findById(Long id) {
		if(id == null) return Optional.empty();
		
		try {
			return Optional.of(entityManager.find(Student.class, id));
		} catch (RuntimeException e) {
			return Optional.empty();
		}
	}

	public Student save(Student student) {

		if (findById(student.getId()).isPresent()) {
			entityManager.merge(student);
		} else {
			entityManager.persist(student);
		}
		return student;
	}

	public void deleteById(Long id) {
		Optional<Student> student = findById(id);
		student.ifPresent(entityManager::remove);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		entityManager.persist(passport);

		Student student = new Student("Hoyos");

		student.setPassport(passport);
		entityManager.persist(student);	
	}
	
	public void insertCourseToStudent(Long studentId, Course course){
		Student student = findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
		student.addCourse(course);
		course.addStudent(student);
		entityManager.persist(student);
	}
}

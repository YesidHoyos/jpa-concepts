package com.yesid.concepts.relationships.springdata;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.yesid.concepts.DatabaseApplicationJPARelationships;
import com.yesid.concepts.jpa.relationships.entity.Course;
import com.yesid.concepts.jpa.relationships.springdata.repository.CourseSpringDataRepository;

@SpringBootTest(classes = DatabaseApplicationJPARelationships.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository repository;

	@Test
	void findByIdCoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	void findByIdCourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	void playingAroundWithSpringDataRepository() {
		//Course course = new Course("Microservices in 100 Steps");
		//repository.save(course);

		//course.setName("Microservices in 100 Steps - Updated");
		//repository.save(course);
		logger.info("Courses -> {} ", repository.findAll());
		logger.info("Count -> {} ", repository.count());
	}
	
	@Test
	void sort() {
		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		logger.info("Sorted Courses -> {} ", repository.findAll(sort));
		//Courses -> [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]] 
	}

	@Test
	void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {} ", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {} ", secondPage.getContent());
	}
}

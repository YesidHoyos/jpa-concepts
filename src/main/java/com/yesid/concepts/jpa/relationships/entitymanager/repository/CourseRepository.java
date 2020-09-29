package com.yesid.concepts.jpa.relationships.entitymanager.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.jpa.relationships.entity.Course;
import com.yesid.concepts.jpa.relationships.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	private final EntityManager entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public CourseRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Optional<Course> findById(Long id) {
		if (id == null) return Optional.empty();
		
		try {
			return Optional.of(entityManager.find(Course.class, id));
		} catch(RuntimeException e) {
			return Optional.empty();
		}
	}
	
	public Course save(Course course) {
		if(findById(course.getId()).isPresent()) {
			entityManager.merge(course);
		} else {
			entityManager.persist(course);
		}
		return course;
	}
	
	public void deleteById(Long id) {
		Optional<Course> courseOptional = findById(id);
		courseOptional.ifPresent(entityManager::remove);
	}
	
	public void addReviewsForCourse(Long courseId, Review review) {		
		Course course = findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		
		log.info("course.getReviews() -> {}", course.getReviews());		
		
		//setting the relationship
		course.addReview(review);
		review.setCourse(course);
		
		entityManager.persist(review);
	}
}

package com.yesid.concepts.relationships.entitymanager;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.jpa.relationships.entity.Course;
import com.yesid.concepts.jpa.relationships.entity.Review;
import com.yesid.concepts.jpa.relationships.entitymanager.repository.CourseRepository;

@SpringBootTest
public class CourseRepositoryTest {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional //is necessary because @OneToMany use LAZY fetching
	void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L).get();
		logger.info("{}", course.getReviews());
	}

	@Test
	//@Transactional - is not necessary because @ManyToOne use EAGER fetching
	void retrieveCourseForReview() {
		Review review = entityManager.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}
}

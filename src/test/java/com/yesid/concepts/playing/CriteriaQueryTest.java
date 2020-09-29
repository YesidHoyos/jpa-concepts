package com.yesid.concepts.playing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yesid.concepts.DatabaseApplicationPlayingEntityManager;
import com.yesid.concepts.jpa.relationships.entity.Course;

@SpringBootTest(classes = DatabaseApplicationPlayingEntityManager.class)
class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	void allCourses() {
		// "Select c From Course c"

		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder

		// 4. Add Predicates etc to the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));

		List<Course> resultList = query.getResultList();

		logger.info("Typed Query -> {}", resultList);
		// [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring
		// Boot in 100 Steps]]
	}

	@Test
	void allCoursesHaving100Steps() {
		// "Select c From Course c where name like '%100 Steps' "

		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Predicate like100Steps = criteriaBuilder.like(courseRoot.get("name"), "%100 Steps");

		// 4. Add Predicates etc to the Criteria Query
		criteriaQuery.where(like100Steps);

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));

		List<Course> resultList = query.getResultList();

		logger.info("Typed Query -> {}", resultList);
		// [Course[Spring Boot in 100 Steps]]
	}

	@Test
	void allCoursesWithoutStudents() {
		// "Select c From Course c where c.students is empty"

		// 1. Use Criteria Builder to create a Criteria Query returning the
		// expected result object
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Predicate studentsIsEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));

		// 4. Add Predicates etc to the Criteria Query
		criteriaQuery.where(studentsIsEmpty);

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));

		List<Course> resultList = query.getResultList();

		logger.info("Typed Query -> {}", resultList);
		// [Course[Spring in 50 Steps]]
	}

}

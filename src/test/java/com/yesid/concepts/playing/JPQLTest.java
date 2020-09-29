package com.yesid.concepts.playing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yesid.concepts.DatabaseApplicationPlayingEntityManager;
import com.yesid.concepts.jpa.playing.entity.CourseP;
import com.yesid.concepts.jpa.relationships.entity.Course;
import com.yesid.concepts.jpa.relationships.entity.Student;

@SpringBootTest(classes = DatabaseApplicationPlayingEntityManager.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	void jpqlBasic() {
		TypedQuery<CourseP> query = entityManager.createNamedQuery("query_get_all_courses", CourseP.class);
		List<CourseP> resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}", resultList);
	}

	@Test
	void jpqlTyped() {
		TypedQuery<CourseP> query = entityManager.createNamedQuery("query_get_all_courses", CourseP.class);

		List<CourseP> resultList = query.getResultList();

		logger.info("Select  c  From Course c -> {}", resultList);
	}

	@Test
	void jpqlWhere() {
		TypedQuery<CourseP> query = entityManager.createNamedQuery("query_get_100_Step_courses", CourseP.class);

		List<CourseP> resultList = query.getResultList();

		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}", resultList);
		// [Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
	}
	
	@Test
	void coursesWithoutStudents() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		// [Course[Spring in 50 Steps]]
	}

	
	@Test
	void coursesWithAtleastTwoStudents() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		//[Course[JPA in 50 Steps]]
	}

	@Test
	void coursesOrderedByStudents() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	void studentsWithPassportsInACertainPattern() {
		TypedQuery<Student> query = entityManager.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	void join(){
		Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	void leftJoin(){
		Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	void crossJoin(){
		Query query = entityManager.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
}

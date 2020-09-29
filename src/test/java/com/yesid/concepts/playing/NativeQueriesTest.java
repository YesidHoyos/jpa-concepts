package com.yesid.concepts.playing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.DatabaseApplicationPlayingEntityManager;
import com.yesid.concepts.jpa.playing.entity.CourseP;

@SpringBootTest(classes = DatabaseApplicationPlayingEntityManager.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	void nativeQueriesBasic() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", CourseP.class);
		List<CourseP> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE  -> {}", resultList);
		//SELECT * FROM COURSE  -> [Course[Web Services in 100 Steps], Course[JPA in 50 Steps - Updated], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
	}

	@Test
	void nativeQueriesWithParameter() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE where id = ?", CourseP.class);
		query.setParameter(1, 10001L);
		List<CourseP> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE  where id = ? -> {}", resultList);
		//[Course[JPA in 50 Steps - Updated]]
	}

	@Test
	void nativeQueriesWithNamedParameter() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE where id = :id", CourseP.class);
		query.setParameter("id", 10001L);
		List<CourseP> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE  where id = :id -> {}", resultList);

	}
	
	@Test
	@Transactional
	void nativeQueriesToUpdate() {
		Query query = entityManager.createNativeQuery("Update COURSE set last_updated_date=sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated  -> {}", noOfRowsUpdated);
	}
}

package com.yesid.concepts.jpa.playing.entitymanager.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.jpa.playing.entity.CourseP;

@Repository
@Transactional
public class CourseRepositoryP {

	EntityManager entityManager;
	
	public CourseRepositoryP(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Course is update in database without call entityManager.merge method
	 */
	public void updateAfterSave() {
		CourseP course = new CourseP("Learn JPA");
		entityManager.persist(course);
		
		
		entityManager.flush(); //write changes in database without commit them
		
		course.setName("Learn JPA - update");
	}
	
	/**
	 * Course is not update in database
	 */
	public void notUpdateAfterSave() {
		CourseP course = new CourseP("Learn JPA");
		entityManager.persist(course);
		entityManager.flush(); //write changes in database without commit them
		
		//entityManager.detach(course);
		entityManager.clear();
		course.setName("Learn JPA - update");
	}
	
	/**
	 * if another client is connected, he can't get this record until after 20 seconds, when
	 * the method has finished
	 * @throws InterruptedException
	 */
	public void notReadBeforeCommited() throws InterruptedException {
		CourseP course = new CourseP("Learn JPA");
		entityManager.persist(course);		
		Thread.sleep(20000);
		System.out.println("after 20s");
	}
	
	/**
	 * NO SIRVE AÚN
	 * the changes are committed before that the method has finished
	 */
	public void readBeforeCommited() throws InterruptedException {
		CourseP course = new CourseP("Learn JPA");
		entityManager.persist(course);	
		//entityManager.getTransaction().commit();
		
		Thread.sleep(20000);
		System.out.println("after 20s");
	}
	
	/**
	 * any modification of the object is discarded with the refresh method
	 * because the refresh method synchronize the entity with the database model
	 */
	public void refreshAfterUpdate() {
		CourseP course = new CourseP("Learn JPA");
		entityManager.persist(course);		
		
		entityManager.flush();
		
		course.setName("Learn JPA - update");
		
		entityManager.refresh(course);
		
		entityManager.flush();
		System.out.println("course: " + course.getName());
	}
}

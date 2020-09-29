package com.yesid.concepts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yesid.concepts.jpa.relationships.entity.Course;
import com.yesid.concepts.jpa.relationships.entity.Review;
import com.yesid.concepts.jpa.relationships.entity.ReviewRating;
import com.yesid.concepts.jpa.relationships.entitymanager.repository.CourseRepository;
import com.yesid.concepts.jpa.relationships.entitymanager.repository.StudentRepository;


@SpringBootApplication
public class DatabaseApplicationJPARelationships implements CommandLineRunner{
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplicationJPARelationships.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//log.info("Student -> {}", studentRepository.findById(20001L));
		//studentRepository.saveStudentWithPassport();
		//courseRepository.addReviewsForCourse(10003L, new Review(ReviewRating.FIVE, "Great Hands-on Stuff."));
		/*Course course = courseRepository.findById(10003L).orElseThrow(() -> new RuntimeException("Course not found"));
		studentRepository.insertCourseToStudent(20003L, course);
		log.info("Students of 10003L course -> {}", course.getStudents());*/
	}

}

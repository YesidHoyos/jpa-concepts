package com.yesid.concepts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yesid.concepts.jpa.playing.entitymanager.repository.CourseRepositoryP;


//@SpringBootApplication
public class DatabaseApplicationPlayingEntityManager implements CommandLineRunner{

	@Autowired
	private CourseRepositoryP courseRepository;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplicationPlayingEntityManager.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//courseRepository.updateAfterSave();
		//courseRepository.notUpdateAfterSave();
		//courseRepository.notReadBeforeCommited();
		//courseRepository.readBeforeCommited();
		//courseRepository.refreshAfterUpdate();
	}

}

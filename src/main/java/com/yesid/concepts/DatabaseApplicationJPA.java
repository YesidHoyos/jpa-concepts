package com.yesid.concepts;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yesid.concepts.jpa.simplecrud.entity.Person;
import com.yesid.concepts.jpa.simplecrud.entitymanager.repository.PersonRepositoryJPA;

//@SpringBootApplication
public class DatabaseApplicationJPA implements CommandLineRunner{

	@Autowired
	private PersonRepositoryJPA personRepository;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplicationJPA.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("Result -> {}", personRepository.findAll());
		log.info("Result for 10001 -> {}", personRepository.findById(10001));
		personRepository.deleteById(10002);
		log.info("Inserting -> {}", 
				personRepository.insert(new Person(10004, "Pepita", "Porahí", LocalDate.now())));
		
		log.info("Update 10003 -> {}", 
				personRepository.update(new Person(10003, "Juanito Nieves", "Poracá", LocalDate.now())));
	}

}

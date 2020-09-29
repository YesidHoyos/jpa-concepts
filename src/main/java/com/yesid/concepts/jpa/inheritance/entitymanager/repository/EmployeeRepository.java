package com.yesid.concepts.jpa.inheritance.entitymanager.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.concepts.jpa.inheritance.entity.Employee;
import com.yesid.concepts.jpa.inheritance.entity.FullTimeEmployee;
import com.yesid.concepts.jpa.inheritance.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

	private final EntityManager entityManager;
	
	public EmployeeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insert(Employee employee) {
		entityManager.persist(employee);
	}

	public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
		return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}

	public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
		return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
}

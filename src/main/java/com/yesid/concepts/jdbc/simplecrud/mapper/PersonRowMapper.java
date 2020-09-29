package com.yesid.concepts.jdbc.simplecrud.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yesid.concepts.jdbc.simplecrud.entity.Person;

public class PersonRowMapper implements RowMapper<Person>{
	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("id"));
		person.setName(rs.getString("name"));
		person.setLocation(rs.getString("location"));
		person.setBirthDate(rs.getDate("birth_date").toLocalDate());
		return person;
	}
	
}

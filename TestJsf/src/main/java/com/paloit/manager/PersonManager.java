package com.paloit.manager;

import java.util.List;

import com.paloit.entities.Person;

public interface PersonManager {

	public void savePerson(Person person);

	public List<Person> getAllPersons();

	public Person getPersonById(String id);

	public void deletePerson(Person person);

}

package com.paloit.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.PersonDao;
import com.paloit.entities.Person;

@Service
@Transactional(readOnly = true)
public class PersonManagerImpl implements PersonManager {

	private PersonDao personDao;

	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Transactional(readOnly = false)
	public void savePerson(Person person) {
		personDao.savePerson(person);

	}

	public List<Person> getAllPersons() {
		return personDao.getAllPersons();
	}

	public Person getPersonById(String id) {
		return personDao.getPersonById(id);
	}

	@Transactional(readOnly = false)
	public void deletePerson(Person person) {
		personDao.deletePerson(person);
	}

}

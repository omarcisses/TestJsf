package com.paloit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.paloit.entities.Person;

@Repository("personDao")
public class PersonDaoImpl implements PersonDao{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory factory){
		sessionFactory=factory;
	}

	public void savePerson(Person person) {
		sessionFactory.getCurrentSession().merge(person);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
	}

	public Person getPersonById(String id) {
		return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
	}

	public void deletePerson(Person person) {
		sessionFactory.getCurrentSession().delete(person);
	}

	public List<Person> getPersonbyName(String name) {
		return sessionFactory.getCurrentSession().createQuery("From Person WHERE name =:name").list();
	}

}

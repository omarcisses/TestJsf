package com.paloit.dao;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.paloit.entities.Person;

public class PersonDaoTest {
	static PersonDaoImpl dao;
	static SessionFactory factory;
	static Session session;
	
	@BeforeClass
	public static void initClass(){
		dao = new PersonDaoImpl();
		factory = mock(SessionFactory.class);
		session = mock(Session.class);
		dao.setSessionFactory(factory);
	}

	@Before
	public void init() {
		reset(factory,session);
		when(factory.getCurrentSession()).thenReturn(session);
	}

	@Test(expected = HibernateException.class)
	public void testCreateDataKO() {
		// Setup
		Person user = new Person();
		when(session.merge(user)).thenThrow(new HibernateException("fail"));
		// Action
		dao.savePerson(user);
	}

	@Test
	public void testCreateDataOK() {
		// Setup
		Person input = new Person();
		// Action
		dao.savePerson(input);
		// test
		verify(factory, times(1)).getCurrentSession();
		verify(session, times(1)).merge(input);
	}

	@Test(expected = HibernateException.class)
	public void testRetrieveDataKO() {
		// /Setup
		Criteria criteria = mock(Criteria.class);
		when(session.createCriteria(Person.class)).thenReturn(criteria);
		when(criteria.list()).thenThrow(new HibernateException(""));
		dao.getAllPersons();
	}

	@Test
	public void testRetrieveDataOK() {
		// /Setup
		Criteria criteria = mock(Criteria.class);
		when(session.createCriteria(Person.class)).thenReturn(criteria);
		List<Person> persons = mock(List.class);
		when(criteria.list()).thenReturn(persons);
		// Action
		dao.getAllPersons();
		// Test
		verify(session, times(1)).createCriteria(Person.class);
		verify(criteria, times(1)).list();
	}

	@Test(expected = HibernateException.class)
	public void testDeleteDataKO() {
		// Setup
		Person input = new Person();
		doThrow(new HibernateException("")).when(session).delete(input);
		// Action
		dao.deletePerson(input);
	}

	@Test
	public void testDeleteDataOK() {
		// Setup
		Person input = new Person();
		// Action
		dao.deletePerson(input);
		// Test
		verify(factory, times(1)).getCurrentSession();
		verify(session, times(1)).delete(input);
	}
	
}

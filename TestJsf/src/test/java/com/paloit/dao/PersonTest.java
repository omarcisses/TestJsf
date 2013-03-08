package com.paloit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paloit.entities.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/db-config.xml" })
public class PersonTest {
	static Session  session;

	@Autowired
	public void setFactory(SessionFactory factory) {
		session = factory.openSession();
	}

	@Test
	public void testConfiguration() {
		// Setup
		Person person = new Person();
		person.setName("Palo");
		person.setAge(21);
		session.beginTransaction();
		// Action
		person=(Person) session.merge(person);
		List<Person> persons = session.createCriteria(Person.class).list();
		// Test
		Assert.assertEquals(1, persons.size());
		Assert.assertEquals(person, persons.get(0));
	}
	
	@AfterClass
	public static void tearDown(){
		session.close();
	}

}

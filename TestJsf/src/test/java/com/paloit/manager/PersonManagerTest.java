package com.paloit.manager;
 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import com.paloit.dao.PersonDao;
import com.paloit.entities.Person;
 
public class PersonManagerTest {
 
    private static PersonManagerImpl managerImpl;
    private static PersonDao personDao;
 
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        managerImpl = new PersonManagerImpl();
        personDao = mock(PersonDao.class);
        managerImpl.setPersonDao(personDao);
    }
 
    @Before
    public void setUp() throws Exception {
        reset(personDao);
    }
 
    @Test(expected = HibernateException.class)
    public void testSavePersonKO() {
        // Setup
        Person person = new Person();
        doThrow(new HibernateException("fail")).when(personDao).savePerson(
                person);
        // Action
        managerImpl.savePerson(person);
    }
 
    @Test
    public void testSavePersonOK() {
        // Setup
        Person person = new Person();
        // Action
        managerImpl.savePerson(person);
        // Test
        verify(personDao, times(1)).savePerson(person);
    }
 
    @Test(expected = HibernateException.class)
    public void testGetPersonByIdKO() {
        // Setup
        when(personDao.getPersonById("id")).thenThrow(
                new HibernateException("fail"));
        // Action
        managerImpl.getPersonById("id");
    }
 
    @Test
    public void testGetPersonByIdOK() {
        // Setup
        Person input = new Person();
        String id = "id";
        input.setAge(23);
        input.setId(id);
        input.setName("palo");
        when(personDao.getPersonById(id)).thenReturn(input);
        // Action
        Person output = managerImpl.getPersonById(id);
        // Test
        assertEquals(input, output);
        verify(personDao, times(1)).getPersonById(id);
    }
 
}
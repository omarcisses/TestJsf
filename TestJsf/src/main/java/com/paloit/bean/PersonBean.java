/*
 *   Palo-it  source code: 
 */
package com.paloit.bean;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.entities.Person;
import com.paloit.manager.PersonManager;

/**
 * PersonBean
 * 
 * @author Palo-IT
 * @since 22 févr. 2013
 */
@Component
@Scope
public class PersonBean {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private Person person;

    private PersonManager manager;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    public PersonBean() {
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    public String savePerson() {
        manager.savePerson(person);
        return "pretty:home";
    }

    public void deletePerson(ActionEvent event) {
        manager.deletePerson(person);
    }
    
    public String editPerson(){
        return "pretty:edit";
    }
    
    public String newPerson(){
        reinit();
        return "pretty:new_user";
    }

    public void reinit() {
        person = new Person();
    }

    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    public List<Person> getAllPersons() {
        return manager.getAllPersons();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Autowired
    public void setManager(PersonManager manager) {
        this.manager = manager;
    }

}

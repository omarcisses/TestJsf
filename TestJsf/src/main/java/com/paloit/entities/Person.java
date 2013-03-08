package com.paloit.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 8496087166198616020L;
	private String id;
	private String name;
	private Integer age;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PersonId")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PersonName", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PersonAge")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Person person = (Person) obj;
		return new EqualsBuilder().append(name, person.getName())
				.append(id, person.getId()).append(age, person.getAge())
				.isEquals();
	}

}

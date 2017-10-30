package com.meta.volvo.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "DEPARTMENTS")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6610370310807521168L;

	@Column(length = 8, nullable = false, name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(length = 50, nullable = false, name = "NAME")
	private String name;

	@Column(length = 50, nullable = false, name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private Set<User> user;

	public Department() {
	}

	public Department(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

}

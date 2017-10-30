package com.meta.volvo.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "PERMISSIONS")
public class Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496465957294679946L;
	
	@Column(length = 8, nullable = false, name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(length = 50, nullable = false, name = "NAME")
	private String name;
	
	@Column(length = 50, nullable = false, name = "DESCRIPTION")
	private String description;
	
	@ManyToMany(mappedBy = "permissions")
	private Set<User> users;

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


}

package com.meta.volvo.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878339487358078070L;

	@Column(length = 8, nullable = false, name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(length = 50, nullable = false, name = "NAME")
	private String name;

	@Column(length = 50, nullable = false, name = "DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	@ManyToMany
	@JoinTable(name = "USER_PERMISSION", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
	private Set<Permission> permissions;

	public User() {
	}

	public User(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public User(String name, String description, Department department, Set<Permission> permissions) {
		this.name = name;
		this.description = description;
		this.department = department;
		this.permissions = permissions;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}

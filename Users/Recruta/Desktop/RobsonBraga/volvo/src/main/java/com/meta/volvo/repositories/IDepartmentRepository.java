package com.meta.volvo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.meta.volvo.entities.Department;

public interface IDepartmentRepository extends CrudRepository<Department, Long> {

}

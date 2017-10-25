package com.meta.volvo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.meta.volvo.entities.Department;

public interface IDeptPageRepository extends PagingAndSortingRepository<Department, Long> {

}

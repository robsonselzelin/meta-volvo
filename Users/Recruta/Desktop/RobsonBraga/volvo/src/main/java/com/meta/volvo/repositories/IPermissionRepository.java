package com.meta.volvo.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.meta.volvo.entities.Permission;

public interface IPermissionRepository extends CrudRepository<Permission, Long> {

	// @Query(value = "SELECT p FROM Permission p WHERE p.id IN (:ids)")
	// public Set<Permission> findAllByIds(@Param("ids") List<Integer> ids);
	public Set<Permission> findAllByIdIn(List<Long> ids);

}

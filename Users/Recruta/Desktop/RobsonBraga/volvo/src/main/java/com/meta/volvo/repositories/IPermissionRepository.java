package com.meta.volvo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.meta.volvo.entities.Permission;

public interface IPermissionRepository extends CrudRepository<Permission, Long> {

}

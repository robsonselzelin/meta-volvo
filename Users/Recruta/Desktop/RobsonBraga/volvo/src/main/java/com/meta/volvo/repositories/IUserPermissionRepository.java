package com.meta.volvo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.meta.volvo.entities.User;
import com.meta.volvo.entities.UserPermission;

public interface IUserPermissionRepository extends CrudRepository<UserPermission, User> {

	@Modifying
	@Query(value = "insert into USER_PERMISSION(user_id, permission_id) values (userId, permissionId)")
	public void insertUserPermission(
			@Param("userId") Long userId
			, @Param("permissionId") Long permissionId);
}

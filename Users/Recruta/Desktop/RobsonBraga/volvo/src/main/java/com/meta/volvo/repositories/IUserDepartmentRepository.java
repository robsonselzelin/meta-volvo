package com.meta.volvo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.meta.volvo.entities.User;
import com.meta.volvo.entities.UserDepartment;

public interface IUserDepartmentRepository extends CrudRepository<UserDepartment, User> {
	
	@Modifying
	@Query(value = "insert into USER_DEPARTMENT(user_id, department_id) values (userId, departmentId)")
	public void insertUserRepository(
			@Param("userId") Long userId
			, @Param("departmentId") Long departmentId);


}

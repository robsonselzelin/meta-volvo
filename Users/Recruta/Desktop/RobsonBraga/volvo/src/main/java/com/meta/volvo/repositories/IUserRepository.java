package com.meta.volvo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.meta.volvo.entities.User;

public interface IUserRepository extends CrudRepository<User, Long>{
	

}

package com.meta.volvo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.meta.volvo.entities.User;

public interface IUserPageRepository extends PagingAndSortingRepository<User, Long> {

}

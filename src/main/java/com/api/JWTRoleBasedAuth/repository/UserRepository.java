package com.api.JWTRoleBasedAuth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.JWTRoleBasedAuth.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}

package com.api.JWTRoleBasedAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.JWTRoleBasedAuth.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}

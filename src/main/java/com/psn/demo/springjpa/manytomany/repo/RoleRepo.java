package com.psn.demo.springjpa.manytomany.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psn.demo.springjpa.manytomany.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(String name);
}

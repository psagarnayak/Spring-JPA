package com.psn.demo.springjpa.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psn.demo.springjpa.onetoone.model.Person;

public interface PersonRepo extends JpaRepository<Person, Integer>{

}

package com.psn.demo.springjpa.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psn.demo.springjpa.onetoone.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}

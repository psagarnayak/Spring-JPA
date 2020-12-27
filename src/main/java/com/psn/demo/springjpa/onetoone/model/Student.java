package com.psn.demo.springjpa.onetoone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studentId;
	
	private String branch;
	private int year;
	
	@OneToOne
	@JoinColumn(name = "personId", referencedColumnName = "id")
	private Person person;
	// Note here that Student is the owning entity and Student has a 
	// foreign key column "personId" that points to the Referenced entity.

	// When you have got an one-to-many relationship, the objects related
	// to the "many" part will be the owning side. otherwise you would have
	// to store many references from a single object to a multitude.
	
	

}

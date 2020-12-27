package com.psn.demo.springjpa.onetoone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String homeCity;
	
	// JsonIgnore serves to prevent infinite recursion when sending this object 
	// as a rest response.
	@JsonIgnore
	@OneToOne(mappedBy = "person")
	private Student student;
	
	// Using mappedBy, If we only call person.setStudent(student),
	// the foreign key column personId of Student will NOT be linked
	// because this is not the owning /tracked side of the relation!


}

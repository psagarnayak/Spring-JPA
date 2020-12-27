package com.psn.demo.springjpa.manytomany.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@JsonIgnore
	@Builder.Default
	@ManyToMany(mappedBy = "roles") //mappedBy mention is optional but gives clarity
	List<User> users = new ArrayList<>();
	
	public Role addUsers(User ... user) {
		Collections.addAll(this.users, user);
		return this;
	}
}

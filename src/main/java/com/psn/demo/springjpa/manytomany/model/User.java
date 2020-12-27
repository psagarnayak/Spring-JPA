package com.psn.demo.springjpa.manytomany.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	
	
	@Builder.Default
	@ManyToMany
	@JoinTable(
			name = "USER_ROLES",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name ="ROLE_ID")
	) //JoinTable and descendant mention is optional but gives clarity
	List<Role> roles = new ArrayList<>();
	
	public User addRoles(Role ... roles) {
		Collections.addAll(this.roles, roles);
		return this;
	}
	
	public User removeRole(Role role ) {
		this.roles.removeIf( r -> r.getName().equals(role.getName()));
		return this;		
	}
}

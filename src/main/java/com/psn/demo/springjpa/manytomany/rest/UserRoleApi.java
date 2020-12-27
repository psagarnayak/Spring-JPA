package com.psn.demo.springjpa.manytomany.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psn.demo.springjpa.manytomany.model.Role;
import com.psn.demo.springjpa.manytomany.model.User;
import com.psn.demo.springjpa.manytomany.repo.RoleRepo;
import com.psn.demo.springjpa.manytomany.repo.UserRepo;

@RestController
@RequestMapping("/rest")
public class UserRoleApi {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	@GetMapping("/users")
	public List<User> fetchUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/roles")
	public List<Role> fetchRoles(){
		return roleRepo.findAll();
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User user) {
		

		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			user.setRoles(user.getRoles().stream().map(role -> {

				Optional<Role> foundRole = roleRepo.findByName(role.getName());

				if (foundRole.isPresent()) {
					return foundRole.get();
				}

				return roleRepo.save(role);
			}).collect(Collectors.toList()));

		}

		return userRepo.save(user);
	}

	@PostMapping("/roles")
	public Role saveRole(@RequestBody Role role) {
		return roleRepo.save(role);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int userId) {
		userRepo.deleteById(userId);
	}
	
	@DeleteMapping("/roles/{id}")
	public void deleteRole(@PathVariable("id") int roleId) {
		
		Optional<Role> optRole = roleRepo.findById(roleId);
		
		if(optRole.isPresent()) {
			Role role = optRole.get();
			List<User> usersWithrole = role.getUsers();
			
			usersWithrole.forEach(user -> {
				user.removeRole(role);
			});
			
			roleRepo.deleteById(roleId);
		}
	}
	
}

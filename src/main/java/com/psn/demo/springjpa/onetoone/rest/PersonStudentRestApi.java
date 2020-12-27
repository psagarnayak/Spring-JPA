package com.psn.demo.springjpa.onetoone.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psn.demo.springjpa.onetoone.model.Person;
import com.psn.demo.springjpa.onetoone.model.Student;
import com.psn.demo.springjpa.onetoone.repo.PersonRepo;
import com.psn.demo.springjpa.onetoone.repo.StudentRepo;

@RestController
@RequestMapping("/rest")
public class PersonStudentRestApi {
	
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private PersonRepo personRepo;
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		
		return studentRepo.findAll();
	}
	
	@GetMapping("/persons")
	public List<Person> getAllPersons(){
		
		return personRepo.findAll();
	}
	
	@PostMapping("/students")	
	public Student saveStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}
	
	@PostMapping("/persons")	
	public Person savePerson(@RequestBody Person person) {
		return personRepo.save(person);
	}
	
	@DeleteMapping("/persons/{id}")
	public Person deletePerson(@PathVariable("id") int personId) {
		
		Optional<Person> person = personRepo.findById(personId);
		person.ifPresent(personRepo::delete);
		return person.orElse(null);
	}
	
	@DeleteMapping("/students/{id}")
	public Student deleteStudent(@PathVariable("id") int studentId) {
		
		Optional<Student> student = studentRepo.findById(studentId);
		student.ifPresent(studentRepo::delete);
		return student.orElse(null);
	}

}

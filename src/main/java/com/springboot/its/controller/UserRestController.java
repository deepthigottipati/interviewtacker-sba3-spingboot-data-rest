package com.springboot.its.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.its.entity.Interview;
import com.springboot.its.entity.User;
import com.springboot.its.service.InterviewRestService;
import com.springboot.its.service.UserRestService;


@RestController
@RequestMapping("/its")
public class UserRestController {

	
	@Autowired
	private UserRestService userRestService;

	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRestService.retrieveAllUsers();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRestService.deleteUser(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		return userRestService.createUser(user);

	}

}


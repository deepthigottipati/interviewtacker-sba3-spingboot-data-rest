package com.springboot.its.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.its.dao.InterviewRepository;
import com.springboot.its.dao.UserRepository;
import com.springboot.its.entity.Interview;
import com.springboot.its.entity.User;
import com.springboot.its.exception.InterviewNotFoundException;
import com.springboot.its.exception.UserAlreadyAssignedException;


@Service
public class UserRestService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InterviewRepository interviewRepository;

	
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	/*
	 * public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
	 * User savedUser = userRepository.save(user);
	 * 
	 * URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (savedUser.getId()) .toUri();
	 * 
	 * return ResponseEntity.created(location).build();
	 * 
	 * }
	 */
	
}

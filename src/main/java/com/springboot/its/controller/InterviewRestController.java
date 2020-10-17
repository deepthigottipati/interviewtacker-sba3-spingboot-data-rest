package com.springboot.its.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.its.dao.InterviewRepository;
import com.springboot.its.dao.UserRepository;
import com.springboot.its.entity.Interview;
import com.springboot.its.entity.User;
import com.springboot.its.exception.InterviewNotFoundException;
import com.springboot.its.exception.UserNotFoundException;

@RestController
@RequestMapping("/its")
public class InterviewRestController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InterviewRepository interviewRepository;

	@GetMapping("/interviews")
	public List<Interview> retrieveAllInterviews() {
		return interviewRepository.findAll();
	}

	@GetMapping("/interviews/{id}")
	public EntityModel<Interview> retrieveInterview(@PathVariable int id) {
		Optional<Interview> interview = interviewRepository.findById(id);

		if (!interview.isPresent())
			throw new InterviewNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<Interview> resource = EntityModel.of(interview.get());//new EntityModel<User>(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllInterviews());


		resource.add(linkTo.withRel("all-interviews"));


		return resource;
	}

	@DeleteMapping("/interviews/{id}")
	public void deleteInterview(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	

	@PostMapping("/interviews")
	public ResponseEntity<Object> createInterview(@Valid @RequestBody Interview interview) {
		Interview savedInterview = interviewRepository.save(interview);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedInterview.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/interviews/{id}/users")
	public List<User> retrieveAllInterviews(@PathVariable int id) {
		Optional<Interview> interviewOptional = interviewRepository.findById(id);
		
		if(!interviewOptional.isPresent()) {
			throw new InterviewNotFoundException("id-" + id);
		}
		
		return interviewOptional.get().getUsers();
	}


	@PostMapping("/interviews/{id}/users")
	public ResponseEntity<Object> createInterview(@PathVariable int id, @RequestBody User user) {
		
		Optional<Interview> interviewOptional = interviewRepository.findById(id);
		
		if(!interviewOptional.isPresent()) {
			throw new InterviewNotFoundException("id-" + id);
		}

		Interview interview = interviewOptional.get();
		
		user.setInterview(interview);
		
		userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

}

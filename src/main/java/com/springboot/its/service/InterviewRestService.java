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
public class InterviewRestService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InterviewRepository interviewRepository;

	public List<Interview> retrieveAllInterviews() {
		return interviewRepository.findAll();
	}
	
	

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

	/*
	 * public ResponseEntity<Interview> getbyInterviewerName(@PathVariable("interviewername")
	 * String interviewername){ ResponseEntity<Interview> response=null;
	 * 
	 * Interview interview = (Interview)
	 * interviewRepository.FindByInterviewerFirstName(interviewername);
	 * 
	 * if(interview!=null) { response =new ResponseEntity<Interview>(interview,
	 * HttpStatus.OK); }else { response =new
	 * ResponseEntity<Interview>(HttpStatus.NOT_FOUND); }
	 * 
	 * return response; }
	 */
	
	/*
	 * @GetMapping("/interviews/interviewername/{interviewername}") public
	 * EntityModel<Interview> retrieveInterviewbyFirstName(@PathVariable String
	 * interviewername) { List<Interview> interview =
	 * interviewRepository.FindByInterviewerFirstName(interviewername);
	 * 
	 * if (interview.isEmpty()) throw new
	 * InterviewNotFoundException("interviewername-" + interviewername);
	 * 
	 * 
	 * EntityModel<Interview> resource = EntityModel.of(interview.get());
	 * WebMvcLinkBuilder linkTo =
	 * linkTo(methodOn(this.getClass()).retrieveAllInterviews());
	 * 
	 * 
	 * resource.add(linkTo.withRel("all-interviews"));
	 * 
	 * 
	 * return resource; }
	 */
	
	
	public void deleteInterview(@PathVariable int id) {
		interviewRepository.deleteById(id);
	}

	

	public ResponseEntity<Object> createInterview(@Valid @RequestBody Interview interview) {
		Interview savedInterview = interviewRepository.save(interview);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedInterview.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	public List<User> retrieveAllUsersOfInterview(@PathVariable int id) {
		
		
		Optional<Interview> interviewOptional = interviewRepository.findById(id);
		
		
		if(!interviewOptional.isPresent()) {
			throw new InterviewNotFoundException("id-" + id);
		}
		
		return interviewOptional.get().getUsers();
	}
	/*
	 * @GetMapping("/interviews/filter") public ResponseEntity<?> filter( Interview
	 * interview, Pageable page, PagedResourcesAssembler assembler,
	 * PersistentEntityResourceAssembler entityAssembler ){
	 * 
	 * ExampleMatcher matcher = ExampleMatcher.matching() .withIgnoreCase()
	 * .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
	 * 
	 * Example example = Example.of(interview, matcher);
	 * 
	 * Page<?> result = (Page<?>) this.interviewRepository.findAll(example);
	 * 
	 * return ResponseEntity.ok(assembler.toResource(result, entityAssembler));
	 * 
	 * }
	 */

	public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody User user) {
		
		
		List<User> users=userRepository.findAll();
		boolean newuser=true;
		
		for(User tempuser:users)
		{
			if(tempuser.getEmail().equals(user.getEmail()))
			{
				newuser=false;
				
				break;
			}
		}
		
		if(newuser==true)
		{
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
		else
			throw new UserAlreadyAssignedException("user" +user.getFirstName()+ "already Assigned to " + id + "interview");


	}

}

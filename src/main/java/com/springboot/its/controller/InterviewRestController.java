package com.springboot.its.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
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


@RestController
@RequestMapping("/its")
public class InterviewRestController {

	
	@Autowired
	private InterviewRestService interviewRestService;

	@GetMapping("/interviews")
	public List<Interview> retrieveAllInterviews() {
		return interviewRestService.retrieveAllInterviews();
	}
	
	
	@GetMapping("/interviews/{id}")
	public EntityModel<Interview> retrieveInterview(@PathVariable int id) {
	
		return interviewRestService.retrieveInterview(id);
	}
	@GetMapping("/interviews/interviewer_name/{interviewername}")
	public ResponseEntity<List<Interview>> retrievebyInterviewerName(@PathVariable String interviewername) {
	
		return new ResponseEntity<>(interviewRestService.getbyInterviewerName(interviewername),HttpStatus.OK);
	}
	
	@GetMapping("/interviews/interviewer_name/{interviewname}")
	public ResponseEntity<List<Interview>> retrievebyInterviewName(@PathVariable String interviewername) {
	
		return new ResponseEntity<>(interviewRestService.getbyInterviewName(interviewername),HttpStatus.OK);
	}
	
	@DeleteMapping("/interviews/{id}")
	public void deleteInterview(@PathVariable int id) {
		interviewRestService.deleteInterview(id);
	}

	

	@PostMapping("/interviews")
	public ResponseEntity<Object> createInterview(@Valid @RequestBody Interview interview) {
		
		return interviewRestService.createInterview(interview);

	}
	
	@GetMapping("/interviews/{id}/users")
	public List<User> retrieveAllUsersOfInterview(@PathVariable int id) {
		
		return interviewRestService.retrieveAllUsersOfInterview(id);
	}
	
	@PostMapping("/interviews/{id}/users")
	public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody User user) {
	
		return interviewRestService.createUser(id, user);

	}

}

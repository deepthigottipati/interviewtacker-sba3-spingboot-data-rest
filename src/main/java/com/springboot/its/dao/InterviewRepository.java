package com.springboot.its.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.its.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer>{
	
	//public List<Interview> FindByInterviewerFirstName(String name);

}

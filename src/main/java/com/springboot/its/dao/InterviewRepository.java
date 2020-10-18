package com.springboot.its.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.its.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer>{
	
	@Query("Select id from interview id where id.interviewer_name=?1")
	public List<Interview> FindByInterviewerName(String name);
	
	@Query("Select id from interview id where id.interview_name=?1")
	public List<Interview> FindByInterviewName(String name);

}

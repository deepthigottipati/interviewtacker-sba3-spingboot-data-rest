package com.springboot.its.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.its.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
}

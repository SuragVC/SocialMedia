package com.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media.models.UserManager;

public interface UserManagerDAO extends JpaRepository<UserManager, Integer>{
	
}

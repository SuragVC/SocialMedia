package com.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.media.models.LogIn;



@Repository
public interface LogInDAO extends JpaRepository<LogIn, Integer>{
	
	
}

package com.media.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.media.models.User;



@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUserName(String userName);
	
	public Optional<User>findByMobileNo(String mobileNo);
}

package com.media.services;

import com.media.exceptions.LoginException;
import com.media.models.User;

public interface UserService {
public User createNewSignUp(User signUp) throws LoginException;;
	
	public User updateSignUpDetails(User signUp,String key) throws LoginException;
	
}

package com.media.services;

import com.media.exceptions.LoginException;
import com.media.models.LogIn;

public interface LoginService {
	
	public String logInAccount(LogIn loginData) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}

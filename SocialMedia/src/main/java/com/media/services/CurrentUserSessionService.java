package com.media.services;

import com.media.exceptions.LoginException;
import com.media.models.CurrentSessionUser;
import com.media.models.User;

public interface CurrentUserSessionService {
	public CurrentSessionUser getCurrentUserSession(String key) throws LoginException;;
	public Integer getCurrentUserSessionId(String key) throws LoginException;;
	
	public User getSignUpDetails(String key) throws LoginException;;
}

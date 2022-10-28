package com.media.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.media.exceptions.LoginException;
import com.media.models.User;
import com.media.models.UserManager;
import com.media.repository.UserDAO;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO signUpDAO;
	
	@Autowired
	private CurrentUserSessionService getCurrentLoginUserSession;
	
	@Override
	public User createNewSignUp(User newSignUp) throws LoginException {
		
		Optional<User> opt = signUpDAO.findByUserName(newSignUp.getUserName());
		if(opt.isPresent())
		{
			throw new LoginException("User Already Exist!");
		}
		
		UserManager userManager = new UserManager();
		userManager.setUser(newSignUp);
		newSignUp.setManager(userManager);
		System.out.println("worked");
		return signUpDAO.save(newSignUp);
	}
	@Override
	public User updateSignUpDetails(User signUp, String key) throws LoginException {
		User signUpDetails = getCurrentLoginUserSession.getSignUpDetails(key);
		
		if(signUpDetails == null)
		{
			throw new LoginException("UnAuthorized!!! No User Found....Try To login first!");
		}
		
		if(signUpDetails.getUserId() == signUp.getUserId())
			{
			signUpDAO.save(signUp);
			return signUp;
			}
		else
			throw new LoginException("Can't change UserId!!");
	}

	
	

}

package com.media.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.exceptions.LoginException;
import com.media.models.CurrentSessionUser;
import com.media.models.LogIn;
import com.media.models.User;
import com.media.repository.LogInDAO;
import com.media.repository.UserDAO;



@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserDAO signUpDAO;
	
	@Autowired
	private com.media.repository.SessionDAO SessionDAO;
	
	@Autowired
	private CurrentUserSessionService getCurrentLoginUserSession;
	
	@Autowired
	private LogInDAO loginDAO;
	
	

	@Override
	public String logInAccount(LogIn loginData) throws LoginException {
		Optional<User> options = signUpDAO.findByMobileNo(loginData.getMobileNo());
		
		if(!options.isPresent()) {
			throw new LoginException("Invalid mobile Number ");
		}
		
		User newSignUp = options.get();
		
		System.out.println(newSignUp);
		
		System.out.println(loginData.getMobileNo());
		Integer newSignUpId = newSignUp.getUserId();
		Optional<CurrentSessionUser> currentSessionUser = SessionDAO.findByUserId(newSignUpId);
		
		if(currentSessionUser.isPresent()) {
			throw new LoginException("User already login with this userId");
		}
		
		if((newSignUp.getMobileNo().equals(loginData.getMobileNo()))  && newSignUp.getPassword().equals(loginData.getPassword())) {
			String key = RandomString.getRandomString();
			CurrentSessionUser currentSessionUser2 = new CurrentSessionUser(newSignUp.getUserId(), key, newSignUp.getMobileNo(),LocalDateTime.now());
			loginDAO.save(loginData);
			SessionDAO.save(currentSessionUser2);
			return currentSessionUser2.toString();
		}else {
			throw new LoginException("Invalid mobile and Password");
		}
		
	}
	

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		Optional<CurrentSessionUser> currentSessionuserOptional = SessionDAO.findByUuid(key);
		
		if(!currentSessionuserOptional.isPresent()) {
			throw new LoginException("User has not looged in with this Userid");
		}
		
		CurrentSessionUser currentSessionUser =getCurrentLoginUserSession.getCurrentUserSession(key);
		
		SessionDAO.delete(currentSessionUser);
		
		Optional<LogIn> logindata = loginDAO.findById(currentSessionuserOptional.get().getUserId());
		
		loginDAO.delete(logindata.get());
		
		return "Logged Out Succefully....";
	}
	

}

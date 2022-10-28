package com.media.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.exceptions.InvalidPostException;
import com.media.exceptions.LoginException;
import com.media.exceptions.UserNotFoundException;
import com.media.exceptions.UserNotLogedinException;
import com.media.models.CurrentSessionUser;
import com.media.models.Post;
import com.media.models.User;
import com.media.repository.PostDAO;
import com.media.repository.SessionDAO;
import com.media.repository.UserDAO;

@Service
public class PostServiceImpl implements PostServices{
	@Autowired
	private UserDAO userDao;
	@Autowired
	private SessionDAO currentDao;
	@Autowired
	private PostDAO postDao;
	@Override
	public Post createPost(Integer user_id, String uuid, Post post) throws InvalidPostException,UserNotLogedinException, UserNotFoundException {

		Optional<CurrentSessionUser> currentUser = currentDao.findByUuid(uuid);
		if(currentUser.isPresent()) {
			Optional<User> user = userDao.findById(user_id);
			if(user.isPresent()) {
				List<Post>list = user.get().getManager().getPost();
				list.add(post);
				postDao.save(post);
				return post;
			}else {
				throw new UserNotFoundException("User Not found with user id "+user_id);
			}
		}else {
			throw new UserNotLogedinException("User needs to login to create post");
		}
		
	}
	@Override
	public List<Post> getAllPostByUserId(String uuid, Integer UserId) throws UserNotFoundException,UserNotLogedinException{
		Optional<CurrentSessionUser> currentUser = currentDao.findByUuid(uuid);
		if(currentUser.isPresent()) {
			Optional<User> user = userDao.findById(UserId);
			if(user.isPresent()) {
				List<Post>list=user.get().getManager().getPost();
				return list;
			}else {
				throw new UserNotFoundException("User Not found with user id "+UserId);
			}
		}else {
			throw new UserNotLogedinException("User needs to login to create post");
		}
	}
	
	
}

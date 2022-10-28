package com.media.services;

import java.util.List;

import com.media.exceptions.InvalidPostException;
import com.media.exceptions.UserNotFoundException;
import com.media.exceptions.UserNotLogedinException;
import com.media.models.Post;

public interface PostServices {
	public Post createPost(Integer user_id,String uuid,Post post)throws InvalidPostException, UserNotFoundException, UserNotLogedinException;
	public List<Post> getAllPostByUserId(String uuid,Integer UserId)throws UserNotFoundException,UserNotLogedinException;
}

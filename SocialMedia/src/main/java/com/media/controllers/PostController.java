package com.media.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.exceptions.InvalidPostException;
import com.media.exceptions.UserNotFoundException;
import com.media.exceptions.UserNotLogedinException;
import com.media.models.Post;
import com.media.repository.UserDAO;
import com.media.services.PostServices;
import com.media.services.UserService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostServices postService;
	@Autowired
	private UserService userServices;
	
	@PostMapping("{userId}/{currentId}")
	public ResponseEntity<Post>createNewPost(@PathVariable @Valid Integer userId,@PathVariable String currentId,@RequestBody @Valid Post post) throws InvalidPostException, UserNotFoundException, UserNotLogedinException{
		System.out.println("d");
		Post savedPost =postService.createPost(userId, currentId, post);
		return new ResponseEntity<Post>(savedPost,HttpStatus.CREATED);
	}
	@GetMapping("/all/{uuid}/{UserId}")
	public ResponseEntity<List<Post>>getAllPostById(@PathVariable String uuid,@PathVariable Integer UserId) throws UserNotFoundException, UserNotLogedinException{
		List<Post> list = postService.getAllPostByUserId(uuid, UserId);
		return new ResponseEntity<List<Post>>(list,HttpStatus.OK);
	}
	
}

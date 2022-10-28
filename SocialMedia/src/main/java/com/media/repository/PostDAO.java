package com.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media.models.Post;

public interface PostDAO extends JpaRepository<Post, Integer>{

}

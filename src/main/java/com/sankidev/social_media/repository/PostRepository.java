package com.sankidev.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sankidev.social_media.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	@Query("select post from Post post where post.userId = :userId")
	List<Post> findPostByUserId(Integer userId);
}

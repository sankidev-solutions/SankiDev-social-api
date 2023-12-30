package com.sankidev.social_media.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sankidev.social_media.models.Post;
import com.sankidev.social_media.response.ApiResponse;
import com.sankidev.social_media.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception{
		Post createdPost = postService.createNewPost(post, userId);
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		String msg = postService.deletePost(postId, userId);
		ApiResponse response = new ApiResponse(msg,true);
		return new ResponseEntity<> (response,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception{
		Post post = postService.findPostById(postId);
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
}

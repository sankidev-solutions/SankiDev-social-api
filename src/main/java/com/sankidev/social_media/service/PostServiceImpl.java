package com.sankidev.social_media.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankidev.social_media.models.Post;
import com.sankidev.social_media.models.User;
import com.sankidev.social_media.repository.PostRepository;
import com.sankidev.social_media.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setUser(userService.findUserById(userId));
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new Exception("You can't delete the another user's post");
		}
		
		postRepository.deleteById(postId);
		
		return "Post Deleted Successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isEmpty()) {
			throw new Exception("Post Not found for postId : "+postId);	
		}
		return post.get();
	}

	@Override
	public List<Post> findAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		return postRepository.save(post);
		
	}

}

package com.sankidev.social_media.service;

import java.util.List;

import com.sankidev.social_media.models.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws Exception;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws Exception;
		
	public List<User> searchUser(String query);

	public User updateUser(Integer id, User user) throws Exception;
}

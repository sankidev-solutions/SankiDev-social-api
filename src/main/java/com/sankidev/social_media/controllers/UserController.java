package com.sankidev.social_media.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sankidev.social_media.models.User;
import com.sankidev.social_media.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping("/{id}")
	public User findUserById(@PathVariable("id") Integer id) throws Exception {
		return userService.findUserById(id);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") Integer id,@RequestBody User user) throws Exception {
		return userService.updateUser(id, user);
	}
	
	@PutMapping("/follow/{curentUserId}/{followUserId}")
	public User followUserHandle(@PathVariable("curentUserId") Integer curentUserId, 
									@PathVariable("followUserId") Integer followUserId) throws Exception {
		return userService.followUser(curentUserId, followUserId);
	}
	
	@GetMapping("/search")
	public List<User> searchUser(@RequestParam("query") String query){
		return userService.searchUser(query);
	}
}

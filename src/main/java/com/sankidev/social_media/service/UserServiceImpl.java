package com.sankidev.social_media.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankidev.social_media.models.User;
import com.sankidev.social_media.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		newUser.setGender(user.getGender());
		
		User savedUser = userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new Exception("User Not Found : "+userId);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User followUser(Integer currentUserId, Integer followUserId) throws Exception {
		User currentUser = findUserById(currentUserId);
		User followUser = findUserById(followUserId);
		
		followUser.getFollowers().add(currentUser.getId());
		currentUser.getFollowings().add(followUser.getId());
		
		userRepository.save(currentUser);
		userRepository.save(followUser);
		
		return currentUser;
		
	}

	@Override
	public User updateUser(Integer id, User user) throws Exception {
		Optional<User> dbUser = userRepository.findById(id);
		if(dbUser.isEmpty()) {
			throw new Exception("User Not found");
		}
	
		User oldUser = dbUser.get();
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		
		return userRepository.save(oldUser);
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}


}

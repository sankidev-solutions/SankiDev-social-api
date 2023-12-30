package com.sankidev.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sankidev.social_media.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	@Query("select user from User user where user.firstName like %:query% OR user.lastName like %:query% OR user.email like %:query%")
	public List<User> searchUser(@Param("query") String query);
}

package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

//Services
@Service
public class UserService {
	
	//Autowire user repository
	@Autowired
	private UserRepository userRepository; 
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Create User 
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	//Get User
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user; 	
	}
	
	//Update User by Id
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//delete user by id
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	//find by username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	

}

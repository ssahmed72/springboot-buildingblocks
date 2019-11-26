package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		if (userRepository.findByUsername(user.getUsername())==null)
			return userRepository.save(user);
		else throw new UserExistsException("User already exists: " + user.getUsername());
	}
	
	//Get User
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) throw new UserNotFoundException("User Not Found in User Repository");
		return user; 	
	}
	
	//Update User by Id
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException("User Not Found in User Repository With Id:" + id);
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	//delete user by id
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found in User Repository With Id:" + id);
		}
	}
	
	//find by username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	

}

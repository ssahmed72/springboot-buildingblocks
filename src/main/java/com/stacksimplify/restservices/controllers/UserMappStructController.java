package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.dtos.UserMmDto;
import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.repositories.UserRepository;

//Controller
@RestController
@RequestMapping(value = "/mapstructs/users")
public class UserMappStructController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserMsDto> getAllUserDtos(){
		return userMapper.usersToUserDtos(userRepository.findAll());
	}
	
	// getUserById
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(id);
		User user=userOptional.get();
		return userMapper.userToUserDto(user);
	}
	
	

}

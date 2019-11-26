package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/hateoas/users")
public class UserHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService; 
	
	@GetMapping
	public CollectionModel<User> getAllUsers(){
		List<User> allUsers=userService.getAllUsers();
		for(User user:allUsers) {
			Long userId=user.getId();
			Link selfLink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			//Relationship link with order
			try {
			CollectionModel<Order> orders= WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
			Link ordersLink=WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
			}catch(Exception e) {
				
			}
			
			
		}
		//self link for getallusers
		Link selfLinkGetAllUsers= WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalResource= new CollectionModel<User>(allUsers,selfLinkGetAllUsers);
		return finalResource;
	}
	
	//getUserById
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional= userService.getUserById(id);
			User user=userOptional.get();
			Long userId=user.getId();
			Link selfLink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalResource= new EntityModel<User>(user);
			return finalResource;
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); 
		}
	}
	

}

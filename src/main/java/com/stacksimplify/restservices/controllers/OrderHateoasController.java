package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	//get All Orders for a user
		@GetMapping("/{userid}/orders")
		public CollectionModel<Order>  getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException{
			Optional<User> userOptional = userRepository.findById(userid);
			if (!userOptional.isPresent())
				throw new UserNotFoundException("User Not Found");
			
			List<Order> allOrders=userOptional.get().getOrders();
			CollectionModel<Order> finalResource= new CollectionModel<Order>(allOrders);
			return finalResource;
			
			
			
		}

}

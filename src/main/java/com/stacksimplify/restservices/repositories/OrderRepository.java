package com.stacksimplify.restservices.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Optional<Order> findByorderidAndUser(Long orderid, User user);    

}

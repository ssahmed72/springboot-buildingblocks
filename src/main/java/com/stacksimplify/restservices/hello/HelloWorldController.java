package com.stacksimplify.restservices.hello;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//Simple Method
	//URI - /helloworld
	//GET
	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World1"; 
	}
	
	/*
	 * @Bean(name="bean1") public UserDetails userDetails() { return new
	 * UserDetails("Syed", "Ahmed", "Chicago2"); }
	 * 
	 * @Resource(name="bean1") UserDetails userDetails;
	 */
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Syed", "Ahmed", "Chicago"); 
		
	}

}

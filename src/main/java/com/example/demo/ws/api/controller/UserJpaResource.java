package com.example.demo.ws.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ws.api.Exception.UseNotFoundException;
import com.example.demo.ws.api.model.User;
import com.example.demo.ws.api.model.UserRepository;
import com.example.demo.ws.api.service.UserDaoService;


@RestController
@ControllerAdvice
public class UserJpaResource {

	@Autowired
	public UserDaoService userDaoService;
	
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping(path="/jpa/users")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public User findById(@PathVariable int id){
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UseNotFoundException("id :"+id+" not prsent in DB");
		}
		return user.get();
	}
	
	@DeleteMapping(path="/jpa/users/delete/{id}")
	public ResponseEntity<User> deleteById(@PathVariable int id){
	   userRepository.deleteById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@PostMapping(path="/jpa/users/import")
	ResponseEntity<User> customHeader(@Valid @RequestBody User user) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "foo");
	    User newdata=userRepository.save(user);
	    
	    if(newdata!= null) {
	    	return new ResponseEntity<User>(
	    		      headers, HttpStatus.OK);
	    }
		return new ResponseEntity<User>(
  		      headers, HttpStatus.BAD_REQUEST);
	}
}

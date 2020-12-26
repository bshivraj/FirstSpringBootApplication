package com.example.demo.ws.api.controller;

import java.util.List;

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
import com.example.demo.ws.api.service.UserDaoService;

@RestController
@ControllerAdvice
public class UserResource{
	
	@Autowired
	public UserDaoService userDaoService;
	
	@GetMapping(path="/users")
	public List<User> findAll(){
		return userDaoService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User findById(@PathVariable int id){
		User user=userDaoService.findOne(id);
		if(user==null) {
			throw new UseNotFoundException("id :"+id+" not prsent in DB");
		}
		return user;
	}
	
	@DeleteMapping(path="/users/{id}")
	public ResponseEntity<User> deleteById(@PathVariable int id){
		boolean user=userDaoService.delete(id);
		if(!user) {
			throw new UseNotFoundException("id :"+id+" not prsent in DB");
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@PostMapping(path="/users/import")
	ResponseEntity<User> customHeader(@Valid @RequestBody User user) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "foo");
	    
	    if(userDaoService.Save(user) != null) {
	    	return new ResponseEntity<User>(
	    		      headers, HttpStatus.OK);
	    }
		return new ResponseEntity<User>(
  		      headers, HttpStatus.BAD_REQUEST);
	}
}

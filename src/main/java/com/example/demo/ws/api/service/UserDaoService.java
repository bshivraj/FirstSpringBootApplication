package com.example.demo.ws.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.Response;

import org.springframework.stereotype.Component;

import com.example.demo.ws.api.model.User;

@Component
public class UserDaoService {

	private static List<User> users=new ArrayList<User>();
	
	private static int userId=3;
	
	static {
		 users.add(new User(1, "shiva", new Date()));
			users.add(new User(2, "madhu", new Date()));
			users.add(new User(3, "rachi", new Date()));
	}
	
   
	public List<User> findAll() {
		return users;
	}
	public User Save(User user) {
		if(user.getId()==null || user.getId()==0) {
			user.setId(userId+1);
		}
		users.add(user);
		return user;
		
	}
	
	public User findOne(int id) {
		List<User> list=users.stream().filter(x->x.getId()==id).collect(Collectors.toList());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	public boolean delete(int id) {
		User user=users.stream().filter(x->x.getId()==id).collect(Collectors.toList()).get(0);
		if(user!=null) {
			users.remove(user);
			return true;
		}
		return false;
	}
}



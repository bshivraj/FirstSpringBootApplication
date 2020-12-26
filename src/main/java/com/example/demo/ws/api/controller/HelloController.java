package com.example.demo.ws.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(method =RequestMethod.GET,path="/hello")
	public String helloSpring() {
		return "Hello GoodStart";
		
	}
	
	@RequestMapping(method =RequestMethod.GET,path="/hello-bean")
	public HelloWorldBean getBeans() {
		return new HelloWorldBean("Hi welcome");
	}
	
	@RequestMapping(method =RequestMethod.GET,path="/hello/{name}")
	public String getPathParam(@PathVariable String name) {
		return "Hi welcome "+name; 
	}
}

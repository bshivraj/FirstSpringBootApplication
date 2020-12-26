package com.example.demo.ws.api.controller;

public class HelloWorldBean {
	
	private String msg;
	
	HelloWorldBean(String msg){
		this.msg=msg;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [msg=" + msg + "]";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

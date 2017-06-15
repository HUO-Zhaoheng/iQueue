package com.iQueue.model;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String userName;
	private String password;
	private String status;
	public void setUserName(String name) {
		this.userName = name;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String psd) {
		this.password = psd;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}

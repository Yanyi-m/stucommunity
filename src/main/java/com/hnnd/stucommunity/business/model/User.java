package com.hnnd.stucommunity.business.model;


public class User {
	private Integer userId;
	private String username;
	private String password;
	private Integer blackUser=0;
	private Integer authority=0;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBlackUser() {
		return blackUser;
	}
	public void setBlackUser(Integer blackUser) {
		this.blackUser = blackUser;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	
}

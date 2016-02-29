package com.hnnd.stucommunity.business.model;


public class User {
	private int userId;
	private String username;
	private String password;
	private int blackUser;
	private int authority;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	public int getBlackUser() {
		return blackUser;
	}
	public void setBlackUser(int blackUser) {
		this.blackUser = blackUser;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	
}

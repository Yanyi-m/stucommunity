package com.hnnd.stucommunity.business.model;

import java.util.Date;

public class Community {
	private int id;
	private String name;
	private int creator;
	private Date createTime;
	private int blackcommunity=0;
	private int level=1;
	private String introduction;
	private String picture="image/test.jpg";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getBlackcommunity() {
		return blackcommunity;
	}
	public void setBlackcommunity(int blackcommunity) {
		this.blackcommunity = blackcommunity;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}

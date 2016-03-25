package com.hnnd.stucommunity.business.model;

public class TagRelation {
	private int id;
	private int tagId;
	private int objId;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public int getType(){
		return type;
	}
	public void setType(int type){
		this.type=type;
	}
}

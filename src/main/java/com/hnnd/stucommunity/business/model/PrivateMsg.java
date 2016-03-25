package com.hnnd.stucommunity.business.model;

public class PrivateMsg {
	private Integer id;
	private Integer senderId;
	private Integer recipientId;
	private	Integer dialogId;
	private Integer msgId;
	private Integer isRead;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}
	public Integer getDialogId() {
		return dialogId;
	}
	public void setDialogId(Integer dialogId) {
		this.dialogId = dialogId;
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}

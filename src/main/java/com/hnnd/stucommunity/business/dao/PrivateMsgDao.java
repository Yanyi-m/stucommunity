package com.hnnd.stucommunity.business.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hnnd.stucommunity.business.model.PrivateMsg;

@Repository
public class PrivateMsgDao {


	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * 得到新生成的dialogId
	 * @return
	 */
	public Integer saveDialogId(){
		String sql="insert into dialog values ()";
		
		Integer dialogId= jdbcTemplate.update(sql);
		
		return dialogId;
	}
	
	/**
	 * 得到messageId
	 * @param message
	 * @return
	 */
	public Integer saveMessage(String message){
		String sql="insert into message_text (message_text) values (?)";
		
		Integer messageId=jdbcTemplate.update(sql,message);
		
		return messageId;
	}
	
	/**
	 * 插入私信表，完成一条私信记录的产生
	 * @param privateMsg
	 * @return
	 */
	public Integer savePrivateMsg(PrivateMsg privateMsg){
		String sql="insert into privatemessage (sender_uid,recipient_uid,dialog_id,message_text_id,is_read) values (senderId,:recipientId,:dialogId,:msgId,:isRead)";
		
		KeyHolder keyholder = new GeneratedKeyHolder();
		SqlParameterSource sps = new BeanPropertySqlParameterSource(privateMsg);
		namedParameterJdbcTemplate.update(sql,sps,keyholder);
		
		return keyholder.getKey().intValue();
	}
	
}

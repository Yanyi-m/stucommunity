package com.hnnd.stucommunity.business.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hnnd.stucommunity.business.model.User;
import com.hnnd.stucommunity.business.model.UserInformation;
import com.hnnd.stucommunity.common.encrypt.MD5;

@Repository
public class LoginDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	protected Logger logger=LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 创建账号。
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public int saveUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		  	String password = MD5.EncoderByMd5(user.getPassword());

	        user.setPassword(password + user.getUsername());
		
			String sql="insert into user (username,password,blackuser,authority) values (:username,:password,:blackUser,:authority) ";
			
			KeyHolder keyholder = new GeneratedKeyHolder();
			SqlParameterSource sps = new BeanPropertySqlParameterSource(user);
			namedParameterJdbcTemplate.update(sql, sps, keyholder);
			
	        return keyholder.getKey().intValue();
	}
	
	
	/**
	 * 插入用户个人信息。
	 * @param user
	 * @return
	 */
	public boolean saveUserInformation(UserInformation userinfo) {

        String sql = "insert into user_information (user_id,nickname,user_introduction"
        		+ ",user_picture,identity,stu_id,university,college,specialty,mobile,email,jiondate) values "
        		+ "(:userId,:nickName,:userIntroduction,:userPicture,:identity,:stuId,:university,"
        		+ ":college,:specialty,:mobile,:email,:jionDate)";
        
        KeyHolder keyholder = new GeneratedKeyHolder();
		SqlParameterSource sps = new BeanPropertySqlParameterSource(userinfo);
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
        
		return true;
    }
	
	/**
	 * 查找用户是否存在
	 * @param email
	 * @return
	 */
	public User findUser(String username){
		
		String sql="select * from user where username=?";
		
		List<User> list=jdbcTemplate.query(sql,new UserMapper(),username);
		if(list.size()==0){
			User user=new User();
			return user;
		}
		User user=list.get(0);
		return user;
	}
	
	
	private class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user=new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setBlackUser(rs.getInt("blackuser"));
            user.setAuthority(rs.getInt("authority"));
			return  user;
		}
		
	}
}

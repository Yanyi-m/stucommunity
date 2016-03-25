package com.hnnd.stucommunity.business.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	 * 根据userId获取用户信息
	 * @param username
	 * @return
	 */
	public UserInformation getUserInformationByUserId(Integer userId){
		String sql="select * from user_information where user_id=?"; 
		
		List<UserInformation> list=jdbcTemplate.query(sql,new UserInformationMapper(),userId);
		UserInformation userInformation;
		if(list.size()==0){
			userInformation=new UserInformation();
			return userInformation;
		}
		userInformation=list.get(0);
		
		return userInformation;
	}
	
	
	/**
	 * 查找用户是否存在
	 * @param email
	 * @return
	 */
	public User getUser(String username){
		
		String sql="select * from user where username=?";
		
		List<User> list=jdbcTemplate.query(sql,new UserMapper(),username);
		if(list.size()==0){
			User user=new User();
			return user;
		}
		User user=list.get(0);
		return user;
	}
	
	/**
	 * 得到用户信息通过昵称
	 * 
	 * @param name
	 * @return
	 */
	public List<UserInformation> getUserInformationByName(String name){
		String sql="select * from user_information where nickname like ?";
		
		String keyword="%"+name+"%";
		
		List<UserInformation> list=jdbcTemplate.query(sql,new UserInformationMapper(),keyword);
		
		if(list.size()==0){
			list=new ArrayList<UserInformation>();
			return list;
		}
		
		return list;
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
	
	private class UserInformationMapper implements RowMapper<UserInformation>{

		@Override
		public UserInformation mapRow(ResultSet rs, int rowNum) throws SQLException{
			UserInformation userInformation=new UserInformation();
			userInformation.setUserId(rs.getInt("user_id"));
			userInformation.setNickName(rs.getString("nickname"));
			userInformation.setUserIntroduction(rs.getString("user_introduction"));
			userInformation.setUserPicture(rs.getString("user_picture"));
			userInformation.setCollege(rs.getString("college"));
			userInformation.setIdentity(rs.getInt("identity"));
			userInformation.setStuId(rs.getInt("stu_Id"));
			userInformation.setUniversity(rs.getString("university"));
			userInformation.setSpecialty(rs.getString("specialty"));
			userInformation.setEmail(rs.getString("email"));
			userInformation.setMobile(rs.getInt("mobile"));
			userInformation.setJionDate(rs.getDate("jiondate"));
		
			return userInformation;
		}
		
	}
}

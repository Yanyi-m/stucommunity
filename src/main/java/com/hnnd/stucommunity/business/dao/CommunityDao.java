package com.hnnd.stucommunity.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hnnd.stucommunity.business.model.Community;

@Repository
public class CommunityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * 创建社团
	 * @param community
	 * @return
	 */
	public int saveCommunity(Community community){
		
		String sql="insert into community (community_id,community_name,community_creator,"
				+ "community_create_time,blackcommunity,level,community_introduction,"
				+ "community_picture) values (:id,:name,:creator,:createTime,:blackCommunity,:level,:introduction,:picture)";
		
		KeyHolder keyholder = new GeneratedKeyHolder();
		SqlParameterSource sps = new BeanPropertySqlParameterSource(community);
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		
        return keyholder.getKey().intValue();
	}
	
	/**
	 * 用户加入社团
	 * @param userId
	 * @param communityId
	 * @return
	 */
	public int saveCommunityMember(Integer userId,Integer communityId){
		
		String sql="insert into community_user (user_id,community_id) values (?,?)";
		
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql,userId,communityId,keyholder);
		
		return keyholder.getKey().intValue();
		
	}
	
	
	/**
	 * 得到该社团的所有成员Id
	 * @param communityId
	 * @return
	 */
	public List<Integer> getCommunityMember(Integer communityId){
		
		String sql="select distinct * from community_user where community_id=?";
		
		List<Integer> list=jdbcTemplate.query(sql,new CommunityMemberMapper() ,communityId);
		
		if(list.size()==0){
			list=new ArrayList<Integer>();
			return list;
		}
		return list;
	}
	
	
	private class CommunityMemberMapper implements RowMapper<Integer>{
	
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer userId;
			userId=rs.getInt("user_id");
		
			return userId;
		}
	}
	
}

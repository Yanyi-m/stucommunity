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

import com.hnnd.stucommunity.business.model.Activity;

@Repository
public class ActivityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	/**
	 * 创建活动
	 * @param activity
	 * @return
	 */
	public int saveActivity(Activity activity){
		
		String sql="insert into activity (community_Id,activity_creator,"
				+ "activity_start_date,activity_end_date,activity_introduce,"
				+ "activity_name) values (:communityId,:creator,:startTime,:endTime,:introduce,:name)";
		
		KeyHolder keyholder = new GeneratedKeyHolder();
		SqlParameterSource sps = new BeanPropertySqlParameterSource(activity);
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		
        return keyholder.getKey().intValue();
	}
	
	/**
	 * 通过社团id,得到活动id
	 * @param communityId
	 * @return
	 */
	public List<Integer> getActivityByCommunityId(Integer communityId){
		String sql="select activity_id from activity where community_id=?";
		
		List<Integer> list=jdbcTemplate.query(sql,new ActivityMapper() ,communityId);
		if(list.size()==0){
			list=new ArrayList<Integer>();
			return list;
		}
		
	
		return list;
	}
	
	private class ActivityMapper implements RowMapper<Integer>{

		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer activityId;
			activityId=rs.getInt("activity_Id");
			return activityId;
		}
		
	}
}

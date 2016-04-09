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

import com.hnnd.stucommunity.business.model.Tag;
import com.hnnd.stucommunity.business.model.TagRelation;


@Repository
public class TagDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	/**
	 * 插入Tag
	 * @param tag
	 * @return
	 */
	public int saveTag(Tag tag){
		
		String sql="insert into tag (tag_name) values (:name)";
		

		KeyHolder keyholder = new GeneratedKeyHolder();
		SqlParameterSource sps = new BeanPropertySqlParameterSource(tag);
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		
        return keyholder.getKey().intValue();
		
	}
	
	/**
	 * 插入Tag关系
	 * @param tagRelation
	 * @return
	 */
	public int saveTagRelation(TagRelation tagRelation){
		
		String sql="insert into tag_relation (tag_id,obj_id) values (:tagId,:objId)";
	    
		KeyHolder keyholder = new GeneratedKeyHolder();
		SqlParameterSource sps = new BeanPropertySqlParameterSource(tagRelation);
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		
        return keyholder.getKey().intValue();
	}
	
	/**
	 * 查找所有Tag
	 * @return
	 */
	public List<Tag> getTag(){
		String sql="select * from Tag";
		
		List<Tag> list = jdbcTemplate.query(sql,new TagMapper());
		if(list.size()==0){
			list=new ArrayList<Tag>();
			return list;
		}
		return list;
	}
	
	private class TagMapper implements RowMapper<Tag>{

		@Override
		public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag=new Tag();
            tag.setId(rs.getInt("tag_id"));
            tag.setName(rs.getString("tag_name"));
           
			return  tag;
		}
		
	}
	
	
	/**
	 * 根据id,查找obj所拥有的标签
	 * @param tagRelation
	 * @return
	 */
	public List<TagRelation> getTagByObjId(TagRelation tagRelation){
		
		String sql="select distinct * from tag_relation where obj_id=? and type=? ";
	    
		List<TagRelation> list=jdbcTemplate.query(sql,new TagRelationMapper(),tagRelation);
		if(list.size()==0){
			list=new ArrayList<TagRelation>();
			return list;
		}
		return list;
		
	}
	
	private  class TagRelationMapper implements RowMapper<TagRelation>{
	
		@Override
		public TagRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
			TagRelation tagRelation=new TagRelation();
			tagRelation.setTagId(rs.getInt("tag_id"));
			tagRelation.setType(rs.getInt("type"));
			tagRelation.setObjId(rs.getInt("obj_Id"));
			
			return tagRelation;
		}
	}

	
}

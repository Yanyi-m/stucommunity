package com.hnnd.stucommunity.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnnd.stucommunity.business.dao.TagDao;
import com.hnnd.stucommunity.business.model.Tag;
import com.hnnd.stucommunity.business.model.TagRelation;
import com.hnnd.stucommunity.common.base.ResultModel;

@Service
public class TagService {
	@Autowired
	TagDao tagDao;
	
	/**
	 * 从请求中得到tagRelation参数值,保存用户或者社团的标签属性
	 * @param request
	 * @return
	 */
	public ResultModel saveTagRelation(HttpServletRequest request){
		Integer objId=Integer.valueOf(request.getParameter("objId"));
		Integer tagId=Integer.valueOf(request.getParameter("tagId"));
		Integer type=Integer.valueOf(request.getParameter("type"));
		
		TagRelation tagRelation=new TagRelation();
		tagRelation.setObjId(objId);
		tagRelation.setType(type);
		
		//将tag关系插入数据库
		Integer id=tagDao.saveTagRelation(tagRelation);
		if(id!=null || id.equals("")){
			return ResultModel.successModel();
		}
		return ResultModel.failModel();
	}
	
	
	/**
	 * 得到tag
	 * @return
	 */
	public ResultModel getTag(){
		List<Tag> list=tagDao.getTag();
		if(list.size()!=0){
			return ResultModel.successModel(list);
		}
		return ResultModel.failModel();
	}
}

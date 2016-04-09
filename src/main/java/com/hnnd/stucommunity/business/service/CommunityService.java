package com.hnnd.stucommunity.business.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnnd.stucommunity.business.dao.CommunityDao;
import com.hnnd.stucommunity.business.model.Community;
import com.hnnd.stucommunity.common.base.ResultModel;

@Service
public class CommunityService {

	@Autowired
	CommunityDao communityDao;
	
	/**
	 * 创建社团
	 * @param request
	 * @return
	 */
	public ResultModel saveCommunity(HttpServletRequest request){
		Community community=new Community();
		community.setCreator(Integer.valueOf(request.getParameter("userId")));
		community.setCreateTime(new Date());
		community.setName(request.getParameter("name"));
		community.setIntroduction(request.getParameter("introduction"));
	
		Integer communityId=communityDao.saveCommunity(community);
		return ResultModel.successModel();
	}
	
}

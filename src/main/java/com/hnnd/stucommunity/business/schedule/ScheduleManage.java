package com.hnnd.stucommunity.business.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hnnd.stucommunity.business.service.LoginService;



@Component
public class ScheduleManage {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleManage.class);
	
	@Autowired
	LoginService loginService;
	
	/**
	 *每天24点定时清理timesMap 
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void clearTimesMap(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowTime = new Date(System.currentTimeMillis());
		String time=sd.format(nowTime);
		LOGGER.info("timesMap start clear! time:{}",time);
		loginService.clearTimesMap();
	}
}

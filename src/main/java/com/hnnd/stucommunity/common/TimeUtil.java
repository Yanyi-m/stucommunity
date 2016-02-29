package com.hnnd.stucommunity.common;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {
	
	public static Date getNowTime(){
		Date nowTime = new Date(System.currentTimeMillis());
		
		return nowTime;
	}
	
	
	
	public static boolean isValid(Long oldTime,Long nowTime,int value) throws ParseException{
		
		
		long diff=nowTime-oldTime;
		int i=value*1000;
		if(diff>i){
			return true;
		}
		return  false;
	}
	
	public static Date parseStringToDate(String time) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		Date date=sdf.parse(time);
		return date;
	}
}

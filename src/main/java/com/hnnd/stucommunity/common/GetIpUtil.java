package com.hnnd.stucommunity.common;

import javax.servlet.http.HttpServletRequest;

public class GetIpUtil {
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equals(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equals(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equals(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip==null?"":ip;
	}
}

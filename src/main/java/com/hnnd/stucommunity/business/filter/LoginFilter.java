package com.hnnd.stucommunity.business.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.hnnd.stucommunity.config.SecurityConfig;

@Component
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		String url=req.getRequestURI().toString();
		
		HttpSession session=req.getSession();
		Integer userId=(Integer) session.getAttribute("userId");
		Set<String> set=SecurityConfig.getAllowUrl();
		
		if(userId!=null || set.contains(url)){
			chain.doFilter(request, response);	
		}else{
			resp.sendRedirect("login.html");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

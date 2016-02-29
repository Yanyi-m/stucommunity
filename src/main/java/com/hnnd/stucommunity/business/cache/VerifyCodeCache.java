package com.hnnd.stucommunity.business.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;





@Component
public class VerifyCodeCache implements InitializingBean{
	 	@Autowired
	  	CacheManager cacheManager;

	    private Cache cache;

	    public void put(String emailRecipients,String verifyCode){
	        cache.put(emailRecipients,verifyCode);
	    }


	    public String get(String emailRecipients){
	        return cache.get(emailRecipients,String.class);
	    }

	    @Override
	    public void afterPropertiesSet() throws Exception {
	        cache = cacheManager.getCache("verifyCodeCache");
	    }
}

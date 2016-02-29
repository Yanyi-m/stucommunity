package com.hnnd.stucommunity.common;

import java.util.concurrent.ThreadLocalRandom;

public class RandomCodeHelper {

   
    /**
     * 随机生成一串指定长度的数字字母混合的字符串
     */
    public static String randomNumCode(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
        	int j=ThreadLocalRandom.current().nextInt(0,3);
        	if(j==0){
        		builder.append( ThreadLocalRandom.current().nextInt(0,10));
        	}else if(j==1){
        		 builder.append((char)ThreadLocalRandom.current().nextInt(65,90));
        	}else { 
                builder.append((char)ThreadLocalRandom.current().nextInt(97,122));
        	}  
        }
        String code = builder.toString();
        return code;
    }
    
    
    /*public static void main(String[] args) {
		RandomCodeHelper r=new RandomCodeHelper();
		String t=r.randomNumCode(5);
		System.out.println(t);
	}*/
}

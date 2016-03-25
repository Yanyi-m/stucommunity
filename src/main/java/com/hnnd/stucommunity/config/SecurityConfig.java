package com.hnnd.stucommunity.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityConfig {
	
	private static Set<String> whiteList;
	
	private static Set<String> allowUrl;
	
	final static String lists[]={"whiteList","allowUrl"};
	
	public static void init() throws IOException {
		setType();
		
	}
	
	public static void setType() throws IOException{
		Set<String> set = new HashSet<>();
		
		for(String list:lists){
		InputStream in;
		File file =new File("./"+list+".txt");
		if(file.exists()){
			in=new FileInputStream(file);
		}else{
			in=SecurityConfig.class.getClassLoader().getResourceAsStream("config/"+list+".txt");
		}
		InputStreamReader reader=new InputStreamReader(in);
		BufferedReader bufferedreader=new BufferedReader(reader);
		
		String str=null;
		while((str=bufferedreader.readLine())!=null){
			set.add(str);
		}
		if(list.equals("whiteList")){
			whiteList=set;	
		}else{
			allowUrl=set;
		}
		}
	}
	
	
	public static Set<String> getWhiteList(){
        return whiteList;
    }
	
	public static Set<String> getAllowUrl(){
		return allowUrl;
	}
}

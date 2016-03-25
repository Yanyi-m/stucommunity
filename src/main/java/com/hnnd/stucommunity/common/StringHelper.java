package com.hnnd.stucommunity.common;

import java.util.HashMap;
import java.util.Map;

public class StringHelper {
	public static void test() {
	    String str = "abc,ddc";
	    String str2 = "adc,cdb";
	    System.out.println("the result is " + fun(str, str2));
	}
	 
	public static boolean fun(String str, String str2) {
	    if (str == null || str2 == null) {
	        return false;
	    }
	    if (str.length() != str2.length()) {
	        return false;
	    }
	    Map<Character, Integer> map = new HashMap<Character, Integer>();
	    char[] chars = str.toCharArray();
	    for (char ch : chars) {
	        if (map.get(ch) == null) {
	            map.put(ch, 1);
	        } else {
	            map.put(ch, map.get(ch) + 1);
	        }
	    }
	     
	    Map<Character, Integer> tempMap = map;
	    for (char ch : str2.toCharArray()) {
	        if (map.get(ch) == null) {
	            return false;
	        }
	        if (map.get(ch) == 1) {
	            tempMap.remove(ch);
	        } else {
	            tempMap.put(ch, tempMap.get(ch) - 1);
	        }
	    }
	    return tempMap.size() == 0;
	}
	
	public static void main(String[] args) {
		test();
	}
}

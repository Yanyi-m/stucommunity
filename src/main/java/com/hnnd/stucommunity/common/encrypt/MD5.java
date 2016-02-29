package com.hnnd.stucommunity.common.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5 {
	
	/**
     * md5加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
       
        //加密后的字符串
        String newstr = Hex.encodeHexString(md5.digest(str.getBytes("utf-8")));
        return newstr;
       
    }
}

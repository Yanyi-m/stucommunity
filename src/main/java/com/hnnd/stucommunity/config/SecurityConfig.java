package com.hnnd.stucommunity.config;


import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SecurityConfig {

    //ip白名单
    private static Set<String> whiteList;


    public static void init() throws IOException {

        Set<String> set = new HashSet<>();

        InputStream in;

        File file = new File("./config/whitelist.txt");
        if(file.exists()){
            in = new FileInputStream(file);
        }else {
            in = SecurityConfig.class.getClassLoader().getResourceAsStream("config/whitelist.txt");
        }

        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        for(String str = bufferedReader.readLine();
                str != null;
                str = bufferedReader.readLine()){
            set.add(str);
        }

        whiteList = set;

        in.close();
    }

    public static Set<String> getWhiteList(){
        return whiteList;
    }

}

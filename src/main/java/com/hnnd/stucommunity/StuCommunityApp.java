package com.hnnd.stucommunity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hnnd.stucommunity.config.DbConfig;
import com.hnnd.stucommunity.config.SecurityConfig;
import com.hnnd.stucommunity.config.WebConfig;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
public class StuCommunityApp {

	private static Logger logger = LoggerFactory.getLogger(StuCommunityApp.class);

    public static void main(String[] args) throws Exception {

      

        logger.info("Config加载完毕~");

        SpringApplication.run(StuCommunityApp.class, args);
    }
    

    public static void initConfig() throws Exception{
        WebConfig.init();
        SecurityConfig.init();
        DbConfig.init();
    }
}

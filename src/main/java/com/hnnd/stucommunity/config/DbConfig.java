package com.hnnd.stucommunity.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class DbConfig {

    public static String jdbcUrl;
    public static String userName;
    public static String password;
    public static String driverClass;
    public static int maxActive = 50;

    public static String jdbcUrl2;
    public static String userName2;
    public static String password2;
    public static String driverClass2;


    public static void init() throws IOException {
        InputStream in;
        File file = new File("./config/db.properties");
        if(file.exists()){

            in = new FileInputStream(file);
        }else {
            in = SecurityConfig.class.getClassLoader().getResourceAsStream("config/db.properties");
        }

        Properties properties = new Properties();
        properties.load(in);
        in.close();


        jdbcUrl = properties.getProperty("url");
        userName = properties.getProperty("username");
        password = properties.getProperty("password");
        driverClass = properties.getProperty("driverClass");
        if(properties.getProperty("maxActive") != null){
            maxActive = Integer.parseInt( properties.getProperty("maxActive") );
        }



//        jdbcUrl2 = properties.getProperty("url2");
//        userName2 = properties.getProperty("username2");
//        password2 = properties.getProperty("password2");
//        driverClass2 = properties.getProperty("driverClass2");
    }


    //==================================
    //第一数据源
    //==================================
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DbConfig.driverClass);
        dataSource.setUrl(DbConfig.jdbcUrl);
        dataSource.setUsername(DbConfig.userName);
        dataSource.setPassword(DbConfig.password);
        dataSource.setMaxActive(DbConfig.maxActive);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return namedParameterJdbcTemplate;
    }



    //==================================
    //第二数据源
    //==================================
//    @Bean
//    public DataSource dataSource2(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(DbConfig.driverClass2);
//        dataSource.setUrl(DbConfig.jdbcUrl2);
//        dataSource.setUsername(DbConfig.userName2);
//        dataSource.setPassword(DbConfig.password2);
//
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate2(){
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource2());
//        return jdbcTemplate;
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate2(){
//        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource2());
//        return namedParameterJdbcTemplate;
//    }


    //德鲁伊自带的监控
   /* @Bean
    public ServletRegistrationBean druidStatRegistration(){
        HttpServlet servlet = new StatViewServlet();
        ServletRegistrationBean registration = new ServletRegistrationBean(servlet);
        registration.addUrlMappings("/druid/*");
        registration.addInitParameter("loginUsername","admin");
        registration.addInitParameter("loginPassword","hlj_passw@rd");
        //registration.addInitParameter("allow","192.168.0.1/16");
        return registration;
    }
    */

}

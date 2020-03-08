package com.spring.mvc;

import com.spring.annotation.Component;
import com.spring.annotation.Value;

@Component
public class UserDao {
	//根据注解,从配置文件获取配置数据,并为属性赋值
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver}")
	private String driver;
	
	public void test() {
		System.out.println("UserDao的属性----------");
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(driver);
	}
}

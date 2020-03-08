package com.spring.mvc;

import com.spring.annotation.Autowired;
import com.spring.annotation.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void test() {
		System.out.println("业务对象---------------");
		System.out.println("userDao"+userDao);
	}
}

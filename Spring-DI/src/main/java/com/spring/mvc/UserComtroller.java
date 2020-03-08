package com.spring.mvc;

import com.spring.annotation.Autowired;
import com.spring.annotation.Controller;

@Controller
public class UserComtroller {
	@Autowired
	private UserService userService;
	
	public void test() {
		System.out.println("控制器对象-------------");
		System.out.println("userService"+userService);
	}
}

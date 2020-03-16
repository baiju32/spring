package com.spring.test;

import java.lang.reflect.Field;

import com.spring.annotation.Value;
import com.spring.config.Configure;
import com.spring.mvc.UserDao;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Configure.load();
		//新建UserDao实例,并为属性赋值
		UserDao userDao = new UserDao();
		Class<UserDao> class1=UserDao.class;
		//获取所有的成员变量
		Field[] fields = class1.getDeclaredFields();
		//遍历变量
		for (Field field : fields) {
			if (field.isAnnotationPresent(Value.class)) {
				Value value = field.getDeclaredAnnotation(Value.class);
				String ognl=value.ognl();
				if ("".equals(ognl)) {
					ognl=value.value();
				}
				//从配置文件,获取配置数据
				String v = Configure.get(ognl);
				//私有变量设置可以访问
				field.setAccessible(true);
				//把取出的配置数据,保存到field变量中
				field.set(userDao, v);
			}
		}
		userDao.test();
	}
}

package com.spring.factory;
/**
 * spring容器,用来自动创建实例,并把实例存入一个集合
 */

import java.io.File;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.Controller;
import com.spring.annotation.Service;
import com.spring.annotation.Value;
import com.spring.config.Configure;
import com.spring.mvc.UserComtroller;
import com.spring.mvc.UserDao;
import com.spring.mvc.UserService;

public class SpringContext {
	private Map<String, Object> map=new HashMap<>();

	//自动在bin目录下,扫描所有的class文件,并创建实例,放入map集合
	public void autoScan() throws Exception {
		String path = SpringContext.class.getResource("/").getPath();
		path=URLDecoder.decode(path,"UTF-8");
		StringBuilder pkg=new StringBuilder();
		//扫描目录
		scan(new File(path),pkg);
		//自动注入,遍历所有的实例,把实例需要的对象或数据注入
		autowire();
	}

	private void autowire() throws Exception {
		Collection<Object> values = map.values();
		for (Object object : values) {
			Class<?> cls = object.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Value.class)) {//注入数据
					injectValue(cls,object,field);
				}else if (field.isAnnotationPresent(Autowired.class)) {//注入对象
					injectObject(cls,object,field);
				}
			}
		}
	}

	private void injectObject(Class<?> cls, Object object, Field field) throws Exception {
		Class<?> targetClass = field.getType();
		Object targetobj= getObject(targetClass);
		field.setAccessible(true);
		field.set(object, targetobj);
	}

	private void injectValue(Class<?> cls, Object object, Field field) throws Exception {
		Value value = field.getDeclaredAnnotation(Value.class);
		String ognl=value.ognl();
		if (ognl.equals("")) ognl=value.value();
		String v = Configure.get(ognl);
		field.setAccessible(true);
		field.set(object, v);
	}

	private void scan(File dir,StringBuilder pkg) {
		File[] files = dir.listFiles();
		if (files==null) return;
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {//file是文件
				//利用反射创建实例
				if (fileName.endsWith(".class")) {
					String className=pkg+"."+fileName.substring(0, fileName.length()-6);
					try {
						Class<?> cls= Class.forName(className);
						//根据类上的注解(Controller,Service,Component),来创建实例并存储到map
						newInstance(cls);
					} catch (Exception e) {
						System.out.println(className+"不能创建实例!");
					}
				}
			}else {//file是目录
				//如果pkg是空串直接拼接目录名,如果有内容先平拼点在拼目录名
				if (pkg.length()!=0) pkg.append(".");
				pkg.append(fileName);
				//递归调用
				scan(file,pkg);
				//子目录处理完毕
				//如果有点,把最后一个点位置到末尾删除,否则清空
				int index = pkg.lastIndexOf(".");//获取最后一个点的位置
				if (index==-1) {
					pkg.delete(0, pkg.length());//清空
				}else {
					pkg.delete(index, pkg.length());//把最后一个点位置到末尾删除
				}
			}
		}
		
	}

	private void newInstance(Class<?> cls) throws Exception {
		if (cls.isAnnotationPresent(Controller.class)||
				cls.isAnnotationPresent(Service.class)||
				cls.isAnnotationPresent(Component.class)) {
			Object obj =cls.newInstance();
			map.put(cls.getName(), obj);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(Class<T> cls){
		String className = cls.getName();
		return (T) map.get(className);
	}



	public static void main(String[] args) throws Exception {
		Configure.load();
		SpringContext springContext = new SpringContext();
		springContext.autoScan();
	//	System.out.println(springContext.map);
		UserDao userDao = springContext.getObject(UserDao.class);
		userDao.test();
		UserService userService = springContext.getObject(UserService.class);
		userService.test();
		UserComtroller controller = springContext.getObject(UserComtroller.class);
		controller.test();
	}
}

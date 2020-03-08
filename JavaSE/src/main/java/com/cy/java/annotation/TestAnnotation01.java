package com.cy.java.annotation;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface ComponentScan{
	String value() default ""; 
}

@ComponentScan("com.cy.java.cache")
class AppConfig{
}

public class TestAnnotation01 {
	public static void main(String[] args) {
		//1.获取AppConfig类的字节码对象
		Class<?> c=AppConfig.class;
		//2.获取AppConfig类上的@ComponentScan注解
		ComponentScan comp = c.getAnnotation(ComponentScan.class);
		if (comp==null) return;
		//3.获取@ComponentScan注解中的value属性的值
		String path = comp.value();
		if ("".equals(path)) 
			path=c.getPackage().getName();
		
		//4.输出@ComponentScan注解对呀value属性指定的包中所有的类
		//4.1将包结构中的"."替换为"/"
		path=path.replace(".", "/");
		//4.2基于ClassLoader对象获取指定目录对应的绝对路径
		URL url = ClassLoader.getSystemResource(path);
		System.out.println(url.getPath());
		//4.3基于File对象获取绝对路基中的Class文件
		File file = new File(url.getPath());
		File[] fileList = file.listFiles();
		for (File f : fileList) {
			System.out.println(f.getName());
		}
	}
}

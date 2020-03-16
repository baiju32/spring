package com.cy.java.oop.cls;

import java.util.Arrays;

//跟踪类的加载过程
//jvm参数配置:-XX:+TraceClassLoading
public class TestClassObject01 {
	public static void main(String[] args) throws Exception {
		System.out.println(
				"TestClassObject01.args="+Arrays.toString(args));
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		System.out.println(loader);//AppClassLoader
		
		//获取TestClassObject01的字节码对象
		Class<?> c1=TestClassObject01.class;
		Class<?> c2=Class.forName("com.cy.java.oop.cls.TestClassObject01");
		System.out.println(c1==c2);//true
		
		TestClassObject01 instance = new TestClassObject01();
		Class<?> c3 = instance.getClass();
		System.out.println(c2==c3);//true
		
	}
}

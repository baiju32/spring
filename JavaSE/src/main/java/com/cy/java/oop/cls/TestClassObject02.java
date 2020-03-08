package com.cy.java.oop.cls;


public class TestClassObject02 {
	public static void main(String[] args) throws Exception {
		
	//	Class<?> c1 = Object.class;
		//获取类加载器对象
		ClassLoader appLoader = ClassLoader.getSystemClassLoader();
		System.out.println(appLoader);
		
		ClassLoader extLoader = appLoader.getParent();
		System.out.println(extLoader);
		
		ClassLoader bootLoader = extLoader.getParent();
		System.out.println(bootLoader);
	}
}

package com.cy.java.oop.cls;


public class TestClassObject03 {
	public static void main(String[] args) throws Exception {
		
		Class<?> c1 = ClassAA.class;//不执行静态代码块
		
		Class<?> c2 = Class.forName(
				"com.cy.java.oop.cls.ClassAA");//执行静态代码块
		
		Class<?> c3 = Class.forName(
				"com.cy.java.oop.cls.ClassAA", false, ClassLoader.getSystemClassLoader());//不执行静态代码块
		System.out.println(c1);		
		System.out.println(c2);		
		System.out.println(c3);		
	}
}

class ClassAA{
	static {
		System.out.println("static{}");
	}
}

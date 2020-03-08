package com.cy.java.oop.cls;

class Outer{
	class Inner{
		
	}
}

public class TestClassFAQ01 {
	public static void main(String[] args) throws Exception {
	//	Class<?> c1 = Class.forName("com.cy.java.oop.faq.Outer.Inner");
		Class<?> c1 = Class.forName("com.cy.java.oop.faq.Outer$Inner");
		
		System.out.println(c1);
		
	}
}

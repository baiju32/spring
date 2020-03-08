package com.cy.java.oop.cls;


class ClassF{
	static {
		System.out.println("static{}.b="+ClassF.b);
	}
//	static int b=10;//b=0
	static final int b=10;//b=10
}
public class TestClassObject09 {
	public static void main(String[] args) throws Exception {
		Class.forName("com.cy.java.oop.cls.ClassF");
	}
	
}



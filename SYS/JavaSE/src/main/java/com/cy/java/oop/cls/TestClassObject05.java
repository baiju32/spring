package com.cy.java.oop.cls;

class ClassA5{
	static class Inner{
		static int[] array=new int[1024];
	}
	public static void show() {	}
	public static int length() {
		//通过内部类实现对array对象的延迟加载
		return Inner.array.length;
	}
}

public class TestClassObject05 {
	public static void main(String[] args) throws Exception {
		//调用类的方法时会导致类的加载
		//ClassA5.show();
		
		ClassA5.length();
		
		
	}
	
}



package com.cy.java.oop.cls;
/**
 * 静态属性和静态代码块的初始化顺序有定义决定
 * @author lll
 *
 */
class ClassD{
	static ClassD instanceClassD=new ClassD();
	public ClassD() {
		System.out.println("ClassD()");
	}
	static {
		System.out.println("static{}");
	}
	public static void show() {}
	{//实例代码块(每次构建对象实例,次代码块都会执行)
		System.out.println("{}");
	}
}
public class TestClassObject07 {
	public static void main(String[] args) throws Exception {
		ClassD.show();
	}
	
}



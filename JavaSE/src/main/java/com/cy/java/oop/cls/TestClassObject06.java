package com.cy.java.oop.cls;


/**
 * 何时会触发类加载？
 * 1)使用类加载器直接加载
 * 2)构建本类或这个类的子类对象时
 * 3)访问类中成员时(包含属性和方法),但是也有特殊情况
 * 3.1)访问类中使用static final修饰的8种基本数据类型
 *     以及字符类型时不会触发类的加载
 * @author lll
 *
 */
class ClassC{
	static int a=10;//会触发类加载
	static final int b=20;//不会触发类加载
	static final String s="hello";//不会触发类加载
	static final Integer c=1;//会触发类加载
	static {
		System.out.println("static{}");
	}
}
//当通过子类访问父类成员时,触发了父类的加载
//那么父类会自动加载,子类伟被动加载
//被动加载的类不会执行静态代码块
class ClassCC extends ClassC{
	static {
		System.out.println("staticc{}");
	}
}
public class TestClassObject06 {
	public static void main(String[] args) throws Exception {
		//此类会加载父类,子类
		//但是不会执行子类的静态代码块
		int a=ClassCC.c;
		System.out.println(a);
	}
	
}



package com.cy.java.oop.cls;

import java.lang.reflect.Constructor;

/**
 * 此工厂要基于类的字节码对象创建实例对象
 * @author 
 *
 */
class ObjectFactory{
	
	public static Object newInstance(Class<?> cls)
			throws Exception {
//		//基于私有无参构造
//		//基于字节码对象获取类的无参构造函数对象
//		Constructor<?> con = cls.getDeclaredConstructor();
//		//假如无参构造函数不可访问,则设置可访问
//		if(!con.isAccessible())
//			con.setAccessible(true);
//		//基于构造方法对象构建类的实例对象
//		return con.newInstance();
		return newInstance(cls, null, null);
	}
	public static Object newInstance(
			Class<?> cls,Class<?>[]parameterTypes,Object[]initargs) 
			throws Exception {
		//基于私有无参构造
		//基于字节码对象获取类的指定参数的构造函数对象
		Constructor<?> con = cls.getDeclaredConstructor(parameterTypes);
		//假如构造函数不可访问,则设置可访问
		if(!con.isAccessible())
			con.setAccessible(true);
		//基于构造方法对象构建类的实例对象
		return con.newInstance(initargs);
	}
}

class ClassA{
	private ClassA() {
	}
}

class ClassB{
	private ClassB(int b) {
		System.out.println(b);
	}
}

public class TestClassObject04 {
	public static void main(String[] args) throws Exception {
		Class<?> c1 = Class.forName("java.util.Date");
		Object o1=ObjectFactory.newInstance(c1);
		System.out.println(o1);
		
		Class<ClassA> c2 = ClassA.class;
		Object o2=ObjectFactory.newInstance(c2);
		System.out.println(o2);
		
		Class<ClassB> c3 = ClassB.class;
		Object o3=ObjectFactory.newInstance(
				c3,new Class[] {int.class},new Object[] {10});
		System.out.println(o3);
	}
}



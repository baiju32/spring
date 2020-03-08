package com.cy.java.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * 泛型只在编译器有效,运行期无效
 * 运行期都会转化为Object
 * @author lll
 *
 */
public class TestGeneric06 {
	public static void main(String[] args) throws Exception{
		List<String> list=new ArrayList<String>();
		list.add("A");
		list.add("B");
	//	list.add(100);
		//在运行时将100写入list集合
		//1.获取list对象的字节码对象
		//2.获取list对象的字节码对象中的add方法对象
		//3.通过反射执行方法对象将100写入集合
		Class<?> class1 =list.getClass();
		Method method1 = class1.getMethod("add",Object.class);
		method1.invoke(list, 100);
		System.out.println(list);
		
		Method method2 = class1.getMethod("add",int.class,Object.class);
		method2.invoke(list,0,100);
		System.out.println(list);
	}
}
 
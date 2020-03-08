package com.cy.java.generic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类上的泛型不能影响静态方法
 * @author lll
 *
 * @param <T>
 */
class ContainerUtil{
	//静态方法中使用泛型可以直接用泛型方法
	public static <T>void sort(List<T> list) {
	}
	
}
/**
 * 通配符:?
 * 不确定类型时使用
 * 限定通配符:? extends   ,?super
 * 方法参数和方法返回值上使用
 * @author lll
 *
 */
class PrintUtil{
	//限定通配符:? extends (上界)
	public static void doPrint(List<? extends Object> list) {
		System.out.println(list);
	}
	//限定通配符:? super (下界)
	public static void doPrint(Set<? super Integer> set) {
		System.out.println(set);
	}
	
}

public class TestGeneric05 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("ssss");
		PrintUtil.doPrint(list);
		Set<Number> set=new HashSet<>();
		set.add(22);
		PrintUtil.doPrint(set); 
		
	}
}
 
package com.cy.java.generic;

import java.util.HashMap;

/**
 * 1.class a<A,B,C,D>{}
 * 2.interface IA<K,V>{}
 * 3.List<Object> list=new ArrayList<String>();错误
 */

interface Container<T>{
	public void add(T t);
	T get(int index);
}

class AbsContainer<T> implements Container<T>{

	@Override
	public void add(T t) {
	}
	@Override
	public T get(int index) {
		return null;
	}
}

class StringMap<V> extends HashMap<String, V>{

}

public class TestGeneric01 {
	public static void main(String[] args) {
		StringMap<Integer> map = new StringMap<>();
		map.put("a", 1);
		
		
	}
}

package com.cy.java.oop.instance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class ObjectContainer{
	private static Map<String, Object> objectCache=
			new HashMap<String, Object>();
	static {
		objectCache.put("A", new Date());
		objectCache.put("b", new Integer(200));
	}
	public static Object getObject(String key) {
		return objectCache.get(key);
	}
}

public class TestInstanceObject05 {

	public static void main(String[] args) {
		Object o1 = ObjectContainer.getObject("A");
		Object o2 = ObjectContainer.getObject("A");
		System.out.println(o1==o2);
		
		Object o3 = ObjectContainer.getObject("B");
		Object o4 = ObjectContainer.getObject("B");
		System.out.println(o3==o4);
		
		
		
	}
}
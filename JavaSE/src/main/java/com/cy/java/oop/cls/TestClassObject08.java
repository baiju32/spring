package com.cy.java.oop.cls;

import java.util.HashMap;
import java.util.Map;

class ClassE{
	static ClassE instanceClassE=new ClassE();
	static Map<String, Object> map=new HashMap<>();
	public ClassE() {
		map.put("A", 100);
		map.put("B", 200);
	}
}
public class TestClassObject08 {
	public static void main(String[] args) throws Exception {
		System.out.println(ClassE.map);
	}
	
}



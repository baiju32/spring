package com.cy.java.oop.features;

import java.util.LinkedHashMap;
import java.util.Map;

class ComposeLRUCache{
	private Map<String,Object> cache;
	
	public ComposeLRUCache(int initialCapacity) {
		this.cache=new LinkedHashMap<String,Object>(
				initialCapacity, 0.75f,true){
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<String, Object> eldest) {
				return size()>initialCapacity;
			}
		};
	}
	
	public void put(String key,Object value) {
		cache.put(key, value);
	}
	public Object get(String key) {
		return cache.get(key);
	}
	@Override
	public String toString() {
		return cache.toString();
	}
}

public class TestComposeLRUCache01 {
	public static void main(String[] args) {
		ComposeLRUCache Cache = new ComposeLRUCache(3);
		Cache.put("A", 100);
		Cache.put("B", 200);
		Cache.put("C", 300);
		Cache.put("B", 400);
		Cache.get("A");
		Cache.put("D", 500);
		System.out.println(Cache);
		
	}
}

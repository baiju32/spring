package com.cy.java.generic;

import java.util.ArrayList;
import java.util.HashMap;

class MyList<T> extends ArrayList<T>{
	private static final long serialVersionUID = 8421005610617125609L;
	@Override
	public synchronized boolean add(T e) {
		return super.add(e);
	}
	@Override
	public synchronized T get(int index) {
		return super.get(index);
	}
}

class MyHashMap<K,V> extends HashMap<K,V>{
	private static final long serialVersionUID = 4287345638113949371L;
	@Override
	public synchronized V put(K key, V value) {
		return super.put(key, value);
	}
	@Override
	public synchronized V get(Object key) {
		return super.get(key);
	}
}

public class TestGeneric03 {
	public static void main(String[] args) {

	}
}

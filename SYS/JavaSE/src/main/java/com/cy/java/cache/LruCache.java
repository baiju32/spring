package com.cy.java.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache implements Cache{

	private final Cache delegate;	
	private Map<Object, Object> keyMap;

	private Object eldestKey;

	public LruCache(Cache delegate) {
		this.delegate=delegate;
		setSize(3);
	}

	private void setSize(final int size) {
		keyMap=new LinkedHashMap<Object, Object>(size, 0.75f, true){
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<Object, Object> eldest) {
				boolean tooBig = size() > size;
				if (tooBig) {
					eldestKey = eldest.getKey();
				}
				return tooBig;
			}
		};


	}

	@Override
	public void putObject(Object key, Object value) {
		delegate.putObject(key, value);
		cycleKeyList(key);
	}
	private void cycleKeyList(Object key) {
		keyMap.put(key, key);
		if (eldestKey != null) {
			delegate.removeObject(eldestKey);
			eldestKey = null;
		}
	}
	@Override
	public Object getObject(Object key) {
		keyMap.get(key); 
		return delegate.getObject(key);
	}

	@Override
	public Object removeObject(Object key) {
		return delegate.removeObject(key);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}
	
	
	public static void main(String[] args) {
		LruCache cache=new LruCache(new PerpetualCache());
		cache.putObject("A", 100);
		cache.putObject("B", 200);
		cache.putObject("C", 300);
		cache.getObject("A");
		cache.putObject("D", 400);
		System.out.println(cache);
	}
	
}

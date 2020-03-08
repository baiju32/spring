package com.cy.java.cache;
/**
 * 此cache本身不存数据,只负责提供缓存淘汰算法
 * @author lll
 *
 */

import java.util.LinkedList;

public class FifoCache implements Cache{
	
	private Cache cache;
	private LinkedList<Object> keyOrderList;
	private int maxCap;
	
	public FifoCache(int maxCap,Cache cache) {
		this.maxCap=maxCap;
		this.cache=cache;
		keyOrderList=new LinkedList<>();
	}
	
	@Override
	public void putObject(Object key, Object value) {
		keyOrderList.addLast(key);
		if(keyOrderList.size()>maxCap) {
			Object oldKey = keyOrderList.removeFirst();
			cache.removeObject(oldKey);
		}
		cache.putObject(key, value);
	}

	@Override
	public Object getObject(Object key) {
		return cache.getObject(key);
	}

	@Override
	public Object removeObject(Object key) {
		return cache.removeObject(key);
	}
	
	@Override
	public String toString() {
		return cache.toString();
	}

	public static void main(String[] args) {
		FifoCache cache=new FifoCache(3, new PerpetualCache());
		cache.putObject("A", 100);
		cache.putObject("B", 200);
		cache.putObject("C", 300);
		cache.putObject("D", 400);
		System.out.println(cache);
		
	}
	
	
}

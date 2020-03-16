package com.cy.java.oop.features;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class FifoCache{
	/**基于此属性记录key的顺序*/
	private LinkedList<String> keyOrderList=new LinkedList<>();
	/**基于此map对象实现数据的缓存*/
	private Map<String,Object> cache=new HashMap<>();
	/**记录容器的最大容量*/
	private int maxCap;
	public FifoCache(int maxCap) {
		this.maxCap=maxCap;
	}
	
	public void put(String key,Object value) {
		//1.存储key
		//解决bug
		for (int i=0;i<keyOrderList.size();i++) {
			String string = keyOrderList.get(i);
			if (string==key) {
				keyOrderList.remove(i);
			}
		}
		keyOrderList.addLast(key);
		//2.检测cache是否已满,满了则移除元素
		if (keyOrderList.size()>maxCap) {
			String oldKey = keyOrderList.removeFirst();
			cache.remove(oldKey);
		}
		//3.将key/value存储到cache中
		cache.put(key, value);
	}
	
	public Object get(String key) {
		return cache.get(key);
	}

	@Override
	public String toString() {
		return cache.toString()+keyOrderList.toString();
	}
	
	
}

public class TestComposeFifoCache01 {
	public static void main(String[] args) {
		FifoCache Cache = new FifoCache(3);
		Cache.put("A", 100);
		Cache.put("B", 200);
		Cache.put("C", 300);
		Cache.put("B", 400);//有bug
		Cache.put("D", 500);
		System.out.println(Cache);
		
	}
}

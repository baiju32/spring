package com.cy.java.oop.features;

import java.util.LinkedHashMap;
/**
 * LinkedHashMap简介
 * 1)存储结构:链表+散列表
 * 2)存储算法:LRU+哈希算法
 * LinkedHashMap特性
 * 1)记录元素的添加顺序,访问数据
 * 2)线程不安全
 * 
 * LRU算法(Least Recently Used Cache):缓存淘汰算法(最近最少适用算法)
 */
class LRUCache extends LinkedHashMap<String, Object>{
	private static final long serialVersionUID = 6773571326345881205L;
	private final int maxCap;//最大容量
	
	public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
		this.maxCap=initialCapacity;
	}
	/**
	 * 每次执行put方法底层都会调用此方法进行判定
	 */
	protected boolean removeEldestEntry(
			java.util.Map.Entry<String,Object> eldest) {
			
				return size()>maxCap;
	};
	
	
	
}

public class TestExtendsLRUCache01 {
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3,0.75f,true);//true=访问数据顺序
		cache.put("A", 100);
		cache.put("D", 100);
		cache.put("C", 100);
		Object put = cache.put("B", 100);
		System.out.println(put);
		cache.get("D");
		System.out.println(cache);
	}
}

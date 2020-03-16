package com.cy.java.cache;
/**
 * 此cache对象要在cache的基础上添加日志操作
 * 例如记录元素的命中率
 */
public class LogCache implements Cache{
	
	private Cache cache;
	//请求数
	private int requests;
	//命中数
	private int hit;
	
	public LogCache(Cache cache) {
		this.cache=cache;
	}
	
	
	public void putObject(Object key, Object value) {
		cache.putObject(key, value);
	}

	@Override
	public Object getObject(Object key) {
		requests++;
		Object object = cache.getObject(key);
		if (object!=null) {
			hit++;
		}
		System.out.println("命中率:"+hit*1.0/requests);
		return object;
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
		LogCache logCache = new LogCache(new FifoCache(3, new PerpetualCache()));
		logCache.putObject("A", 100);
		logCache.putObject("B", 200);
		logCache.putObject("C", 300);
		logCache.putObject("D", 400);
		logCache.getObject("A");
		logCache.getObject("B");
		logCache.getObject("C");
		System.out.println(logCache);
	}
	
}

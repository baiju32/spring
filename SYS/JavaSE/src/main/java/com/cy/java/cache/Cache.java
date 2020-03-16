package com.cy.java.cache;

/**
 * Cache接口:定义缓存数据的标准
 * @author lll
 *
 */
public interface Cache {
	
	public void putObject(Object key,Object value);
	
	public Object getObject(Object key);
	
	public Object removeObject(Object key);
}

package com.cy.java.cache;

import java.io.IOException;
import java.io.Serializable;

import com.cy.java.serializable.KryoUtil;


/**
 * 序列化cache
 * 1.将对象序列化为字节
 * 2.将字节存储到cache中
 * 
 * 反序列化
 * 1.将字节从cache中读出
 * 2.将字节反序列化为对象
 * 
 * @author lll
 *
 */
public class KryoSerializableCache implements Cache{
	
	private Cache cache;
	
	public KryoSerializableCache(Cache cache) {
		this.cache=cache;
	}
	
	/**向cache存对象:序列化方法存储*/
	@Override
	public void putObject(Object key, Object obj) {
		if (obj!=null&obj instanceof Serializable) {
			cache.putObject(key, serialize(obj));
		}else {
			System.out.println("该对象可能为null或没有实现序列化接口");
		} 
	}

	private byte[] serialize(Object obj) {
		try {
			return KryoUtil.serialize(obj);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("put失败!");
		}
	}
	
	/**从cache读数据:将cache中的数据反序列化*/
	@Override
	public Object getObject(Object key) {
		Object object = cache.getObject(key);
	    return object == null ? null : deserialize((byte[]) object);
	}

	private Object deserialize(byte[] object) {
		Object result;
		try {
			result=KryoUtil.deserialize(object);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("get失败!");
		}
		return result;
	}

	@Override
	public Object removeObject(Object key) {
		return null;
	}

	@Override
	public String toString() {
		return cache.toString();
	}
	
	public static void main(String[] args) {
		KryoSerializableCache cache = 
				new KryoSerializableCache(new PerpetualCache());
		
		cache.putObject("1","你好吧");
		cache.putObject("2","熟不熟你好吧");
		cache.putObject("3","啊啊啊啊");
		System.out.println(cache);
		Object object1 = cache.getObject("1");
		Object object2 = cache.getObject("1");
		System.out.println(object1==object2);
		System.out.println(object1);
	}
	
}

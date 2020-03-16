package com.cy.java.generic;

import java.util.Collection;
import java.util.Date;
/**
 * 泛型方法应用
 *
 */
class ObjectFactory{
	public static <T>T newInstance(Class<T> cls) throws InstantiationException, IllegalAccessException {
		return cls.newInstance();
	}
	/**
	 * 判断一个对象是否为Collection类型
	 * @param cls
	 * @return
	 */
	public <T>boolean isCollection(Class<T> cls) {
		return Collection.class.isAssignableFrom(cls);
	}
}

public class TestGeneric04 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Date date=ObjectFactory.newInstance(Date.class);
		System.out.println(date.getTime());
	}
}
 
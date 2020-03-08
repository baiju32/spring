package com.cy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

interface SearchaMapper{
	Object search(String msg);
}
interface SqlSession{
	List<Object> selectList(String statement);
}
class DefaultSqlSession implements SqlSession{

	@Override
	public List<Object> selectList(String statement) {
		System.out.println("select List");
		return null;
	}}

class MapperProxyHandler implements InvocationHandler{
	
	private SqlSession sqlSession;
	private Class<?> targetInterface;
	public MapperProxyHandler(SqlSession sqlSession,Class<?> targetInterface) {
		this.sqlSession=sqlSession; 
		this.targetInterface=targetInterface;
	}
	
	/**invoke方法是为代理对象执行具体业务的方法*/
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("MapperProxyHandler.invoke()执行了");
		String className=targetInterface.getClass().getName();
		String methodName=method.getName();
		String statement=className+"."+methodName;
		return sqlSession.selectList(statement);
	}
	
}

class MapperProxyFactory{
	/**借助此属性封装要产生代理对象的目标接口*/
	private Class<?> targetInterface;
	public MapperProxyFactory(Class<?> targetInterface) {
		this.targetInterface=targetInterface;
	}
	
	/**
	 * 创建代理对象,并未代理对象传入一个InvocationHandler对象
	 * @return
	 */
	public Object newInstance(MapperProxyHandler handler) {
		return Proxy.newProxyInstance(
				targetInterface.getClassLoader(),
				new Class[]{targetInterface}, handler);
	}
	
}


public class TestMapperProxy {
	public static void main(String[] args) {
		//1.构建目标接口的类对象
		Class<?> targetInterface=SearchaMapper.class;
		//2.获取Sqlsession对象
		SqlSession sqlSession=new DefaultSqlSession();
		//3.创建一个MapperProxyHandler对象
		MapperProxyHandler proxyHandler = new MapperProxyHandler(sqlSession,targetInterface);
		//4.创建一个MapperProxyFactory工厂对象
		MapperProxyFactory proxyFactory = new MapperProxyFactory(targetInterface);
		//5.基于工厂对象为目标接口创建代理对象
		SearchaMapper searchaMapper = (SearchaMapper) proxyFactory.newInstance(proxyHandler);
		System.out.println(searchaMapper.getClass().getName());
		//6.基于代理对象执行业务操作
		searchaMapper.search("hello mybatis proxy");
	}
}

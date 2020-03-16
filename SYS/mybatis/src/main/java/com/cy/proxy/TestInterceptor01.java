package com.cy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Executor{
	void execute(String statement);
}

class DefaultExecutor implements Executor{
	@Override
	public void execute(String statement) {
		System.out.println("execute() "+statement);
	}
}

class TargetProxyHandler implements InvocationHandler{
	private Object target;
	public TargetProxyHandler(Object target) {
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("execute start time:"+System.nanoTime());
		Object result = method.invoke(target, args);
		System.out.println("execute end time:"+System.nanoTime());
		return result;
	}
}

class TargetProxyFactory{
	public static Object newProxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new TargetProxyHandler(target));
	}
}

/**基于代理对象处理器进行业务扩扩展*/
public class TestInterceptor01 {
	public static void main(String[] args) {
		Executor target=new DefaultExecutor();
		Executor proxy = (Executor) TargetProxyFactory.newProxy(target);
		proxy.execute("select");
	}
}

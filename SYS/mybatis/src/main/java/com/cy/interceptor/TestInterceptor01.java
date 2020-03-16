package com.cy.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

interface Executor{
	void execute(String statement);
}

class DefaultExecutor implements Executor{
	@Override
	public void execute(String statement) {
		System.out.println("execute() "+statement);
	}
}

class Invocation{
	private Object target;
	private Method method;
	private Object[] args;
	public Invocation(Object target, Method method, Object[] args) {
		this.target = target;
		this.method = method;
		this.args = args;
	}
	public Object process() throws Exception{
		return method.invoke(target, args);
	}
}

interface Interceptor{
	public Object intercepe(Invocation invocation) throws Exception;
	public Object plugin(Object target);
}

class LogInterceptor implements Interceptor{
	@Override
	public Object intercepe(Invocation invocation) throws Exception {
		System.out.println("execute start time:"+System.nanoTime());
		Object result = invocation.process();
		System.out.println("execute end time:"+System.nanoTime());
		return result;
	}
	@Override
	public Object plugin(Object target) {
		return TargetProxyFactory.newProxy(target, this);
	}
}
class TransactionInterceptor implements Interceptor{
	@Override
	public Object intercepe(Invocation invocation) throws Exception {
		System.out.println("Transaction begin ....");
		Object result = invocation.process();
		System.out.println("Transaction commit ....");
		return result;
	}
	@Override
	public Object plugin(Object target) {
		return TargetProxyFactory.newProxy(target, this);
	}
	
}

class InterceptorChain{
	List<Interceptor> interceptors=new ArrayList<>();
	public void addInterceptor(Interceptor interceptor) {
		interceptors.add(interceptor);
	}
	public Object pluginAll(Object target) {
		for (Interceptor interceptor : interceptors) {
			target = interceptor.plugin(target);
		}
		return target;
	}
}



class TargetProxyHandler implements InvocationHandler{
	private Object target;
	private Interceptor interceptor;
	public TargetProxyHandler(Object target,Interceptor interceptor) {
		this.target=target;
		this.interceptor=interceptor;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		System.out.println("execute start time:"+System.nanoTime());
//		Object result = method.invoke(target, args);
//		System.out.println("execute end time:"+System.nanoTime());
		Invocation invocation = new Invocation(target, method, args);
		return interceptor.intercepe(invocation);
	}
}

class TargetProxyFactory{
	public static Object newProxy(Object target,Interceptor interceptor) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new TargetProxyHandler(target,interceptor));
	}
}


/**基于拦截器进行业务扩扩展*/
public class TestInterceptor01 {
	public static void main(String[] args) {
//		Executor target=new DefaultExecutor();
//		Interceptor interceptor=new LogInterceptor();
//		Executor proxy = (Executor) TargetProxyFactory.newProxy(target,interceptor);
//		proxy.execute("select");
		
		Executor target=new DefaultExecutor();
		Interceptor interceptor1=new LogInterceptor();
		Interceptor interceptor2=new TransactionInterceptor();
		InterceptorChain interceptorChain = new InterceptorChain();
		interceptorChain.addInterceptor(interceptor1);
		interceptorChain.addInterceptor(interceptor2);
		Executor proxy = (Executor)interceptorChain.pluginAll(target);
		proxy.execute("select");
		
	}
}

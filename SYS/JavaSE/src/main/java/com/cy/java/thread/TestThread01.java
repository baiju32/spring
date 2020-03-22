package com.cy.java.thread;

public class TestThread01 {
	static String content;

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread() {//新建状态
			@Override
			public void run() {//运行状态
				content="helloworld";
			}
		};//run方法执行结束线程处于死亡状态
		
		t1.start();//就绪状态(可运行状态)
	//	Thread.sleep(1000); //主线程阻塞
	//	t1.join();//让t1线程执行结束,主线程阻塞
		while(content==null) {
			Thread.yield();//让当前线程放弃CPU,处于就绪状态 
		}
		
		System.out.println(content.toUpperCase());
	}
}	

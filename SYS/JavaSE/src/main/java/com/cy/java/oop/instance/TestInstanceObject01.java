package com.cy.java.oop.instance;

//最大堆:-Xmx10m
//最小堆-Xms10m
//对象逃逸分析:-XX:+DoEscapeAnalysis
//输出GC信息:-XX:+PrintGC
//-Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
public class TestInstanceObject01 {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			createBuf();
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	static void createBuf() {
		//小对象,没有逃逸(方法外部有引用,则为逃逸对象)
		byte[] buf=new byte[1];
		buf[0]=1;
	}
	
	
}

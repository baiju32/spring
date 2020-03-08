package com.cy.java.oop.instance;

class Looper{
	public Looper() {
		System.out.println("Looper()");
	}
}
class UtilLooper{
	private static ThreadLocal<Looper> tl=new ThreadLocal<>();
	
	public static Looper getLooper() {
		Looper looper=tl.get();
		if (looper==null) {
			looper=new Looper();
			tl.set(looper);
		}
		return looper;		
	}
	
}

public class TestInstanceObject07 {
	public static void main(String[] args) {
		UtilLooper.getLooper();
		UtilLooper.getLooper();
		UtilLooper.getLooper();
		new Thread() {
			public void run() {
				UtilLooper.getLooper();
			};
		}.start();
	}
}
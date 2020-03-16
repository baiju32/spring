package com.cy.java.oop.instance;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
//java中的四大引用
//内存泄漏(指对象已经不用,但还占着内存.可能是导致内存溢出的一个导火索)
//内存溢出
//弱引用,软引用只要是为了更好避免内存泄漏现象的发生
//强引用,
//虚引用:当对象被回收是,可以得到通知
class ClassB{
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize()");
	}
}
//-Xmx5m -Xms5m -XX:+PrintGC
public class TestInstanceObject03 {
	public static void main(String[] args) throws InterruptedException {
		//1.强引用
		ClassB c1 = new ClassB();
	//	System.gc();
		
		//2.软引用(内存不足时,引用的对象会被回收)
//		SoftReference<ClassB> sr = 
//				new SoftReference<>(new ClassB());
	//	System.out.println(sr.get());
	//	System.gc();
//		List<byte[]> list=new ArrayList<byte[]>();
//		while(true) {
//			list.add(new byte[1024*100]);
//			System.out.println(sr.get());
//			Thread.sleep(300);
//		}
		
		//3.弱引用(只要出现GC时,引用的对象会被回收)
		WeakReference<ClassB> wr = 
				new WeakReference<>(new ClassB());
		List<byte[]> list=new ArrayList<byte[]>();
		while(true) {
			list.add(new byte[1024*100]);
			System.out.println(wr.get());
			Thread.sleep(500);
		}
		
		
		
		
	}
}

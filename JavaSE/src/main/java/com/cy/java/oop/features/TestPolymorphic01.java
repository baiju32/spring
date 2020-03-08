package com.cy.java.oop.features;

class ClassA{
	public void doSay() {
		System.out.println("ClassA.doSay()");
	}
}

class ClassB extends ClassA{
	@Override
	public void doSay() {
		System.out.println("ClassB.doSay()");
	}
	public void doDance() {
		System.out.println("ClassB.doDance()");
	}
}

class ClassC{
	//多态应用在方法参数上
	public void doSay(ClassA cls) {
		cls.doSay();
	}
}

public class TestPolymorphic01 {
	public static void main(String[] args) {
		ClassB b1 = new ClassB();
		b1.doSay();
		
		ClassA a1=b1;//父类引用指向子类对象
		a1.doSay();
		//a1.doDance();//不能调用
		((ClassB)a1).doDance();
		
	}
}

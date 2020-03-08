package com.cy.java.abs;


interface IA{
	int a = 100;
	void doMethod();
}

interface IB{
	default void doMethod01() {}
}
 
class A implements IA{
	@Override
	public void doMethod() {
	}
}
class B implements IB{
}

public class TestInterface01 {
	public static void main(String[] args) {
		
	}
	
}

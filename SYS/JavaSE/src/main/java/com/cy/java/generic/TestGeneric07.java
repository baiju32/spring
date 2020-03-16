package com.cy.java.generic;

import java.util.LinkedList;

class Stack<E>{
	private LinkedList<E> stack=new LinkedList<>();
	public void push(E e) {
		stack.addFirst(e);
	}
	public E pop() {
		return stack.pop();
	}
	@Override
	public String toString() {
		return stack.toString();
	}
	
}

public class TestGeneric07 {
	public static void main(String[] args) throws Exception{
		Stack<Integer> stack = new Stack<>();
		stack.push(100);
		stack.push(200);
		stack.push(300);
		stack.push(400);
		stack.pop();
		System.out.println(stack);
	}
}
 
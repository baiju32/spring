package com.cy.java.generic;

import java.util.Arrays;
/**
 * 队列容器
 * 1)存储结构:数组
 * 2)存取算法:FIFO
 * @author lll
 *
 * @param <E>
 */
class QueueContainer<E>{
	/**存储数据*/
	private Object[] array;
	/**记录存储元素个数*/
	private int size;
	public QueueContainer() {
		this(16);
	}
	public QueueContainer(int cap) {
		array=new Object[cap];
	}
	/**存储数据:将元素永远存储在size位置,满了则扩容*/
	public void add(E e) {
		//1.检测容器是否已满,满了则扩容
		if (size==array.length) {
			array=Arrays.copyOf(array, array.length*2);
		}
		//2.存储数据
		array[size]=e;
		//3.元素个数加1
		size++;
	}
	/**永远取第0个位置元素*/
	@SuppressWarnings("unchecked")
	public E take() {
		//1.判定容器是否为空
		if (size==0) return null;
		//2.取数据
		Object obj=array[0];
		//3.移动元素
		System.arraycopy(array, 1, array, 0, size-1);
		//4.元素个数减1
		size--;
		//5.初始化size位置值
		array[size]=null;
		return (E) obj;
	}
	@Override
	public String toString() {
		return  Arrays.toString(array);
	}
	
}

public class TestGeneric08 {
	public static void main(String[] args) throws Exception{
		QueueContainer<Integer> q = new QueueContainer<>();
		q.add(100);
		q.add(200);
		q.add(300);
		q.add(400);
		q.add(500);
		System.out.println(q);
		Integer take = q.take();
		System.out.println(q);
	}
}
 
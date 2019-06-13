package com.tju.ds;

/**
 * MyQueue类：循环队列的实现 
 * 采用一个一维数组来实现循环队列，队头队尾指针使用数组下标代替
 */
public class MyQueue<E>{
	private E[] elements; //元素个数
	private int front; // 队头指针
	private int rear; // 队尾指针
	private static final int INITIAL_CAPACITY = 40; //默认容量
	
	
	/**
	 * 默认构造方法
	 */
	public MyQueue() {
		elements = (E[]) new Object[INITIAL_CAPACITY]; //！！！强制转换，Java不支持泛型数组
		rear = front = 0;
	}

	/**
	 * 指定队列长度的构造方法
	 */
	public MyQueue(int capacity) {
		elements = (E[]) new Object[capacity];
		rear = front = 0;
	}
	
	/**
	 * 获队列中元素个数
	 */
	public int size() {
		if (front < rear) {
			return rear - front;
		} else {
			return rear - front + elements.length;
		}
	}

	
	/**
	 * 队尾增加元素
	 */
	public void add(E element) {
		if (size() == elements.length - 1) {
			resize(); //队列已满，重新分配长度，使长度为原2倍
		}
		elements[rear] = element;
		if (rear < elements.length - 1) { //判断指针是否到达数组末尾
			rear++;
		} else {
			rear = 0; 
		}
	}
	
	/**
	 * 重新分配长度
	 */
	private void resize() {
		int size = size();//队列中元素个数
		int len = elements.length;//队列长度
		if(size == len) {	//只有当元素个数等于长度时，允许重新分配，扩大为2倍
			Object[] a = new Object[2 * size];
			
			//复制队列
			System.arraycopy(elements, front, a, 0, len - front);//对头到数组尾
			System.arraycopy(elements, 0, a, len - front, front);//数组头到队尾
			elements = (E[]) a;
			
			//重置指针
			front = 0;
			rear = size;
		}else {
			throw new IllegalArgumentException("队列未满");
		}
	}
	
	/**
	 * 获取队头元素
	 */
	public E element() {
		if (size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		return elements[front];
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * 删除队头元素
	 */
	public E remove() {
		if (size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		E element = elements[front];
		elements[front] = null;
		front++;
		if (front == rear) { // 队列为空
			front = rear = 0;
		}
		if (front == elements.length) { //对头指针到达数组末尾， 回到队头
			front = 0;
		}
		return element;
	}

}

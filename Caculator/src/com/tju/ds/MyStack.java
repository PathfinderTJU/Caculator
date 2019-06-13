package com.tju.ds;

/**
 * MyStack：栈的实现
 * 使用一维数组实现栈
 */
public class MyStack<E>{
	private E[] elements;// 元素数组
	private int size;// 长度
	private static final int INITIAL_CAPACITY = 30;// 默认栈大小

	/**
	 * 默认大小的构造方法
	 */
	public MyStack() {
		elements = (E[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * 指定大小的构造方法
	 */
	public MyStack(int capacity) {
		elements = (E[]) new Object[capacity];
	}

	/**
	 * 返回栈中元素的个数
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 访问栈顶元素（但不删除）
	 */
	public E peek() {
		if (size == 0) {//栈为空，抛出异常
			throw new java.util.EmptyStackException();
		}
		return elements[size - 1];
	}

	/**
	 * 删除并返回当前栈顶元素
	 */
	public E pop() {
		if (size == 0) {//栈为空，抛出异常
			throw new java.util.EmptyStackException();
		}
		
		//获取栈顶元素，并清空栈顶元素
		E element = elements[size-1];
		elements[size-1] = null;
		
		//返回并减小长度
		size--;
		return element;
	}

	/**
	 * 入栈
	 */
	public void push(Object element) {
		if (size == elements.length) {//栈满，重新分配栈空间
			resize();
		}
		
		elements[size] = (E) element;
		size ++;
	}

	/**
	 * 重新分配大小，长度变为2倍
	 */
	private void resize() {
		if (size == elements.length) {//只有栈满才允许重新分配，否则抛出异常
			Object[] a = new Object[2 * size];
			System.arraycopy(elements, 0, a, 0, size);
			elements = (E[]) a;
		}else {
			throw new IllegalArgumentException("栈未满");
		}
	}
}

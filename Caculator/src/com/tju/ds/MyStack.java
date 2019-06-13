package com.tju.ds;

/**
 * MyStack��ջ��ʵ��
 * ʹ��һά����ʵ��ջ
 */
public class MyStack<E>{
	private E[] elements;// Ԫ������
	private int size;// ����
	private static final int INITIAL_CAPACITY = 30;// Ĭ��ջ��С

	/**
	 * Ĭ�ϴ�С�Ĺ��췽��
	 */
	public MyStack() {
		elements = (E[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * ָ����С�Ĺ��췽��
	 */
	public MyStack(int capacity) {
		elements = (E[]) new Object[capacity];
	}

	/**
	 * ����ջ��Ԫ�صĸ���
	 */
	public int size() {
		return size;
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * ����ջ��Ԫ�أ�����ɾ����
	 */
	public E peek() {
		if (size == 0) {//ջΪ�գ��׳��쳣
			throw new java.util.EmptyStackException();
		}
		return elements[size - 1];
	}

	/**
	 * ɾ�������ص�ǰջ��Ԫ��
	 */
	public E pop() {
		if (size == 0) {//ջΪ�գ��׳��쳣
			throw new java.util.EmptyStackException();
		}
		
		//��ȡջ��Ԫ�أ������ջ��Ԫ��
		E element = elements[size-1];
		elements[size-1] = null;
		
		//���ز���С����
		size--;
		return element;
	}

	/**
	 * ��ջ
	 */
	public void push(Object element) {
		if (size == elements.length) {//ջ�������·���ջ�ռ�
			resize();
		}
		
		elements[size] = (E) element;
		size ++;
	}

	/**
	 * ���·����С�����ȱ�Ϊ2��
	 */
	private void resize() {
		if (size == elements.length) {//ֻ��ջ�����������·��䣬�����׳��쳣
			Object[] a = new Object[2 * size];
			System.arraycopy(elements, 0, a, 0, size);
			elements = (E[]) a;
		}else {
			throw new IllegalArgumentException("ջδ��");
		}
	}
}

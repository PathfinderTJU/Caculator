package com.tju.ds;

/**
 * MyQueue�ࣺѭ�����е�ʵ�� 
 * ����һ��һά������ʵ��ѭ�����У���ͷ��βָ��ʹ�������±����
 */
public class MyQueue<E>{
	private E[] elements; //Ԫ�ظ���
	private int front; // ��ͷָ��
	private int rear; // ��βָ��
	private static final int INITIAL_CAPACITY = 40; //Ĭ������
	
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MyQueue() {
		elements = (E[]) new Object[INITIAL_CAPACITY]; //������ǿ��ת����Java��֧�ַ�������
		rear = front = 0;
	}

	/**
	 * ָ�����г��ȵĹ��췽��
	 */
	public MyQueue(int capacity) {
		elements = (E[]) new Object[capacity];
		rear = front = 0;
	}
	
	/**
	 * �������Ԫ�ظ���
	 */
	public int size() {
		if (front < rear) {
			return rear - front;
		} else {
			return rear - front + elements.length;
		}
	}

	
	/**
	 * ��β����Ԫ��
	 */
	public void add(E element) {
		if (size() == elements.length - 1) {
			resize(); //�������������·��䳤�ȣ�ʹ����Ϊԭ2��
		}
		elements[rear] = element;
		if (rear < elements.length - 1) { //�ж�ָ���Ƿ񵽴�����ĩβ
			rear++;
		} else {
			rear = 0; 
		}
	}
	
	/**
	 * ���·��䳤��
	 */
	private void resize() {
		int size = size();//������Ԫ�ظ���
		int len = elements.length;//���г���
		if(size == len) {	//ֻ�е�Ԫ�ظ������ڳ���ʱ���������·��䣬����Ϊ2��
			Object[] a = new Object[2 * size];
			
			//���ƶ���
			System.arraycopy(elements, front, a, 0, len - front);//��ͷ������β
			System.arraycopy(elements, 0, a, len - front, front);//����ͷ����β
			elements = (E[]) a;
			
			//����ָ��
			front = 0;
			rear = size;
		}else {
			throw new IllegalArgumentException("����δ��");
		}
	}
	
	/**
	 * ��ȡ��ͷԪ��
	 */
	public E element() {
		if (size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		return elements[front];
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * ɾ����ͷԪ��
	 */
	public E remove() {
		if (size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		E element = elements[front];
		elements[front] = null;
		front++;
		if (front == rear) { // ����Ϊ��
			front = rear = 0;
		}
		if (front == elements.length) { //��ͷָ�뵽������ĩβ�� �ص���ͷ
			front = 0;
		}
		return element;
	}

}

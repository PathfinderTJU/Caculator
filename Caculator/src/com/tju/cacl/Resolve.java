package com.tju.cacl;

import java.util.*;
import com.tju.ds.MyQueue;
import com.tju.ds.MyStack;

/*
 * Resolve�ࣺ���ڽ����������ѧ���ʽ�ĺ���
 * ����׺���ʽ��Ϊ��׺���ʽ���ٽ������㡣
 */
public class Resolve {

	//��ԭ���ʽ�еĸ����滻Ϊ#
	public static String ReplaceMinus(String string) {

		StringBuffer str = new StringBuffer(string);

		// ���ų����ڱ��ʽ��ͷ
		if (string.startsWith("-")) {
			str.replace(0, 1, "#");
		}

		// ���ų����ڱ��ʽ�м䣬���������
		while (str.toString().contains("(-")) {
			str.replace(str.indexOf("(-") + 1, str.indexOf("(-") + 2, "#");
		}
		
		return str.toString();
	}

	//������׺���ʽ��һ�������֣�ͬʱ����#�ͦУ�������������Ϊdouble����
	public static Double ReplaceBackMinusAndPi(String str) {
		
		if (str.contains("#")) {
			String s1 = str.replaceAll("#", "-");
			if (s1.equals("��")) {
				return Math.PI;
			} else if (s1.equals("-��")) {
				return -Math.PI;
			} else {
				return Double.parseDouble(s1);
			}
		} else if (str.equals("��")) {
			return Math.PI;
		} else if (str.equals("-��")) {
			return -Math.PI;
		} else {
			return Double.parseDouble(str);
		}

	}

	//��׺���ʽת��׺���ʽ
	public static MyQueue<String> ToSuffix(String Infix)
	{
		//��ѭ���������洢�õ��ĺ�׺���ʽ
		MyQueue<String> Suffix = new MyQueue<String>();
		
		//��ջ��ʱ�洢������
		MyStack<String> operater = new MyStack<String>();
		operater.push("="); //ջ��ѹ�Ⱥ���Ϊ������־
		
		//ʹ���ַ�����ʽ��ʱ�洢һ����
		StringBuffer temp = new StringBuffer();
		
		//��ͷɨ��ԭ��׺���ʽ
		for (int i = 0; i < Infix.length() ; i++) {
			char ch = Infix.charAt(i);
			
			char[] numberArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '#'};
			char[] functionArray = {'s', 'c', 't', 'S', 'C', 'T', 'l', 'g', 'm', 'n'};

			if (scan(numberArray, ch)) {
				while (Character.isDigit(ch) || ch == '.' || ch == '#' || ch == '��')//��δ���������ʱ��ƴ��һ������
				{
					temp.append(ch);
					ch = Infix.charAt(++i);
				}
				
				Suffix.add(temp.toString());// һ������ɨ�������д���׺���ʽ
				
				temp = new StringBuffer();
				i--;//�ظ�i
				continue;
			}else if (ch == '��') { //�����Ħ���ջ
				
				Suffix.add("��");
				
			}else if (ch == '(') { //�������������ջ
				
				operater.push("(");
				
			}else if (ch == ')') { //���������ţ���������ջ��������֮ǰ�Ĳ�������ȡ������ӵ���׺���ʽ
				
				while (operater.peek() != "(") { //ѭ����ջ
					Suffix.add(operater.pop());
				}
				operater.pop(); //�����ų�ջ
				
			}else if (ch == '+' || ch == '-') { 
				
				// ��ǰ���������ȼ����ڵ���ջ��, ѹջ�� ���򵯳�ջ��ֱ������ջ��
				// + �� - �����ȼ���ͣ����ֱ�ӽ����в���������
				while ((operater.size() > 1 && operater.peek() != "(")) {//��������ջ�����ȼ����
					Suffix.add(operater.pop());
				}
				
				operater.push(String.valueOf(ch));
				
			}else if (ch == '*' || ch == '/') {
				
				// *��/�����ȼ�����+��-
				while (operater.size() > 1 && (operater.peek() == "*" || operater.peek() == "/"
								|| operater.peek() == "^" || operater.peek() == "��" 
								|| scan(functionArray, operater.peek().charAt(0)))) {
					Suffix.add(operater.pop());
				}
				operater.push(String.valueOf(ch));
				
			}else if (scan(functionArray, ch)) {
				
				//�������ȼ�����*��/
				while (operater.size() > 1 && (scan(functionArray, operater.peek().charAt(0)) 
							|| operater.peek() == "^" || operater.peek() == "��")) {
					Suffix.add(operater.pop());
				}
				
				operater.push(String.valueOf(ch));
			
			}else if (ch == '^' || ch == '��') {
				
				//�˷��������ȼ����
				while (operater.size() > 1 && (operater.peek() == "^" || operater.peek() == "��")) {
					Suffix.add(operater.pop());
				}

				operater.push(String.valueOf(ch));
				
			}
		}
		
		//��󽫲�����ջȫ���������ײ�һ�����������ţ������жϣ�
		while (operater.size() > 0) {
			
			Suffix.add(operater.pop());
			
		}

		return Suffix;
	}

	//��׺���ʽ���㣬�Ƕ���
	public static Double Angle(MyQueue<String> Suffix) {
		
		//x1�� x2Ϊ����������nΪ������
		Double x1, x2, n;
		
		//�����ֵ��ջ
		MyStack<String> s = new MyStack<String>();
		
		while (Suffix.element() != "=") {//����=��ʱ��������
			//��������һ��Ԫ��
			String str = Suffix.remove();
			
			char ch = str.charAt(0);
			if (Character.isDigit(ch) || ch == '#' || ch == '��') {//����ѹջ
				
				s.push(str);
				
			}else if (ch == '+') {
				
				//ȡ�����������㣬������Ϊ���֣������ѹ��ջ
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 + x1;
				//����10λС��, ��ͬ
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '-') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 - x1;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '*') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x1 * x2;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '/') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 / x1;
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 's') {
				
				//���Ǻ����Ⱥ���ֻ��һ��������
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.sin(x1 * Math.PI / 180);//����Ϊ�Ƕ��� 1rad = 180��
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'c') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.cos(x1 * Math.PI / 180);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 't') {
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.tan(x1 * Math.PI / 180);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'S') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.asin(x1) * 180 / Math.PI;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'C') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.acos(x1) * 180 / Math.PI;
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'T') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.atan(x1) * 180 / Math.PI;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'l') { //��eΪ�׵Ķ���
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.log(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'g') { //��10Ϊ�׵Ķ���
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.log10(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '��') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.sqrt(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == '^') {

				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = Math.pow(x2, x1);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'm') {

				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2%x1;
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'n') {

				x1 = ReplaceBackMinusAndPi(s.pop());
				n = fact(x1.intValue()).doubleValue();
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}
		}
		
		return ReplaceBackMinusAndPi(s.pop());//���ս������Ϊ����
	}

	//��׺���ʽ��ֵ��������
	public static Double RadFigure(MyQueue<String> Suffix) {

		Double x1, x2, n;
		
		MyStack<String> s = new MyStack<String>();
		
		while (Suffix.element() != "=") {
			
			String str = Suffix.remove();
			
			char ch = str.charAt(0);
			if (Character.isDigit(ch) || ch == '#' || ch == '��') {
				
				s.push(str);
				
			}else if (ch == '+') {
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 + x1;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '-') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 - x1;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '*') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x1 * x2;
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '/') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 / x1;
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 's') {
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.sin(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'c') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.cos(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 't') {
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.tan(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'S') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.asin(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'C') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.acos(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'T') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.atan(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'l') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.log(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'g') {
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.log10(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '��') {
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.sqrt(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == '^') {

				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = Math.pow(x2, x1);
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'm') {

				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2%x1;
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}else if (ch == 'n') {

				x1 = ReplaceBackMinusAndPi(s.pop());
				n = fact(x1.intValue()).doubleValue();
				s.push(String.valueOf(String.format("%.10f", n)));
				
			}
		}
		
		return ReplaceBackMinusAndPi(s.pop());
	}
	
	//ɨ�������а���ĳ��Ԫ��
	public static boolean scan(char[] array, char key) {
		for (int i = 0 ; i < array.length ; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		
		return false;
	}
	
	//��׳�, �������-1
	public static Integer fact(int n) {
	
		if (n == 1 || n == 0) {
			return 1;
		}
		
		if (n * fact(n - 1) <= 0) {//�������
			return -1;
		}

		return n * fact(n - 1);
	}
}

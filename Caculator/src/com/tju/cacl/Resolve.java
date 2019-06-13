package com.tju.cacl;

import java.util.*;
import com.tju.ds.MyQueue;
import com.tju.ds.MyStack;

/*
 * Resolve类：用于解析输入的数学表达式的函数
 * 将中缀表达式变为后缀表达式，再进行运算。
 */
public class Resolve {

	//将原表达式中的负号替换为#
	public static String ReplaceMinus(String string) {

		StringBuffer str = new StringBuffer(string);

		// 负号出现在表达式开头
		if (string.startsWith("-")) {
			str.replace(0, 1, "#");
		}

		// 负号出现在表达式中间，必须加括号
		while (str.toString().contains("(-")) {
			str.replace(str.indexOf("(-") + 1, str.indexOf("(-") + 2, "#");
		}
		
		return str.toString();
	}

	//解析后缀表达式中一个长数字（同时解析#和π），并将它解析为double数字
	public static Double ReplaceBackMinusAndPi(String str) {
		
		if (str.contains("#")) {
			String s1 = str.replaceAll("#", "-");
			if (s1.equals("π")) {
				return Math.PI;
			} else if (s1.equals("-π")) {
				return -Math.PI;
			} else {
				return Double.parseDouble(s1);
			}
		} else if (str.equals("π")) {
			return Math.PI;
		} else if (str.equals("-π")) {
			return -Math.PI;
		} else {
			return Double.parseDouble(str);
		}

	}

	//中缀表达式转后缀表达式
	public static MyQueue<String> ToSuffix(String Infix)
	{
		//用循环队列来存储得到的后缀表达式
		MyQueue<String> Suffix = new MyQueue<String>();
		
		//用栈暂时存储操作符
		MyStack<String> operater = new MyStack<String>();
		operater.push("="); //栈底压等号作为结束标志
		
		//使用字符串形式临时存储一个数
		StringBuffer temp = new StringBuffer();
		
		//从头扫描原中缀表达式
		for (int i = 0; i < Infix.length() ; i++) {
			char ch = Infix.charAt(i);
			
			char[] numberArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '#'};
			char[] functionArray = {'s', 'c', 't', 'S', 'C', 'T', 'l', 'g', 'm', 'n'};

			if (scan(numberArray, ch)) {
				while (Character.isDigit(ch) || ch == '.' || ch == '#' || ch == 'π')//当未出现运算符时，拼接一个数字
				{
					temp.append(ch);
					ch = Infix.charAt(++i);
				}
				
				Suffix.add(temp.toString());// 一个数字扫描结束，写入后缀表达式
				
				temp = new StringBuffer();
				i--;//回复i
				continue;
			}else if (ch == 'π') { //单独的π入栈
				
				Suffix.add("π");
				
			}else if (ch == '(') { //左括号入操作数栈
				
				operater.push("(");
				
			}else if (ch == ')') { //遇到右括号，将操作符栈中左括号之前的操作符都取出，添加到后缀表达式
				
				while (operater.peek() != "(") { //循环出栈
					Suffix.add(operater.pop());
				}
				operater.pop(); //左括号出栈
				
			}else if (ch == '+' || ch == '-') { 
				
				// 当前操作符优先级大于等于栈顶, 压栈， 否则弹出栈顶直到大于栈顶
				// + 、 - 号优先级最低，因此直接将所有操作符弹出
				while ((operater.size() > 1 && operater.peek() != "(")) {//左括号在栈中优先级最低
					Suffix.add(operater.pop());
				}
				
				operater.push(String.valueOf(ch));
				
			}else if (ch == '*' || ch == '/') {
				
				// *、/号优先级高于+、-
				while (operater.size() > 1 && (operater.peek() == "*" || operater.peek() == "/"
								|| operater.peek() == "^" || operater.peek() == "√" 
								|| scan(functionArray, operater.peek().charAt(0)))) {
					Suffix.add(operater.pop());
				}
				operater.push(String.valueOf(ch));
				
			}else if (scan(functionArray, ch)) {
				
				//函数优先级高于*、/
				while (operater.size() > 1 && (scan(functionArray, operater.peek().charAt(0)) 
							|| operater.peek() == "^" || operater.peek() == "√")) {
					Suffix.add(operater.pop());
				}
				
				operater.push(String.valueOf(ch));
			
			}else if (ch == '^' || ch == '√') {
				
				//乘方开方优先级最高
				while (operater.size() > 1 && (operater.peek() == "^" || operater.peek() == "√")) {
					Suffix.add(operater.pop());
				}

				operater.push(String.valueOf(ch));
				
			}
		}
		
		//最后将操作符栈全部弹出（底部一定不是左括号，无需判断）
		while (operater.size() > 0) {
			
			Suffix.add(operater.pop());
			
		}

		return Suffix;
	}

	//后缀表达式计算，角度制
	public static Double Angle(MyQueue<String> Suffix) {
		
		//x1， x2为两操作数，n为运算结果
		Double x1, x2, n;
		
		//存放数值的栈
		MyStack<String> s = new MyStack<String>();
		
		while (Suffix.element() != "=") {//读到=号时，输出结果
			//队列中下一个元素
			String str = Suffix.remove();
			
			char ch = str.charAt(0);
			if (Character.isDigit(ch) || ch == '#' || ch == 'π') {//数字压栈
				
				s.push(str);
				
			}else if (ch == '+') {
				
				//取出两个数运算，并解析为数字，将结果压入栈
				x1 = ReplaceBackMinusAndPi(s.pop());
				x2 = ReplaceBackMinusAndPi(s.pop());
				n = x2 + x1;
				//保留10位小数, 下同
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
				
				//三角函数等函数只有一个操作数
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.sin(x1 * Math.PI / 180);//换算为角度制 1rad = 180度
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
			
			}else if (ch == 'l') { //以e为底的对数
			
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.log(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == 'g') { //以10为底的对数
				
				x1 = ReplaceBackMinusAndPi(s.pop());
				n = Math.log10(x1);
				s.push(String.valueOf(String.format("%.10f", n)));
			
			}else if (ch == '√') {
			
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
		
		return ReplaceBackMinusAndPi(s.pop());//最终结果解析为数字
	}

	//后缀表达式求值，弧度制
	public static Double RadFigure(MyQueue<String> Suffix) {

		Double x1, x2, n;
		
		MyStack<String> s = new MyStack<String>();
		
		while (Suffix.element() != "=") {
			
			String str = Suffix.remove();
			
			char ch = str.charAt(0);
			if (Character.isDigit(ch) || ch == '#' || ch == 'π') {
				
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
			
			}else if (ch == '√') {
			
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
	
	//扫描数组中包含某个元素
	public static boolean scan(char[] array, char key) {
		for (int i = 0 ; i < array.length ; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		
		return false;
	}
	
	//算阶乘, 溢出返回-1
	public static Integer fact(int n) {
	
		if (n == 1 || n == 0) {
			return 1;
		}
		
		if (n * fact(n - 1) <= 0) {//数据溢出
			return -1;
		}

		return n * fact(n - 1);
	}
}

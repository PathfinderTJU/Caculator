package com.tju.cacl;

import java.awt.*;
import java.awt.event.*;
import javax.naming.NameAlreadyBoundException;
import javax.swing.*;
import com.tju.ds.MyQueue;

/*
 * CalcFrame类:计算器主类
 * 用于设置计算器的外观、设置监听、进行运算的逻辑
 */
public class CalcFrame extends JFrame {

	// 声明按钮与文本框
	// 顶部面板
	private JTextField text1;// 下方文本框1，用于接受用户输入
	private JTextField text2;// 上方文本框2，用于显示运算式及答案

	// 右侧基本面板
	private JButton btn1;// 1
	private JButton btn2;// 2
	private JButton btn3;// 3
	private JButton btn4;// 4
	private JButton btn5;// 5
	private JButton btn6;// 6
	private JButton btn7;// 7
	private JButton btn8;// 8
	private JButton btn9;// 9
	private JButton btn0;// 0
	private JButton btnPoint;// 小数点
	private JButton btnAdd;// +
	private JButton btnSub;// -
	private JButton btnMul;// *
	private JButton btnDiv;// /
	private JButton btnPow;// ^
	private JButton btnOk;// =
	private JButton btnSign;// 正负号
	private JButton btnFlush;// 清除
	private JButton btnSqrt;// 根号
	private JButton btnReverse;// 倒数
	private JButton btnBackSpace;// 退格

	// 科学计算器左侧
	private JButton btnleft; // 左括号
	private JButton btnright; // 右括号
	private JButton btnMod; // 取模
	private JButton btnSin; // sin
	private JButton btnAsin; // arcsin
	private JButton btnCos; // cos
	private JButton btnAcos; // arccos
	private JButton btnTan; // tan
	private JButton btnAtan; // arctan
	private JButton btnLn; // ln
	private JButton btnN; // 阶乘
	private JButton btnLog; // lg
	private JButton btnPi; // π

	// 弧度角度切换
	private JRadioButton rbtnAngle; // 角度
	private JRadioButton rbtnRad; // 弧度

	// 菜单栏
	private JMenuBar menubar;// 菜单栏
	private JMenu menu_help; // 帮助菜单
	private JMenuItem help_about; // 关于选项卡

	// 面板
	private JPanel panel1; // 科学计算器按钮面板

	// 计算器构造方法，设置一些参数并且初始化面板、菜单栏和监听器
	public CalcFrame() {
		super("计算器");// 程序名称
		this.setResizable(false);// 程序窗口大小不可改变
		this.setSize(500, 335);// 设置窗口大小
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 设置点击关闭按钮直接关闭

		initPanel1(); // 初始化面板
		initBar(); // 初始化菜单栏
		initListener();// 初始化监听器

		this.setLocationRelativeTo(null);// 默认初始显示为桌面中央
		this.setVisible(true);// 设置可见

	}

	// 初始化菜单栏
	public void initBar() {
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		menu_help = new JMenu("帮助(H)");
		menu_help.setMnemonic('H');
		menubar.add(menu_help);
		help_about = new JMenuItem("关于计算器");
		menu_help.add(help_about);

		this.add(panel1);
	}

	// 初始化主面板
	public void initPanel1() {
		panel1 = new JPanel();
		panel1.setLayout(null); // 默认自由布局
		panel1.setSize(500, 335);

		// 设置角度弧度切换的单选按钮，并添加到一个group中
		rbtnAngle = new JRadioButton("角度");
		rbtnRad = new JRadioButton("弧度");
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbtnAngle);
		btnGroup.add(rbtnRad);
		rbtnAngle.setBounds(20, 65, 80, 30);
		rbtnAngle.setForeground(Color.blue);
		rbtnAngle.setSelected(true);
		rbtnRad.setBounds(100, 65, 80, 30);
		rbtnRad.setForeground(Color.blue);

		// 注册按钮(按行排序)
		btnFlush = new MyButton("C", 260, 100, 50, 30);
		btnBackSpace = new MyButton("<-----", 315, 100, 105, 30);
		btnDiv = new MyButton("/", 425, 100, 45, 30);

		btn1 = new MyButton("1", 260, 135, 50, 30);
		btn2 = new MyButton("2", 315, 135, 50, 30);
		btn3 = new MyButton("3", 370, 135, 50, 30);
		btnMul = new MyButton("*", 425, 135, 45, 30);

		btn4 = new MyButton("4", 260, 170, 50, 30);
		btn5 = new MyButton("5", 315, 170, 50, 30);
		btn6 = new MyButton("6", 370, 170, 50, 30);
		btnSub = new MyButton("-", 425, 170, 45, 30);

		btn7 = new MyButton("7", 260, 205, 50, 30);
		btn8 = new MyButton("8", 315, 205, 50, 30);
		btn9 = new MyButton("9", 370, 205, 50, 30);
		btnAdd = new MyButton("+", 425, 205, 45, 30);

		btnSign = new MyButton("±", 260, 240, 50, 30);
		btn0 = new MyButton("0", 315, 240, 50, 30);
		btnPoint = new MyButton(".", 370, 240, 50, 30);
		btnOk = new MyButton("=", 425, 240, 45, 30);

		btnMod = new MyButton("Mod", 20, 100, 55, 30);
		btnPi = new MyButton("π", 80, 100, 35, 30);
		btnleft = new MyButton("(", 120, 100, 35, 30);
		btnright = new MyButton(")", 160, 100, 35, 30);

		btnLog = new MyButton("log", 20, 135, 55, 30);
		btnSin = new MyButton("sin", 80, 135, 55, 30);
		btnAsin = new MyButton("asin", 140, 135, 55, 30);

		btnLn = new MyButton("ln", 20, 170, 55, 30);
		btnCos = new MyButton("cos", 80, 170, 55, 30);
		btnAcos = new MyButton("acos", 140, 170, 55, 30);

		btnN = new MyButton("n!", 20, 205, 55, 30);
		btnTan = new MyButton("tan", 80, 205, 55, 30);
		btnAtan = new MyButton("atan", 140, 205, 55, 30);

		btnPow = new MyButton("^", 20, 240, 55, 30);
		btnSqrt = new MyButton("√", 80, 240, 55, 30);
		btnReverse = new MyButton("1/x", 140, 240, 55, 30);

		// 向面板中添加已经注册好的按钮
		panel1.add(btn1);
		panel1.add(btn2);
		panel1.add(btn3);
		panel1.add(btn4);
		panel1.add(btn5);
		panel1.add(btn6);
		panel1.add(btn7);
		panel1.add(btn8);
		panel1.add(btn9);
		panel1.add(btn0);
		panel1.add(btnPoint);
		panel1.add(btnAdd);
		panel1.add(btnSub);
		panel1.add(btnMul);
		panel1.add(btnDiv);
		panel1.add(btnPow);
		panel1.add(btnOk);
		panel1.add(btnSign);
		panel1.add(btnFlush);
		panel1.add(btnSqrt);
		panel1.add(btnReverse);
		panel1.add(btnBackSpace);
		panel1.add(btnleft);
		panel1.add(btnright);
		panel1.add(rbtnAngle);
		panel1.add(rbtnRad);
		panel1.add(btnLn);
		panel1.add(btnSin);
		panel1.add(btnCos);
		panel1.add(btnTan);
		panel1.add(btnAsin);
		panel1.add(btnAcos);
		panel1.add(btnAtan);
		panel1.add(btnMod);
		panel1.add(btnN);
		panel1.add(btnLog);
		panel1.add(btnPi);

		text1 = new JTextField(); // 注册下方文本框1
		text1.setBounds(20, 35, 450, 25);
		text1.setFont(new Font("宋体", Font.PLAIN, 18)); // 设置字体
		text1.setHorizontalAlignment(JTextField.RIGHT); // 设置右对齐
		text1.setBackground(new Color(100, 149, 237)); // 设置背景色
		text1.setBorder(null);// 设置没有边框

		text2 = new JTextField();// 注册上方文本框2（默认字体）
		text2.setBounds(20, 10, 450, 25);
		text2.setHorizontalAlignment(JTextField.RIGHT);
		text2.setBackground(new Color(100, 149, 237));
		text2.setBorder(null);

		// 添加注册好的文本框
		panel1.add(text1);
		panel1.add(text2);

	}

	// 初始化监听器
	public void initListener() {

		// 为数字、函数等按键添加相同的监听器
		btn1.addActionListener(new ButtonAction());
		btn2.addActionListener(new ButtonAction());
		btn3.addActionListener(new ButtonAction());
		btn4.addActionListener(new ButtonAction());
		btn5.addActionListener(new ButtonAction());
		btn6.addActionListener(new ButtonAction());
		btn7.addActionListener(new ButtonAction());
		btn8.addActionListener(new ButtonAction());
		btn9.addActionListener(new ButtonAction());
		btn0.addActionListener(new ButtonAction());
		btnPoint.addActionListener(new ButtonAction());
		btnAdd.addActionListener(new ButtonAction());
		btnSub.addActionListener(new ButtonAction());
		btnMul.addActionListener(new ButtonAction());
		btnDiv.addActionListener(new ButtonAction());
		btnPow.addActionListener(new ButtonAction());
		btnOk.addActionListener(new ButtonAction());
		btnSqrt.addActionListener(new ButtonAction());
		btnSin.addActionListener(new ButtonAction());
		btnCos.addActionListener(new ButtonAction());
		btnTan.addActionListener(new ButtonAction());
		btnAcos.addActionListener(new ButtonAction());
		btnAsin.addActionListener(new ButtonAction());
		btnAtan.addActionListener(new ButtonAction());
		btnLog.addActionListener(new ButtonAction());
		btnLn.addActionListener(new ButtonAction());
		btnleft.addActionListener(new ButtonAction());
		btnright.addActionListener(new ButtonAction());
		btnPi.addActionListener(new ButtonAction());
		btnSign.addActionListener(new ButtonAction());
		btnMod.addActionListener(new ButtonAction());
		btnReverse.addActionListener(new ButtonAction());
		btnN.addActionListener(new ButtonAction());

		// 清屏按钮
		btnFlush.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 直接将两个文本框清空
				text1.setText(null);
				text2.setText(null);
			}

		});

		// 退格按钮
		btnBackSpace.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 内容为空时无操作，否则截取除去最后一个字符的剩余表达式
				if (!text2.getText().equals("")) {
					text2.setText(text2.getText().substring(0, text2.getText().length() - 1));
				}
			}

		});

		// 关于菜单
		help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 四个参数为：当前类，显示的文字内容，弹窗类型（提示消息），左侧提示图片
				JOptionPane.showMessageDialog(CalcFrame.this, "程序设计实践3作业\n我的科学计算器\n作者：3017218063 刘兴宇\n", "关于",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		});

		// 关闭按钮
		this.addWindowListener(new WindowAdapter() { // 点击关闭按钮
			public void windowClosing(WindowEvent e) {// 关闭窗口监听器
				super.windowClosing(e);
				// 创建一个提示框，并获取点击按钮为确定还是取消
				int n = JOptionPane.showConfirmDialog(CalcFrame.this, "     确认退出吗？", "提示", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				// 点击确定，退出程序
				if (n == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	/**
	 * 将中缀表达式中的函数名替换为单个字符，替换规则为： sin-->s cos-->c tan-->t asin-->S acos-->C atan-->T
	 * log-->g ln-->l mod-->m 阶乘-->n
	 */
	public String transform(String string) {
		StringBuffer stringbuffer = new StringBuffer(string);
		String[] functions = { "sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "Mod", "n!" };
		String[] code = { "s", "c", "t", "S", "C", "T", "g", "l", "m", "n" };
		int n = -1;

		while ((n = check(functions, stringbuffer)) != -1) {
			int lengthOfFunction = functions[n].length();
			stringbuffer.replace(stringbuffer.indexOf(functions[n]), stringbuffer.indexOf(functions[n]) + lengthOfFunction, code[n]);
		}

		return stringbuffer.toString();
	}

	// 判断表达式中是否包含函数名，返回包括的函数名在functions数组中的下标，不存在返回-1
	public static int check(String[] functions, StringBuffer string) {
		String temp = string.toString();
		for (int i = 0; i < functions.length; i++) {
			if (temp.contains(functions[i])) {
				return i;
			}
		}
		return -1;
	}

	// 扫描数组中包含某个元素
	public static boolean scan(String[] array, String key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(key)) {
				return true;
			}
		}

		return false;
	}

	// 输入按钮监听器类，继承自ActionListener监听器类
	class ButtonAction implements ActionListener {

		// 不同的指令集
		private String[] repeatable = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "√", "sin", "cos", "tan",
				"asin", "acos", "atan", "log", "ln", "n!", "(", ")" };
		private String[] numbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		private String[] mul = { "√", "sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "n!", "(" };
		private String[] unrepeat = { "π", ".", "+", "-", "*", "/", "^" };

		// 触发监听器时执行的方法
		public void actionPerformed(ActionEvent e) {
			String thisCommand = e.getActionCommand();//存放本次点击的命令
			
			//用于一次计算后再次计算，将下方文本框中结果放入上方文本框中作为下一运算式的一部分，清空下方文本框
			if (!text1.getText().equals("")) {
				text2.setText(text1.getText());
				text1.setText("");
			}
			
			//对不同的按键进行操作
			if (scan(repeatable, thisCommand)) {//repeatable为可以连续重复出现的指令
				if (text2.getText().equals("")) {//首次输入时，下同
					text2.setText(thisCommand);
				} else {//错误结束后输入
					if (text2.getText().equals("Error! Please Check!")) {
						text2.setText(thisCommand);
					} else {
						if (scan(mul, thisCommand)) {//非数字的指令前一个不能为非数字
							//last为当前算式最后一个字符
							String last = String.valueOf(text2.getText().charAt(text2.getText().length() - 1));
							if (scan(numbers, last)) {
								text2.setText("Error! Please Check!");
							} else {
								text2.setText(text2.getText() + thisCommand);
							}
						} else {
							text2.setText(text2.getText() + thisCommand);
						}
					}
				}
			} else if (scan(unrepeat, thisCommand)) {//不可连续重复出现的字符
				if (text2.getText().equals("")) {
					if (thisCommand.equals("π")) {//包括π
						text2.setText(thisCommand);
					}else {
						text2.setText("Error! Please Check!");
					}
				} else {
					String last = String.valueOf(text2.getText().charAt(text2.getText().length() - 1));
					if (thisCommand.equals("π")) {
						if (last.equals("π")) {
							text2.setText("Error! Please Check!");
						}else {
							text2.setText(text2.getText() + thisCommand);
						}
					}
					else if  (scan(unrepeat, last) && !last.equals("π")) {
						String temp = text2.getText().substring(0, text2.getText().length() - 2);
						text2.setText(temp + thisCommand);
					} else if (scan(numbers, last) || last.equals("π") || last.equals(")")) {
						text2.setText(text2.getText() + thisCommand);
					} else {
						text2.setText("Error! Please Check!");
					}
				}
			} else if (thisCommand.equals("Mod")) {//mod运算特殊，前面必须为数字
				if (text2.getText().equals("")) {
					text2.setText("Error! Please Check!");
				} else {
					String last = String.valueOf(text2.getText().charAt(text2.getText().length() - 1));

					if ((!scan(numbers, last) && !last.equals(")")) || last == "d") {
						text2.setText("Error! Please Check!");
					} else {
						text2.setText(text2.getText() + thisCommand);
					}
				}
			} else if (thisCommand.equals("1/x")) {//倒数，只输出分子，分母留给用户输入
				if (text2.getText().equals("")) {
					text2.setText("1/");
				} else {
					String last = String.valueOf(text2.getText().charAt(text2.getText().length() - 1));

					if (scan(numbers, last)) {
						text2.setText("Error! Please Check!");
					} else {
						text2.setText(text2.getText() + "1/");
					}
				}
			} else if (thisCommand.equals("±")) {//正负号，当前为正时（默认无符号）变为负号，否则去掉负号
				if (text2.getText().equals("")) {
					text2.setText("-");
				} else {

					String last = String.valueOf(text2.getText().charAt(text2.getText().length() - 1));

					if (last.equals("-")) {
						String temp = text2.getText().substring(0, text2.getText().length() - 1);
						text2.setText(temp);
					} else {
						text2.setText(text2.getText() + "-");
					}
				}
			} else if (thisCommand.equals("=")) {//为等于时，计算表达式
				if (rbtnRad.isSelected()) {//弧度制计算
					try {
						text2.setText(text2.getText() + "=");
						
						//单个运算符时，直接等于报错
						if (scan(mul, text2.getText()) || (scan(unrepeat, text2.getText()) && text2.getText() != "π")) {
							text1.setText(null);
							text2.setText("Error! Please Check!");
						} else {
							String s1 = transform(text2.getText());//转换函数
							String s = Resolve.ReplaceMinus(s1);//替换负号
							MyQueue<String> PFX = Resolve.ToSuffix(s);//转后缀表达式
							Double result = Resolve.RadFigure(PFX);//计算
							text1.setText(result.toString());
						}
					} catch (Exception e1) {
						text1.setText(null);
						text2.setText("Error! Please Check!");
						e1.printStackTrace();
					}
				} else if (rbtnAngle.isSelected()) {//角度制计算
					try {
						text2.setText(text2.getText() + "=");
						if (scan(mul, text2.getText()) || (scan(unrepeat, text2.getText()) && text2.getText() != "π")) {
							text1.setText(null);
							text2.setText("Error! Please Check!");
						} else {
							String s1 = transform(text2.getText());
							String s = Resolve.ReplaceMinus(s1);
							MyQueue<String> PFX = Resolve.ToSuffix(s);
							Double result = Resolve.Angle(PFX);
							text1.setText(result.toString());
						}
					} catch (Exception e1) {
						text1.setText(null);
						text2.setText("Error! Please Check!");
						e1.printStackTrace();
					}

				}

			}
		}

	}

}

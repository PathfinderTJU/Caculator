package com.tju.cacl;

import java.awt.*;
import java.awt.event.*;
import javax.naming.NameAlreadyBoundException;
import javax.swing.*;
import com.tju.ds.MyQueue;

/*
 * CalcFrame��:����������
 * �������ü���������ۡ����ü���������������߼�
 */
public class CalcFrame extends JFrame {

	// ������ť���ı���
	// �������
	private JTextField text1;// �·��ı���1�����ڽ����û�����
	private JTextField text2;// �Ϸ��ı���2��������ʾ����ʽ����

	// �Ҳ�������
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
	private JButton btnPoint;// С����
	private JButton btnAdd;// +
	private JButton btnSub;// -
	private JButton btnMul;// *
	private JButton btnDiv;// /
	private JButton btnPow;// ^
	private JButton btnOk;// =
	private JButton btnSign;// ������
	private JButton btnFlush;// ���
	private JButton btnSqrt;// ����
	private JButton btnReverse;// ����
	private JButton btnBackSpace;// �˸�

	// ��ѧ���������
	private JButton btnleft; // ������
	private JButton btnright; // ������
	private JButton btnMod; // ȡģ
	private JButton btnSin; // sin
	private JButton btnAsin; // arcsin
	private JButton btnCos; // cos
	private JButton btnAcos; // arccos
	private JButton btnTan; // tan
	private JButton btnAtan; // arctan
	private JButton btnLn; // ln
	private JButton btnN; // �׳�
	private JButton btnLog; // lg
	private JButton btnPi; // ��

	// ���ȽǶ��л�
	private JRadioButton rbtnAngle; // �Ƕ�
	private JRadioButton rbtnRad; // ����

	// �˵���
	private JMenuBar menubar;// �˵���
	private JMenu menu_help; // �����˵�
	private JMenuItem help_about; // ����ѡ�

	// ���
	private JPanel panel1; // ��ѧ��������ť���

	// ���������췽��������һЩ�������ҳ�ʼ����塢�˵����ͼ�����
	public CalcFrame() {
		super("������");// ��������
		this.setResizable(false);// ���򴰿ڴ�С���ɸı�
		this.setSize(500, 335);// ���ô��ڴ�С
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // ���õ���رհ�ťֱ�ӹر�

		initPanel1(); // ��ʼ�����
		initBar(); // ��ʼ���˵���
		initListener();// ��ʼ��������

		this.setLocationRelativeTo(null);// Ĭ�ϳ�ʼ��ʾΪ��������
		this.setVisible(true);// ���ÿɼ�

	}

	// ��ʼ���˵���
	public void initBar() {
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		menu_help = new JMenu("����(H)");
		menu_help.setMnemonic('H');
		menubar.add(menu_help);
		help_about = new JMenuItem("���ڼ�����");
		menu_help.add(help_about);

		this.add(panel1);
	}

	// ��ʼ�������
	public void initPanel1() {
		panel1 = new JPanel();
		panel1.setLayout(null); // Ĭ�����ɲ���
		panel1.setSize(500, 335);

		// ���ýǶȻ����л��ĵ�ѡ��ť������ӵ�һ��group��
		rbtnAngle = new JRadioButton("�Ƕ�");
		rbtnRad = new JRadioButton("����");
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbtnAngle);
		btnGroup.add(rbtnRad);
		rbtnAngle.setBounds(20, 65, 80, 30);
		rbtnAngle.setForeground(Color.blue);
		rbtnAngle.setSelected(true);
		rbtnRad.setBounds(100, 65, 80, 30);
		rbtnRad.setForeground(Color.blue);

		// ע�ᰴť(��������)
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

		btnSign = new MyButton("��", 260, 240, 50, 30);
		btn0 = new MyButton("0", 315, 240, 50, 30);
		btnPoint = new MyButton(".", 370, 240, 50, 30);
		btnOk = new MyButton("=", 425, 240, 45, 30);

		btnMod = new MyButton("Mod", 20, 100, 55, 30);
		btnPi = new MyButton("��", 80, 100, 35, 30);
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
		btnSqrt = new MyButton("��", 80, 240, 55, 30);
		btnReverse = new MyButton("1/x", 140, 240, 55, 30);

		// �����������Ѿ�ע��õİ�ť
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

		text1 = new JTextField(); // ע���·��ı���1
		text1.setBounds(20, 35, 450, 25);
		text1.setFont(new Font("����", Font.PLAIN, 18)); // ��������
		text1.setHorizontalAlignment(JTextField.RIGHT); // �����Ҷ���
		text1.setBackground(new Color(100, 149, 237)); // ���ñ���ɫ
		text1.setBorder(null);// ����û�б߿�

		text2 = new JTextField();// ע���Ϸ��ı���2��Ĭ�����壩
		text2.setBounds(20, 10, 450, 25);
		text2.setHorizontalAlignment(JTextField.RIGHT);
		text2.setBackground(new Color(100, 149, 237));
		text2.setBorder(null);

		// ���ע��õ��ı���
		panel1.add(text1);
		panel1.add(text2);

	}

	// ��ʼ��������
	public void initListener() {

		// Ϊ���֡������Ȱ��������ͬ�ļ�����
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

		// ������ť
		btnFlush.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// ֱ�ӽ������ı������
				text1.setText(null);
				text2.setText(null);
			}

		});

		// �˸�ť
		btnBackSpace.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// ����Ϊ��ʱ�޲����������ȡ��ȥ���һ���ַ���ʣ����ʽ
				if (!text2.getText().equals("")) {
					text2.setText(text2.getText().substring(0, text2.getText().length() - 1));
				}
			}

		});

		// ���ڲ˵�
		help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ĸ�����Ϊ����ǰ�࣬��ʾ���������ݣ��������ͣ���ʾ��Ϣ���������ʾͼƬ
				JOptionPane.showMessageDialog(CalcFrame.this, "�������ʵ��3��ҵ\n�ҵĿ�ѧ������\n���ߣ�3017218063 ������\n", "����",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		});

		// �رհ�ť
		this.addWindowListener(new WindowAdapter() { // ����رհ�ť
			public void windowClosing(WindowEvent e) {// �رմ��ڼ�����
				super.windowClosing(e);
				// ����һ����ʾ�򣬲���ȡ�����ťΪȷ������ȡ��
				int n = JOptionPane.showConfirmDialog(CalcFrame.this, "     ȷ���˳���", "��ʾ", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				// ���ȷ�����˳�����
				if (n == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	/**
	 * ����׺���ʽ�еĺ������滻Ϊ�����ַ����滻����Ϊ�� sin-->s cos-->c tan-->t asin-->S acos-->C atan-->T
	 * log-->g ln-->l mod-->m �׳�-->n
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

	// �жϱ��ʽ���Ƿ���������������ذ����ĺ�������functions�����е��±꣬�����ڷ���-1
	public static int check(String[] functions, StringBuffer string) {
		String temp = string.toString();
		for (int i = 0; i < functions.length; i++) {
			if (temp.contains(functions[i])) {
				return i;
			}
		}
		return -1;
	}

	// ɨ�������а���ĳ��Ԫ��
	public static boolean scan(String[] array, String key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(key)) {
				return true;
			}
		}

		return false;
	}

	// ���밴ť�������࣬�̳���ActionListener��������
	class ButtonAction implements ActionListener {

		// ��ͬ��ָ�
		private String[] repeatable = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "��", "sin", "cos", "tan",
				"asin", "acos", "atan", "log", "ln", "n!", "(", ")" };
		private String[] numbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		private String[] mul = { "��", "sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "n!", "(" };
		private String[] unrepeat = { "��", ".", "+", "-", "*", "/", "^" };

		// ����������ʱִ�еķ���
		public void actionPerformed(ActionEvent e) {
			String thisCommand = e.getActionCommand();//��ű��ε��������
			
			//����һ�μ�����ٴμ��㣬���·��ı����н�������Ϸ��ı�������Ϊ��һ����ʽ��һ���֣�����·��ı���
			if (!text1.getText().equals("")) {
				text2.setText(text1.getText());
				text1.setText("");
			}
			
			//�Բ�ͬ�İ������в���
			if (scan(repeatable, thisCommand)) {//repeatableΪ���������ظ����ֵ�ָ��
				if (text2.getText().equals("")) {//�״�����ʱ����ͬ
					text2.setText(thisCommand);
				} else {//�������������
					if (text2.getText().equals("Error! Please Check!")) {
						text2.setText(thisCommand);
					} else {
						if (scan(mul, thisCommand)) {//�����ֵ�ָ��ǰһ������Ϊ������
							//lastΪ��ǰ��ʽ���һ���ַ�
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
			} else if (scan(unrepeat, thisCommand)) {//���������ظ����ֵ��ַ�
				if (text2.getText().equals("")) {
					if (thisCommand.equals("��")) {//������
						text2.setText(thisCommand);
					}else {
						text2.setText("Error! Please Check!");
					}
				} else {
					String last = String.valueOf(text2.getText().charAt(text2.getText().length() - 1));
					if (thisCommand.equals("��")) {
						if (last.equals("��")) {
							text2.setText("Error! Please Check!");
						}else {
							text2.setText(text2.getText() + thisCommand);
						}
					}
					else if  (scan(unrepeat, last) && !last.equals("��")) {
						String temp = text2.getText().substring(0, text2.getText().length() - 2);
						text2.setText(temp + thisCommand);
					} else if (scan(numbers, last) || last.equals("��") || last.equals(")")) {
						text2.setText(text2.getText() + thisCommand);
					} else {
						text2.setText("Error! Please Check!");
					}
				}
			} else if (thisCommand.equals("Mod")) {//mod�������⣬ǰ�����Ϊ����
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
			} else if (thisCommand.equals("1/x")) {//������ֻ������ӣ���ĸ�����û�����
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
			} else if (thisCommand.equals("��")) {//�����ţ���ǰΪ��ʱ��Ĭ���޷��ţ���Ϊ���ţ�����ȥ������
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
			} else if (thisCommand.equals("=")) {//Ϊ����ʱ��������ʽ
				if (rbtnRad.isSelected()) {//�����Ƽ���
					try {
						text2.setText(text2.getText() + "=");
						
						//���������ʱ��ֱ�ӵ��ڱ���
						if (scan(mul, text2.getText()) || (scan(unrepeat, text2.getText()) && text2.getText() != "��")) {
							text1.setText(null);
							text2.setText("Error! Please Check!");
						} else {
							String s1 = transform(text2.getText());//ת������
							String s = Resolve.ReplaceMinus(s1);//�滻����
							MyQueue<String> PFX = Resolve.ToSuffix(s);//ת��׺���ʽ
							Double result = Resolve.RadFigure(PFX);//����
							text1.setText(result.toString());
						}
					} catch (Exception e1) {
						text1.setText(null);
						text2.setText("Error! Please Check!");
						e1.printStackTrace();
					}
				} else if (rbtnAngle.isSelected()) {//�Ƕ��Ƽ���
					try {
						text2.setText(text2.getText() + "=");
						if (scan(mul, text2.getText()) || (scan(unrepeat, text2.getText()) && text2.getText() != "��")) {
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

package com.tju.cacl;

import java.awt.*;
import javax.swing.JButton;

/*
 * MyButton�ࣺ���ü�ʱ��GUI�İ�ť��ʽ���̳�ԭJButton
 */
public class MyButton extends JButton {
	
	/*
	 * ����һ����ť
	 * ���������֣�a��b��c��d�ĸ�����ı߾�
	 */
	public MyButton(String text, int a, int b, int c, int d) {
		super(text);// ���ø��๹�캯�������ð�ť�ı�
		this.setActionCommand(this.getText()); // ���ð�ť�����
		this.setForeground(Color.black);//������ɫ
		this.setBounds(a, b, c, d);// ���ð�ť��λ�ü���С
		this.setFocusPainted(false);// ���ð�ť���ʱ����ʾ�����
		this.setMargin(new Insets(0, 0, 0, 0));// ���ֱ߾�
		this.setFont(new Font("΢���ź�",Font.PLAIN,12));//����
	}
}

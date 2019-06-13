package com.tju.cacl;

import java.awt.*;
import javax.swing.JButton;

/*
 * MyButton类：设置计时器GUI的按钮样式，继承原JButton
 */
public class MyButton extends JButton {
	
	/*
	 * 构造一个按钮
	 * 参数：文字，a，b，c，d四个方向的边距
	 */
	public MyButton(String text, int a, int b, int c, int d) {
		super(text);// 调用父类构造函数，设置按钮文本
		this.setActionCommand(this.getText()); // 设置按钮命令符
		this.setForeground(Color.black);//文字颜色
		this.setBounds(a, b, c, d);// 设置按钮的位置及大小
		this.setFocusPainted(false);// 设置按钮点击时不显示焦点框
		this.setMargin(new Insets(0, 0, 0, 0));// 文字边距
		this.setFont(new Font("微软雅黑",Font.PLAIN,12));//字体
	}
}

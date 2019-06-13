package com.tju.test;

import javax.swing.*;
import com.tju.cacl.CalcFrame;

/*
 * Test类：主类
 * 用于启动计算器
 * 使用了JTatto外观框架
 */
public class Test {
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");// 设置JTatto外观
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "外观初始化失败");
		}

		new CalcFrame();
	}
}

package com.tju.test;

import javax.swing.*;
import com.tju.cacl.CalcFrame;

/*
 * Test�ࣺ����
 * ��������������
 * ʹ����JTatto��ۿ��
 */
public class Test {
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");// ����JTatto���
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��۳�ʼ��ʧ��");
		}

		new CalcFrame();
	}
}

package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ModifyPasswordPage extends JPanel {
	
	public ModifyPasswordPage(PageHandler pageHandler) {
		this.setLayout(null);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBackground(Color.WHITE);
		bodyPanel.setBounds(10,90,460,550);
		
		JLabel passwordCh = new JLabel("���� ��й�ȣ");
		passwordCh.setFont(new Font("���� ���", Font.BOLD, 25));
		passwordCh.setBounds(40, 30, 162, 35);
		bodyPanel.add(passwordCh);
		
		JPasswordField passwordFieldCh= new JPasswordField();
		passwordFieldCh.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordFieldCh.setBounds(40, 74, 380, 35);
		bodyPanel.add(passwordFieldCh);
		
		JLabel password2Ch = new JLabel("������ ��й�ȣ ");
		password2Ch.setFont(new Font("���� ���", Font.BOLD, 25));
		password2Ch.setBounds(40, 147, 200, 35);
		bodyPanel.add(password2Ch);
		
		JPasswordField passwordField2Ch = new JPasswordField();
		passwordField2Ch.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordField2Ch.setBounds(40, 192, 380, 35);
		bodyPanel.add(passwordField2Ch);
		
		JLabel password3Ch = new JLabel("��й�ȣ ���Է�");
		password3Ch.setFont(new Font("���� ���", Font.BOLD, 25));
		password3Ch.setBounds(40, 262, 200, 35);
		bodyPanel.add(password3Ch);
		
		JPasswordField passwordField3Ch = new JPasswordField();
		passwordField3Ch.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordField3Ch.setBounds(40, 307, 380, 35);
		bodyPanel.add(passwordField3Ch);
		
		JButton cancelBt = new JButton("��  ��");
		cancelBt.setBackground(new Color(211, 211, 211));
		cancelBt.setFont(new Font("���� ���", Font.BOLD, 23));
		cancelBt.setBounds(40, 400, 162, 42);
		cancelBt.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("SelfInfoPage");
			}
		});
		bodyPanel.add(cancelBt);
		
		JButton changeBt = new JButton("�����ϱ�");
		changeBt.setBackground(new Color(211, 211, 211));
		changeBt.setFont(new Font("���� ���", Font.BOLD, 23));
		changeBt.setBounds(255, 400, 162, 42);
		changeBt.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String passwdFrom = String.valueOf(passwordFieldCh.getPassword());
				String passwdTo = String.valueOf(passwordField2Ch.getPassword());
				String passwdRe = String.valueOf(passwordField3Ch.getPassword());

				if (passwdTo.equals(passwdRe)) {
					if (pageHandler.service.modifyPatientPw(passwdFrom, passwdTo, passwdRe)) {
						pageHandler.change("SelfInfoPage");
						return;
					}
				}
				
				JOptionPane.showMessageDialog(null, "��й�ȣ ���濡 �����ϼ̽��ϴ�");

			}
		});
		bodyPanel.add(changeBt);
		
		this.add(bodyPanel);
	}
}

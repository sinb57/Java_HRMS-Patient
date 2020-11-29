package main.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterPage extends JPanel{
	private Footer lowerButton;
	
	RegisterPage(PageHandler pageHandler) {
		this.setBackground(pageHandler.backgroundColor);
		this.setLayout(null);
		
		JLabel ID = new JLabel("���̵�");
		ID.setFont(new Font("���� ���", Font.BOLD, 25));
		ID.setBounds(40, 20, 92, 35);
		this.add(ID);

		JLabel IDplus = new JLabel(" (4~10������ �����ڿ� ���� ����)");
		IDplus.setFont(new Font("���� ���", Font.PLAIN, 12));
		IDplus.setBounds(120, 27, 235, 35);
		this.add(IDplus);
		
		JTextField IDField = new JTextField();
		IDField.setFont(new Font("���� ���", Font.PLAIN, 18));
		IDField.setBounds(40, 55, 400, 35);
		this.add(IDField);

		
		JLabel password = new JLabel("��й�ȣ");
		password.setFont(new Font("���� ���", Font.BOLD, 25));
		password.setBounds(40, 112, 122, 35);
		this.add(password);
		
		JLabel passwordplus = new JLabel(" (8~16������ �����ڿ� ���� ����)");
		passwordplus.setFont(new Font("���� ���", Font.PLAIN, 12));
		passwordplus.setBounds(147, 119, 193, 35);
		this.add(passwordplus);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordField.setBounds(40, 147, 400, 35);
		this.add(passwordField);

		
		JLabel password2 = new JLabel("��й�ȣ Ȯ��");
		password2.setFont(new Font("���� ���", Font.BOLD, 25));
		password2.setBounds(40, 208, 212, 30);
		this.add(password2);

		JPasswordField passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordField2.setBounds(40, 241, 400, 35);
		this.add(passwordField2);

		
		JLabel name = new JLabel("�̸�");
		name.setFont(new Font("���� ���", Font.BOLD, 25));
		name.setBounds(40, 298, 57, 30);
		this.add(name);

		JTextField nameField = new JTextField("");
		nameField.setFont(new Font("���� ���", Font.PLAIN, 25));
		nameField.setBounds(40, 331, 400, 35);
		this.add(nameField);

		
		JLabel phoneNumber= new JLabel("��ȭ��ȣ");
		phoneNumber.setFont(new Font("���� ���", Font.BOLD, 25));
		phoneNumber.setBounds(40, 391, 122, 30);
		this.add(phoneNumber);

		JLabel phoneNumberplus = new JLabel("(010-****-****)");
		phoneNumberplus.setFont(new Font("���� ���", Font.PLAIN ,15));
		phoneNumberplus.setBounds(151, 393, 101, 35);
		this.add(phoneNumberplus);

		JTextField phoneNumberField = new JTextField("");
		phoneNumberField.setFont(new Font("���� ���", Font.PLAIN, 25));
		phoneNumberField.setBounds(40, 423, 400, 35);
		this.add(phoneNumberField);

		
		JButton cancelButton = new JButton("");
		cancelButton.setIcon(new ImageIcon(pageHandler.path + "cancelIcon.png"));
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFont(new Font("���� ���", Font.BOLD, 30));
		cancelButton.setBounds(40, 484, 162, 42);
		cancelButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("LoginPage");
			}
		});
		this.add(cancelButton);

		JButton registerButton = new JButton("");
		registerButton.setIcon(new ImageIcon(pageHandler.path + "signupIcon.png"));
		registerButton.setFont(new Font("���� ���", Font.BOLD, 30));
		registerButton.setBounds(278, 484, 162, 42);
		registerButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = IDField.getText();
				String userPw = String.valueOf(passwordField.getPassword());
				String userPw2 = String.valueOf(passwordField2.getPassword());
				String userName = nameField.getText();
				String phoneNumber = phoneNumberField.getText();
				
				if (pageHandler.service.join(userId, userPw, userPw2, userName, phoneNumber)) {
					JOptionPane.showMessageDialog(null, "You have registered in successfully");
					pageHandler.change("HospitalListPage");
				} else {
					JOptionPane.showMessageDialog(null, "You failed to register");
				}
			}
		});
		this.add(registerButton);

	}
	
}

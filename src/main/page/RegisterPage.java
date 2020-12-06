package main.page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterPage extends JPanel {

	RegisterPage(PageHandler pageHandler) {
		this.setBackground(pageHandler.backgroundColor);
		this.setLayout(null);

		JLabel ID = new JLabel("���̵�");
		ID.setFont(new Font("���� ���", Font.BOLD, 25));
		ID.setBounds(40, 40, 92, 35);
		this.add(ID);

		JLabel IDplus = new JLabel(" (4~10������ �����ڿ� ���� ����)");
		IDplus.setFont(new Font("���� ���", Font.PLAIN, 12));
		IDplus.setBounds(120, 47, 235, 35);
		this.add(IDplus);

		JTextField IDField = new JTextField();
		IDField.setFont(new Font("���� ���", Font.PLAIN, 18));
		IDField.setBounds(40, 75, 400, 35);
		this.add(IDField);

		JLabel password = new JLabel("��й�ȣ");
		password.setFont(new Font("���� ���", Font.BOLD, 25));
		password.setBounds(40, 132, 122, 35);
		this.add(password);

		JLabel passwordplus = new JLabel(" (8~16������ �����ڿ� ���� ����)");
		passwordplus.setFont(new Font("���� ���", Font.PLAIN, 12));
		passwordplus.setBounds(147, 139, 193, 35);
		this.add(passwordplus);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordField.setBounds(40, 167, 400, 35);
		this.add(passwordField);

		JLabel password2 = new JLabel("��й�ȣ Ȯ��");
		password2.setFont(new Font("���� ���", Font.BOLD, 25));
		password2.setBounds(40, 228, 212, 30);
		this.add(password2);

		JPasswordField passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordField2.setBounds(40, 261, 400, 35);
		this.add(passwordField2);

		JLabel name = new JLabel("�̸�");
		name.setFont(new Font("���� ���", Font.BOLD, 25));
		name.setBounds(40, 318, 57, 30);
		this.add(name);

		JTextField nameField = new JTextField("");
		nameField.setFont(new Font("���� ���", Font.PLAIN, 25));
		nameField.setBounds(40, 351, 400, 35);
		this.add(nameField);

		JLabel phoneNumber = new JLabel("��ȭ��ȣ");
		phoneNumber.setFont(new Font("���� ���", Font.BOLD, 25));
		phoneNumber.setBounds(40, 411, 122, 30);
		this.add(phoneNumber);

		JLabel phoneNumberplus = new JLabel("(010-****-****)");
		phoneNumberplus.setFont(new Font("���� ���", Font.PLAIN, 15));
		phoneNumberplus.setBounds(151, 413, 101, 35);
		this.add(phoneNumberplus);

		JTextField phoneNumberField = new JTextField("");
		phoneNumberField.setFont(new Font("���� ���", Font.PLAIN, 25));
		phoneNumberField.setBounds(40, 443, 400, 35);
		this.add(phoneNumberField);

		JButton cancelButton = new JButton("��  ��");
		cancelButton.setBackground(new Color(211, 211, 211));
		cancelButton.setFont(new Font("���� ���", Font.BOLD, 23));
		cancelButton.setBounds(40, 504, 162, 45);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("LoginPage");
			}
		});
		this.add(cancelButton);

		JButton registerButton = new JButton("�����ϱ�");
		registerButton.setBackground(new Color(211, 211, 211));
		registerButton.setFont(new Font("���� ���", Font.BOLD, 23));
		registerButton.setBounds(278, 504, 162, 45);
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

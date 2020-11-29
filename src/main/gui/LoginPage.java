package main.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginPage extends JPanel {
	
	public LoginPage(PageHandler pageHandler) {
		this.setBackground(pageHandler.backgroundColor);
		this.setLayout(null);

		JLabel subTitleLabel = new JLabel("ȯ���մϴ�");
		subTitleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		subTitleLabel.setBounds(165, 60, 200, 50);
		

		JLabel idIconLabel = new JLabel("");
		idIconLabel.setIcon(new ImageIcon(pageHandler.path + "login_ID.png"));
		
		JTextField idTextField = new JTextField();
		idTextField.setColumns(10);
		idTextField.setFont(new Font("���� ���", Font.BOLD, 18));
		
		JLabel passwdIconLabel = new JLabel("");
		passwdIconLabel.setIcon(new ImageIcon(pageHandler.path + "login_passwd.png"));
		
		JPasswordField passwdTextField = new JPasswordField();
		passwdTextField.setFont(new Font("���� ���", Font.BOLD, 18));
		
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		loginPanel.setBounds(40,150,390,180);
		loginPanel.setBackground(pageHandler.backgroundColor);
		
		GridBagConstraints gridBagContraints = new GridBagConstraints();
		gridBagContraints.fill = GridBagConstraints.BOTH;
		
		gridBagContraints.weightx = 0.1;
		gridBagContraints.gridx = 0;
		gridBagContraints.gridy = 0;
		loginPanel.add(idIconLabel, gridBagContraints);
		
		gridBagContraints.weightx = 1;
		gridBagContraints.gridx = 1;
		gridBagContraints.gridy = 0;
		loginPanel.add(idTextField, gridBagContraints);
		
		gridBagContraints.weightx = 0.1;
		gridBagContraints.gridx = 0;
		gridBagContraints.gridy = 1;
		loginPanel.add(passwdIconLabel, gridBagContraints);
		
		gridBagContraints.weightx = 1;
		gridBagContraints.gridx = 1;
		gridBagContraints.gridy = 1;
		loginPanel.add(passwdTextField, gridBagContraints);
		

		JLabel registerExplainLabel = new JLabel("���� ȸ���� �ƴϽŰ���?");
		registerExplainLabel.setFont(new Font("���� ���", Font.BOLD, 12));
		registerExplainLabel.setBounds(163, 335, 170, 15);

		JLabel registerLinkLabel = new JLabel("ȸ������");
		registerLinkLabel.setForeground(Color.BLUE);
		registerLinkLabel.setFont(new Font("���� ���", Font.BOLD, 12));
		registerLinkLabel.setBounds(250, 355, 50, 15);
		registerLinkLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				pageHandler.change("RegisterPage");
		    }
		});
		
		JButton loginButton = new JButton("");
		loginButton.setIcon(new ImageIcon(pageHandler.path + "loginButton2.png"));
		loginButton.setBounds(310, 330, 120, 50);
		loginButton.setPressedIcon(new ImageIcon(pageHandler.path + "loginButton_click.png"));
		loginButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = idTextField.getText();
				String userPw = String.valueOf(passwdTextField.getPassword());
				if (pageHandler.service.login(userId, userPw)) {
					JOptionPane.showMessageDialog(null, "You have logged in successfully");
					pageHandler.change("HospitalListPage");
				} else {
					JOptionPane.showMessageDialog(null, "You failed to log in");
				}
			}
		});
		
		
		this.add(subTitleLabel);
		this.add(loginPanel);
		this.add(registerExplainLabel);
		this.add(registerLinkLabel);
		this.add(loginButton);
		

	}
	
}

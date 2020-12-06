package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.domain.Patient;

public class SelfInfoPage extends JPanel {

	public SelfInfoPage(PageHandler pageHandler) {
		this.setLayout(null);

		Patient patient = pageHandler.service.getSelfInfo();

		JLabel pageHeaderLabel = new JLabel(patient.getPatientName() + "´ÔÀÇ °³ÀÎÁ¤º¸ÀÔ´Ï´Ù");
		pageHeaderLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		pageHeaderLabel.setBounds(20, 20, 300, 40);
		this.add(pageHeaderLabel);

		JButton logoutButton = new JButton("·Î±×¾Æ¿ô");
		logoutButton.setBounds(370, 20, 100, 40);
		logoutButton.setBackground(new Color(46, 204, 113));
		logoutButton.setFont(new Font("Dialog", Font.BOLD, 16));
		logoutButton.setBorder(new EmptyBorder(1, 1, 1, 1));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageHandler.service.logout()) {
					pageHandler.change("LoginPage");
				}
			}
		});
		this.add(logoutButton);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBackground(Color.WHITE);
		bodyPanel.setBounds(10, 90, 460, 550);

		JPanel patientInfoPanel = new JPanel();
		patientInfoPanel.setLayout(null);
		patientInfoPanel.setBounds(30, 30, 400, 220);

		JLabel patientIdPlusLabel = new JLabel("¾ÆÀÌµð :");
		patientIdPlusLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		patientIdPlusLabel.setBounds(20, 30, 200, 40);
		patientInfoPanel.add(patientIdPlusLabel);

		JLabel patientIdLabel = new JLabel(patient.getPatientId());
		patientIdLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		patientIdLabel.setBounds(140, 30, 200, 40);
		patientInfoPanel.add(patientIdLabel);

		JLabel patientNamePlusLabel = new JLabel("ÀÌ¸§ :");
		patientNamePlusLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		patientNamePlusLabel.setBounds(20, 80, 200, 40);
		patientInfoPanel.add(patientNamePlusLabel);

		JLabel patientNameLabel = new JLabel(patient.getPatientName());
		patientNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		patientNameLabel.setBounds(140, 80, 200, 40);
		patientInfoPanel.add(patientNameLabel);

		JLabel patientPhoneNumberPlusLabel = new JLabel("ÀüÈ­¹øÈ£ :");
		patientPhoneNumberPlusLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		patientPhoneNumberPlusLabel.setBounds(20, 130, 200, 40);
		patientInfoPanel.add(patientPhoneNumberPlusLabel);

		JLabel patientPhoneNumberLabel = new JLabel(patient.getPhoneNumber());
		patientPhoneNumberLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		patientPhoneNumberLabel.setBounds(140, 130, 200, 40);
		patientInfoPanel.add(patientPhoneNumberLabel);

		bodyPanel.add(patientInfoPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(30, 290, 400, 200);

		JButton passwdModifyButtom = new JButton("ºñ¹Ð¹øÈ£ º¯°æ");
		passwdModifyButtom.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		passwdModifyButtom.setForeground(Color.BLACK);
		passwdModifyButtom.setBackground(Color.WHITE);
		passwdModifyButtom.setBounds(20, 30, 230, 50);
		passwdModifyButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("ModifyPasswordPage");
			}
		});
		buttonPanel.add(passwdModifyButtom);

		JButton leavebutton = new JButton("È¸¿ø Å»Åð");
		leavebutton.setForeground(Color.BLACK);
		leavebutton.setBackground(Color.WHITE);
		leavebutton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		leavebutton.setBounds(20, 110, 230, 50);
		leavebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = new String[] { "Yes", "No" };
				int ans = JOptionPane.showOptionDialog(null, "Á¤¸»·Î Å»ÅðÇÏ½Ã°Ú½À´Ï±î?", "È¸¿ø Å»Åð", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

				if (ans == 0) {
					pageHandler.service.leave();
					pageHandler.change("LoginPage");
				}
			}
		});
		buttonPanel.add(leavebutton);

		bodyPanel.add(buttonPanel);

		this.add(bodyPanel);
	}

}

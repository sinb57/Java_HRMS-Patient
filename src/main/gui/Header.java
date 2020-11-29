package main.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Header extends JPanel {
	private PageHandler pageHandler;
	private String title = "";
	
	Header(PageHandler pageHandler) {
		this.pageHandler = pageHandler;
	}
	
	void build() {
		this.setLayout(null);
		
		JLabel logoLabel = new JLabel("°Å±ä ¾î¶§");
		logoLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		logoLabel.setBounds(15, 0, 100, 50);
		
		ImageIcon reloadIcon = new ImageIcon(pageHandler.path + "reload.png");
		Image reloadImage = reloadIcon.getImage();
		reloadImage = reloadImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		reloadIcon = new ImageIcon(reloadImage);
		
		JButton reloadButton = new JButton();
		reloadButton.setIcon(reloadIcon);
		reloadButton.setBounds(430, 10, 35, 35);
		reloadButton.setBorderPainted(false);
		reloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.reload();
			}
		});
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBounds(0, 30, 475, 50);

		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 33));
		titlePanel.add(titleLabel);

		
		this.add(logoLabel);
		this.add(reloadButton);
		this.add(titlePanel);
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(0, 100));
	}
	
	void setTitle(String title) {
		this.title = title;
		this.build();
	}
}

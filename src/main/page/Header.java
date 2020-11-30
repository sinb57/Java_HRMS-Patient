package main.page;

import java.awt.*;

import javax.swing.*;

public class Header extends JPanel {
	private String title = "";
	
	Header(PageHandler pageHandler, String title) {
		this.title = title;
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(0, 100));
		build();
	}
	void build() {
		
		JLabel logoLabel = new JLabel("°Å±ä ¾î¶§");
		logoLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		logoLabel.setBounds(15, 0, 100, 50);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBounds(0, 30, 475, 50);

		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 33));
		titlePanel.add(titleLabel);
		
		this.add(logoLabel);
		this.add(titlePanel);

	}
	
}

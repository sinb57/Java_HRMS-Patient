package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Footer extends JPanel {

	Footer(PageHandler pageHandler) {
		GridLayout gridLayout = new GridLayout(1,3);
		this.setLayout(gridLayout);

		JButton hospitalLowerButton = new JButton("new Button");
		hospitalLowerButton.setIcon(new ImageIcon(pageHandler.path + "loginHospitalButton.png"));
		hospitalLowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("HospitalListPage");
			}
		});

		JButton reservationLowerButton = new JButton("new Button");
		reservationLowerButton.setIcon(new ImageIcon(pageHandler.path + "loginReservationButton.png"));
		reservationLowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("ReservationListPage");
			}
		});

		JButton informationLowerButton = new JButton("new Button");
		informationLowerButton.setIcon(new ImageIcon(pageHandler.path + "loginInformationButton.png"));
		informationLowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("SelfInfoPage");
			}
		});
		
		this.add(hospitalLowerButton);
		this.add(reservationLowerButton);
		this.add(informationLowerButton);
	}
}

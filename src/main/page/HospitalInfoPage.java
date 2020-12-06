package main.page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import main.domain.Hospital;
import main.domain.Reservation;

public class HospitalInfoPage extends JPanel {
	private PageHandler pageHandler;
	private String hospitalId;

	HospitalInfoPage(PageHandler pageHandler, String hospitalId) {
		this.pageHandler = pageHandler;
		this.hospitalId = hospitalId;
		this.setLayout(null);
		build();
	}

	void build() {

		Hospital hospital = pageHandler.service.getHospital(hospitalId);
		Reservation reservation = pageHandler.service.getRecentReservation(hospitalId);

		JLabel hospitalNameLabel = new JLabel(hospital.getHospitalName());
		hospitalNameLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 23));
		hospitalNameLabel.setBounds(20, 20, 300, 40);
		this.add(hospitalNameLabel);

		JButton reservationButton = new JButton("øπæ‡«œ±‚");
		reservationButton.setBounds(370, 20, 100, 40);
		reservationButton.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 16));
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("ProcessReservationPage");
			}
		});
		this.add(reservationButton);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBackground(Color.WHITE);
		bodyPanel.setBounds(10, 90, 460, 550);

		JLabel isReservationedLabel = new JLabel("");
		isReservationedLabel.setBounds(20, 20, 200, 35);
		isReservationedLabel.setOpaque(true);
		isReservationedLabel.setBackground(Color.GREEN);
		isReservationedLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		bodyPanel.add(isReservationedLabel);

		if (reservation == null) {
			reservationButton.setEnabled(true);
			reservationButton.setBackground(Color.GREEN);
			isReservationedLabel.setText(" √÷±Ÿ ¡¯∑· : æ¯¿Ω");
		} else if (reservation.getReservationState().equals("øπæ‡¡ﬂ")) {
			reservationButton.setEnabled(false);
			isReservationedLabel
					.setText(" øπæ‡ : " + reservation.getReservationDate() + " " + reservation.getReservationTime());
		} else {
			reservationButton.setEnabled(true);
			reservationButton.setBackground(Color.GREEN);
			isReservationedLabel
					.setText(" √÷±Ÿ ¡¯∑· : " + reservation.getReservationDate() + " " + reservation.getReservationTime());
		}

		JPanel hospitalSubjectPanel = new JPanel();
		hospitalSubjectPanel.setBounds(20, 70, 300, 35);
		hospitalSubjectPanel.setBackground(Color.WHITE);
		hospitalSubjectPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		for (String careType : hospital.getCareTypeList()) {
			JLabel hospitalSubjectLabel = new JLabel(careType);
			hospitalSubjectLabel.setOpaque(true);
			hospitalSubjectLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
			hospitalSubjectLabel.setBackground(new Color(52, 152, 219));
			hospitalSubjectLabel.setForeground(Color.WHITE);
			hospitalSubjectPanel.add(hospitalSubjectLabel);
		}
		bodyPanel.add(hospitalSubjectPanel);

		JPanel hospitalInfoPanel = new JPanel();
		hospitalInfoPanel.setBounds(20, 115, 420, 424);
		hospitalInfoPanel.setBorder(new LineBorder(Color.gray, 2));
		hospitalInfoPanel.setOpaque(true);
		hospitalInfoPanel.setBackground(Color.WHITE);
		hospitalInfoPanel.setLayout(null);

		JLabel phoneNumberLabel = new JLabel("ø¨∂Ù√≥ : ");
		phoneNumberLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		phoneNumberLabel.setBounds(20, 10, 100, 40);
		hospitalInfoPanel.add(phoneNumberLabel);

		JLabel phoneNumberInputLabel = new JLabel(hospital.getPhoneNumber());
		phoneNumberInputLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 16));
		phoneNumberInputLabel.setBounds(100, 10, 195, 40);
		hospitalInfoPanel.add(phoneNumberInputLabel);

		JLabel addressLabel = new JLabel("¡÷º“");
		addressLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		addressLabel.setBounds(20, 50, 100, 40);
		hospitalInfoPanel.add(addressLabel);

		JLabel addressInfo = new JLabel(hospital.getAddress());
		addressInfo.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		addressInfo.setBounds(30, 80, 400, 40);
		hospitalInfoPanel.add(addressInfo);

		JLabel time = new JLabel("¡¯∑· Ω√∞£");
		time.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		time.setBounds(20, 120, 131, 40);
		hospitalInfoPanel.add(time);

		JPanel timeTablePanel = new JPanel();
		timeTablePanel.setLayout(null);
		timeTablePanel.setBounds(20, 160, 380, 230);

		ArrayList<String> careTimeList = hospital.getCareTimeList();

		for (int i = 0; i < careTimeList.size(); i++) {
			JLabel timeInfo = new JLabel(careTimeList.get(i));
			timeInfo.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 16));
			timeInfo.setBounds(10, 10 + 30 * i, 430, 25);
			timeTablePanel.add(timeInfo);
		}

		hospitalInfoPanel.add(timeTablePanel);

		bodyPanel.add(hospitalInfoPanel);

		this.add(bodyPanel);
	}

}

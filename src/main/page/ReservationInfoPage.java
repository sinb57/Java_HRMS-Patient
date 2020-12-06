package main.page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.domain.Hospital;
import main.domain.Reservation;

public class ReservationInfoPage extends JPanel {
	private PageHandler pageHandler;
	private String hospitalId;
	private String reservationDate;
	private String reservationTime;

	ReservationInfoPage(PageHandler pageHandler, String hospitalId, String reservationDate, String reservationTime) {
		this.pageHandler = pageHandler;
		this.hospitalId = hospitalId;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.setLayout(null);
		build();
	}

	void build() {

		Reservation reservation = pageHandler.service.getReservation(hospitalId, reservationDate, reservationTime);
		String hospitalId = reservation.getHospitalId();
		Hospital hospital = pageHandler.service.getHospital(hospitalId);

		String reservationDate = reservation.getReservationDate();
		String reservationTime = reservation.getReservationTime();
		String reservationCareType = reservation.getCareType();

		JLabel reservationDateTimeLabel = new JLabel(reservationDate + " " + reservationTime);
		reservationDateTimeLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 23));
		reservationDateTimeLabel.setBounds(20, 20, 300, 40);
		this.add(reservationDateTimeLabel);

		JLabel reservationCareTypeLabel = new JLabel("(" + reservation.getCareType() + ")");
		reservationCareTypeLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 22));
		reservationCareTypeLabel.setBounds(220, 20, 300, 40);
		this.add(reservationCareTypeLabel);

		JButton reservationButton = new JButton("øπæ‡«œ±‚");
		reservationButton.setVisible(false);
		reservationButton.setEnabled(false);
		reservationButton.setBounds(370, 20, 100, 40);
		reservationButton.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 16));
		reservationButton.setBackground(Color.GREEN);
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("ProcessReservationPage");
			}
		});
		this.add(reservationButton);

		JButton cancelButton = new JButton("øπæ‡√Îº“");
		cancelButton.setVisible(false);
		cancelButton.setEnabled(false);
		cancelButton.setBounds(370, 20, 100, 40);
		cancelButton.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 16));
		cancelButton.setBackground(Color.GREEN);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageHandler.service.cancelReservation(hospitalId, reservationDate, reservationTime)) {
				}
				pageHandler.change("ReservationInfoPage");
			}
		});
		this.add(cancelButton);

		if (reservation.getReservationState().equals("øπæ‡¡ﬂ")) {
			cancelButton.setVisible(true);
			cancelButton.setEnabled(true);
		} else {
			reservationButton.setVisible(true);
			reservationButton.setEnabled(true);
		}

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBackground(Color.WHITE);
		bodyPanel.setBounds(10, 90, 460, 550);

		JPanel symptomPanel = new JPanel();
		symptomPanel.setBounds(20, 70, 450, 35);
		symptomPanel.setBackground(Color.WHITE);
		symptomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		for (String symptom : reservation.getSymptomList()) {
			JLabel hospitalSubjectLabel = new JLabel(symptom);
			hospitalSubjectLabel.setOpaque(true);
			hospitalSubjectLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
			hospitalSubjectLabel.setBackground(new Color(52, 152, 219));
			hospitalSubjectLabel.setForeground(Color.WHITE);
			symptomPanel.add(hospitalSubjectLabel);
		}
		bodyPanel.add(symptomPanel);

		JLabel hospitalNameLabel = new JLabel(hospital.getHospitalName());
		hospitalNameLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 23));
		hospitalNameLabel.setBounds(20, 20, 300, 40);
		bodyPanel.add(hospitalNameLabel);

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

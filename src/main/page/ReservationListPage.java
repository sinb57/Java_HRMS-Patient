package main.page;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import main.PatientApplication;
import main.domain.*;

public class ReservationListPage extends JPanel {
	private PageHandler pageHandler;
	private JPanel reservationListPanel;
	private JButton pagingButton_now;

	private Patient patient;
	private int pageNum = 1;

	public ReservationListPage(PageHandler pageHandler) {
		this.pageHandler = pageHandler;
		patient = pageHandler.service.getPatient();
		this.setLayout(null);
		build();
	}

	void build() {

		JLabel reservationNameLabel = new JLabel(patient.getPatientName() + "¥‘¿« øπæ‡∏ÆΩ∫∆Æ¿‘¥œ¥Ÿ");
		reservationNameLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 23));
		reservationNameLabel.setBounds(30, 20, 470, 40);
		this.add(reservationNameLabel);

		reservationListPanel = makeReservationListPanel();
		this.add(reservationListPanel);

		JButton pagingButton_previous = new JButton("<");
		pagingButton_previous.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		pagingButton_previous.setBounds(160, 640, 45, 45);
		pagingButton_previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNum > 1)
					pageNum--;
				reload();
			}
		});
		this.add(pagingButton_previous);

		pagingButton_now = new JButton(pageNum + "");
		pagingButton_now.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		pagingButton_now.setBounds(220, 640, 45, 45);
		this.add(pagingButton_now);

		JButton pagingButton_next = new JButton(">");
		pagingButton_next.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		pagingButton_next.setBounds(280, 640, 45, 45);
		pagingButton_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNum++;
				reload();
			}
		});
		this.add(pagingButton_next);

	}

	private void reload() {
		this.remove(reservationListPanel);
		reservationListPanel = makeReservationListPanel();
		this.add(reservationListPanel);
		pagingButton_now.setText(pageNum + "");
		revalidate();
		repaint();
	}

	private ArrayList<Reservation> getReservationList() {
		return pageHandler.service.getReservationList(pageNum);
	}

	private JPanel makeReservationListPanel() {

		JPanel reservationListPanel = new JPanel();
		reservationListPanel.setLayout(null);
		reservationListPanel.setBackground(Color.WHITE);
		reservationListPanel.setBounds(25, 120, 430, 510);

		ArrayList<JPanel> reservationInfoPanelList = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			JPanel reservationInfoPanel = new JPanel();
			reservationInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			reservationInfoPanel.setBackground(Color.WHITE);
			reservationInfoPanel.setBounds(12, 10 + 125 * i, 410, 115);
			reservationInfoPanel.setLayout(null);
			reservationInfoPanelList.add(reservationInfoPanel);
			reservationListPanel.add(reservationInfoPanel);
		}

		ArrayList<Reservation> reservationList = getReservationList();

		if (reservationList != null) {

			for (int i = 0; i < reservationList.size(); i++) {
				Reservation reservation = reservationList.get(i);
				String hospitalId = reservation.getHospitalId();
				Hospital hospital = pageHandler.service.getHospital(hospitalId);

				JPanel reservationInfoPanel = reservationInfoPanelList.get(i);

				String reservationDate = reservation.getReservationDate();
				String reservationTime = reservation.getReservationTime();
				String reservationCareType = reservation.getCareType();

				JLabel ReservationDateTimeLabel = new JLabel(reservationDate + " " + reservationTime);
				ReservationDateTimeLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 22));
				ReservationDateTimeLabel.setBounds(12, 10, 300, 35);
				reservationInfoPanel.add(ReservationDateTimeLabel);

				JLabel hospitalNameLabel = new JLabel(hospital.getHospitalName() + "  (" + reservationCareType + ")");
				hospitalNameLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
				hospitalNameLabel.setBounds(12, 41, 300, 35);
				reservationInfoPanel.add(hospitalNameLabel);

				JLabel hospitalLocationLabel = new JLabel(hospital.getAddress());
				hospitalLocationLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
				hospitalLocationLabel.setBounds(12, 77, 300, 26);
				reservationInfoPanel.add(hospitalLocationLabel);

				JPanel reservationStatusPanel = new JPanel();
				reservationStatusPanel.setLayout(null);
				reservationStatusPanel.setBounds(334, 1, 75, 113);

				String state = reservation.getReservationState();
				JLabel reservationStatusLabel = new JLabel(state);
				reservationStatusLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
				reservationStatusLabel.setForeground(Color.WHITE);
				reservationStatusLabel.setBounds(7, 40, 60, 30);
				reservationStatusLabel.setHorizontalAlignment(JLabel.CENTER);

				switch (state) {
				case "¡¯∑·øœ∑·":
					reservationStatusPanel.setBackground(new Color(0, 132, 212));
					break;
				case "¡¯∑·√Îº“":
					reservationStatusPanel.setBackground(new Color(90, 90, 90));
					break;
				case "øπæ‡√Îº“":
					reservationStatusPanel.setBackground(new Color(175, 117, 117));
					break;
				case "øπæ‡¡ﬂ":
					reservationStatusPanel.setBackground(new Color(255, 192, 0));
					break;
				}
				reservationStatusPanel.add(reservationStatusLabel);

				reservationInfoPanel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						pageHandler.hospitalId = hospitalId;
						pageHandler.reservationDate = reservationDate;
						pageHandler.reservationTime = reservationTime;
						pageHandler.change("ReservationInfoPage");
					}
				});

				reservationInfoPanel.add(reservationStatusPanel);
			}
		}

		return reservationListPanel;
	}
}

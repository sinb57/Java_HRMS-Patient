package main.page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import main.service.PatientService;

public class PageHandler extends JFrame {
	PatientService service = new PatientService();

	final Color backgroundColor = new Color(0, 176, 240);
	final String path = "./image/";
	String location = "경기도 수원시 영통구";
	String hospitalId = "";
	String reservationDate = "";
	String reservationTime = "";

	public PageHandler() {
		service.connect("localhost", 9999);

		this.setTitle("거긴 어때");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.white);
		this.setSize(500, 960);
		this.setVisible(true);
	}

	public void start() {
		change("LoginPage");
	}

	void change(String panelName) {

		JPanel page = null;
		String title = "";

		switch (panelName) {
		case "LoginPage":
			page = new LoginPage(this);
			title = "로그인";
			break;
		case "RegisterPage":
			page = new RegisterPage(this);
			title = "회원가입";
			break;
		case "HospitalListPage":
			page = new HospitalListPage(this);
			title = "병원리스트";
			break;
		case "HospitalInfoPage":
			page = new HospitalInfoPage(this, hospitalId);
			title = "병원정보";
			break;
		case "ProcessReservationPage":
			page = new ProcessReservationPage(this, hospitalId);
			title = "예약접수";
			break;
		case "ReservationListPage":
			page = new ReservationListPage(this);
			title = "예약리스트";
			break;
		case "ReservationInfoPage":
			page = new ReservationInfoPage(this, hospitalId, reservationDate, reservationTime);
			title = "예약정보";
			break;
		case "SelfInfoPage":
			page = new SelfInfoPage(this);
			title = "개인정보";
			break;
		case "ModifyPasswordPage":
			page = new ModifyPasswordPage(this);
			title = "비밀번호 변경";
			break;
		}

		getContentPane().removeAll();

		Header header = new Header(this, title);
		Footer footer = new Footer(this);
		page.setBackground(new Color(0, 176, 240));

		getContentPane().add(header, BorderLayout.PAGE_START);
		getContentPane().add(page);
		getContentPane().add(footer, BorderLayout.PAGE_END);
		revalidate();
		repaint();
	}

}
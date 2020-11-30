package main.page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import main.service.PatientService;

public class PageHandler extends JFrame {
	PatientService service = new PatientService();
	
	final Color backgroundColor = new Color(0, 176, 240);
	final String path = "./image/";
	String location = "��⵵ ������ ��ȱ�";
	String hospitalId = "hrms_001";
	String reservationDate = "2020-11-28";
	String reservationTime = "14:00";

	public PageHandler() {
		service.connect("localhost",  9999);
		service.login("arinlove", "dkfls123");
		
		this.setTitle("�ű� �");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.white);
		this.setSize(500, 960);
		this.setVisible(true);
	}
	
	public void start() {
		change("ModifyPasswordPage");
	}
	
	void change(String panelName) {
		
		JPanel page = null;
		String title = "";
		
		switch(panelName) {
		case "LoginPage":
			page = new LoginPage(this);
			title = "�α���";
			break;
		case "RegisterPage":
			page = new RegisterPage(this);
			title = "ȸ������";
			break;
		case "HospitalListPage":
			page = new HospitalListPage(this);
			title = "��������Ʈ";
			break;
		case "HospitalInfoPage":
			page = new HospitalInfoPage(this, hospitalId);
			title = "��������";
			break;
		case "ProcessReservationPage":
			page = new ProcessReservationPage(this, hospitalId);
			title = "��������";
			break;
		case "ReservationListPage":
			page = new ReservationListPage(this);
			title = "���ฮ��Ʈ";
			break;
		case "ReservationInfoPage":
			page = new ReservationInfoPage(this, hospitalId, reservationDate, reservationTime);
			title = "��������";
			break;
		case "SelfInfoPage":
			page = new SelfInfoPage(this);
			title = "��������";
			break;
		case "ModifyPasswordPage":
			page = new ModifyPasswordPage(this);
			title = "��й�ȣ ����";
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
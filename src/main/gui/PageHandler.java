package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import main.service.PatientService;

public class PageHandler extends JFrame {
	PatientService service = new PatientService();
	final String path = "./image/";
	final Color backgroundColor = new Color(0, 176, 240);
	private Header header = null;
	private Footer footer = null;

	public PageHandler() {
		this.setTitle("거긴 어때");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.white);
		this.setSize(500, 800);
		this.setVisible(true);
		
		header = new Header(this);
		footer = new Footer(this);
	}
	
	public void start() {
		change("HospitalListPage");
	}
	
	void change(String panelName) {
		
		JPanel page = null;
		String title = "";
		
		switch(panelName) {
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
			/*
		case "HospitalIn
		foPage":
			page = new HospitalInfoPage();
			break;
		case "ReservationListPage":
			page = new ReservationListPage();
			break;
		case "ReservationInfoPage":
			page = new ReservationInfoPage();
			break;
		case "SelfInfoPage":
			page = new SelfInfoPage();
			break;
			*/
		}
		
		getContentPane().removeAll();
		
		header.setTitle(title);
		page.setBackground(new Color(0, 176, 240));
		
		getContentPane().add(header, BorderLayout.PAGE_START);
		getContentPane().add(page);
		getContentPane().add(footer, BorderLayout.PAGE_END);
		revalidate();
		repaint();
	}
	
	void reload() {
		
	}
}
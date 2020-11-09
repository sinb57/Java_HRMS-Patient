package main.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import main.domain.*;
import main.socket.SocketHandler;

public class PatientService {
	public static Patient patient = new Patient();
	private ArrayList<Hospital> hospitalList;
	private ArrayList<Reservation> reservationList;
	private SocketHandler socketHandler = new SocketHandler();
	
	public boolean connect(String ipAddress, int port) {
		return socketHandler.run(ipAddress, port);
	}
	
	// 환자 로그인
	public boolean login(String userId, String userPw) {
		
		StringTokenizer responseData = socketHandler.login(userId, userPw);
		
		try {
			patient.read(responseData);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		
		return true;
	}
	


	// 개인정보수정
	public boolean modifyPatientPw(String passwd, String passwd2) {

		if (passwd.equals(passwd2)) {
		
			String token = patient.getToken();
			
			if (socketHandler.modifyPatientPw(token, passwd))
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Hospital> getHospitalList(int pageNum, String keyword) {
		
		String token = patient.getToken();

		if (pageNum < 0)
			return null;
		
		if (keyword == null)
			keyword = "";
		
		keyword = keyword.trim();

		hospitalList = socketHandler.searchHospitalList(token, pageNum, keyword);
		
		return hospitalList;
	}
	

	public void requestReservation(Scanner keyScanner) {
		Reservation r = null;
		r = new Reservation();
		int days = keyScanner.nextInt();
		int hours = keyScanner.nextInt();
		int minutes =  keyScanner.nextInt();
	}
	
	// 대기번호
	public void waitingNum(String ID) {
		if(patient.matches(ID)) {
			for (Reservation r : reservationList) {
				System.out.printf("대기 순번은 [ %d 번] 입니다. ", r.getReservationID());
			}
		}
		
	}


	
}

package main.service;

import java.util.ArrayList;
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
	

    public ArrayList<Reservation> getReservationList(String reservationId) {
    	
    	
    	
    	reservationList = socketHandler.getReservationList(token, hospitalId);
    	
    	return reservationList;
    }
	
	public int getWatingNumber(long reservationId) {
		
		String token = patient.getToken();
		
		
		
		return 0;
	}

 
}

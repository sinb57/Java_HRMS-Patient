package main.service;

import java.util.ArrayList;
import java.util.StringTokenizer;

import main.domain.*;
import main.socket.SocketHandler;

public class PatientService {
	private Patient patient = new Patient();
	private ArrayList<Hospital> hospitalList = new ArrayList<>();
	private SocketHandler socketHandler = new SocketHandler();
	
	public boolean connect(String ipAddress, int port) {
		return socketHandler.run(ipAddress, port);
	}
	
	// Login
	public boolean login(String userId, String userPw) {
		StringTokenizer tokenizer = socketHandler.login(userId, userPw);
		
		patient.init(tokenizer);
		
		return true;
	}
	
	// Return Patient
	public Patient getPatient() {

		String token = patient.getToken();
		
		StringTokenizer tokenizer = socketHandler.requestSelfInfo(token);
		
		patient.read(tokenizer);
		
		return patient;
	}


	// Modify Self Info
	public boolean modifyPatientPw(String passwd, String passwd2) {

		if (passwd.equals(passwd2)) {
		
			String token = patient.getToken();
			
			if (socketHandler.modifySelfPw(token, passwd))
				return true;
		}
		
		return false;
	}
	
	// Return Hospital List
	// Include Search function
	public ArrayList<Hospital> getHospitalList(int pageNum, String keyword) {
		
		String token = patient.getToken();

		if (pageNum < 0)
			return null;
		
		if (keyword == null)
			keyword = "";
		
		keyword = keyword.trim();

		StringTokenizer tokenizer = socketHandler.requestHospitalList(token, pageNum, keyword);
		
		hospitalList.clear();
		
		while (tokenizer.hasMoreTokens()) {
			Hospital hospital = new Hospital();
			hospital.read(tokenizer);
		}
		
		return hospitalList;
	}
	
	// Return Specific Hospital
	public Hospital getHospital(String hospitalId) {
		
		String token = patient.getToken();
		
		StringTokenizer tokenizer = socketHandler.requestHospitalInfo(token, hospitalId);
		
		Hospital hospital = new Hospital();

		hospital.read(tokenizer);
		
		return hospital;
	}
	
	// Return Reservation List
    public ArrayList<Reservation> getReservationList() {
    	
    	String token = patient.getToken();
    	
    	StringTokenizer tokenizer = socketHandler.requestReservationList(token);
    	
    	patient.clearReservationList();
    	
		while (tokenizer.hasMoreTokens()) {
			Reservation reservation = new Reservation();
			reservation.init(tokenizer, patient);
		}
		
		return (patient.getReservationList());
    }
    
    // Return Reservation Info
	public Reservation getReservation(long reservationId) {

		String token = patient.getToken();
		
		StringTokenizer tokenizer = socketHandler.requestReservationInfo(token, Long.toString(reservationId));
		
		Reservation reservation = patient.getReservation(reservationId);
		
		reservation.read(tokenizer);
		
		return reservation;
	}
	
 
}

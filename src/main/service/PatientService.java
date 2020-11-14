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
		
		if (tokenizer == null)
			return false;
		
		patient.init(tokenizer);
		
		return true;
	}
	
	// Return Patient
	public Patient getSelfInfo() {

		String cookie = patient.getCookie();
		
		StringTokenizer tokenizer = socketHandler.requestSelfInfo(cookie);
		
		if (tokenizer == null)
			return null;
		
		patient.read(tokenizer);
		
		return patient;
	}


	// Modify Self Info
	public boolean modifyPatientPw(String passwdFrom, String passwdTo, String passwdRe) {

		if (passwdTo.equals(passwdRe)) {
		
			String cookie = patient.getCookie();
			
			if (socketHandler.modifySelfPw(cookie, passwdFrom, passwdRe))
				return true;
		}
		
		return false;
	}
	
	// Return Hospital List
	// Include Search function
	public ArrayList<Hospital> getHospitalList(int pageNum, boolean isSelectedOnlyOpend, String keyword) {
		
		String cookie = patient.getCookie();

		if (pageNum < 0)
			return null;
		
		if (keyword == null)
			keyword = "";
		
		keyword = keyword.trim();

		StringTokenizer tokenizer = socketHandler.requestHospitalList(cookie, pageNum, isSelectedOnlyOpend, keyword);
		
		if (tokenizer == null)
			return null;
		
		hospitalList.clear();
		
		Hospital hospital;
		while (tokenizer.hasMoreTokens()) {
			hospital = new Hospital();
			hospital.read(tokenizer);
			hospitalList.add(hospital);
		}
		
		return hospitalList;
	}
	
	// Return Specific Hospital
	public Hospital getHospital(String hospitalId) {
		
		String cookie = patient.getCookie();
		
		StringTokenizer tokenizer = socketHandler.requestHospitalInfo(cookie, hospitalId);
		
		if (tokenizer == null)
			return null;
		
		Hospital hospital = new Hospital();

		hospital.read(tokenizer);
		
		return hospital;
	}
	
	// Return Reservation List
    public ArrayList<Reservation> getReservationList() {
    	
		String cookie = patient.getCookie();
    	
    	StringTokenizer tokenizer = socketHandler.requestReservationList(cookie);
		
		if (tokenizer == null)
			return null;
		
    	patient.clearReservationList();
    	
		while (tokenizer.hasMoreTokens()) {
			Reservation reservation = new Reservation();
			reservation.init(tokenizer, patient);
		}
		
		return (patient.getReservationList());
    }
    
    // Return Reservation Info
	public Reservation getReservation(long reservationId) {

		String cookie = patient.getCookie();
		
		StringTokenizer tokenizer = socketHandler.requestReservationInfo(cookie, Long.toString(reservationId));
		
		if (tokenizer == null)
			return null;
		
		Reservation reservation = patient.getReservation(reservationId);
		
		reservation.read(tokenizer);
		
		return reservation;
	}
	
 
}

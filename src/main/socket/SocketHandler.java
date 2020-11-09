package main.socket;

import java.util.ArrayList;
import java.util.StringTokenizer;

import main.domain.Hospital;
import main.domain.Reservation;

public class SocketHandler {

	private Client client;

	public boolean run(String ipAddress, int port) {
		try {
			client = new Client(ipAddress, port);

		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public StringTokenizer login(String userId, String userPw) {
		
		String requestData = "POST /auth/Login";
		requestData += "\n" + userId;
		requestData += "\n" + userPw;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Login Failed"))
			return null;

		return responseData;
	}
	
	public boolean modifyPatientPw(String token, String passwd) {
		
		String requestData = "PUT /auth/me";
		requestData += "\n" + token;
		requestData += "\n" + passwd;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Modify Failed"))
			return false;
		
		return true;
	}
	
	public ArrayList<Hospital> searchHospitalList(String token, int pageNum, String keyword) {
		
		String requestData = "GET /hospitals/" + pageNum;
		requestData += "\n" + token;
		requestData += "\n" + keyword;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Hospital List Failed"))
			return null;
		
		ArrayList<Hospital> hospitalList = new ArrayList<>();
		
		while (responseData.hasMoreTokens()) {
			Hospital hospital = new Hospital();
			hospital.read(responseData);
		}
		
		return hospitalList;
	}
	
	public boolean makeReservation(String token, String hospitalId, String reservationDate, String reservationTime) {
		
		String requestData = "POST /hospitals/reservations/" + hospitalId;
		requestData += "\n" + token;
		requestData += "\n" + reservationDate;
		requestData += "\n" + reservationTime;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Make Reservation Failed"))
			return false;
		
		return true;
	}
	
	public ArrayList<Reservation> getReservationList(String token, String hospitalId) {
		String requestData = "GET /hospitals/reservations/" + hospitalId;
		requestData += "\n" + token;

		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Make Reservation Failed"))
			return null;
		
		ArrayList<Reservation> reservationList = new ArrayList<>(); 
		while (responseData.hasMoreTokens()) {
			Hospital hospital = new Hospital();
			hospital.read(responseData);
		}
		
		return reservationList;
	}
}

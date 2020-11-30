package main.socket;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
		
		String requestData = "POST /auth/login";
		requestData += "\n" + userId;
		requestData += "\n" + userPw;

		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Login Failed"))
			return null;

		return responseData;
	}
	
	public boolean logout(String cookie) {
		String requestData = "GET /auth/logout";
		requestData += "\n" + cookie;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Logout Failed"))
			return false;

		return true;
	}
	
	public boolean join(String userId, String userPw, String userName, String phoneNumber) {
		String requestData = "POST /auth/join";
		requestData += "\n" + userId;
		requestData += "\n" + userPw;
		requestData += "\n" + userName;
		requestData += "\n" + phoneNumber;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Join Failed"))
			return false;

		return true;
	}
	
	public boolean leave(String cookie) {
		String requestData = "GET /auth/leave";
		requestData += "\n" + cookie;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Leave Failed"))
			return false;

		return true;
	}
	
	public StringTokenizer requestSelfInfo(String cookie) {
		
		String requestData = "GET /auth/me";
		requestData += "\n" + cookie;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Self Info Failed"))
			return null;

		return responseData;
	}
	
	public boolean modifySelfPw(String cookie, String passwdFrom, String passwdTo) {
		
		String requestData = "PUT /auth/me";
		requestData += "\n" + cookie;
		requestData += "\n" + passwdFrom;
		requestData += "\n" + passwdTo;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Modify Self Password Failed"))
			return false;
		
		return true;
	}
	
	public StringTokenizer requestHospitalList(String cookie, int pageNum, String address, String careType, String state, String keywords) {
		
		String requestData = "GET /hospitals/list/" + pageNum;
		requestData += "\n" + cookie;
		requestData += "\n" + address;
		requestData += "\n" + careType;
		requestData += "\n" + state;
		requestData += "\n" + keywords;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();

		if (responseHeader.equals("Get Hospital List Failed"))
			return null;

		return responseData;
	}
	
	public StringTokenizer requestHospitalInfo(String cookie, String hospitalId) {
		
		String requestData = "GET /hospitals/info/" + hospitalId;
		requestData += "\n" + cookie;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();

		if (responseHeader.equals("Get Hospital Info Failed"))
			return null;
		
		return responseData;
	}
	
	
	public boolean makeReservation(String cookie, String hospitalId, String reservationDate, String reservationTime, String careType, ArrayList<String> symptomList) {
		
		String requestData = "POST /patients/reservations/" + hospitalId;
		requestData += "\n" + cookie;
		requestData += "\n" + reservationDate;
		requestData += "\n" + reservationTime;
		requestData += "\n" + careType;
		requestData += "\n";
		for (String symptom: symptomList)
			requestData += symptom + " ";
		
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Make Reservation Failed"))
			return false;
		
		return true;
	}
	
	public boolean cancelReservation(String cookie, String hospitalId, String reservationDate, String reservationTime) {
	
		String requestData = "DELETE /patients/reservations/" + hospitalId;
		requestData += "\n" + cookie;
		requestData += "\n" + reservationDate;
		requestData += "\n" + reservationTime;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Cancel Reservation Failed"))
			return false;
		return true;
	}
	
	public StringTokenizer requestReservationList(String cookie, int pageNum) {
		String requestData = "GET /patients/reservations/list/" + pageNum;
		requestData += "\n" + cookie;

		StringTokenizer responseData = client.request(requestData);

		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation List Failed"))
			return null;
		
		return responseData;
	}
	
	public StringTokenizer requestRecentReservation(String cookie, String hospitalId) {
		String requestData = "GET /patients/reservations/recent/" + hospitalId;
		requestData += "\n" + cookie;

		System.out.println(requestData);
		StringTokenizer responseData = client.request(requestData);

		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Recent Reservation Info Failed"))
			return null;
		
		return responseData;
	}
	
	public StringTokenizer requestReservationInfo(String cookie, String hospitalId, String reservationDate, String reservationTime) {

		String requestData = "GET /patients/reservations/info/" + hospitalId;
		requestData += "\n" + cookie;
		requestData += "\n" + reservationDate;
		requestData += "\n" + reservationTime;

		StringTokenizer responseData = client.request(requestData);

		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation Info Failed"))
			return null;
		
		return responseData;
	}
}

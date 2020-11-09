package main.socket;

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
		
		String requestData = "POST /auth/Login";
		requestData += "\n" + userId;
		requestData += "\n" + userPw;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Login Failed"))
			return null;

		return responseData;
	}
	
	public StringTokenizer requestPatientInfo(String token) {
		
		String requestData = "GET /auth/me";
		requestData += "\n" + token;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Patient Info Failed"))
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
	
	public StringTokenizer requestHospitalList(String token, int pageNum, String keyword) {
		
		String requestData = "GET /hospitals/" + pageNum;
		requestData += "\n" + token;
		requestData += "\n" + keyword;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Hospital List Failed"))
			return null;

		return responseData;
	}
	
	public StringTokenizer requestHospitalInfo(String token, String hospitalId) {
		
		String requestData = "GET /hospitals/" + hospitalId;
		requestData += "\n" + token;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Hospital Info Failed"))
			return null;
		
		return responseData;
	}
	
	
	public boolean makeReservation(String token, String hospitalId, String reservationDate, String reservationTime) {
		
		String requestData = "POST /patients/reservations/" + hospitalId;
		requestData += "\n" + token;
		requestData += "\n" + reservationDate;
		requestData += "\n" + reservationTime;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Make Reservation Failed"))
			return false;
		
		return true;
	}
	
	public StringTokenizer requestReservationList(String token) {
		String requestData = "GET /hospitals/reservations";
		requestData += "\n" + token;

		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation List Failed"))
			return null;
		
		return responseData;
	}
	
	public StringTokenizer requestReservationInfo(String token, String reservationId) {

		String requestData = "GET /patients/reservations/" + reservationId;
		requestData += "\n" + token;

		StringTokenizer responseData = client.request(requestData);

		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation Info Failed"))
			return null;
		
		return responseData;
	}
}
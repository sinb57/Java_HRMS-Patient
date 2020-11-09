package main.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Patient {
    private String patientId;
    private String patientName;
    private String phoneNumber;
	private String token;
    private Long point;
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    

    public void read(StringTokenizer dataTokenizer) throws ArrayIndexOutOfBoundsException {
    	token = dataTokenizer.nextToken();
    	patientId = dataTokenizer.nextToken();
    	patientName = dataTokenizer.nextToken();
    	phoneNumber = dataTokenizer.nextToken();
    }
    
    // 임시 메소드 -> GUI 연동시 삭제
    public void print() {
    	System.out.printf("[%s] 이름: %s / 번호: %s", patientId, patientName, phoneNumber);
    	System.out.println();
    }
    
    public String getPatientId() {
    	return patientId;
    }

    public String getPatientName() {
    	return this.patientName;
    }
    
    public String getPhoneNumber() {
    	return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    
    public String getToken() {
    	return this.token;
    }
    
    public ArrayList<Reservation> getReservationList() {
    	return this.reservationList;
    }
    
    public void setReservationList(ArrayList<Reservation> reservationList) {
    	this.reservationList = reservationList;
    }
}

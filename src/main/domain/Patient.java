package main.domain;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Patient {
    private String patientId;
    private String patientName;
    private String phoneNumber;
	private String token;
    private Long point;
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    

    public void read(StringTokenizer dataTokenizer) {
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
    
    public Reservation getReservation(long reservationId) {
    	for (Reservation reservation: reservationList) {
			if (reservation.matches(reservationId))
				return reservation;			
		}
    	return null;
    }
    
    public void addReservation(Reservation reservation) {
    	this.reservationList.add(reservation);
    }
    
    public void clearReservationList() {
    	this.reservationList.clear();
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
    
    public void setToken(String token) {
    	this.token = token;
    }
    
    public ArrayList<Reservation> getReservationList() {
    	return this.reservationList;
    }
    
    public void setReservationList(ArrayList<Reservation> reservationList) {
    	this.reservationList = reservationList;
    }
}

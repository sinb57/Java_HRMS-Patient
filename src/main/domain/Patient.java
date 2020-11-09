package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Patient {
	private String token;
    private String patientId;
    private String patientName;
    private String phoneNumber;
    private Long point;
    private List<Reservation> reservationList = new ArrayList<>();
    

    public void read(StringTokenizer dataTokenizer) throws ArrayIndexOutOfBoundsException {
    	token = dataTokenizer.nextToken();
    	patientId = dataTokenizer.nextToken();
    	patientName = dataTokenizer.nextToken();
    	phoneNumber = dataTokenizer.nextToken();
    }
    
    public void print() {
    	System.out.printf("[%s] 이름: %s / 번호: %s", patientId, patientName, phoneNumber);
    	System.out.println();
    }
    
    public String getPatientId() {
    	return patientId;
    }
    
    public boolean matches(String patientId) {
    	if (this.patientId.contentEquals(patientId))
    		return true;
    	return false;
    }
    
    public void addReservation(Reservation reservation) {
    	reservationList.add(reservation);
    }
    
	public void popReservation(Reservation reservation) {
		int index = reservationList.indexOf(reservation);
		
		if (index == -1)
			return;
		
		reservationList.remove(index);
	}
	
	private int indexOf(Reservation reservationI) {
		int index = 0;
		long reservationId = 0;
		
		for (Reservation reservation: reservationList) {
			reservationId = reservation.getReservationID();
			if (reservationI.matches(reservationId))	
				return index;			
			index++;
		}
		return -1;
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
	
}

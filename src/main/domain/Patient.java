package main.domain;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Patient {
    private String patientId;
    private String patientName;
    private String phoneNumber;
	private String token;
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    
    public void init(StringTokenizer dataTokenizer) {
    	token = dataTokenizer.nextToken();
    	read(dataTokenizer);
    }

    public void read(StringTokenizer dataTokenizer) {
    	patientId = dataTokenizer.nextToken();
    	patientName = dataTokenizer.nextToken();
    	phoneNumber = dataTokenizer.nextToken();
    }
    
    // Temporary Method -> Drop after GUI linked
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
    
    public String getToken() {
    	return this.token;
    }
    
    public ArrayList<Reservation> getReservationList() {
    	return this.reservationList;
    }

}

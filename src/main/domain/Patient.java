package main.domain;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Patient {
    private String patientId;
    private String patientName;
    private String phoneNumber;
	private String cookie;
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    
    public void init(StringTokenizer dataTokenizer) {
    	cookie = dataTokenizer.nextToken().trim();
    	read(dataTokenizer);
    }

    public void read(StringTokenizer patientTokenizer) {
    	patientId = patientTokenizer.nextToken().trim();
    	patientName = patientTokenizer.nextToken().trim();
    	phoneNumber = patientTokenizer.nextToken().trim();
    }
    
    // Temporary Method -> Drop after GUI linked
    public void print() {
    	System.out.printf("[%s]\t이름: %s / 번호: %s", patientId, patientName, phoneNumber);
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
    
    public String getCookie() {
    	return this.cookie;
    }
    
    public ArrayList<Reservation> getReservationList() {
    	return this.reservationList;
    }

}

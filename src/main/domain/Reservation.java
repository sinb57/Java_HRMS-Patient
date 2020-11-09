package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Reservation {
    private long reservationId;
    private String hospitalId;
	private String reservationDay;
	private String reservationTime;
	
    public void read(StringTokenizer dataTokenizer) {
    	reservationId = Long.parseLong(dataTokenizer.nextToken());
    	hospitalId = dataTokenizer.nextToken();
    	reservationDay = dataTokenizer.nextToken();
    	reservationTime = dataTokenizer.nextToken();
    }
	
	public void print() {
		System.out.printf("¿¹¾à: " + reservationTime);
    	System.out.println();
    }
	
	public long getReservationID() {
		return reservationId;
	}
	
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
		
	public void setReservationDay(String reservationDay) {
		this.reservationDay = reservationDay;
	}
	
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	public boolean matches(long reservationId) {
		if (this.reservationId == reservationId)
			return true;
		return false;
	}
	
}

package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Reservation {
    private long reservationId;
    private String hospitalId;
	private LocalDate reservationDate;
	private LocalDateTime reservationTime;
	private int waitingNum;
	
    public void init(StringTokenizer dataTokenizer, Patient patient) {
		
    	read(dataTokenizer);
    	
    	patient.addReservation(this);
    }
    
    public void read(StringTokenizer dataTokenizer) {
		
		reservationId = Long.parseLong(dataTokenizer.nextToken());
    	hospitalId = dataTokenizer.nextToken();
    	
    	DateTimeFormatter formatter;
    	formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	    
    	reservationDate = LocalDate.parse(dataTokenizer.nextToken(), formatter);
		formatter = DateTimeFormatter.ofPattern("HH:mm");
    	reservationTime = LocalDateTime.parse(dataTokenizer.nextToken(), formatter);
    	
    	waitingNum = Integer.parseInt(dataTokenizer.nextToken());
    }
	
    public boolean matches(long reservationId) {
		if (this.reservationId == reservationId)
			return true;
		return false;
	}
	

    // Temporary Method -> Drop after GUI linked
    public void print() {
		System.out.printf("[%s] %s %s ", hospitalId, reservationDate, reservationTime);
		
		if (waitingNum != -1)
			System.out.printf("¼ø¼­:%d", waitingNum);
		
    	System.out.println();
    }
	
    public long getReservationID() {
    	return this.reservationId;
    }
}
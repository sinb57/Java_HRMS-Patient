package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientId;
    private String userName;
    private String phoneNumber;
    private Long point;
    private List<Reservation> reservationList = new ArrayList<>();
    
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
}

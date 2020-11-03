package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Long reservationId;
    private Hospital hospital;
	private LocalDateTime reservationDay;
	private LocalDateTime reservationTime;
	
	public long getReservationID() {
		return reservationId;
	}
	
	public void setHospitalId(Hospital hospital) {
		this.hospital = hospital;
	}
		
	public void setReservationDay(LocalDateTime reservationDay) {
		this.reservationDay = reservationDay;
	}
	
	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	public boolean matches(long reservationId) {
		if (this.reservationId == reservationId)
			return true;
		return false;
	}
}

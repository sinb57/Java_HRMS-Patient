package main.domain;

import java.util.StringTokenizer;

public class Reservation {
	private String reservationState;
	private String patientId;
	private String hospitalId;
	private String reservationDate;
	private String reservationTime;
	private String careType;
	private String[] symptomList;
	
    public void read(StringTokenizer dataTokenizer, Patient patient) {
    	
    	reservationState = dataTokenizer.nextToken();
    	patientId = dataTokenizer.nextToken();
    	hospitalId = dataTokenizer.nextToken();
    	
        reservationDate = dataTokenizer.nextToken();
        reservationTime = dataTokenizer.nextToken();

        careType = dataTokenizer.nextToken();
        symptomList = dataTokenizer.nextToken().trim().split(" ");
    	
        patient.addReservation(this);
    }
	

	public String getData() {
    	String data = "";
    	data += reservationState + " ";
    	data += patientId + " ";
    	data += hospitalId + "\n";

    	data += reservationDate + " ";
    	data += reservationTime + " ";

    	data += careType + "\n";
    	for (String symptom : symptomList)
    		data += symptom + " ";
    	data += "\n\n";

        return data;
	}
    
    
	public String getReservationState() {
		return reservationState;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public String getCareType() {
		return careType;
	}

	public String[] getSymptomList() {
		return symptomList;
	}
}
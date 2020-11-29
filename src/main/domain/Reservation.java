package main.domain;

import java.util.StringTokenizer;

public class Reservation {
	private String reservationState;
	private String patientId;
	private String hospitalId;
	private String reservationDate;
	private String reservationTime;
	private String careTime;
	private String docterName;
	private String careType;
	private String[] symptomList;
	
    public void read(StringTokenizer dataTokenizer) {
    	
    	reservationState = dataTokenizer.nextToken().trim();
    	patientId = dataTokenizer.nextToken().trim();
    	hospitalId = dataTokenizer.nextToken().trim();
    	
        reservationDate = dataTokenizer.nextToken().trim();
        reservationTime = dataTokenizer.nextToken().trim();

        careType = dataTokenizer.nextToken().trim();
        symptomList = dataTokenizer.nextToken().trim().split(" ");
        
    	if (reservationState.equals("진료완료")) {
	        careTime = dataTokenizer.nextToken().trim();
            docterName = dataTokenizer.nextToken().trim();
    	}
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
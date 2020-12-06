package main.domain;

import java.util.StringTokenizer;

public class Patient {
	private String patientId;
	private String patientName;
	private String phoneNumber;
	private String cookie;

	public void init(StringTokenizer dataTokenizer) {
		cookie = dataTokenizer.nextToken().trim();
		read(dataTokenizer);
	}

	public void read(StringTokenizer patientTokenizer) {
		patientId = patientTokenizer.nextToken().trim();
		patientName = patientTokenizer.nextToken().trim();
		phoneNumber = patientTokenizer.nextToken().trim();
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

	public void clear() {
		patientId = null;
		patientName = null;
		phoneNumber = null;
		cookie = null;
	}
}

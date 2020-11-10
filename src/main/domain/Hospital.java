package main.domain;

import java.util.StringTokenizer;

public class Hospital {
    private String hospitalId;
    private String hospitalName;
    private String phoneNumber;
    private String address;
    private String careTime;
    
    public void read(StringTokenizer dataTokenizer) {
    	hospitalId = dataTokenizer.nextToken();
    	System.out.println(hospitalId);
    	hospitalName = dataTokenizer.nextToken();
    	phoneNumber = dataTokenizer.nextToken();
    	address = dataTokenizer.nextToken();
    	careTime = "";
    	
    	String boundary = dataTokenizer.nextToken();
    	String data = dataTokenizer.nextToken();
    	
    	while (boundary.equals(data) == false) {
    		data = dataTokenizer.nextToken();
    		careTime += data;
    		System.out.println(data);
    	}
    }
    
    // Temporary Method -> Drop after GUI linked
    public void print() {
    	System.out.printf("[%s] 이름: %s / phone: %s", hospitalId, hospitalName, phoneNumber);
    	System.out.println();
    }

    // Temporary Method -> Drop after GUI linked
    public void printDetail() {
    	System.out.printf("[%s] 이름: %s / phone: %s", hospitalId, hospitalName, phoneNumber);
    	System.out.println();
    	System.out.printf("주소: %s", address);
    	System.out.println();
    	System.out.printf("진료시간\n");
    	System.out.println();
    	System.out.printf("%s", careTime);
    	System.out.println();
    }
    
    public String getHospitalId() {
    	return this.hospitalId;
    }
    
    public String getHospitalName() {
    	return this.hospitalName;
    }
    
    public String getPhoneNumber() {
    	return this.phoneNumber;
    }

    public String getAddress() {
    	return address;
    }
    
}

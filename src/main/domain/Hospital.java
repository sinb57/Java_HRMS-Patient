package main.domain;


import java.util.Scanner;
import java.util.StringTokenizer;


public class Hospital {
    private String hospitalId;
    private String hospitalName;
    private String phoneNumber;
    private String address;
    private String careTime;
    
    public void read(StringTokenizer dataTokenizer) {
    	hospitalId = dataTokenizer.nextToken();
    	hospitalName = dataTokenizer.nextToken();
    	phoneNumber = dataTokenizer.nextToken();
    	address = dataTokenizer.nextToken();
    	careTime = "";
    	
    	String boundary = dataTokenizer.nextToken();
    	String data = dataTokenizer.nextToken();
    	
    	while (boundary.equals(data) == false) {
    		careTime += dataTokenizer.nextToken();
    	}
    }
    
    public void print() {
    	System.out.printf("[%s] 이름: %s / phone: %s", hospitalId, hospitalName, phoneNumber);
    	System.out.println();
    }

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
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    
}

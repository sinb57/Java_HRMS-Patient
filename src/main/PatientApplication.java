package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.service.PatientService;
import main.domain.*;

// GUI Class
public class PatientApplication {
	private PatientService service = new PatientService();
	private Scanner scan = new Scanner(System.in);

	
	public void run() {
		
		service.connect("localhost",  9999);

		// 로그인
        String userId = scan.next();
        String userPw = scan.next();
        if (service.login(userId, userPw)) {
        	System.out.println("Login Success");
        }
        else {
        	System.out.println("Login Fail");
        	return;
        }
        
        // 회원정보 출력
        printPatient();
        
        // 병원찾기
        searchHospital();
	}
	
	// 회원정보 출력
	public void printPatient() {
		Patient patient = service.getPatient();
		
		System.out.println(patient.getPatientId() + " 회원 정보 입니다.");
		
		patient.print();
	}
	
	
	public void modifyPatientPw() {
		String passwd = "";
		String passwd2 = "";

		System.out.println("수정할 내용을 작성해주세요.");
		
		System.out.printf("비밀번호 -> ");
		passwd = scan.next();
		
		System.out.printf("비밀번호 재입력  -> ");
		passwd2 = scan.next();
		
		service.modifyPatientPw(passwd, passwd2);
	}
	
	
	public void searchHospital() {
		
		System.out.println("병원 검색 기능입니다.");
		
		System.out.printf("페이지 번호 -> ");
		int pageNum = scan.nextInt();

		System.out.printf("키워드 -> ");
		String keyword = scan.nextLine();
		
		ArrayList<Hospital> hospitalList = service.getHospitalList(pageNum, keyword);
		
		for (Hospital hospital: hospitalList)
			hospital.print();		
	}
	
	
	public void printReservationList() {
		
		System.out.println("예약 목록입니다.");

		ArrayList<Reservation> reservationList = service.getReservationList();
		
		for (Reservation reservation: reservationList) {
			reservation.print();
		}
	}
	
	public void printReservation(long reservationId) {
		System.out.println("예약 정보입니다.");
		
		Reservation reservation = service.getReservation(reservationId);
		
		reservation.print();
	}
	

	
    public static void main(String args[]){
    	PatientApplication app = new PatientApplication();
    	app.run();
    }
}

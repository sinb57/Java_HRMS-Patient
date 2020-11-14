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

        login();
		
        runMenu();
        
        //logout();
	}
	
	public void runMenu() {

		int num = 0;
		
		while(true){
			System.out.println("1 개인정보    2 병원 메뉴    3 예약 메뉴    0 종료");
			num = scan.nextInt();
			scan.nextLine();

			switch (num) {
			case 1:
				runMenuSelfInfo();
				break;
				
			case 2:
				runMenuHospital();
				break;
				
			case 3:
				searchHospital();
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("잘못입력하였습니다.");
				break;
			}
			if (num == 0) break;
		}
	}

	public void runMenuSelfInfo() {

		int num = 0;
		
		while(true){
			System.out.println("개인정보 메뉴입니다!!");
			System.out.println("1 개인정보 보기    2 비밀번호 변경     0 돌아가기");
			num = scan.nextInt();
			scan.nextLine();

			switch (num) {
			case 1:
				System.out.println("개인정보 보기");
				printSelfInfo();
				break;
				
			case 2:
				System.out.println("비밀번호 변경");
				modifyPassword();
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("잘못입력하였습니다.");
				break;
			}
			if (num == 0) break;
		}
	}
	

	public void runMenuHospital() {

		int num = 0;
		
		while(true){
			System.out.println("병원 메뉴입니다!!");
			System.out.println("1 병원리스트    2 병원 상세 보기    3 병원 조회     0 돌아가기");
			num = scan.nextInt();
			scan.nextLine();

			switch (num) {
			case 1:
				System.out.println("병원리스트");
				printHospitalList();
				break;
				
			case 2:
				System.out.println("병원 상세 보기");
				printHospitalInfo();
				break;
				
			case 3:
				System.out.println("병원 조회");
				searchHospital();
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("잘못입력하였습니다.");
				break;
			}
			if (num == 0) break;
		}
	}
	
	public void runMenuReservation() {

		int num = 0;
		
		while(true){
			System.out.println("예약 메뉴입니다!!");
			System.out.println("1 예약 리스트     0 돌아가기");
			num = scan.nextInt();
			scan.nextLine();
			
			switch (num) {
			case 1:
				System.out.println("예약리스트 보기");
				System.out.println("준비중입니다.");
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("잘못입력하였습니다.");
				break;
			}
			if (num == 0) break;
		}
	}
	
	
	
	
	public void login() {
		System.out.println("로그인을 진행하겠습니다.");
		System.out.print("아이디: ");
        String userId = scan.nextLine();
		System.out.print("비밀번호: ");
        String userPw = scan.nextLine();
        
        if (service.login(userId, userPw)) {
        	System.out.println("Login Success");
        }
        else {
        	System.out.println("Login Fail");
        	System.exit(-1);
        }
        System.out.println();
    	return;
	}
	
	public void printSelfInfo() {
		Patient patient = service.getSelfInfo();
		
		if (patient == null)
			System.out.println("정보가 없습니다.");
		else {
			System.out.println(patient.getPatientId() + " 회원 정보 입니다.");
			
			patient.print();
		}
		System.out.println();
	}
	
	public void modifyPassword() {
		String passwdFrom = "";
		String passwdTo = "";
		String passwdRe = "";

		System.out.printf("현재 비밀번호 -> ");
		passwdFrom = scan.next();

		System.out.printf("변경할 비밀번호 -> ");
		passwdTo = scan.next();
		
		System.out.printf("비밀번호 재입력  -> ");
		passwdRe = scan.next();
		
		if (service.modifyPatientPw(passwdFrom, passwdTo, passwdRe))
			System.out.println("변경 성공");
		else
			System.out.println("변경 실패");
		
		System.out.println();
	}
	
	
	public void printHospitalList() {
		
		System.out.println("병원 리스트를 출력하겠습니다.");
		
		System.out.println("영업중인 병원만 출력하기 원하시면 y를 입력해주세요.");
		
		boolean isSelectedOnlyOpend = false;
		if (scan.nextLine().equals("y")) {
			isSelectedOnlyOpend = true;
		}
		
		
		int pageNum = 1;

		while (true) {
			
			ArrayList<Hospital> hospitalList = service.getHospitalList(pageNum, isSelectedOnlyOpend, "");
			
			if (hospitalList == null) {
				System.out.println("끝입니다.");
				break;
			}
			
			for (Hospital hospital: hospitalList)
				hospital.print();
			
			pageNum++;
		}
		
		System.out.println();
	}
	
	public void printHospitalInfo() {
		
		System.out.println("병원 상세보기입니다.");
		
		System.out.println("상세보기를 원하는 병원의 아이디를 입력하세요.");
		System.out.print(">> ");
		
		String hospitalId = scan.nextLine();

		Hospital hospital = service.getHospital(hospitalId);
		
		if (hospital == null) {
			System.out.println("정보가 없습니다.");
			return;
		}
		
		hospital.printDetail();
	
	
		System.out.println();
	}
	
	public void searchHospital() {
		
		System.out.println("병원 조회입니다.");
		
		int pageNum = 1;

		System.out.print("키워드를 입력하세요: ");
		String keyword = scan.nextLine();
		
		ArrayList<Hospital> hospitalList = service.getHospitalList(pageNum, false, keyword);
		
		for (Hospital hospital: hospitalList)
			hospital.printDetail();	
		
		if (hospitalList == null)
			System.out.println("없습니다.");
		
		System.out.println();
	}
	
	
	public void printReservationList() {
		
		System.out.println("예약 목록입니다.");

		ArrayList<Reservation> reservationList = service.getReservationList();
		
		for (Reservation reservation: reservationList) {
			reservation.print();
		}

		if (reservationList == null)
			System.out.println("없습니다.");
		
	}
	
	public void printReservation(long reservationId) {
		
		System.out.println("예약 정보입니다.");
		
		Reservation reservation = service.getReservation(reservationId);
		
		if (reservation == null)
			System.out.println("없습니다.");
		
		reservation.print();
	}
	

	
    public static void main(String args[]){
    	PatientApplication app = new PatientApplication();
    	app.run();
    }
}

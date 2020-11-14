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
			System.out.println("1 ��������    2 ���� �޴�    3 ���� �޴�    0 ����");
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
				System.out.println("�߸��Է��Ͽ����ϴ�.");
				break;
			}
			if (num == 0) break;
		}
	}

	public void runMenuSelfInfo() {

		int num = 0;
		
		while(true){
			System.out.println("�������� �޴��Դϴ�!!");
			System.out.println("1 �������� ����    2 ��й�ȣ ����     0 ���ư���");
			num = scan.nextInt();
			scan.nextLine();

			switch (num) {
			case 1:
				System.out.println("�������� ����");
				printSelfInfo();
				break;
				
			case 2:
				System.out.println("��й�ȣ ����");
				modifyPassword();
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("�߸��Է��Ͽ����ϴ�.");
				break;
			}
			if (num == 0) break;
		}
	}
	

	public void runMenuHospital() {

		int num = 0;
		
		while(true){
			System.out.println("���� �޴��Դϴ�!!");
			System.out.println("1 ��������Ʈ    2 ���� �� ����    3 ���� ��ȸ     0 ���ư���");
			num = scan.nextInt();
			scan.nextLine();

			switch (num) {
			case 1:
				System.out.println("��������Ʈ");
				printHospitalList();
				break;
				
			case 2:
				System.out.println("���� �� ����");
				printHospitalInfo();
				break;
				
			case 3:
				System.out.println("���� ��ȸ");
				searchHospital();
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("�߸��Է��Ͽ����ϴ�.");
				break;
			}
			if (num == 0) break;
		}
	}
	
	public void runMenuReservation() {

		int num = 0;
		
		while(true){
			System.out.println("���� �޴��Դϴ�!!");
			System.out.println("1 ���� ����Ʈ     0 ���ư���");
			num = scan.nextInt();
			scan.nextLine();
			
			switch (num) {
			case 1:
				System.out.println("���ฮ��Ʈ ����");
				System.out.println("�غ����Դϴ�.");
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("�߸��Է��Ͽ����ϴ�.");
				break;
			}
			if (num == 0) break;
		}
	}
	
	
	
	
	public void login() {
		System.out.println("�α����� �����ϰڽ��ϴ�.");
		System.out.print("���̵�: ");
        String userId = scan.nextLine();
		System.out.print("��й�ȣ: ");
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
			System.out.println("������ �����ϴ�.");
		else {
			System.out.println(patient.getPatientId() + " ȸ�� ���� �Դϴ�.");
			
			patient.print();
		}
		System.out.println();
	}
	
	public void modifyPassword() {
		String passwdFrom = "";
		String passwdTo = "";
		String passwdRe = "";

		System.out.printf("���� ��й�ȣ -> ");
		passwdFrom = scan.next();

		System.out.printf("������ ��й�ȣ -> ");
		passwdTo = scan.next();
		
		System.out.printf("��й�ȣ ���Է�  -> ");
		passwdRe = scan.next();
		
		if (service.modifyPatientPw(passwdFrom, passwdTo, passwdRe))
			System.out.println("���� ����");
		else
			System.out.println("���� ����");
		
		System.out.println();
	}
	
	
	public void printHospitalList() {
		
		System.out.println("���� ����Ʈ�� ����ϰڽ��ϴ�.");
		
		System.out.println("�������� ������ ����ϱ� ���Ͻø� y�� �Է����ּ���.");
		
		boolean isSelectedOnlyOpend = false;
		if (scan.nextLine().equals("y")) {
			isSelectedOnlyOpend = true;
		}
		
		
		int pageNum = 1;

		while (true) {
			
			ArrayList<Hospital> hospitalList = service.getHospitalList(pageNum, isSelectedOnlyOpend, "");
			
			if (hospitalList == null) {
				System.out.println("���Դϴ�.");
				break;
			}
			
			for (Hospital hospital: hospitalList)
				hospital.print();
			
			pageNum++;
		}
		
		System.out.println();
	}
	
	public void printHospitalInfo() {
		
		System.out.println("���� �󼼺����Դϴ�.");
		
		System.out.println("�󼼺��⸦ ���ϴ� ������ ���̵� �Է��ϼ���.");
		System.out.print(">> ");
		
		String hospitalId = scan.nextLine();

		Hospital hospital = service.getHospital(hospitalId);
		
		if (hospital == null) {
			System.out.println("������ �����ϴ�.");
			return;
		}
		
		hospital.printDetail();
	
	
		System.out.println();
	}
	
	public void searchHospital() {
		
		System.out.println("���� ��ȸ�Դϴ�.");
		
		int pageNum = 1;

		System.out.print("Ű���带 �Է��ϼ���: ");
		String keyword = scan.nextLine();
		
		ArrayList<Hospital> hospitalList = service.getHospitalList(pageNum, false, keyword);
		
		for (Hospital hospital: hospitalList)
			hospital.printDetail();	
		
		if (hospitalList == null)
			System.out.println("�����ϴ�.");
		
		System.out.println();
	}
	
	
	public void printReservationList() {
		
		System.out.println("���� ����Դϴ�.");

		ArrayList<Reservation> reservationList = service.getReservationList();
		
		for (Reservation reservation: reservationList) {
			reservation.print();
		}

		if (reservationList == null)
			System.out.println("�����ϴ�.");
		
	}
	
	public void printReservation(long reservationId) {
		
		System.out.println("���� �����Դϴ�.");
		
		Reservation reservation = service.getReservation(reservationId);
		
		if (reservation == null)
			System.out.println("�����ϴ�.");
		
		reservation.print();
	}
	

	
    public static void main(String args[]){
    	PatientApplication app = new PatientApplication();
    	app.run();
    }
}

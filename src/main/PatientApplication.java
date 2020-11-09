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

		// �α���
        String userId = scan.next();
        String userPw = scan.next();
        if (service.login(userId, userPw)) {
        	System.out.println("Login Success");
        }
        else {
        	System.out.println("Login Fail");
        	return;
        }
        
        // ȸ������ ���
        printPatient();
        
        //
        
	}
	
	// ȸ������ ���
	public void printPatient() {
		Patient patient = service.patient;
		
		System.out.println(patient.getPatientId() + " ȸ�� ���� �Դϴ�.");
		
		patient.print();
	}
	
	
	public void modifyPatientPw() {
		String passwd = "";
		String passwd2 = "";

		System.out.println("������ ������ �ۼ����ּ���.");
		
		System.out.printf("��й�ȣ -> ");
		passwd = scan.next();
		
		System.out.printf("��й�ȣ ���Է�  -> ");
		passwd2 = scan.next();
		
		service.modifyPatientPw(passwd, passwd2);
	}
	
	
	public void searchHospital() {
		int pageNum = 0;
		String keyword = scan.nextLine();
		
		ArrayList<Hospital> hospitalList = service.getHospitalList(pageNum, keyword);
		
		for (Hospital hospital: hospitalList)
			hospital.print();
		
	}
	
    public static void main(String args[]){
    	PatientApplication app = new PatientApplication();
    	app.run();
    }
}

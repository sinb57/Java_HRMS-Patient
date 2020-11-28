package main;

import main.service.PatientService;

public class PatientApplication {
	private PatientService service = new PatientService();

	
	public void run() {
		service.connect("localhost",  9999);

	}


    public static void main(String args[]){
    	PatientApplication app = new PatientApplication();
    	app.run();
    }
}

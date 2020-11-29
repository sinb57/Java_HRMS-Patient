package main;

import main.gui.PageHandler;

public class PatientApplication {

	public void run() {
		PageHandler pageHandler = new PageHandler();
		pageHandler.start();
	}
	
	public static void main(String[] args) {
		PatientApplication application = new PatientApplication();
		application.run();
	}
}

package main.service;

import java.util.ArrayList;
import java.util.StringTokenizer;

import main.domain.*;
import main.socket.SocketHandler;

public class PatientService {
	private Patient patient = new Patient();
	private SocketHandler socketHandler = new SocketHandler();

	public boolean connect(String ipAddress, int port) {
		return socketHandler.run(ipAddress, port);
	}

	public boolean login(String userId, String userPw) {
		StringTokenizer tokenizer = socketHandler.login(userId, userPw);

		if (tokenizer == null)
			return false;

		patient.init(tokenizer);
		return true;
	}

	public boolean logout() {
		String cookie = patient.getCookie();

		if (socketHandler.logout(cookie)) {
			patient.clear();
			return true;
		}

		return false;
	}

	public boolean join(String userId, String userPw, String userPw2, String userName, String phoneNumber) {
		if (userPw.equals(userPw2)) {
			return socketHandler.join(userId, userPw, userName, phoneNumber);
		}
		return false;
	}

	public boolean leave() {
		String cookie = patient.getCookie();

		if (socketHandler.leave(cookie)) {
			patient.clear();
			return true;
		}

		return false;
	}

	public Patient getSelfInfo() {
		String cookie = patient.getCookie();
		StringTokenizer tokenizer = socketHandler.requestSelfInfo(cookie);

		if (tokenizer == null)
			return null;

		patient.read(tokenizer);
		return patient;
	}

	public boolean modifyPatientPw(String passwdFrom, String passwdTo, String passwdRe) {
		if (passwdTo.equals(passwdRe)) {
			String cookie = patient.getCookie();
			if (socketHandler.modifySelfPw(cookie, passwdFrom, passwdTo))
				return true;
		}
		return false;
	}

	public ArrayList<Hospital> getHospitalList(int pageNum, String address, String careType, String state,
			String keywords) {

		String cookie = patient.getCookie();

		if (pageNum < 0)
			pageNum = 1;

		if (keywords == null)
			keywords = "";

		StringTokenizer tokenizer = socketHandler.requestHospitalList(cookie, pageNum, address, careType, state,
				keywords);

		if (tokenizer == null)
			return null;

		ArrayList<Hospital> hospitalList = new ArrayList<>();
		Hospital hospital;

		while (tokenizer.hasMoreTokens()) {
			hospital = new Hospital();
			hospital.read(tokenizer);
			hospitalList.add(hospital);
		}

		return hospitalList;
	}

	public Hospital getHospital(String hospitalId) {

		String cookie = patient.getCookie();

		StringTokenizer tokenizer = socketHandler.requestHospitalInfo(cookie, hospitalId);

		if (tokenizer == null)
			return null;

		Hospital hospital = new Hospital();

		hospital.read(tokenizer);

		return hospital;
	}

	public ArrayList<Reservation> getReservationList(int pageNum) {

		String cookie = patient.getCookie();

		if (pageNum < 0)
			pageNum = 1;

		StringTokenizer tokenizer = socketHandler.requestReservationList(cookie, pageNum);

		if (tokenizer == null)
			return null;

		ArrayList<Reservation> reservationList = new ArrayList<>();

		while (tokenizer.hasMoreTokens()) {
			Reservation reservation = new Reservation();
			reservation.read(tokenizer);
			reservationList.add(reservation);
		}

		return reservationList;
	}

	public Reservation getRecentReservation(String hospitalId) {

		String cookie = patient.getCookie();

		StringTokenizer tokenizer = socketHandler.requestRecentReservation(cookie, hospitalId);

		if (tokenizer == null)
			return null;

		Reservation reservation = new Reservation();
		reservation.read(tokenizer);

		return reservation;
	}

	public Reservation getReservation(String hospitalId, String reservationDate, String reservationTime) {

		String cookie = patient.getCookie();

		StringTokenizer tokenizer = socketHandler.requestReservationInfo(cookie, hospitalId, reservationDate,
				reservationTime);

		if (tokenizer == null)
			return null;

		Reservation reservation = new Reservation();
		reservation.read(tokenizer);

		return reservation;
	}

	public boolean makeReservation(String hospitalId, String reservationDate, String reservationTime, String careType,
			ArrayList<String> symptomList) {

		String cookie = patient.getCookie();

		if (socketHandler.makeReservation(cookie, hospitalId, reservationDate, reservationTime, careType, symptomList))
			return true;
		return false;
	}

	public boolean cancelReservation(String hospitalId, String reservationDate, String reservationTime) {
		String cookie = patient.getCookie();

		if (socketHandler.cancelReservation(cookie, hospitalId, reservationDate, reservationTime))
			return true;
		return false;
	}

	public Patient getPatient() {
		return patient;
	}

}

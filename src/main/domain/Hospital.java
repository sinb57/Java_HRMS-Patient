package main.domain;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Hospital {
	private String hospitalId;
	private String hospitalName;
	private String phoneNumber;
	private String address;
	private HashMap<String, String[]> careTypeMap = new HashMap<>();
	private CareTime[] careTimeList = new CareTime[7];

	public void read(StringTokenizer hospitalTokenizer) {
		hospitalId = hospitalTokenizer.nextToken().trim();
		hospitalName = hospitalTokenizer.nextToken().trim();
		phoneNumber = hospitalTokenizer.nextToken().trim();
		address = hospitalTokenizer.nextToken().trim();

		String[] careTypeList = hospitalTokenizer.nextToken().trim().split(" ");

		for (String careType : careTypeList) {
			String[] docterList = hospitalTokenizer.nextToken().trim().split(" ");
			careTypeMap.put(careType, docterList);
		}

		for (int i = 0; i < 7; i++) {
			try {
				careTimeList[i] = new CareTime();
				careTimeList[i].read(hospitalTokenizer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Set<String> getCareTypeList() {
		return careTypeMap.keySet();
	}

	public String getStateNow() {
		int state = isOpenNow();

		switch (state) {
		case 0:
			return "영업중";
		case 1:
			return "휴무";
		case 2:
			return "영업마감";
		case 3:
			return "식사중";
		default:
			return "비정상";
		}
	}

	// 0:영업중 1:휴무 2:준비중 3:식사중 -1:비정상
	public int isOpenNow() {
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();

		String dayOfWeek = nowDate.format(DateTimeFormatter.ofPattern("E요일"));

		for (CareTime careTime : careTimeList) {
			if (careTime.equalsDate(dayOfWeek)) {
				if (careTime.isClosedDay())
					return 1;
				if (careTime.isCareTimeNow(nowTime)) {
					if (careTime.isLunchTimeNow(nowTime))
						return 3;
					return 0;
				}
				return 2;
			}
		}

		return -1;
	}

	public ArrayList<String> getCareTimeList() {
		ArrayList<String> careTimeStringList = new ArrayList<>();

		for (CareTime careTime : careTimeList) {
			careTimeStringList.add(careTime.toString());
		}
		return careTimeStringList;
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

	class CareTime {
		private String dayOfWeek;
		private LocalTime startCareTime;
		private LocalTime endCareTime;
		private LocalTime startLunchTime;
		private LocalTime endLunchTime;
		private boolean hasCareTime;
		private boolean hasLunchTime;

		public String toString() {
			String dataFormat = "%s : %s~%s (식사시간 %s~%s)";
			String data = "";

			if (!hasCareTime)
				return String.format("%s : 진료안함", dayOfWeek);

			if (!hasLunchTime)
				return String.format("%s : %s~%s", dayOfWeek, startCareTime, endCareTime);

			return String.format("%s : %s~%s (식사시간 %s~%s)", dayOfWeek, startCareTime, endCareTime, startLunchTime,
					endLunchTime);
		}

		void read(StringTokenizer careTimeTokenizer) throws IOException {
			StringTokenizer dataTokenizer = new StringTokenizer(careTimeTokenizer.nextToken(), " ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

			dayOfWeek = dataTokenizer.nextToken();

			if (dataTokenizer.nextToken().equals("x")) {
				hasCareTime = false;
				return;
			}

			hasCareTime = true;

			startCareTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
			endCareTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);

			if (dataTokenizer.nextToken().equals("x")) {
				hasLunchTime = false;
				return;
			}

			hasLunchTime = true;

			startLunchTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
			endLunchTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
		}

		boolean equalsDate(String dayOfWeek) {
			if (this.dayOfWeek.equals(dayOfWeek))
				return true;
			return false;
		}

		boolean isClosedDay() {
			if (hasCareTime)
				return false;
			return true;
		}

		boolean isCareTimeNow(LocalTime now) {
			if (hasCareTime) {
				if (now.isBefore(startCareTime))
					return false;
				if (now.isAfter(endCareTime))
					return false;
				return true;
			}
			return false;
		}

		boolean isLunchTimeNow(LocalTime now) {
			if (hasLunchTime) {
				if (now.isBefore(startLunchTime))
					return false;
				if (now.isAfter(endLunchTime))
					return false;
				return true;
			}
			return false;
		}

	}

}

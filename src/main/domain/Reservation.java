package main.domain;

import java.util.StringTokenizer;

public class Reservation {
    private long reservationId;
    private String hospitalId;
	private String reservationDate;
	private String reservationTime;
	private int waitingNum;
	
    public void read(StringTokenizer dataTokenizer) {
    	reservationId = Long.parseLong(dataTokenizer.nextToken());
    	hospitalId = dataTokenizer.nextToken();
    	reservationDate = dataTokenizer.nextToken();
    	reservationTime = dataTokenizer.nextToken();
    	waitingNum = -1;
    }
	
    public boolean matches(long reservationId) {
		if (this.reservationId == reservationId)
			return true;
		return false;
	}
	
    public void setWaitingNum(int waitingNum) {
		this.waitingNum = waitingNum;
	}
	
    // �ӽ� �޼ҵ� -> GUI ������ ����
    public void print() {
		System.out.printf("[%s] %s %s ", hospitalId, reservationDate, reservationTime);
		
		if (waitingNum != -1)
			System.out.printf("����:%d", waitingNum);
		
    	System.out.println();
    }
	
}
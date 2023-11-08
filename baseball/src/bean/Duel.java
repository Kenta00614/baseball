package bean;

import java.io.Serializable;

public class Duel implements Serializable {
	private int duelId;
	private int school1;
	private int school2;
	private String status;
	private String round;

	public int getDuelId() {
		return duelId;
	}
	public void setDuelId(int duelId) {
		this.duelId = duelId;
	}
	public int getSchool1() {
		return school1;
	}
	public void setSchool1(int school1) {
		this.school1 = school1;
	}
	public int getSchool2() {
		return school2;
	}
	public void setSchool2(int school2) {
		this.school2 = school2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
}
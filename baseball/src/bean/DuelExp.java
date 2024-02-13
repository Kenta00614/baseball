package bean;

import common.Constants;

public class DuelExp extends Duel {
	private School schoolB1;
	private School schoolB2;
	private Duel duel;
	private String schoolNameA;
	private String schoolNameB;
	private int schoolId1;
	private int schoolId2;
	private String status;
	private String round;
	private String statusStr;
	private String roundStr;
	private int duelId;


	public int getDuelId() {
		return duelId;
	}
	public void setDuelId(int duelId) {
		this.duelId = duelId;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr() {
		String statusStr = Constants.DUEL_STATUS.get(this.status);
		this.statusStr = statusStr;
	}
	public String getRoundStr() {
		return roundStr;
	}
	public void setRoundStr() {
		String roundStr = Constants.DUEL_ROUND.get(this.round);
		this.roundStr = roundStr;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public int getSchoolId1() {
		return schoolId1;
	}
	public void setSchoolId1(int schoolId1) {
		this.schoolId1 = schoolId1;
	}
	public int getSchoolId2() {
		return schoolId2;
	}
	public void setSchoolId2(int schoolId2) {
		this.schoolId2 = schoolId2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public School getSchoolB1() {
		return schoolB1;
	}
	public void setSchoolB1(School schoolB1) {
		this.schoolB1 = schoolB1;
	}
	public School getSchoolB2() {
		return schoolB2;
	}
	public void setSchoolB2(School schoolB2) {
		this.schoolB2 = schoolB2;
	}
	public Duel getDuel() {
		return duel;
	}
	public void setDuel(Duel duel) {
		this.duel = duel;
	}
	public String getSchoolNameA() {
		return schoolNameA;
	}
	public void setSchoolNameA(String schoolNameA) {
		this.schoolNameA = schoolNameA;
	}
	public String getSchoolNameB() {
		return schoolNameB;
	}
	public void setSchoolNameB(String schoolNameB) {
		this.schoolNameB = schoolNameB;
	}
}

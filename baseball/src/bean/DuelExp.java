package bean;

public class DuelExp extends Duel {
	private School schoolB1;
	private School schoolB2;
	private Duel duel;

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

}

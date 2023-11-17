package bean;

import java.sql.Date;

public class TicketsExp extends Tickets{

	private Date eventDate;
	private int spectatorId;
	private String type;
	private String step;
	private int number;
	private int gate;
	private String passage;
	private String block;
	private int ordinalNum;
	private String tournamentName;

	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public int getSpectatorId() {
		return spectatorId;
	}
	public void setSpectatorId(int spectatorId) {
		this.spectatorId = spectatorId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getGate() {
		return gate;
	}
	public void setGate(int gate) {
		this.gate = gate;
	}
	public String getPassage() {
		return passage;
	}
	public void setPassage(String passage) {
		this.passage = passage;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public int getOrdinalNum() {
		return ordinalNum;
	}
	public void setOrdinalNum(int ordinalNum) {
		this.ordinalNum = ordinalNum;
	}
	public String getTournamentName() {
		return tournamentName;
	}
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
}

package bean;

import java.io.Serializable;

public class Tournament implements Serializable {
	private int tournamentId;
	private int year;
	private int ordinalNum;
	private String name;
	private String season;

	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getOrdinalNum() {
		return ordinalNum;
	}
	public void setOrdinalNum(int ordinalNum) {
		this.ordinalNum = ordinalNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
}
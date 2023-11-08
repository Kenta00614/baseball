package bean;

import java.io.Serializable;
import java.sql.Date;

public class Match implements Serializable {
	private int matchId;
	private int tournamentId;
	private Date eventDate;
	private Date saleStartAt;
	private int duel1;
	private int duel2;
	private int duel3;
	private int duel4;

	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getSaleStartAt() {
		return saleStartAt;
	}
	public void setSaleStartAt(Date saleStartAt) {
		this.saleStartAt = saleStartAt;
	}
	public int getDuel1() {
		return duel1;
	}
	public void setDuel1(int duel1) {
		this.duel1 = duel1;
	}
	public int getDuel2() {
		return duel2;
	}
	public void setDuel2(int duel2) {
		this.duel2 = duel2;
	}
	public int getDuel3() {
		return duel3;
	}
	public void setDuel3(int duel3) {
		this.duel3 = duel3;
	}
	public int getDuel4() {
		return duel4;
	}
	public void setDuel4(int duel4) {
		this.duel4 = duel4;
	}
}
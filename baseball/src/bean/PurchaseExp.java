package bean;

import java.sql.Date;

public class PurchaseExp extends Purchase{

	private String tournamentName;
	private String seatType;
	private Date eventDate;
	private Date saleAt;
	private boolean isChild;
	public String getTournamentName() {
		return tournamentName;
	}
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getSaleAt() {
		return saleAt;
	}
	public void setSaleAt(Date saleAt) {
		this.saleAt = saleAt;
	}
	public boolean isChild() {
		return isChild;
	}
	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

}
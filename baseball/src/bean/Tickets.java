package bean;

public class Tickets implements java.io.Serializable {
	private String ticketsId;
	private int purchaseId;
	private int matchId;
	private String seatId;
	private String status;
	private boolean isShared;

	public String getTicketsId() {
		return ticketsId;
	}
	public void setTicketsId(String ticketsId) {
		this.ticketsId = ticketsId;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isShared() {
		return isShared;
	}
	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}
}

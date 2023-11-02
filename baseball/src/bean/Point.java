package bean;

public class Point implements java.io.Serializable {
	private int pointId;
	private int spectatorId;
	private int fluctuation;
	private int purchaseId;
	private String ticketsId;

	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public int getSpectatorId() {
		return spectatorId;
	}
	public void setSpectatorId(int spectatorId) {
		this.spectatorId = spectatorId;
	}
	public int getFluctuation() {
		return fluctuation;
	}
	public void setFluctuation(int fluctuation) {
		this.fluctuation = fluctuation;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getTicketsId() {
		return ticketsId;
	}
	public void setTicketsId(String ticketsId) {
		this.ticketsId = ticketsId;
	}
}

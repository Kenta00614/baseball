package bean;

import java.sql.Timestamp;

public class Purchase implements java.io.Serializable {
	private int purchaseId;
	private int spectatorId;
	private Timestamp purchaseAt;

	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getSpectatorId() {
		return spectatorId;
	}
	public void setSpectatorId(int spectatorId) {
		this.spectatorId = spectatorId;
	}
	public Timestamp getPurchaseAt() {
		return purchaseAt;
	}
	public void setPurchaseAt(Timestamp purchaseAt) {
		this.purchaseAt = purchaseAt;
	}
}

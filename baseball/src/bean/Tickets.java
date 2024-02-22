package bean;

import java.util.UUID;

public class Tickets implements java.io.Serializable {
	private String ticketsId;
	private int purchaseId;
	private int matchId;
	private String seatId;
	private String status;
	private boolean isShared;
	private boolean isChild;
	private UUID uuid;

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
	public boolean getIsShared() {
		return isShared;
	}
	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}
	public boolean isChild() {
		return isChild;
	}
	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public void createTicketsId(){
		String id = "";
		id += this.seatId;
		id += "r00";
		id += "20240223";
        this.ticketsId = id;
	}

	public String toString(){
		String str = "ID:";
		str += this.ticketsId;
		str += "　購入番号：";
		str += this.purchaseId;
		str += "　試合日番号：";
		str += this.matchId;
		str += "　座席番号：";
		str += this.seatId;
		str += "　ステータス：";
		str += this.status;
		str += "　共有：";
		str += this.isShared;
		str += "\n";
		return str;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}


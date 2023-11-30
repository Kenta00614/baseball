package bean;

import java.sql.Date;
import java.sql.Timestamp;

public class PurchaseExp extends Purchase{

	private String tournamentName;
	private String seatType;
	private Date eventDate;
	private Timestamp purchaseAt;
	private boolean isChild;
	private int ordinalNum;
	private String dateStr;
	private String purchaseStr;

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
	public Timestamp getPurchaseAt() {
		return purchaseAt;
	}
	public void setPurchaseAt(Timestamp purchaseAt) {
		this.purchaseAt = purchaseAt;
	}
	public boolean isChild() {
		return isChild;
	}
	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}
	public int getOrdinalNum() {
		return ordinalNum;
	}
	public void setOrdinalNum(int ordinalNum) {
		this.ordinalNum = ordinalNum;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr() {
		String dateStr = this.eventDate.toString();
        int dateIndex = dateStr.lastIndexOf('-');
        String date = dateStr.substring(dateIndex + 1);
        String yyyymm = dateStr.substring(0,dateIndex);
        int monthIndex = yyyymm.lastIndexOf('-');
        String month = yyyymm.substring(monthIndex + 1);
        String yyyy = yyyymm.substring(0,monthIndex);

		this.dateStr = yyyy +"年"+ month+"月"+date+"日";
	}
	public String getPurchaseStr() {
		return purchaseStr;
	}

	public void setPurchaseStr() {
		String purchaseStr = this.purchaseAt.toString();
        int dateIndex = purchaseStr.lastIndexOf('-');
        String date = purchaseStr.substring(dateIndex + 1, dateIndex+3);
        String yyyymm = purchaseStr.substring(0,dateIndex);
        int monthIndex = yyyymm.lastIndexOf('-');
        String month = yyyymm.substring(monthIndex + 1);
        String yyyy = yyyymm.substring(0,monthIndex);

		this.purchaseStr = yyyy +"年"+ month +"月"+ date +"日";
	}
}
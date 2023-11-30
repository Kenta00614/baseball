package bean;

import java.sql.Date;
import java.util.Calendar;

import common.Constants;

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
	private String eventDayOfWeek;
	private String typeStr;
	private int price;
	private boolean child;
//	日付oo月oo日表示にしないなら消す
	private String dateStr;
	private boolean shared;

	public boolean isShared() {
		return shared;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	public boolean isChild() {
		return child;
	}
	public void setChild(boolean child) {
		this.child = child;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice() {
		if (this.child && (this.type.equals("0F") || this.type.equals("0T") || this.type.equals("0R") || this.type.equals("0L"))) {
	        if (this.type.equals("0F") || this.type.equals("0T")) {
	            this.price = 1200;
	        } else {
	        	this.price = 200;
	        }
	    } else {
            this.price = Constants.SEAT_PRICE.get(this.type);
	    }
	}
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
	public String getEventDayOfWeek() {
		return eventDayOfWeek;
	}
//	販売開始日の曜日セット
	public void setEventDayOfWeek() {
		Calendar searchCalendar = Calendar.getInstance();
		searchCalendar.setTime(this.eventDate);
	    int dayNumber = searchCalendar.get(Calendar.DAY_OF_WEEK);
	    if(dayNumber==0){
	    	this.eventDayOfWeek = "土";
	    }else if(dayNumber==1){
	    	this.eventDayOfWeek = "日";
	    }else if(dayNumber==2){
	    	this.eventDayOfWeek = "月";
	    }else if(dayNumber==3){
	    	this.eventDayOfWeek = "火";
	    }else if(dayNumber==4){
	    	this.eventDayOfWeek = "水";
	    }else if(dayNumber==5){
	    	this.eventDayOfWeek = "木";
	    }else if(dayNumber==6){
	    	this.eventDayOfWeek = "金";
	    }
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr() {
		this.typeStr = Constants.SEAT_TYPE.get(this.type);;
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

		this.dateStr = month+"月"+date+"日";
	}
}

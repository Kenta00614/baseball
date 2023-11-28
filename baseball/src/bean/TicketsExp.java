package bean;

import java.sql.Date;
import java.util.Calendar;

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
}

package bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class Match implements Serializable {
	private int matchId;
	private int tournamentId;
	private Date eventDate;
	private Date saleStartAt;
	private int duel1;
	private int duel2;
	private int duel3;
	private int duel4;
	private String eventDayOfWeek;
	private String saleDayOfWeek;
	private boolean dispFlg=false;
	private boolean saleFlg=false;

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
	public String getSaleDayOfWeek() {
		return saleDayOfWeek;
	}
//	開催日の曜日セット
	public void setSaleDayOfWeek() {
		Calendar searchCalendar = Calendar.getInstance();
		searchCalendar.setTime(this.saleStartAt);
	    int dayNumber = searchCalendar.get(Calendar.DAY_OF_WEEK);
	    if(dayNumber==0){
	    	this.saleDayOfWeek = "土";
	    }else if(dayNumber==1){
	    	this.saleDayOfWeek = "日";
	    }else if(dayNumber==2){
	    	this.saleDayOfWeek = "月";
	    }else if(dayNumber==3){
	    	this.saleDayOfWeek = "火";
	    }else if(dayNumber==4){
	    	this.saleDayOfWeek = "水";
	    }else if(dayNumber==5){
	    	this.saleDayOfWeek = "木";
	    }else if(dayNumber==6){
	    	this.saleDayOfWeek = "金";
	    }
	}
	public boolean isDispFlg() {
		return dispFlg;
	}
//	販売日の7日前だとtrue
	public void setDispFlg() {
		java.util.Date nowDate = new java.util.Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(this.saleStartAt);
        cl.add(Calendar.DAY_OF_MONTH, -7);
        if(cl.getTime().equals(nowDate)||cl.getTime().before(nowDate)){
        	this.dispFlg = true;
        }
	}
	public boolean isSaleFlg() {
		return saleFlg;
	}
//	販売日の10時になったらtrue
	public void setSaleFlg() {
		java.util.Date nowDate = new java.util.Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(this.saleStartAt);
        cl.add(Calendar.HOUR_OF_DAY, 10);
        if(cl.getTime().equals(nowDate)||cl.getTime().before(nowDate)){
        	this.saleFlg = true;
        }
	}
}
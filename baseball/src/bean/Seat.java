package bean;

import java.io.Serializable;

public class Seat implements Serializable {
	private String seatId;
	private String seatType;
	private String step;
	private String seatNo;
	private String entNo;
	private String passage;

	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getEntNo() {
		return entNo;
	}
	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}
	public String getPassage() {
		return passage;
	}
	public void setPassage(String passage) {
		this.passage = passage;
	}

	public void createSeatId(){
		String id = "";
		id += this.seatType;
		String step = "";
		if(this.step.length() == 1){
			step = "0"+this.step;
		}else{
			step = this.step;
		}
		id += step;
        String noStr = "";
        if(this.seatNo.length() == 1){
        	noStr = "00"+this.seatNo;
        }else if(this.seatNo.length() == 2){
        	noStr = "0"+this.seatNo;
        }else{
        	noStr = this.seatNo;
        }
        id += noStr;
        this.seatId = id;
	}

	public String toString(){
		String str = "ID:";
		str += this.seatId;
		str += "　席種：";
		str += this.seatType;
		str += "　段：";
		str += this.step;
		str += "　番号：";
		str += this.seatNo;
		return str;

	}
}
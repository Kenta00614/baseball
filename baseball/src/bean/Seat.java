package bean;

import java.io.Serializable;

import common.Constants;

public class Seat implements Serializable {
	private String seatId;
	private String type;
	private String step;
	private int number;
	private int gate;
	private String passage;
	private String block;
	private String typeStr;

	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
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

	public void createSeatId(){
		String id = "";
		id += this.type;
		String step = "";
		if(this.step.length() == 1){
			step = "0"+this.step;
		}else{
			step = this.step;
		}
		id += step;
        String noStr = "";
        if(this.number < 10){
        	noStr = "00"+this.number;
        }else if(this.number < 100){
        	noStr = "0"+this.number;
        }else{
        	noStr = String.valueOf(this.number);
        }
        id += noStr;
        this.seatId = id;
	}

	public String toString(){
		String str = "ID:";
		str += this.seatId;
		str += "　席種：";
		str += this.type;
		str += "　段：";
		str += this.step;
		str += "　番号：";
		str += this.number;
		str += "　入口：";
		str += this.gate;
		str += "　通路：";
		str += this.passage;
		str += "　ブロック：";
		str += this.block;
		str += "\n";
		return str;

	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr() {
		this.typeStr = Constants.SEAT_TYPE.get(this.type);;
	}
}
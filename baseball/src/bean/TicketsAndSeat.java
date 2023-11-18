package bean;

import java.sql.Date;

public class TicketsAndSeat implements java.io.Serializable {
	private Tickets ticket;
	private Seat seat;
	private Date eventDate;

	public Tickets getTicket() {
		return ticket;
	}
	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
package org.test.hotelsystp.entity;

import java.util.Date;

public class Reservation extends DataTable{
	private Integer reservationID = 0;
	private String reservationDetail = "";
	private Integer roomID = 0;
	private Integer guestID = 0;
	private String guestName = null;
	private String roomNumber = null;
	private Date reservationDate = null;
	
	public Integer getReservationID() {
		return reservationID;
	}
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}
	public String getReservationDetail() {
		return reservationDetail;
	}
	public void setReservationDetail(String reservationDetail) {
		this.reservationDetail = reservationDetail;
	}
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public Integer getGuestID() {
		return guestID;
	}
	public void setGuestID(Integer guestID) {
		this.guestID = guestID;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", reservationDetail=" + reservationDetail + ", roomID="
				+ roomID + ", guestID=" + guestID + ", guestName=" + guestName + ", roomNumber=" + roomNumber
				+ ", reservationDate=" + reservationDate + "]";
	}	


}
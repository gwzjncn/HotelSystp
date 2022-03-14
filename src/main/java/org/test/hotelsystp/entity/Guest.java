package org.test.hotelsystp.entity;

public class Guest extends DataTable{
	private Integer guestID = 0;
	private String guestName = null;
	private Integer reservationID = 0;
	private String guestIDCard = null;
	public Integer getGuestID() {
		return guestID;
	}
	public void setGuestID(Integer guestID) {
		this.guestID = guestID;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public Integer getReservationID() {
		return reservationID;
	}
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}
	public String getGuestIDCard() {
		return guestIDCard;
	}
	public void setGuestIDCard(String guestIDCard) {
		this.guestIDCard = guestIDCard;
	}
	
	@Override
	public String toString() {
		return "Guest [guestID=" + guestID + ", guestName=" + guestName + ", reservationID=" + reservationID
				+ ", guestIDCard=" + guestIDCard + "]";
	}
	

}
package org.test.hotelsystp.entity;

public class Room extends DataTable{
	private Integer roomID = 0;
	private Integer reservationID = 0;
	private String roomNumber = null;
	private Integer isAvailable = 0;
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public Integer getReservationID() {
		return reservationID;
	}
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", reservationID=" + reservationID + ", roomNumber=" + roomNumber
				+ ", isAvailable=" + isAvailable + "]";
	}
	
	

}
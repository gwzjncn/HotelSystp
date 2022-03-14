package org.test.hotelsystp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.hotelsystp.entity.Room;
import org.test.hotelsystp.service.IRoomService;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
	@Autowired
	private IRoomService roomService;

	@RequestMapping(value = "/delete/{id}")
	public void deleteRoom(@PathVariable int id) {
		roomService.delete(id);
	}

	@RequestMapping(value = "/")
	public List<Room> getRooms() {
		return roomService.findAll();
	}

	@RequestMapping(value = "/{id}")
	public Room getRoom(@PathVariable int id) {
		Room room = roomService.findOne(id);
		return room;
	}

	@RequestMapping(value = "/search/availableRooms/{reservationDate}")
	public List<Room> getRoomsByName(@PathVariable String reservationDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date reservationDateTmp = null;
		try {
			reservationDateTmp = simpleDateFormat.parse(reservationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    List<Room> rooms = roomService.findAvailableRooms(reservationDateTmp);
	    return rooms;
	}

	@RequestMapping(value = "/deployToRoomValue/{roomCount}")
	public int deployToRoomValue(@PathVariable int roomCount) {
		return roomService.deployToRoomValue(roomCount);
	}

	@RequestMapping(value = "/deployToSystemValue")
	public boolean deployToSystemValue() {
		return roomService.deployToSystemValue();
	}
}
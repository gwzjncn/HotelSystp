package org.test.hotelsystp.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.hotelsystp.entity.Reservation;
import org.test.hotelsystp.entity.Room;
import org.test.hotelsystp.service.IReservationService;
import org.test.hotelsystp.service.IRoomService;
import org.test.hotelsystp.util.IDGenerator;

@SpringBootTest
public class RoomServiceImplTest {
	
	@Autowired
	IRoomService roomService;
	
	@Autowired
	IReservationService reservationService;
	
	@Test
	public void testFindAll() {
		roomService.deployToRoomValue(10);
		assertEquals(10, roomService.findAll().size());
	}
	
	@Test
	public void testFindAvailableRooms() {
		roomService.deployToSystemValue();
		roomService.deployToRoomValue(4);
		
		IDGenerator idGenerator = IDGenerator.getInstance();
		Integer id = idGenerator.getIdNumber();
		System.out.println(id);
		
		System.out.println(roomService.findAll());
		
		Reservation reservation = new Reservation();
		reservation.setGuestName("GuestName1");
		reservation.setRoomNumber("RoomNumber1");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date reservationDate = null;
		try {
			reservationDate = simpleDateFormat.parse("2022-01-04"); 
			reservation.setReservationDate(reservationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	    reservationService.saveReservation(reservation);
		System.out.println(reservationService.findAll());
		
		List<Room> roomList = roomService.findAvailableRooms(reservationDate);
		System.out.println(roomList);
		assertEquals(4-1, roomList.size());
	}
	
	
}
package org.test.hotelsystp.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.hotelsystp.entity.Reservation;
import org.test.hotelsystp.service.IReservationService;
import org.test.hotelsystp.service.ReservationServiceImpl;
import org.test.hotelsystp.util.IDGenerator;
@SpringBootTest
public class ReservationServiceImplTest {
	
	@Autowired
	IReservationService reservationService;
	
	@Test
	public void testFindReservationDistinct() {
		IDGenerator idGenerator = IDGenerator.getInstance();
		Integer id = idGenerator.getIdNumber();
		System.out.println(id);
		
		Reservation reservation = new Reservation();
		reservation.setGuestName("GuestName1");
		reservation.setRoomNumber("RoomNumber1");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			reservation.setReservationDate(simpleDateFormat.parse("2022-01-04"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	    reservationService.saveReservation(reservation);
		System.out.println(reservationService.findAll());
		
		reservation = null;
		try {
			reservation = reservationService.findReservationDistinct("GuestName1", simpleDateFormat.parse("2022-01-04"), "RoomNumber1");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(reservation);
	}

}
package org.test.hotelsystp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.hotelsystp.entity.Guest;
import org.test.hotelsystp.entity.Reservation;
import org.test.hotelsystp.service.IReservationService;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController
{
  @Autowired
  private IReservationService reservationService;
 
  @RequestMapping(value = "/add/{guestName}/{reservationDate}/{roomNumber}")
  public int addReservation(@PathVariable String guestName, @PathVariable String reservationDate,
    @PathVariable String roomNumber)
  {
	Reservation reservation = new Reservation();
	reservation.setGuestName(guestName);
	reservation.setRoomNumber(roomNumber);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	try {
		reservation.setReservationDate(simpleDateFormat.parse(reservationDate));
	} catch (ParseException e) {
		e.printStackTrace();
		return 0;
	}
	
    int saveResult = reservationService.saveReservation(reservation);
    return saveResult;
  }
 
  @RequestMapping(value = "/delete/{id}")
  public void deleteReservation(@PathVariable int id)
  {
	  reservationService.delete(id);
  }
 
  @RequestMapping(value = "/")
  public List<Reservation> getReservations()
  {
    return reservationService.findAll();
  }
 
  @RequestMapping(value = "/{id}")
  public Reservation getReservation(@PathVariable int id)
  {
	Reservation reservation = reservationService.findOne(id);
    return reservation;
  }
  
  @RequestMapping(value = "/search/{guestName}")
  public List<Reservation> getReservationsByGuestName(@PathVariable String guestName)
  {
    List<Reservation> reservations = reservationService.findReservationsByGuestName(guestName);
    return reservations;
  }
}
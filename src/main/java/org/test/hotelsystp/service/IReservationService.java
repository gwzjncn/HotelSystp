package org.test.hotelsystp.service;

import java.util.Date;
import java.util.List;

import org.test.hotelsystp.entity.Reservation;
 
public interface IReservationService
{
  public List<Reservation> findAll();
 
  public int saveReservation(Reservation reservation);
   
  public Reservation findOne(int id);
 
  public void delete(int id);
 
  public List<Reservation> findReservationsByGuestName(String guestName);

  
  public Reservation findReservationDistinct(String guestName, Date reservationDate, String roomNumber);
}
package org.test.hotelsystp.service;

import java.util.Date;
import java.util.List;

import org.test.hotelsystp.entity.Room;
 
public interface IRoomService
{
  public List<Room> findAll();
 
  public void saveRoom(Room guest);
   
  public Room findOne(int id);
  
  public List<Room> findAvailableRooms(Date reservationDate);
 
  public void delete(int id);
 
  public Room findOneByRoomNumber(String name);
  
  public int deployToRoomValue(int roomCount);

  public boolean deployToSystemValue();
}
package org.test.hotelsystp.service;

import java.util.List;

import org.test.hotelsystp.entity.Guest;
 
public interface IGuestService
{
  public List<Guest> findAll();
 
  public void saveGuest(Guest guest);
   
  public Guest findOne(int id);
 
  public void delete(int id);
 
  public Guest findOneByName(String name);

}
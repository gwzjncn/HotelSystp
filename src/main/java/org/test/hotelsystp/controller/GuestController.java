package org.test.hotelsystp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.hotelsystp.entity.Guest;
import org.test.hotelsystp.service.IGuestService;

@RestController
@RequestMapping(value = "/guest")
public class GuestController
{
  @Autowired
  private IGuestService guestService;
 
  @RequestMapping(value = "/add/{id}/{guestName}/{guestIDCard}")
  public Guest addGuest(@PathVariable int id, @PathVariable String guestName,
    @PathVariable String guestIDCard)
  {
	Guest guest = new Guest();
	guest.setGuestID(id);
	guest.setGuestName(guestName);
	guest.setGuestIDCard(guestIDCard);
    guestService.saveGuest(guest);
    return guest;
  }
 
  @RequestMapping(value = "/delete/{id}")
  public void deleteGuest(@PathVariable int id)
  {
	  guestService.delete(id);
  }
 
  @RequestMapping(value = "/")
  public List<Guest> getGuests()
  {
    return guestService.findAll();
  }
 
  @RequestMapping(value = "/{id}")
  public Guest getGuest(@PathVariable int id)
  {
	Guest guest = guestService.findOne(id);
    return guest;
  }
  
  @RequestMapping(value = "/search/specification/{name}")
  public List<Guest> getGuestsByName(@PathVariable String name)
  {
    List<Guest> guests = guestService.findAll();
    return guests;
  }
 
  
}
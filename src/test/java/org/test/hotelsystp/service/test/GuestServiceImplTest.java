package org.test.hotelsystp.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.hotelsystp.entity.Guest;
import org.test.hotelsystp.service.GuestServiceImpl;
import org.test.hotelsystp.service.IGuestService;
import org.test.hotelsystp.util.IDGenerator;

@SpringBootTest
public class GuestServiceImplTest {
	
	@Autowired
	IGuestService guestService;
	@Test
	public void testSaveGuest() {
		IDGenerator idGenerator = IDGenerator.getInstance();
		Integer id = idGenerator.getIdNumber();
		System.out.println(id);
		
		Guest guest = new Guest();
		guest.setGuestID(id);
		guest.setGuestName("GuestName" + id);
		guest.setGuestIDCard("GuestIDCard" + id);
		guestService.saveGuest(guest);
		
		System.out.println(guestService.findOne(id));
		
		assertNotNull(guestService.findOne(id));
	}
	
	@Test
	public void testFindAll() {
		IDGenerator idGenerator = IDGenerator.getInstance();
		Integer id = idGenerator.getIdNumber();
		System.out.println(id);
		
		Guest guest = new Guest();
		guest.setGuestID(id);
		guest.setGuestName("GuestName" + id);
		guest.setGuestIDCard("GuestIDCard" + id);
		guestService.saveGuest(guest);
		
		id = idGenerator.getIdNumber();
		guest = new Guest();
		guest.setGuestID(id);
		guest.setGuestName("GuestName" + id);
		guest.setGuestIDCard("GuestIDCard" + id);
		guestService.saveGuest(guest);
		
		id = idGenerator.getIdNumber();
		guest = new Guest();
		guest.setGuestID(id);
		guest.setGuestName("GuestName" + id);
		guest.setGuestIDCard("GuestIDCard" + id);
		guestService.saveGuest(guest);
		
		System.out.println(guestService.findAll());
		
		assertEquals(4-1, guestService.findAll().size());
	}
	
}
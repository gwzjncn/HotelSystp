package org.test.hotelsystp.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.jupiter.api.Test;
import org.test.hotelsystp.entity.DataTable;
import org.test.hotelsystp.entity.Guest;
import org.test.hotelsystp.exception.HotelSystpException;
import org.test.hotelsystp.repository.DataManager;
import org.test.hotelsystp.repository.TableEnum;

public class DataManagerTest {
	
	private final DataManager dataManager = DataManager.getInstance();
	
	@Test
    public void testAddTable() {
		ConcurrentMap<Integer, DataTable> testTable = new ConcurrentHashMap<Integer, DataTable>();
		Guest guest = new Guest();
		guest.setGuestID(1010);
		guest.setGuestName("1010");
		guest.setGuestIDCard("1010");
		testTable.put(1010, guest);
		
		try {
			dataManager.addTable(TableEnum.GUEST.getName(), testTable);
			testTable = dataManager.getTable(TableEnum.GUEST.getName());
		} catch (HotelSystpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(testTable);
		assertTrue(testTable.entrySet().size() > 0);
    }
	
	@Test
    public void testAddTableThroughWrongName() {
		ConcurrentMap<Integer, DataTable> testTable = new ConcurrentHashMap<Integer, DataTable>();
		Guest guest = new Guest();
		guest.setGuestID(1010);
		guest.setGuestName("1010");
		guest.setGuestIDCard("1010");
		testTable.put(1010, guest);
		
		assertThrows(HotelSystpException.class, () -> dataManager.addTable("TestWrongTableNameParameter", testTable));
    }
	
	
}
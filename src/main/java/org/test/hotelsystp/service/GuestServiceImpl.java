package org.test.hotelsystp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.test.hotelsystp.entity.DataTable;
import org.test.hotelsystp.entity.Guest;
import org.test.hotelsystp.exception.HotelSystpException;
import org.test.hotelsystp.repository.DataManager;
import org.test.hotelsystp.repository.TableEnum;

@Service
public class GuestServiceImpl implements IGuestService {
	
	private static DataManager dataManager = null;
	private ConcurrentMap<Integer, DataTable> guestTable = null;
	
	public GuestServiceImpl() {
		dataManager = DataManager.getInstance();
		
		if(dataManager.getTable(TableEnum.GUEST.getName()) == null){
			ConcurrentMap<Integer, DataTable> guestTable = new ConcurrentHashMap<Integer, DataTable>();
			try {
				dataManager.addTable(TableEnum.GUEST.getName(), guestTable);
			} catch (HotelSystpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Guest> findAll() {
		guestTable = dataManager.getTable(TableEnum.GUEST.getName());
		List<DataTable> dataList = guestTable.values().stream().toList();
		List<Guest> guestList = new ArrayList();
		for(DataTable guestData: dataList) {
			guestList.add((Guest)guestData);
		}
		
		return guestList;
	}
	
	public void saveGuest(Guest guest) {
		guestTable = dataManager.getTable(TableEnum.GUEST.getName());
		guestTable.put(guest.getGuestID(), guest);
	}

	public Guest findOne(int id) {
		guestTable = dataManager.getTable(TableEnum.GUEST.getName());
		return (Guest)guestTable.get(id);
	}
	
	public Guest findOneByName(String guestName) {
		guestTable = dataManager.getTable(TableEnum.GUEST.getName());
		Map<Object, Object> guestTableTemp = guestTable.entrySet().stream().filter(entry -> ((Guest)entry.getValue()).getGuestName().equals(guestName)).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(guestTableTemp != null && guestTableTemp.size() > 0)
			return (Guest)guestTableTemp.values().stream().toList().get(0);
		
		return null;
	}
	
	public void delete(int id) {
		guestTable = dataManager.getTable(TableEnum.GUEST.getName());
		guestTable.remove(id);
	}
}
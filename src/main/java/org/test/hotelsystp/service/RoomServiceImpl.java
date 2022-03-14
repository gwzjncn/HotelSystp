package org.test.hotelsystp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.test.hotelsystp.entity.DataTable;
import org.test.hotelsystp.entity.Guest;
import org.test.hotelsystp.entity.Reservation;
import org.test.hotelsystp.entity.Room;
import org.test.hotelsystp.exception.HotelSystpException;
import org.test.hotelsystp.repository.DataManager;
import org.test.hotelsystp.repository.TableEnum;

@Service
public class RoomServiceImpl implements IRoomService {
	
	private static DataManager dataManager = null;
	private ConcurrentMap<Integer, DataTable> roomTable = null;
	
	public RoomServiceImpl() {
		dataManager = DataManager.getInstance();
		
		if(dataManager.getTable(TableEnum.ROOM.getName()) == null){
			ConcurrentMap<Integer, DataTable> roomTable = new ConcurrentHashMap<Integer, DataTable>();
			try {
				dataManager.addTable(TableEnum.ROOM.getName(), roomTable);
			} catch (HotelSystpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Room> findAll() {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		List<DataTable> dataList = roomTable.values().stream().toList();
		List<Room> roomList = new ArrayList();
		for(DataTable roomData: dataList) {
			roomList.add((Room)roomData);
		}
		
		return roomList;
	}
	
	public void saveRoom(Room room) {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		roomTable.put(room.getRoomID(), room);
	}

	public Room findOne(int id) {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		return (Room)roomTable.get(id);
	}
	
	public Room findOneByRoomNumber(String roomNumber) {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		Map<Object, Object> roomTableTemp = roomTable.entrySet().stream().filter(entry -> ((Room)entry.getValue()).getRoomNumber().equals(roomNumber)).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(roomTableTemp != null)
			return (Room)roomTableTemp.values().stream().toList().get(0);
		
		return null;
	}
	
	public List<Room> findAvailableRooms(Date reservationDate) {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		ConcurrentMap<Integer, DataTable> reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		Map<Object, Object> reservationTableTmp =  reservationTable.entrySet().stream().filter(entry -> ((Reservation)entry.getValue()).getReservationDate().equals(reservationDate)).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(reservationTableTmp == null || reservationTableTmp.size() <= 0) {
			List<DataTable> dataList = roomTable.values().stream().toList();
			List<Room> roomList = new ArrayList();
			for(DataTable roomData: dataList) {
				roomList.add((Room)roomData);
			}
			
			return roomList;
		}
		
		Map<Object, Object> roomTableTemp = roomTable.entrySet().stream().filter(
				entry -> !(reservationTableTmp.values().stream().anyMatch(
						 reservationEntry -> ((Reservation)reservationEntry).getRoomNumber().equals(
								 ((Room)entry.getValue()).getRoomNumber())))).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(roomTableTemp != null && roomTableTemp.size() > 0) {
			List<Object> dataList = roomTableTemp.values().stream().toList();
			List<Room> roomList = new ArrayList();
			for(Object roomData: dataList) {
				roomList.add((Room)roomData);
			}
			
			return roomList;
		}
		
		List<DataTable> dataList = roomTable.values().stream().toList();
		List<Room> roomList = new ArrayList();
		for(DataTable roomData: dataList) {
			roomList.add((Room)roomData);
		}
		
		return roomList;
	}
	
	public void delete(int id) {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		roomTable.remove(id);
	}
	
	public int deployToRoomValue(int roomCount) {
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		roomTable.clear();
		for(int i=0; i<roomCount; i++) {
			Room room = new Room();
			room.setRoomID(i+1);
			room.setRoomNumber("RoomNumber" + (i+1));
			room.setIsAvailable(0);
			roomTable.put(i+1, room);
		}
		
		return roomCount;
	}

	public boolean deployToSystemValue() {
		ConcurrentMap<Integer, DataTable> guestTable = dataManager.getTable(TableEnum.GUEST.getName());
		ConcurrentMap<Integer, DataTable> reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		
		guestTable.clear();
		reservationTable.clear();
		roomTable.clear();
				
		return true;
	}
	
}
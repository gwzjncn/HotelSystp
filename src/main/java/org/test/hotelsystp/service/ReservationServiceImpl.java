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
import org.test.hotelsystp.util.IDGenerator;

@Service
public class ReservationServiceImpl implements IReservationService {
	
	private static DataManager dataManager = null;
	private ConcurrentMap<Integer, DataTable> reservationTable = null;
	
	public ReservationServiceImpl() {
		dataManager = DataManager.getInstance();
		
		if(dataManager.getTable(TableEnum.RESERVATION.getName()) == null){
			ConcurrentMap<Integer, DataTable> reservationTable = new ConcurrentHashMap<Integer, DataTable>();
			try {
				dataManager.addTable(TableEnum.RESERVATION.getName(), reservationTable);
			} catch (HotelSystpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Reservation> findAll() {
		reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		List<DataTable> dataList = reservationTable.values().stream().toList();
		List<Reservation> reservationList = new ArrayList();
		for(DataTable reservationData: dataList) {
			reservationList.add((Reservation)reservationData);
		}
		
		return reservationList;
	}
	
	public int saveReservation(Reservation reservation) {
		reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		Reservation reservationResult = findReservationDistinct(reservation.getGuestName(), reservation.getReservationDate(), reservation.getRoomNumber());
		if(reservationResult != null)
			return 0;
		
		ConcurrentMap<Integer, DataTable> roomTable = dataManager.getTable(TableEnum.ROOM.getName());
		Map<Object, Object> roomTableTmp = roomTable.entrySet().stream().filter(
				entry -> ((Room)entry.getValue()).getRoomNumber().equals(reservation.getRoomNumber())).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(roomTableTmp == null || roomTableTmp.size() <= 0)
			return 0;
		
		IDGenerator idGenerator = IDGenerator.getInstance();
		int reservationID = idGenerator.getIdNumber();	
		reservation.setReservationID(reservationID);
		reservationTable.put(reservationID, reservation);
		
		return 1;
	}

	public Reservation findOne(int id) {
		reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		return (Reservation)reservationTable.get(id);
	}
	
	public List<Reservation> findReservationsByGuestName(String guestName) {
		reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		Map<Object, Object> reservationTableTemp = reservationTable.entrySet().stream().filter(entry -> ((Reservation)entry.getValue()).getGuestName().equals(guestName)).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(reservationTableTemp != null) {
			List<Reservation> reservationList = new ArrayList();
			for(Object reservationData: reservationTableTemp.values().stream().toList()) {
				reservationList.add((Reservation)reservationData);
			}
			
			return reservationList;
		}
		
		return null;
	}
	
	public Reservation findReservationDistinct(String guestName, Date reservationDate, String roomNumber) {
		reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		Map<Object, Object> reservationTableTemp = reservationTable.entrySet().stream().filter(
				entry -> ((Reservation)entry.getValue()).getGuestName().equals(guestName) && 
						 ((Reservation)entry.getValue()).getReservationDate().equals(reservationDate) &&
						 ((Reservation)entry.getValue()).getRoomNumber().equals(roomNumber)).
				collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		if(reservationTableTemp != null && reservationTableTemp.size() > 0)
			return (Reservation)reservationTableTemp.values().stream().toList().get(0);
		
		return null;
	}
	
	public void delete(int id) {
		reservationTable = dataManager.getTable(TableEnum.RESERVATION.getName());
		reservationTable.remove(id);
	}
}
package org.test.hotelsystp.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.test.hotelsystp.entity.DataTable;
import org.test.hotelsystp.exception.HotelSystpException;

public class DataManager {

	private static DataManager dataManager = new DataManager();
	private static ConcurrentMap concurrentMap = new ConcurrentHashMap<String, ConcurrentMap<Integer, DataTable>>();
	
	private DataManager() {
		
	}
	
	public static DataManager getInstance() {
		return dataManager;
	}
	
	public void addTable(String tableName, ConcurrentMap<Integer, DataTable> tableInstance) throws HotelSystpException {
		boolean isFound = false;
		for(TableEnum tableEnum : TableEnum.values()) {
			if(tableEnum.getName().equals(tableName)) {
				isFound = true;
				break;
			}
		}
		
		if(!isFound) {
			throw new HotelSystpException("The data table is not valid in the system!", null);
		}
		
		if(concurrentMap.get(tableName) != null)
			return;
		
		concurrentMap.put(tableName, tableInstance);
	}
	
	public ConcurrentMap<Integer, DataTable> getTable(String tableName) {
		return (ConcurrentMap<Integer, DataTable>) concurrentMap.get(tableName);
	}
}





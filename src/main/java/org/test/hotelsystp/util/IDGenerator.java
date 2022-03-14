package org.test.hotelsystp.util;

public class IDGenerator {
	private static IDGenerator idGenerator = new IDGenerator(); 
	private static Integer idNumberGeneratorCounter = 0;
	
	private IDGenerator() {
		
	}
	
	public static IDGenerator getInstance() {
		return idGenerator;
	}
	
	public static Integer getIdNumber() {
		return ++idNumberGeneratorCounter;
	}
}
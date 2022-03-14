package org.test.hotelsystp.exception;

public class HotelSystpException extends Throwable{
	private Throwable exception = null;
	private String exceptionMessage = "";
	
	public HotelSystpException() {
		
	}
	
	public HotelSystpException(String exceptionMessage, Throwable exception) {
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
	}
}

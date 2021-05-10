package com.appegate.task.operarions.enums;

/**
 * 
 * @author daniel.sarmiento
 *
 */
public enum EnumMessage {

	NO_DATA_MESSAGE_ERROR("NO DATA"), 
	DATA_NO_DEVIDE_BUT_ZERO("DATA CONTAINS A CERO ELEMENT");

	private final String message;

	EnumMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}

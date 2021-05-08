package com.appegate.task.operarions.enums;

/**
 * 
 * @author daniel.sarmiento
 *
 */
public enum EnumMessage {

	NO_DATA_MESSAGE_ERROR("NO DATA");

	private final String message;

	EnumMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}

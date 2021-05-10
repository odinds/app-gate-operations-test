package com.appegate.task.operarions.useCase.session;

/**
 * 
 * @author daniel.sarmiento
 *
 */
public interface SessionService {

	/**
	 * @author daniel.sarmiento
	 * @see start session to operations's proccess
	 * @return
	 */
	String startSession();
	
	/**
	 * 
	 */
	void scheduleEndSession();
}

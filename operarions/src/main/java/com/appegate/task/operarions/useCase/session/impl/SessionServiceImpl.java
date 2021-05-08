package com.appegate.task.operarions.useCase.session.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.appegate.task.operarions.useCase.session.SessionService;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@Service
public class SessionServiceImpl implements SessionService {

	/**
	 * 
	 */
	public String startSession() {
		String idSession = UUID.randomUUID().toString();
		return idSession;
	}

}

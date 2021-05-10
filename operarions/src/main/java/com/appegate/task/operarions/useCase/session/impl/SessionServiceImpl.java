package com.appegate.task.operarions.useCase.session.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.appegate.task.operarions.domain.OperandData;
import com.appegate.task.operarions.infraestructure.repository.OperandDataRepository;
import com.appegate.task.operarions.useCase.session.SessionService;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private OperandDataRepository operandDataRepository;
	
	/**
	 * 
	 */
	public String startSession() {
		String idSession = UUID.randomUUID().toString();
		createSession(
				idSession);
		return idSession;
		
	}
	
	@Async("threadPoolTaskExecutor")
	private void createSession(String idSession) {
		OperandData operandData = new OperandData();
		operandData.setIdSession(idSession);
		operandData.setOperands(new LinkedList<>());
		operandDataRepository.save(operandData);
	}

}

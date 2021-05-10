package com.appegate.task.operarions.useCase.session.impl;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
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
	Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);
	
	
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
		operandData.setCreateDate(LocalDateTime.now());
		operandDataRepository.save(operandData);
	}

	/**
	 * @author daniel.sarmiento
	 * @see Review all data weather delete session
	 */
    @Scheduled(fixedDelay = 1000*60, initialDelay = 1000)
    public void scheduleEndSession() {
        operandDataRepository.findAll().forEach(s ->{
        	
        	
        	if(Objects.nonNull(s.getUpdateDate())) {
        		if(s.getUpdateDate().isBefore(LocalDateTime.now().minusMinutes(5L))) {
        			operandDataRepository.deleteById(s.getIdSession());
        			logger.info("Session ended:"+s.getIdSession());
        		}
        	}else {
        		if(s.getCreateDate().isBefore(LocalDateTime.now().minusMinutes(5L))) {
        			operandDataRepository.deleteById(s.getIdSession());
        			logger.info("Session ended:"+s.getIdSession());
        		}        		
        	}
        });
    }
}

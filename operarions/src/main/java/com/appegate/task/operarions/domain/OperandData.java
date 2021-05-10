package com.appegate.task.operarions.domain;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@RedisHash("OperandData")
public class OperandData {

	@Id
	private String IdSession;
	private LinkedList<BigDecimal> operands;
	
	public String getIdSession() {
		return IdSession;
	}
	public void setIdSession(String idSession) {
		IdSession = idSession;
	}
	public LinkedList<BigDecimal> getOperands() {
		return operands;
	}
	public void setOperands(LinkedList<BigDecimal> operands) {
		this.operands = operands;
	}
	
	
}

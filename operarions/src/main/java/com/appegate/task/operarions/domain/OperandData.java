package com.appegate.task.operarions.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
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
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
}

package com.appegate.task.operarions.infraestructure.dto;

import java.math.BigDecimal;

/**
 * 
 * @author daniel.sarmiento
 *
 */
public class OperandDto {

	/**
	 * 
	 */
	BigDecimal operand;

	public BigDecimal getOperand() {
		return operand;
	}

	public void setOperand(BigDecimal operand) {
		this.operand = operand;
	}

	@Override
	public String toString() {
		return "OperandDto [operand=" + operand + "]";
	}
	
}

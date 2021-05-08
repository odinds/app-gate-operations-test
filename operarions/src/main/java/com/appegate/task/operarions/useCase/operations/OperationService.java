package com.appegate.task.operarions.useCase.operations;

import java.math.BigDecimal;

import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.infraestructure.dto.OperandDto;

/**
 * 
 * @author daniel.sarmiento
 *
 */
public interface OperationService {

	/**
	 * 
	 * @param operand
	 * @param idSession
	 * @throws OperationsAppGateException
	 */
	void addOperand(OperandDto operand, String idSession) throws OperationsAppGateException;

	/**
	 * 
	 * @param idSession
	 * @return
	 * @throws OperationsAppGateException
	 */
	BigDecimal sum(String idSession) throws OperationsAppGateException;

}

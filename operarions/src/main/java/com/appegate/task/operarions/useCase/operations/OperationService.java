package com.appegate.task.operarions.useCase.operations;

import java.math.BigDecimal;

import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.exceptions.SessionAppGateException;
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
	 * @throws SessionAppGateException
	 *  
	 */
	void addOperand(OperandDto operand, String idSession) throws OperationsAppGateException, SessionAppGateException;

	/**
	 * 
	 * @param idSession
	 * @return
	 * @throws OperationsAppGateException
	 * @throws SessionAppGateException
	 */
	BigDecimal sum(String idSession) throws OperationsAppGateException, SessionAppGateException;;

	/**
	 * 
	 * @param idSession
	 * @return
	 * @throws OperationsAppGateException
	 * @throws SessionAppGateException
	 */
	BigDecimal multiply(String idSession) throws OperationsAppGateException, SessionAppGateException;;

	/**
	 * 
	 * @param idSession
	 * @return
	 * @throws OperationsAppGateException
	 * @throws SessionAppGateException
	 */
	BigDecimal substract(String idSession) throws OperationsAppGateException, SessionAppGateException;;

	/**
	 * 
	 * @param idSession
	 * @return
	 * @throws OperationsAppGateException
	 * @throws SessionAppGateException
	 */
	BigDecimal devide(String idSession) throws OperationsAppGateException, SessionAppGateException;;

}

package com.appegate.task.operarions.useCase.operations.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appegate.task.operarions.domain.OperandData;
import com.appegate.task.operarions.enums.EnumMessage;
import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.infraestructure.dto.OperandDto;
import com.appegate.task.operarions.infraestructure.repository.OperandDataRepository;
import com.appegate.task.operarions.useCase.operations.OperationService;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperandDataRepository operandDataRepository;

	/**
	 * @see Add a operand to session @idSession
	 * @param operand
	 * @param idSession
	 */
	@Override
	public void addOperand(OperandDto operand, String idSession) throws OperationsAppGateException {
		
		if(validate(operand)){
			Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
			LinkedList<BigDecimal> operandList = null;
			OperandData operandData = null;
			if(operandDataOpt.isEmpty()) {
				operandList = new LinkedList<>();
				operandData = new OperandData();
				operandData.setIdSession(idSession);
			}else {
				operandList = operandDataOpt.map(d -> d.getOperands()).get(); 
				operandData = operandDataOpt.get();
			}
			operandList.add(operand.getOperand());
			operandData.setOperands(operandList);
			
			operandDataRepository.save(operandData);
		}else {
			throw new OperationsAppGateException(EnumMessage.NO_DATA_MESSAGE_ERROR.getMessage());
		}
	}

	private boolean validate(OperandDto operand) {
		if(Objects.isNull(operand)) {
			return Boolean.FALSE;
		}
		if(Objects.isNull(operand.getOperand())) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
			
	}

	/**
	 * @see Sum operands thats contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal sum(String idSession) throws OperationsAppGateException {
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			return BigDecimal.ZERO;
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		BigDecimal sum = operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return sum;
	}

	/**
	 * @see Multiply operands thats contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal multiply(String idSession) throws OperationsAppGateException {
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			return BigDecimal.ZERO;
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		BigDecimal sum = operandList.stream().reduce(BigDecimal.ONE, BigDecimal::multiply);
		
		return sum;
	}

	/**
	 * @see Substracts operands thats contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal substract(String idSession) throws OperationsAppGateException {
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			return BigDecimal.ZERO;
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		if(operandList.isEmpty()) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal base = operandList.pollFirst();
		
		BigDecimal sum = operandList.stream().reduce(base, BigDecimal::subtract);
		
		return sum;
	}


}

package com.appegate.task.operarions.useCase.operations.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.appegate.task.operarions.domain.OperandData;
import com.appegate.task.operarions.enums.EnumMessage;
import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.exceptions.SessionAppGateException;
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
	public void addOperand(OperandDto operand, String idSession) throws OperationsAppGateException, SessionAppGateException{
		
		if(validate(operand)){
			Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
			LinkedList<BigDecimal> operandList = null;
			OperandData operandData = null;
			if(operandDataOpt.isEmpty()) {
				throw new SessionAppGateException(EnumMessage.SESSION_DONT_EXISTS.getMessage());
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

	/**
	 * @see Sum operands that contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal sum(String idSession) throws OperationsAppGateException, SessionAppGateException{
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			throw new SessionAppGateException(EnumMessage.SESSION_DONT_EXISTS.getMessage());
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		BigDecimal sum = operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return sum;
	}

	/**
	 * @see Multiply operands that contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal multiply(String idSession) throws OperationsAppGateException,SessionAppGateException {
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			throw new SessionAppGateException(EnumMessage.SESSION_DONT_EXISTS.getMessage());
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		BigDecimal result = operandList.stream().reduce(BigDecimal.ONE, BigDecimal::multiply);
		saveResult(operandDataOpt,result);
		
		return result;
	}

	/**
	 * @see Substracts operands that contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal substract(String idSession) throws OperationsAppGateException,SessionAppGateException {
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			throw new SessionAppGateException(EnumMessage.SESSION_DONT_EXISTS.getMessage());
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		if(operandList.isEmpty()) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal base = operandList.pollFirst();
		BigDecimal result = operandList.stream().reduce(base, BigDecimal::subtract);
		saveResult(operandDataOpt,result);
		
		return result;
	}

	/**
	 * @see devide operands that contians in a session
	 * @param idSession
	 */
	@Override
	public BigDecimal devide(String idSession) throws OperationsAppGateException,SessionAppGateException {
		Optional<OperandData> operandDataOpt = operandDataRepository.findById(idSession);
		
		if(operandDataOpt.isEmpty()) {
			throw new SessionAppGateException(EnumMessage.SESSION_DONT_EXISTS.getMessage());
		}
		
		LinkedList<BigDecimal> operandList = operandDataOpt.map(d -> d.getOperands()).get();
		if(operandList.isEmpty()) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal base = operandList.pollFirst();
		BigDecimal result = BigDecimal.ZERO;
		try {
			result = operandList.stream().reduce(base, BigDecimal::divide);
			saveResult(operandDataOpt,result);
		}catch (ArithmeticException  e) {
			throw new OperationsAppGateException(EnumMessage.DATA_NO_DEVIDE_BUT_ZERO.getMessage());
		}
		
		return result;
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

	@Async("threadPoolTaskExecutor")
	private void saveResult(Optional<OperandData> operandDataOpt, BigDecimal result) {
		operandDataOpt.ifPresent(s -> {
			LinkedList<BigDecimal> operands = new LinkedList<>();
			operands.add(result);
			s.setOperands(operands);
			operandDataRepository.save(s);
		});
	}


}

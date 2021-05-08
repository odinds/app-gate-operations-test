package com.appegate.task.operarions.useCase.operations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.appegate.task.operarions.domain.OperandData;
import com.appegate.task.operarions.enums.EnumMessage;
import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.infraestructure.dto.OperandDto;
import com.appegate.task.operarions.infraestructure.repository.OperandDataRepository;
import com.appegate.task.operarions.useCase.operations.impl.OperationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTests {


	@Mock
	private OperandDataRepository operandDataRepository;
	
	@InjectMocks
	private OperationServiceImpl operationService;
	
	/**
	 * 
	 * @throws OperationsAppGateException
	 */
	@Test
	public void getOperationNoData() throws OperationsAppGateException{
		String idSession= "1";
		OperandDto operandDto = new OperandDto();
		operandDto.setOperand(BigDecimal.ONE);
		
		Optional<OperandData> operandDataOpt = Optional.empty();
		when(operandDataRepository.findById(idSession)).thenReturn(operandDataOpt);

		operationService.addOperand(operandDto, idSession);
	}
	
	/**
	 * 
	 */
	@Test
	public void getOperationErrorOperandNull(){
		String idSession= "1";
		OperandDto operandDto = new OperandDto();
		
		try {
			operationService.addOperand(operandDto, idSession);
		} catch (OperationsAppGateException e) {
			assertThat(e.getMessage()).isEqualTo(EnumMessage.NO_DATA_MESSAGE_ERROR.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void getOperationErrorOperand(){
		String idSession= "1";
		OperandDto operandDto = new OperandDto();
		
		try {
			operationService.addOperand(operandDto, idSession);
		} catch (OperationsAppGateException e) {
			assertThat(e.getMessage()).isEqualTo(EnumMessage.NO_DATA_MESSAGE_ERROR.getMessage());
		}
	}

	/**
	 * 
	 * @throws OperationsAppGateException
	 */
	@Test
	public void getOperationData() throws OperationsAppGateException{
		String idSession= "1";
		OperandData data = createOperationData();
		OperandDto operandDto = new OperandDto();
		operandDto.setOperand(BigDecimal.ONE);
		
		Optional<OperandData> operandDataOpt = Optional.of(data);
		when(operandDataRepository.findById(idSession)).thenReturn(operandDataOpt);
		
		operationService.addOperand(operandDto, idSession);
	}
	
	/**
	 * @see Test sum operation ok
	 * @throws OperationsAppGateException
	 */
	@Test
	public void sumOperationsOk() throws OperationsAppGateException{
		String idSession= "1";
		OperandData data = createOperationData2();
		
		Optional<OperandData> operandDataOpt = Optional.of(data);
		when(operandDataRepository.findById(idSession)).thenReturn(operandDataOpt);
		
		BigDecimal response = operationService.sum(idSession);
		assertThat(response).isEqualTo(BigDecimal.valueOf(11L));
		
	}
	
	/**
	 * @see Test sum operation empty
	 * @throws OperationsAppGateException
	 */
	@Test
	public void sumOperationsEmpy() throws OperationsAppGateException{
		String idSession= "1";
		
		Optional<OperandData> operandDataOpt = Optional.empty();
		when(operandDataRepository.findById(idSession)).thenReturn(operandDataOpt);
		
		BigDecimal response = operationService.sum(idSession);
		assertThat(response).isEqualTo(BigDecimal.ZERO);
	}
	
	/**
	 * @see Test sum operation ok
	 * @throws OperationsAppGateException
	 */
	@Test
	public void multiplyOperationsOk() throws OperationsAppGateException{
		String idSession= "1";
		OperandData data = createOperationData2();
		
		Optional<OperandData> operandDataOpt = Optional.of(data);
		when(operandDataRepository.findById(idSession)).thenReturn(operandDataOpt);
		
		BigDecimal response = operationService.multiply(idSession);
		assertThat(response).isEqualTo(BigDecimal.TEN);
		
	}
	
	/**
	 * @see Test sum operation empty
	 * @throws OperationsAppGateException
	 */
	@Test
	public void multipyOperationsEmpy() throws OperationsAppGateException{
		String idSession= "1";
		
		Optional<OperandData> operandDataOpt = Optional.empty();
		when(operandDataRepository.findById(idSession)).thenReturn(operandDataOpt);
		
		BigDecimal response = operationService.multiply(idSession);
		assertThat(response).isEqualTo(BigDecimal.ZERO);
	}

	private OperandData createOperationData2() {
		OperandData data = new OperandData();
		LinkedList<BigDecimal> operands = new LinkedList<>();
		operands.add(BigDecimal.ONE);
		operands.add(BigDecimal.TEN);
		data.setOperands(operands);
		return data;
	}

	private OperandData createOperationData() {
		OperandData data = new OperandData();
		LinkedList<BigDecimal> operands = new LinkedList<>();
		operands.add(BigDecimal.ONE);
		data.setOperands(operands);
		return data;
	}
}

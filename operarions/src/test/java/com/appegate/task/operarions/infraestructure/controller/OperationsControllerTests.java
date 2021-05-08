package com.appegate.task.operarions.infraestructure.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.infraestructure.dto.OperandDto;
import com.appegate.task.operarions.useCase.operations.impl.OperationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperationsControllerTests {

	
    @Mock
    private OperationServiceImpl operationService;

    @InjectMocks
    private OperationsController operationsController;

    @BeforeEach
    void beforeTests() {
    }

    /**
     * 
     */
    @Test
    public void inOperandOk() {
    	OperandDto operand = new OperandDto();
		String idSession= "1";

		try {
			doNothing().when(operationService).addOperand(operand, idSession);
		} catch (OperationsAppGateException e) {
		}

		ResponseEntity<String> response = operationsController.inOperandValue(operand, idSession);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    /**
     * 
     */
    @Test
    public void inOperandValueInternatError() {
    	OperandDto operand = new OperandDto();
    	String idSession= "1";
    	
    	try {
    		doThrow(new OperationsAppGateException("Exception occured")).when(operationService).addOperand(operand, idSession);
    	} catch (OperationsAppGateException e) {
    	}
    	
    	ResponseEntity<String> response = operationsController.inOperandValue(operand, idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * 
     */
    @Test
    public void inOperandValueSessionError() {
    	OperandDto operand = new OperandDto();
    	String idSession= null;
    	
    	ResponseEntity<String> response = operationsController.inOperandValue(operand, idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
    
    /**
     * 
     */
    @Test
    public void sumOpertionOK() throws OperationsAppGateException {
		String idSession= "1";
		BigDecimal responseOperation = BigDecimal.TEN;

		when(operationService.sum(idSession) ).thenReturn(responseOperation);

		ResponseEntity<BigDecimal> response = operationsController.sumOperate(idSession);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    
    /**
     * 
     */
    @Test
    public void sumOpertionExeption() {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.ZERO;
    	
    	try {
    		doThrow(new OperationsAppGateException("Error")).when(operationService).sum(idSession);
		} catch (OperationsAppGateException e) {
		}
    	
    	ResponseEntity<BigDecimal> response = operationsController.sumOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
}

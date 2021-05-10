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

import com.appegate.task.operarions.enums.EnumMessage;
import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.exceptions.SessionAppGateException;
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
     * @see add Operand ok 
     */
    @Test
    public void inOperandOk() {
    	OperandDto operand = new OperandDto();
		String idSession= "1";

		try {
			doNothing().when(operationService).addOperand(operand, idSession);
		} catch (OperationsAppGateException e) {
		} catch (SessionAppGateException e) {

		}

		ResponseEntity<String> response = operationsController.inOperandValue(operand, idSession);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    /**
     * @see Service generate a error internal
     */
    @Test
    public void inOperandValueInternatError() {
    	OperandDto operand = new OperandDto();
    	String idSession= "1";
    	
    	try {
    		doThrow(new OperationsAppGateException("Exception occured")).when(operationService).addOperand(operand, idSession);
    	} catch (OperationsAppGateException e) {
    	} catch (SessionAppGateException e) {
		}
    	
    	ResponseEntity<String> response = operationsController.inOperandValue(operand, idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * @see Test idSession null error
     */
    @Test
    public void inOperandValueSessionError() {
    	OperandDto operand = new OperandDto();
    	String idSession= null;
    	
    	ResponseEntity<String> response = operationsController.inOperandValue(operand, idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
    
    /**
     * @throws SessionAppGateException 
     * @see Test sum operation ok
     */
    @Test
    public void sumOpertionOK() throws OperationsAppGateException, SessionAppGateException {
		String idSession= "1";
		BigDecimal responseOperation = BigDecimal.TEN;

		when(operationService.sum(idSession) ).thenReturn(responseOperation);

		ResponseEntity<BigDecimal> response = operationsController.sumOperate(idSession);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    
    /**
     * 
     * @see Test sum operation error data
     */
    @Test
    public void sumOpertionExeption() {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.ZERO;
    	
    	try {
    		doThrow(new OperationsAppGateException("Error")).when(operationService).sum(idSession);
		} catch (OperationsAppGateException e) {
		} catch (SessionAppGateException e) {
		}
    	
    	ResponseEntity<BigDecimal> response = operationsController.sumOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    
    /**
     * @throws SessionAppGateException 
     * @see Test multipy operation ok
     */
    @Test
    public void multiOpertionOK() throws OperationsAppGateException, SessionAppGateException {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.TEN;
    	
    	when(operationService.multiply(idSession) ).thenReturn(responseOperation);
    	
    	ResponseEntity<BigDecimal> response = operationsController.multiplyOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    
    /**
     * 
     * @see Test multipy operation error data
     */
    @Test
    public void multiOpertionExeption() {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.ZERO;
    	
    	try {
    		doThrow(new OperationsAppGateException("Error")).when(operationService).multiply(idSession);
    	} catch (OperationsAppGateException e) {
    	} catch (SessionAppGateException e) {
		}
    	
    	ResponseEntity<BigDecimal> response = operationsController.multiplyOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    

    /**
     * @throws SessionAppGateException 
     * @see Test substract operation ok
     */
    @Test
    public void substractOpertionOK() throws OperationsAppGateException, SessionAppGateException {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.valueOf(-5L);
    	
    	when(operationService.substract(idSession) ).thenReturn(responseOperation);
    	
    	ResponseEntity<BigDecimal> response = operationsController.substractOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    
    /**
     * 
     * @see Test substract operation error data
     */
    @Test
    public void substractOpertionExeption() {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.ZERO;
    	
    	try {
    		doThrow(new OperationsAppGateException("Error")).when(operationService).substract(idSession);
    	} catch (OperationsAppGateException e) {
    	} catch (SessionAppGateException e) {
		}
    	
    	ResponseEntity<BigDecimal> response = operationsController.substractOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }    
    
    
    /**
     * @throws SessionAppGateException 
     * @see Test devide operation ok
     */
    @Test
    public void divideOpertionOK() throws OperationsAppGateException, SessionAppGateException {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.valueOf(-5L);
    	
    	when(operationService.devide(idSession) ).thenReturn(responseOperation);
    	
    	ResponseEntity<BigDecimal> response = operationsController.devideOperate(idSession);
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    	assertThat(response.getBody()).isEqualTo(responseOperation);
    }
    
    /**
     * 
     * @throws SessionAppGateException 
     * @see Test substract operation error data
     */
    @Test
    public void devideOpertionExeption() throws SessionAppGateException {
    	String idSession= "1";
    	BigDecimal responseOperation = BigDecimal.ZERO;
    	
    	try {
    		doThrow(new OperationsAppGateException(EnumMessage.DATA_NO_DEVIDE_BUT_ZERO.getMessage())).when(operationService).substract(idSession);
    		ResponseEntity<BigDecimal> response = operationsController.substractOperate(idSession);
    		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    		assertThat(response.getBody()).isEqualTo(responseOperation);
    	} catch (OperationsAppGateException e) {
    	}
    	
    }    
}

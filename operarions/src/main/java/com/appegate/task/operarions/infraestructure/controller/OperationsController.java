package com.appegate.task.operarions.infraestructure.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appegate.task.operarions.exceptions.OperationsAppGateException;
import com.appegate.task.operarions.infraestructure.dto.OperandDto;
import com.appegate.task.operarions.useCase.operations.OperationService;

/**
 * @see Controller that exposes resources to add operands and operate aperands in a sesssion
 * @author daniel.sarmiento
 *
 */
@RestController
@RequestMapping("operations")
public class OperationsController {

	@Autowired
	private OperationService operationService;

	/**
	 * 
	 * @param operand
	 * @param idSession
	 * @return
	 */
	@PostMapping("/addOperand")
	public ResponseEntity<String> inOperandValue(@RequestBody OperandDto operand,@RequestHeader("id-session") String idSession){
		
		if(Objects.isNull(idSession)){
			return new ResponseEntity<String>("No session", HttpStatus.FORBIDDEN);
		}
		
		try {
			operationService.addOperand(operand, idSession);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (OperationsAppGateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param idSession
	 * @return
	 */
	@GetMapping("/doSum")
	public ResponseEntity<BigDecimal> sumOperate(@RequestHeader("id-session") String idSession){
		
		try {
			BigDecimal response = operationService.sum(idSession);
			return new ResponseEntity<BigDecimal>(response, HttpStatus.OK);
		} catch (OperationsAppGateException e) {
			return new ResponseEntity<BigDecimal>(BigDecimal.ZERO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param idSession
	 * @return
	 */
	@GetMapping("/doMultiply")
	public ResponseEntity<BigDecimal> multiplyOperate(@RequestHeader("id-session") String idSession) {
		try {
			BigDecimal response = operationService.multiply(idSession);
			return new ResponseEntity<BigDecimal>(response, HttpStatus.OK);
		} catch (OperationsAppGateException e) {
			return new ResponseEntity<BigDecimal>(BigDecimal.ZERO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.appegate.task.operarions.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appegate.task.operarions.useCase.session.SessionService;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@RestController("/")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("startSession")
    public ResponseEntity<String> startSession() {
        return new ResponseEntity<String>(sessionService.startSession() , HttpStatus.ACCEPTED);
    }
}

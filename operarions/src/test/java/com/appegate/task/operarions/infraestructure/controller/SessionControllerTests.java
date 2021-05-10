package com.appegate.task.operarions.infraestructure.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.appegate.task.operarions.useCase.session.impl.SessionServiceImpl;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@ExtendWith(MockitoExtension.class)
public class SessionControllerTests {
    @Mock
    private SessionServiceImpl sessionService;

    @InjectMocks
    private SessionController sessionController;

    @BeforeEach
    void beforeTests() {
    }

    @Test
    public void shouldReturnDefaultMessage() {
		String idSession= "1";
		when(sessionService.startSession()).thenReturn(idSession);

		ResponseEntity<String> response = sessionController.startSession();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo(idSession);
    }
}

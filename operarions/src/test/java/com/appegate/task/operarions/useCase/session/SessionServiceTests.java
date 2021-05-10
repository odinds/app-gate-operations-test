package com.appegate.task.operarions.useCase.session;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.appegate.task.operarions.infraestructure.repository.OperandDataRepository;
import com.appegate.task.operarions.useCase.session.impl.SessionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTests {

	@Mock
	private OperandDataRepository operandDataRepository;
	
	@InjectMocks
	private SessionServiceImpl sessionService;
	
	@Test
	public void startSession() {
		
		String idSession = sessionService.startSession();
		assertThat(idSession).isNotEmpty();
	}
}

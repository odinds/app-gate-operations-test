package com.appegate.task.operarions.useCase.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.appegate.task.operarions.domain.OperandData;
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
	
	@Test
	public void endSessionWithOutUpdateDate() {
		LinkedList<OperandData> data = createDataWithoutUpdateDate();
		when(operandDataRepository.findAll()).thenReturn(data );
		
		sessionService.scheduleEndSession();
	}
	
	@Test
	public void endSessionWithUpdateDate() {
		LinkedList<OperandData> data = createDataWithUpdateDate();
		when(operandDataRepository.findAll()).thenReturn(data );
		
		sessionService.scheduleEndSession();
	}

	private LinkedList<OperandData> createDataWithoutUpdateDate() {
		LinkedList<OperandData> data = new LinkedList<>();
		
		OperandData d1 = new OperandData();
		d1.setCreateDate(LocalDateTime.now().minusMinutes(20L));
		
		data.add(d1);
		return data;
	}
	
	private LinkedList<OperandData> createDataWithUpdateDate() {
		LinkedList<OperandData> data = new LinkedList<>();
		

		OperandData d1 = new OperandData();
		d1.setCreateDate(LocalDateTime.now().minusMinutes(15));
		d1.setUpdateDate(LocalDateTime.now().minusMinutes(11));
		
		
		data.add(d1);
		return data;
	}
}

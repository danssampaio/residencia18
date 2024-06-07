package com.example.atividades.atividade14;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventHandlerTest {
	EmailService emailServiceMock;
	EventHandler eventHandler;
	
	

	@BeforeEach
	void setUp() {
		emailServiceMock = mock(EmailService.class);
		eventHandler = new EventHandler(emailServiceMock);

	}

	@Test
	void handle_EventSendsEmailWithCorrectParameters() {
		String event = "Some event occurred";

		eventHandler.handleEvent(event);

		verify(emailServiceMock).sendEmail("test@example.com", "Event Occurred", event);
	}
}

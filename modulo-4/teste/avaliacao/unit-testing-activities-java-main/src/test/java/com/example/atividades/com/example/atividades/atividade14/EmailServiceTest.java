package com.example.atividades.atividade14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.github.javafaker.Faker;

class EmailServiceTest {

    private EmailService emailService;
    private Faker faker;
    
    private String recipient;
    private String subject;
    private String body;

    @BeforeEach
    void setUp() {
    	emailService = mock(EmailService.class);
        faker = new Faker();
        
        recipient = faker.internet().emailAddress();
        subject = faker.internet().emailAddress();
        body = faker.lorem().toString();
    }

    @Test
    void sendEmailSendsEmailWithCorrectParameters() {
    	emailService.sendEmail(recipient, subject, body);

        ArgumentCaptor<String> recipientCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailService).sendEmail(recipientCaptor.capture(), subjectCaptor.capture(), bodyCaptor.capture());

        assertEquals(recipient, recipientCaptor.getValue());
        assertEquals(subject, subjectCaptor.getValue());
        assertEquals(body, bodyCaptor.getValue());
    }
}

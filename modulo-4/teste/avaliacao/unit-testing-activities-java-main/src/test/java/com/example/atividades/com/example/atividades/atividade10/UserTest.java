package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.atividades.atividade07.User;
import com.github.javafaker.Faker;

class UserTest {

	String name;
	String email;
	Faker faker;
	
	@BeforeEach
	private void setUp() {
		faker = new Faker();
		name = faker.name().fullName();
		email = faker.internet().emailAddress();
	}

    @Test
    void testUserCreation() {
        User user = new User(name, email);
        
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }

    @Test
    void testGetName() {
        User user = new User(name, email);
        
        assertEquals(name, user.getName());
    }

    @Test
    void testGetEmail() {
        User user = new User(name, email);
        
        assertEquals(email, user.getEmail());
    }

    @Test
    void testUserWithEmptyName() {
        String name = "";
        User user = new User(name, email);
        
        assertEquals(name, user.getName());
    }

    @Test
    void testUserWithEmptyEmail() {
        String email = "";
        User user = new User(name, email);
        
        assertEquals(email, user.getEmail());
    }

    @Test
    void testUserWithNullName() {
        User user = new User(null, email);
        
        assertNull(user.getName());
    }

    @Test
    void testUserWithNullEmail() {
        User user = new User(name, null);
        
        assertNull(user.getEmail());
    }

}

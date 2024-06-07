package com.example.atividades.atividade09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

class ItemTest {

	String name;
	Faker faker;
	
	@BeforeEach
	private void setUp() {
		faker = new Faker();
		name = faker.name().fullName();
	}

    @Test
    void testItemCreation() {
        Item item = new Item(name);
        
        assertNotNull(item);
        assertEquals(name, item.getName());
    }

    @Test
    void testGetName() {
        Item item = new Item(name);
        assertEquals(name, item.getName());
    }

    @Test
    void testItemWithEmptyName() {
        String name = "";
        Item item = new Item(name);
        
        assertEquals(name, item.getName());
    }

    @Test
    void testItemWithNullName() {
        Item item = new Item(null);
        
        assertNull(item.getName());
    }


}

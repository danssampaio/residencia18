package com.example.atividades.atividade13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FactorialTest {

	private Factorial factorial;

	@BeforeEach
	void setUp() {
		factorial = new Factorial();
	}

	@Test
	void calculate_FactorialForPositiveNumber() {
		int n = 5;

		int result = factorial.calculate(n);

		assertEquals(120, result);
	}

	@Test
	void calculate_FactorialForZero() {
		int n = 0;

		int result = factorial.calculate(n);

		assertEquals(1, result);
	}

	@Test
	void calculate_NegativeNumber_ReturnIllegalArgumentException() {
		Factorial factorial = new Factorial();
		int n = -5;

		assertThrows(IllegalArgumentException.class, () -> {
			factorial.calculate(n);
		});
	}
}

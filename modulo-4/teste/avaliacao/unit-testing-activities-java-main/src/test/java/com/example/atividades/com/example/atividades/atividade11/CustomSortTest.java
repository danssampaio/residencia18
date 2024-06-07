package com.example.atividades.atividade11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomSortTest {

	private CustomSort customSort;

	@BeforeEach
	void setUp() {
		customSort = new CustomSort();
	}

	@Test
	void customSort_SortsListInDescendingOrder() {
		List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9);

		List<Integer> sortedNumbers = customSort.customSort(numbers);

		assertEquals(Arrays.asList(9, 8, 5, 2, 1), sortedNumbers);
	}

	@Test
	void customSort_HandlesEmptyList() {
		List<Integer> numbers = Arrays.asList();

		List<Integer> sortedNumbers = customSort.customSort(numbers);

		assertTrue(sortedNumbers.isEmpty());
	}

	@Test
	void customSort_HandlesListWithOneElement() {
		List<Integer> numbers = Arrays.asList(5);

		List<Integer> sortedNumbers = customSort.customSort(numbers);

		assertEquals(Arrays.asList(5), sortedNumbers);
	}

	@Test
	void customSort_HandlesListWithRepeatedElements() {
		List<Integer> numbers = Arrays.asList(5, 5, 2, 2, 8, 1, 9, 9);

		List<Integer> sortedNumbers = customSort.customSort(numbers);

		assertEquals(Arrays.asList(9, 9, 8, 5, 5, 2, 2, 1), sortedNumbers);
	}
}

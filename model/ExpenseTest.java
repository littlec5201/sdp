package com.development.software.finance.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExpenseTest {

	private Expense expense;
	private String name = "Rent";
	private float cost = 150.00f;

	@Before
	public void setup(){
		this.expense = new Expense(name, cost);
	}

	@Test
	public void testNameIsNotNull(){
		assertNotNull(expense.getExpenseName());
	}

	@Test
	public void testNameIsNotEmptyString(){
		assertFalse(expense.getExpenseName().equals(" "));
	}

	@Test
	public void testNameMatchesInput(){
		assertEquals(expense.getExpenseName(),name);
	}

	@Test
	public void testCostIsNotNull(){
		assertNotNull(expense.getCost());
	}

	@Test
	public void testCostIsNotZero(){
		assertFalse(expense.getCost()==0);
	}

	@Test
	public void testCostMatchesInput(){
		assertEquals(cost,expense.getCost(),0);
	}

	@Test
	public void testSetName(){
		this.expense.setExpenseName("Internet");
		assertEquals("Internet", expense.getExpenseName());
	}

	@Test
	public void testSetCost(){
		this.expense.setCost(200.50f);
		assertEquals(200.50f,expense.getCost(), 0);
	}


}

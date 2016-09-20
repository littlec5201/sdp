package com.development.software.finance.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	private String fName = "Bruce";
	private String lName = "Wayne";
	private float savings = 10000000.50f;

	@Before
	public void setUp(){
		this.user = new User(fName,lName,savings);
	}

	@Test
	public void testFNameIsNotNull(){
		assertNotNull(user.getFirstName());
	}

	@Test
	public void testFNameIsNotEmptyString(){
		assertFalse(user.getFirstName().equals(" "));
	}

	@Test
	public void testFNameMatchesInput(){
		assertEquals(fName, user.getFirstName());
	}

	@Test
	public void testLNameIsNotNull(){
		assertNotNull(user.getLastName());
	}

	@Test
	public void testLNameIsNotEmptyString(){
		assertFalse(user.getLastName().equals(" "));
	}

	public void testLNameMatchesInput(){
		assertEquals(lName, user.getLastName());
	}

	@Test
	public void testGetName(){
		assertEquals(fName+" "+lName,user.getName());
	}

	@Test
	public void testSavingsIsNotNull(){
		assertNotNull(user.getSavings());
	}

	@Test
	public void testUpdateSavings(){
		user.updateSavings(3000f);
		assertEquals(3000f,user.getSavings(),0);
	}

	@Test
	public void testAddToSavings(){
		user.updateSavings(3000f);
		user.addToSavings(2000f);
		assertEquals(5000f,user.getSavings(),0);
	}

	@Test
	public void testRemoveFromSavings(){
		user.updateSavings(3000f);
		user.removeFromSavings(1000f);
		assertEquals(2000f,user.getSavings(),0);
	}

}

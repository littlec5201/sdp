package com.development.software.finance.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JobTest {

	private Job job;
	private String name = "My Job";
	private float income = 500.75f;

	@Before
	public void setUp(){
		this.job = new Job(name,income);
	}

	@Test
	public void testJobNameIsNotNull() {
		assertNotNull(job.getJobName());
	}

	@Test
	public void testJobNameIsNotEmptyString(){
		assertFalse(job.getJobName().equals(" "));
	}

	@Test
	public void testJobNameMatchesInput(){
		assertEquals(name,job.getJobName());
	}

	@Test
	public void testJobIncomeIsNotNull(){
		assertNotNull(job.getJobIncome());
	}

	@Test
	public void testJobIncomeIsNotZero(){
		assertFalse(job.getJobIncome()==0);
	}

	@Test
	public void testJobIncomeMatchesInput(){
		assertEquals(income,job.getJobIncome(),0);
	}

	@Test
	public void testSetJobName(){
		job.setJobName("Free Money");
		assertEquals("Free Money",job.getJobName());
	}

	@Test
	public void testSetJobIncome(){
		job.setJobIncome(200.54f);
		assertEquals(200.54f,job.getJobIncome(),0);
	}

}

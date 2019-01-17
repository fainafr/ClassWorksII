package com.pwh.pg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Before
	public void noteBefore() {
		System.out.println("Before works");
	}
	
	
	@After
	public void noteAfterEach() {
		System.out.println("After works");
	}
	
	@Test
	public void contextLoads() {
	}

}


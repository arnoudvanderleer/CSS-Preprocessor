package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassTest {

	@Test
	public void testClass() {
		Class testClass = new Class("main");
		assertEquals("main", testClass.getName());
	}
	
	@Test
	public void testToString() {
		Class testClass = new Class("main");
		assertEquals(".main", testClass.toString());
	}

}

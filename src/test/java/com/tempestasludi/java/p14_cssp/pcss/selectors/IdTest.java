package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import org.junit.Test;

public class IdTest {

	@Test
	public void testId() {
		Id id = new Id("main");
		assertEquals("main", id.getName());
	}

	@Test
	public void testToString() {
		Id id = new Id("main");
		assertEquals("#main", id.toString());
	}

}

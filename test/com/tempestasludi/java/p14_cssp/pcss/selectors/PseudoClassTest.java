package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import org.junit.Test;

public class PseudoClassTest {

	@Test
	public void testPseudoClass() {
		PseudoClass pseudoClass = new PseudoClass("nth-child(3)");
		assertEquals("nth-child(3)", pseudoClass.getName());
	}

	@Test
	public void testToString() {
		PseudoClass pseudoClass = new PseudoClass("nth-child(3)");
		assertEquals(":nth-child(3)", pseudoClass.toString());
	}

}

package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttributeTest {

	@Test
	public void testAttribute() {
		Attribute attribute = new Attribute("data-tag=\"tag\"");
		assertEquals("data-tag=\"tag\"", attribute.getName());
	}
	
	@Test
	public void testToString() {
		Attribute attribute = new Attribute("data-tag=\"tag\"");
		assertEquals("[data-tag=\"tag\"]", attribute.toString());
	}

}

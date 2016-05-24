package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import org.junit.Test;

public class TagTest {

	@Test
	public void testTag() {
		Tag tag = new Tag("div");
		assertEquals("div", tag.getName());
	}

	@Test
	public void testToString() {
		Tag tag = new Tag("div");
		assertEquals("div", tag.toString());
	}

}

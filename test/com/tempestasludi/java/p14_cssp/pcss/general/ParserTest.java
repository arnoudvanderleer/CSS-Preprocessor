package com.tempestasludi.java.p14_cssp.pcss.general;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {
	
	@Test
	public void testSearchBracket1() {
		String bracketString = "[{asdf: bsdf}, {dd: 5}]";
		assertEquals(22, Parser.searchBracket(0, bracketString));
		assertEquals(12, Parser.searchBracket(1, bracketString));
		assertEquals(21, Parser.searchBracket(15, bracketString));
	}
	
	@Test
	public void testSearchBracket2() {
		String bracketString = "[((asdf\": bsdf),\" (dd): 5)]";
		assertEquals(26, Parser.searchBracket(0, bracketString));
		assertEquals(26, Parser.searchBracket(1, bracketString));
		assertEquals(25, Parser.searchBracket(2, bracketString));
	}

	@Test
	public void testSearchStringEnd() {
		String stringString = "asdf\"asfddds\'\"asdf";
		assertEquals(13, Parser.searchStringEnd(4, stringString));
	}

}

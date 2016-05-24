package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SelectorTest {

	@Test
	public void testReadAttribute() {
		String selectorString = "[a=\"ss\"]";
		Selector compareAttribute = new Attribute("a=\"ss\"");
		Selector selector = Selector.read(selectorString);
		assertEquals(compareAttribute, selector);
	}

	@Test
	public void testReadClass() {
		String selectorString = ".asdf-jkl";
		Selector compareAttribute = new Class("asdf-jkl");
		Selector selector = Selector.read(selectorString);
		assertEquals(compareAttribute, selector);
	}

	@Test
	public void testReadId() {
		String selectorString = "#asdf-jkl";
		Selector compareAttribute = new Id("asdf-jkl");
		Selector selector = Selector.read(selectorString);
		assertEquals(compareAttribute, selector);
	}

	@Test
	public void testReadPseudoClass() {
		String selectorString = ":asdf-jkl";
		Selector compareAttribute = new PseudoClass("asdf-jkl");
		Selector selector = Selector.read(selectorString);
		assertEquals(compareAttribute, selector);
	}

	@Test
	public void testReadTag() {
		String selectorString = "asdf";
		Selector compareAttribute = new Tag("asdf");
		Selector selector = Selector.read(selectorString);
		assertEquals(compareAttribute, selector);
	}
	
	@Test
	public void testReadRelations() {
		String selectorString = "ab:cd.ef #gh[ij] > :kl + .mn ~ #op";
		ArrayList<Selector> selectors = new ArrayList<Selector>();
		selectors.add(new Tag("ab"));
		selectors.add(new PseudoClass("cd"));
		selectors.add(new Class("ef"));
		selectors.add(new Id("gh"));
		selectors.add(new Attribute("ij"));
		selectors.add(new PseudoClass("kl"));
		selectors.add(new Class("mn"));
		selectors.add(new Id("op"));
		ArrayList<String> relations = new ArrayList<String>();
		relations.add("");
		relations.add("");
		relations.add(" ");
		relations.add("");
		relations.add(">");
		relations.add("+");
		relations.add("~");
		Selector compareAttribute = new Compound(selectors, relations);
		Selector selector = Selector.read(selectorString);
		assertEquals(compareAttribute, selector);
	}

}

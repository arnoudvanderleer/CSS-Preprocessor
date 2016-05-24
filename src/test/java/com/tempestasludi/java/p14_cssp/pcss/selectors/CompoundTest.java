package com.tempestasludi.java.p14_cssp.pcss.selectors;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CompoundTest {

	private Compound generate() {
		return new Compound(this.generateSelectors(), this.generateRelations());
	}

	private ArrayList<Selector> generateSelectors() {
		ArrayList<Selector> selectors = new ArrayList<Selector>();
		selectors.add(new Tag("div"));
		selectors.add(new Id("main"));
		selectors.add(new Tag("p"));
		selectors.add(new Attribute("class"));
		selectors.add(new Class("child-1"));
		selectors.add(new PseudoClass("nth-child(5)"));
		selectors.add(new Id("child-2"));
		selectors.add(new PseudoClass("hover"));
		return selectors;
	}

	private ArrayList<String> generateRelations() {
		ArrayList<String> relations = new ArrayList<String>();
		relations.add("");
		relations.add(" ");
		relations.add("");
		relations.add(">");
		relations.add("+");
		relations.add("~");
		relations.add("");
		return relations;
	}

	@Test
	public void testCompoundSelectors() {
		Compound compound = this.generate();
		ArrayList<Selector> selectors = this.generateSelectors();
		assertEquals(selectors, compound.getSelectors());
	}
	
	@Test
	public void testCompoundRelations() {
		Compound compound = this.generate();
		ArrayList<String> relations = this.generateRelations();
		assertEquals(relations, compound.getRelations());
	}

	@Test
	public void testToString() {
		Compound compound = this.generate();
		assertEquals("div#main p[class] > .child-1 + :nth-child(5) ~ #child-2:hover", compound.toString());
	}

	@Test
	public void testEqualsSelf() {
		Compound compound = this.generate();
		assertTrue(compound.equals(compound));
	}

	@Test
	public void testEqualsCopy() {
		Compound compound = this.generate();
		Compound copy = this.generate();
		assertTrue(compound.equals(copy));
	}

	@Test
	public void testEqualsNull() {
		Compound compound = this.generate();
		assertFalse(compound.equals(null));
	}

	@Test
	public void testEqualsAddition() {
		Compound compound = this.generate();
		ArrayList<Selector> selectors = compound.getSelectors();
		ArrayList<String> relations = compound.getRelations();
		selectors.add(new Tag("h1"));
		relations.add(" ");
		Compound addition = new Compound(selectors, relations);
		assertFalse(compound.equals(addition));
	}

	@Test
	public void testEqualsRemoval() {
		Compound compound = this.generate();
		ArrayList<Selector> selectors = compound.getSelectors();
		ArrayList<String> relations = compound.getRelations();
		selectors.remove(selectors.size() - 1);
		relations.remove(relations.size() - 1);
		Compound removal = new Compound(selectors, relations);
		assertFalse(compound.equals(removal));
	}

	@Test
	public void testEqualsReplacement() {
		Compound compound = this.generate();
		ArrayList<Selector> selectors = compound.getSelectors();
		ArrayList<String> relations = compound.getRelations();
		selectors.remove(selectors.size() - 1);
		relations.remove(relations.size() - 1);
		selectors.add(new Tag("h1"));
		relations.add(" ");
		Compound repplacement = new Compound(selectors, relations);
		assertFalse(compound.equals(repplacement));
	}

	@Test
	public void testGetSetSelectors() {
		Compound compound = this.generate();
		ArrayList<Selector> newSelectors = new ArrayList<Selector>();
		newSelectors.add(new Tag("p"));
		newSelectors.add(new Attribute("data-tag=\"tag\""));
		compound.setSelectors(newSelectors);
		assertEquals(newSelectors, compound.getSelectors());
	}

	@Test
	public void testGetSetRelations() {
		Compound compound = this.generate();
		ArrayList<String> newRelations = new ArrayList<String>();
		newRelations.add("");
		newRelations.add(" ");
		compound.setRelations(newRelations);
		assertEquals(newRelations, compound.getRelations());
	}

}

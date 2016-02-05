package com.tempestasludi.java.p14_cssp.pcss.general;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.tempestasludi.java.p14_cssp.pcss.properties.Property;
import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Compound;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Id;
import com.tempestasludi.java.p14_cssp.pcss.selectors.PseudoClass;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Selector;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Tag;

public class BlockTest {

	public static Block generate() {
		return new Block(generateSelectors(), generateUnits());
	}

	private static ArrayList<Selector> generateSelectors() {
		ArrayList<Selector> result = new ArrayList<Selector>();
		ArrayList<Selector> selectors = new ArrayList<Selector>();
		ArrayList<String> relations = new ArrayList<String>();
		selectors.add(new Tag("div"));
		relations.add(">");
		selectors.add(new com.tempestasludi.java.p14_cssp.pcss.selectors.Class("asdf-thing"));
		relations.add(" ");
		selectors.add(new PseudoClass("nth-child(4)"));
		result.add(new Compound(selectors, relations));
		result.add(new Tag("p"));
		return result;
	}

	private static ArrayList<Unit> generateUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		units.add(new Variable("bc", "#ff0000"));
		units.add(new Property("border-{top|left}", "1px solid $bc"));
		ArrayList<Selector> blockSelectors = new ArrayList<Selector>();
		blockSelectors.add(new com.tempestasludi.java.p14_cssp.pcss.selectors.Class("sub"));
		ArrayList<Unit> blockUnits = new ArrayList<Unit>();
		blockUnits.add(new Property("color", "$bc"));
		units.add(new Block(blockSelectors, blockUnits));
		return units;
	}

	@Test
	public void testBlockSelectors() {
		Block block = generate();
		assertEquals(generateSelectors(), block.getSelectors());
	}

	@Test
	public void testBlockUnits() {
		Block block = generate();
		assertEquals(generateUnits(), block.getUnits());
	}

	@Test
	public void testGetSetSelectors() {
		Block block = generate();
		ArrayList<Selector> selectors = new ArrayList<Selector>();
		selectors.add(new PseudoClass("pc"));
		selectors.add(new Id("id"));
		block.setSelectors(selectors);
		assertEquals(selectors, block.getSelectors());
	}

	@Test
	public void testGetSetUnits() {
		Block block = generate();
		ArrayList<Unit> units = new ArrayList<Unit>();
		units.add(new Property("color", "green"));
		block.setUnits(units);
		assertEquals(units, block.getUnits());
	}

	@Test
	public void testRead() {
		String blockString = "div > .asdf-thing :nth-child(4), p {\n" + "\t$bc: #ff0000;\n"
				+ "\tborder-{top|left}: 1px solid $bc;\n" + "\t.sub {\n" + "\t\tcolor: $bc;\n" + "\t}\n" + "}";
		Block block = Block.read(blockString);
		Block testBlock = generate();
		assertEquals(testBlock, block);
	}

	@Test
	public void testPreprocess() {
		Block block = generate();
		ArrayList<Unit> testUnits = new ArrayList<Unit>();

		ArrayList<Unit> ownUnits = new ArrayList<Unit>();
		ownUnits.add(new Property("border-top", "1px solid #ff0000"));
		ownUnits.add(new Property("border-left", "1px solid #ff0000"));
		testUnits.add(new Block(generateSelectors(), ownUnits));

		ArrayList<Selector> blockSelectors = new ArrayList<Selector>();

		ArrayList<Selector> blockSelectorsList = new ArrayList<Selector>();
		ArrayList<String> blockRelationList = new ArrayList<String>();
		blockSelectorsList.add(new Tag("div"));
		blockRelationList.add(">");
		blockSelectorsList.add(new com.tempestasludi.java.p14_cssp.pcss.selectors.Class("asdf-thing"));
		blockRelationList.add(" ");
		blockSelectorsList.add(new PseudoClass("nth-child(4)"));
		blockRelationList.add(" ");
		blockSelectorsList.add(new com.tempestasludi.java.p14_cssp.pcss.selectors.Class("sub"));
		Selector blockSelector = new Compound(blockSelectorsList, blockRelationList);
		blockSelectors.add(blockSelector);

		blockSelectorsList = new ArrayList<Selector>();
		blockRelationList = new ArrayList<String>();
		blockSelectorsList.add(new Tag("p"));
		blockRelationList.add(" ");
		blockSelectorsList.add(new com.tempestasludi.java.p14_cssp.pcss.selectors.Class("sub"));
		blockSelector = new Compound(blockSelectorsList, blockRelationList);
		blockSelectors.add(blockSelector);

		ArrayList<Unit> blockUnits = new ArrayList<Unit>();
		blockUnits.add(new Property("color", "#ff0000"));
		testUnits.add(new Block(blockSelectors, blockUnits));

		assertEquals(testUnits, block.preprocess(new ArrayList<Variable>()));
	}

	@Test
	public void testToString() {
		Block block = generate();
		String testString = "div > .asdf-thing :nth-child(4), p {\n" + "\t$bc: #ff0000;\n"
				+ "\tborder-{top|left}: 1px solid $bc;\n" + "\t.sub {\n" + "\t\tcolor: $bc;\n" + "\t}\n" + "}";
		assertEquals(testString, block.toString());
	}

	@Test
	public void testEqualsSelf() {
		Block block = generate();
		assertEquals(block, block);
	}

	@Test
	public void testEqualsCopy() {
		Block block = generate();
		Block copy = generate();
		assertEquals(block, block);
	}

	@Test
	public void testEqualsNull() {
		Block block = generate();
		assertFalse(block.equals(null));
	}

	@Test
	public void testEqualsSelectorAddition() {
		Block block = generate();
		ArrayList<Selector> selectors = generateSelectors();
		selectors.add(new Tag("h1"));
		Block addition = new Block(selectors, generateUnits());
		assertFalse(block.equals(addition));
	}

	@Test
	public void testEqualsUnitAddition() {
		Block block = generate();
		ArrayList<Unit> units = generateUnits();
		units.add(new Property("color", "green"));
		Block addition = new Block(generateSelectors(), units);
		assertFalse(block.equals(addition));
	}

	@Test
	public void testEqualsSelectorRemoval() {
		Block block = generate();
		ArrayList<Selector> selectors = generateSelectors();
		selectors.remove(selectors.size() - 1);
		Block removal = new Block(selectors, generateUnits());
		assertFalse(block.equals(removal));
	}

	@Test
	public void testEqualsUnitRemoval() {
		Block block = generate();
		ArrayList<Unit> units = generateUnits();
		units.remove(units.size() - 1);
		Block removal = new Block(generateSelectors(), units);
		assertFalse(block.equals(removal));
	}

	@Test
	public void testEqualsSelectorReplacement() {
		Block block = generate();
		ArrayList<Selector> selectors = generateSelectors();
		selectors.remove(selectors.size() - 1);
		selectors.add(new Tag("h1"));
		Block replacement = new Block(selectors, generateUnits());
		assertFalse(block.equals(replacement));
	}

	@Test
	public void testEqualsUnitReplacement() {
		Block block = generate();
		ArrayList<Unit> units = generateUnits();
		units.remove(units.size() - 1);
		units.add(new Property("color", "green"));
		Block replacement = new Block(generateSelectors(), units);
		assertFalse(block.equals(replacement));
	}

}

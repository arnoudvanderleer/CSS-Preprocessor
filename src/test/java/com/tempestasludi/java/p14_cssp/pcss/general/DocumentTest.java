package com.tempestasludi.java.p14_cssp.pcss.general;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.tempestasludi.java.p14_cssp.pcss.properties.Property;
import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Compound;
import com.tempestasludi.java.p14_cssp.pcss.selectors.PseudoClass;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Selector;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Tag;

public class DocumentTest {

	private static Document generate() {
		return new Document(generateUnits());
	}

	private static ArrayList<Unit> generateUnits() {
		ArrayList<Unit> result = new ArrayList<Unit>();

		result.add(new Variable("bc", "blue"));

		result.add(BlockTest.generate());

		ArrayList<Selector> blockSelectors = new ArrayList<Selector>();
		blockSelectors.add(new Tag("p"));
		ArrayList<Unit> blockUnits = new ArrayList<Unit>();
		blockUnits.add(new Property("color", "$bc"));
		result.add(new Block(blockSelectors, blockUnits));

		return result;
	}

	@Test
	public void testDocument() {
		Document document = generate();
		assertEquals(generateUnits(), document.getUnits());
	}

	@Test
	public void testGetSetUnits() {
		Document document = generate();
		ArrayList<Unit> testUnits = new ArrayList<Unit>();
		testUnits.add(new Variable("bc", "blue"));
		testUnits.add(new Variable("bw", "5px"));
		document.setUnits(testUnits);
		assertEquals(testUnits, document.getUnits());
	}

	@Test
	public void testRead() {
		String blockString = "$bc: blue;\n" + "div > .asdf-thing :nth-child(4), p {\n" + "\t$bc: #ff0000;\n"
				+ "\tborder-{top|left}: 1px solid $bc;\n" + "\t.sub {\n" + "\t\tcolor: $bc;\n" + "\t}\n" + "}\n" + "\n"
				+ "p {\n" + "\tcolor: $bc;\n" + "}\n" + "";
		Document document = Document.read(blockString);
		assertEquals(generate(), document);
	}

	@Test
	public void testPreprocess() {
		Document document = generate();
		ArrayList<Document> testList = new ArrayList<Document>();
		
		ArrayList<Unit> testUnits = new ArrayList<Unit>();

		ArrayList<Unit> ownUnits = new ArrayList<Unit>();
		ownUnits.add(new Property("border-top", "1px solid #ff0000"));
		ownUnits.add(new Property("border-left", "1px solid #ff0000"));
		testUnits.add(new Block(BlockTest.generateSelectors(), ownUnits));

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

		blockSelectors = new ArrayList<Selector>();
		blockSelectors.add(new Tag("p"));
		blockUnits = new ArrayList<Unit>();
		blockUnits.add(new Property("color", "blue"));
		testUnits.add(new Block(blockSelectors, blockUnits));
		
		testList.add(new Document(testUnits));

		assertEquals(testList, document.preprocess(new ArrayList<Variable>()));
	}

	@Test
	public void testToString() {
		Document document = generate();
		String testString = "$bc: blue;\n" + "div > .asdf-thing :nth-child(4), p {\n" + "\t$bc: #ff0000;\n"
				+ "\tborder-{top|left}: 1px solid $bc;\n" + "\t.sub {\n" + "\t\tcolor: $bc;\n" + "\t}\n" + "}\n" + "\n"
				+ "p {\n" + "\tcolor: $bc;\n" + "}\n" + "";
		assertEquals(testString, document.toString());
	}

	@Test
	public void testEqualsSelf() {
		Document document = generate();
		assertEquals(document, document);
	}

	@Test
	public void testEqualsCopy() {
		Document document = generate();
		Document copy = generate();
		assertEquals(document, copy);
	}

	@Test
	public void testEqualsNull() {
		Document document = generate();
		assertFalse(document.equals(null));
	}

	@Test
	public void testEqualsUnitAddition() {
		Document document = generate();
		ArrayList<Unit> units = generateUnits();
		units.add(new Property("color", "green"));
		Document addition = new Document(units);
		assertFalse(document.equals(addition));
	}

	@Test
	public void testEqualsUnitRemoval() {
		Document document = generate();
		ArrayList<Unit> units = generateUnits();
		units.remove(units.size() - 1);
		Document removal = new Document(units);
		assertFalse(document.equals(removal));
	}

	@Test
	public void testEqualsUnitReplacement() {
		Document document = generate();
		ArrayList<Unit> units = generateUnits();
		units.remove(units.size() - 1);
		units.add(new Property("color", "green"));
		Document replacement = new Document(units);
		assertFalse(document.equals(replacement));
	}
}

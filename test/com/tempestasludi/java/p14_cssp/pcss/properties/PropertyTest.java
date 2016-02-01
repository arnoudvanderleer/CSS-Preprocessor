package com.tempestasludi.java.p14_cssp.pcss.properties;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.tempestasludi.java.p14_cssp.pcss.general.Unit;

public class PropertyTest {

	private Property generate() {
		return new Property("color", "$green");
	}
	
	@Test
	public void testPropertyName() {
		Property property = generate();
		assertEquals("color", property.getName());
	}
	
	@Test
	public void testPropertyValue() {
		Property property = generate();
		assertEquals("$green", property.getValue());
	}

	@Test
	public void testGetSetName() {
		Property property = generate();
		property.setName("background");
		assertEquals("background", property.getName());
	}

	@Test
	public void testGetSetValue() {
		Property property = generate();
		property.setValue("$blue");
		assertEquals("$blue", property.getValue());
	}

	@Test
	public void testPreprocessNameFork() {
		ArrayList<Unit> expected = new ArrayList<Unit>();
		expected.add(new Property("border-left", "1px solid black"));
		expected.add(new Property("border-top", "1px solid black"));
		Property property = new Property("border-{left|top}", "1px solid black");
		assertEquals(expected, property.preprocess(new ArrayList<Variable>()));
	}

	@Test
	public void testPreprocessVariableUse() {
		ArrayList<Unit> expected = new ArrayList<Unit>();
		expected.add(new Property("border", "1px solid black"));
		Property property = new Property("border", "$borderSize solid $borderColor");
		ArrayList<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("color", "green"));
		variables.add(new Variable("borderColor", "black"));
		variables.add(new Variable("background", "1px solid green"));
		variables.add(new Variable("borderSize", "1px"));
		assertEquals(expected, property.preprocess(variables));
	}

	@Test
	public void testPreprocessBrowsers() {
		ArrayList<Unit> expected = new ArrayList<Unit>();
		expected.add(new Property("-moz-border-radius", "1px"));
		expected.add(new Property("-ms-border-radius", "1px"));
		expected.add(new Property("-o-border-radius", "1px"));
		expected.add(new Property("-webkit-border-radius", "1px"));
		expected.add(new Property("border-radius", "1px"));
		Property property = new Property("-browsers-border-radius", "1px");
		ArrayList<Variable> variables = new ArrayList<Variable>();
		assertEquals(expected, property.preprocess(new ArrayList<Variable>()));
	}

	@Test
	public void testToString() {
		Property property = generate();
		assertEquals("color: $green;", property.toString());
	}

}

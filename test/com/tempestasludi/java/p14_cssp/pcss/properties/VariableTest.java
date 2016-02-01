package com.tempestasludi.java.p14_cssp.pcss.properties;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class VariableTest {

	private Variable generate() {
		return new Variable("asdf", "jkl");
	}

	@Test
	public void testVariableString() {
		Variable variable = new Variable("asdf");
		assertEquals("asdf", variable.getName());
	}

	@Test
	public void testVariableStringStringName() {
		Variable variable = generate();
		assertEquals("asdf", variable.getName());
	}

	@Test
	public void testVariableStringStringValue() {
		Variable variable = generate();
		assertEquals("jkl", variable.getValue());
	}

	@Test
	public void testGetSetName() {
		Variable variable = generate();
		variable.setName("ab");
		assertEquals("ab", variable.getName());
	}

	@Test
	public void testGetSetValue() {
		Variable variable = generate();
		variable.setValue("ab");
		assertEquals("ab", variable.getValue());
	}

	@Test
	public void testPreprocessReturn() {
		Variable variable = generate();
		ArrayList<Variable> variables = new ArrayList<Variable>();
		assertEquals(null, variable.preprocess(variables));
	}

	@Test
	public void testPreprocessContains() {
		Variable variable = generate();
		Variable copy = generate();
		copy.setValue("ab");
		ArrayList<Variable> variables = new ArrayList<Variable>();
		variables.add(copy);
		variable.preprocess(variables);
		assertEquals("jkl", variables.get(variables.indexOf(copy)).getValue());
	}

	@Test
	public void testPreprocessNotContains() {
		Variable variable = generate();
		ArrayList<Variable> variables = new ArrayList<Variable>();
		variable.preprocess(variables);
		assertNotEquals(-1, variables.indexOf(variable));
	}

	@Test
	public void testToString() {
		Variable variable = generate();
		assertEquals("$asdf: jkl;", variable.toString());
	}

	@Test
	public void testEqualsSelf() {
		Variable variable = generate();
		assertTrue(variable.equals(variable));
	}

	@Test
	public void testEqualsCopy() {
		Variable variable = generate();
		Variable copy = generate();
		assertTrue(variable.equals(copy));
	}

	@Test
	public void testEqualsSame() {
		Variable variable = generate();
		Variable same = generate();
		same.setValue(null);
		assertTrue(variable.equals(same));
	}

	@Test
	public void testEqualsNull() {
		Variable variable = generate();
		assertFalse(variable.equals(null));
	}

	@Test
	public void testEqualsOther() {
		Variable variable = generate();
		Variable other = new Variable("dds");
		assertFalse(variable.equals(other));
	}

}

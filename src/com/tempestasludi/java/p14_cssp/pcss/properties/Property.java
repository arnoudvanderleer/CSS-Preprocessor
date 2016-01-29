package com.tempestasludi.java.p14_cssp.pcss.properties;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.document.Unit;

/**
 * Property represents a CSS property.
 *
 * @author Tempestas Ludi
 */
public class Property implements Unit {

	/**
	 * The name of the property.
	 */
	private String name;
	
	/**
	 * The value of the property.
	 */
	private String value;

	/**
	 * Class constructor.
	 *
	 * @param name
	 * @param value
	 */
	public Property(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Get the name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the value
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Change the name
	 *
	 * @param name
	 *            the name to change to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Change the value
	 *
	 * @param value
	 *            the value to change to
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Unit preprocess(ArrayList<Variable> variables) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "";
	}
}

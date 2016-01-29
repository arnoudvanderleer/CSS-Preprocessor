package com.tempestasludi.java.p14_cssp.pcss.selectors;

import java.util.ArrayList;

/**
 * Selector represents a CSS selector.
 *
 * @author Tempestas Ludi
 */
public abstract class Selector {

	/**
	 * The name of the selector.
	 */
	private String name;

	/**
	 * Class constructor.
	 *
	 * @param name
	 */
	public Selector(String name) {
		super();
		this.name = name;
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
	 * Change the name
	 *
	 * @param name
	 *            the name to change to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public static Selector read() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract String toString();

}

package com.tempestasludi.java.p14_cssp.pcss.selectors;

/**
 * Attribute represents a CSS attribute selector.
 *
 * @author Tempestas Ludi
 */
public class Attribute extends Selector {

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the attribute
	 */
	public Attribute(String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("[").append(this.getName()).append("]").toString();
	}

}

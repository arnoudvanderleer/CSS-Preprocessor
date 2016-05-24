package com.tempestasludi.java.p14_cssp.pcss.selectors;

/**
 * PseudoClass represents a CSS pseudo class selector.
 *
 * @author Tempestas Ludi
 */
public class PseudoClass extends Selector {

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the pseudo class
	 */
	public PseudoClass(String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(":").append(this.getName()).toString();
	}

}

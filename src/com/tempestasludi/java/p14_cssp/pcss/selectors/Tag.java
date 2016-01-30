package com.tempestasludi.java.p14_cssp.pcss.selectors;

/**
 * Tag represents a CSS tag selector.
 *
 * @author Tempestas Ludi
 */
public class Tag extends Selector {

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the tag
	 */
	public Tag(String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.getName();
	}

}

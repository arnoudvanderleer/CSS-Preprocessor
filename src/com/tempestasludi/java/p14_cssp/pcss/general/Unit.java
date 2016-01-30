package com.tempestasludi.java.p14_cssp.pcss.general;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;

/**
 * Unit represents a unit of a CSS document; either a block, a property/value
 * pair or a variable.
 *
 * @author Tempestas Ludi
 */
public interface Unit {

	/**
	 * Preprocesses the unit into CSS.
	 *
	 * @param variables
	 *            the variables with values to fill in
	 * @return the preprocessed version of the unit
	 */
	public Unit preprocess(ArrayList<Variable> variables);

}

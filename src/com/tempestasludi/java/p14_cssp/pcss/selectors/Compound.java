package com.tempestasludi.java.p14_cssp.pcss.selectors;

import java.util.ArrayList;

/**
 * Compound represents a css selector that consists of multiple selector parts.
 *
 * @author Tempestas Ludi
 */
public class Compound extends Selector {

	/**
	 * The selectors that make up this compound.
	 */
	private ArrayList<Selector> selectors;

	/**
	 * The relations between the selectors (the n'th element describes the
	 * relation beteen selectors n and n+1).
	 */
	private ArrayList<String> relations;

	/**
	 * Class constructor.
	 */
	public Compound(ArrayList<Selector> selectors, ArrayList<String> relations) {
		super("");
		this.selectors = new ArrayList<Selector>(selectors);
		this.relations = new ArrayList<String>(relations);
	}

	/**
	 * Get the selectors
	 *
	 * @return the selectors
	 */
	public ArrayList<Selector> getSelectors() {
		return new ArrayList<Selector>(selectors);
	}

	/**
	 * Get the relations
	 *
	 * @return the relations
	 */
	public ArrayList<String> getRelations() {
		return new ArrayList<String>(relations);
	}

	/**
	 * Change the selectors
	 *
	 * @param selectors
	 *            the selectors to change to
	 */
	public void setSelectors(ArrayList<Selector> selectors) {
		this.selectors = new ArrayList<Selector>(selectors);
	}

	/**
	 * Change the relations
	 *
	 * @param relations
	 *            the relations to change to
	 */
	public void setRelations(ArrayList<String> relations) {
		this.relations = new ArrayList<String>(relations);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		Compound other = (Compound) obj;
		return (this.selectors.equals(other.getSelectors()) && this.relations.equals(other.getRelations()));
	}

}

package com.tempestasludi.java.p14_cssp.pcss.general;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Selector;

/**
 * Block represents a CSS block, starting with a set of selectors, followed by a
 * set of units.
 *
 * @author Tempestas Ludi
 */
public class Block implements Unit {

	/**
	 * The selectors of the block.
	 */
	private ArrayList<Selector> selectors;

	/**
	 * The units of the block.
	 */
	private ArrayList<Unit> units;

	/**
	 * Class constructor.
	 *
	 * @param selectors
	 *            the selectors of the block
	 * @param units
	 *            the units of the block
	 */
	public Block(ArrayList<Selector> selectors, ArrayList<Unit> units) {
		super();
		this.selectors = new ArrayList<Selector>(selectors);
		this.units = new ArrayList<Unit>(units);
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
	 * Get the units
	 *
	 * @return the units
	 */
	public ArrayList<Unit> getUnits() {
		return new ArrayList<Unit>(units);
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
	 * Change the units
	 *
	 * @param units
	 *            the units to change to
	 */
	public void setUnits(ArrayList<Unit> units) {
		this.units = new ArrayList<Unit>(units);
	}

	/**
	 * Reads a CSS block into a Block.
	 *
	 * @param block
	 *            the block data to read
	 * @return a document containing the file
	 */
	public static Block read(String block) {
		return null;
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
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.selectors.size(); i++) {
			builder.append(this.selectors.get(i)).append(" ");
		}
		builder.append("{\n");
		for (int i = 0; i < this.units.size(); i++) {
			builder.append("\t");
			builder.append(this.units.get(i).toString().replaceAll("\n", "\n\t").trim());
			builder.append("\n");
		}
		builder.append("}");
		return builder.toString();
	}

}

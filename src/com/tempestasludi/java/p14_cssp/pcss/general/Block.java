package com.tempestasludi.java.p14_cssp.pcss.general;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.properties.Property;
import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;
import com.tempestasludi.java.p14_cssp.pcss.selectors.Compound;
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
		block = block.trim();
		int i = 0;
		int selectorStart = 0;
		ArrayList<Selector> selectors = new ArrayList<Selector>();
		ArrayList<Unit> units = new ArrayList<Unit>();
		while (i < block.length()) {
			if (block.charAt(i) == '"' || block.charAt(i) == '\'') {
				i = Parser.searchStringEnd(i, block);
			} else if (block.charAt(i) == ',') {
				selectors.add(Compound.read(block.substring(selectorStart, i)));
				selectorStart = i + 1;
			} else if (block.charAt(i) == '{') {
				selectors.add(Compound.read(block.substring(selectorStart, i)));
				i++;
				break;
			}
			i++;
		}
		int unitStart = i;
		int colonPosition = -1;
		while (i < block.length()) {
			if (block.charAt(i) == '"' || block.charAt(i) == '\'') {
				i = Parser.searchStringEnd(i, block);
			} else if (block.charAt(i) == '(') {
				i = Parser.searchBracket(i, block);
			} else if (block.charAt(i) == '{') {
				int endBracket = Parser.searchBracket(i, block);
				if (!block.substring(i + 1, endBracket).matches("[a-zA-Z\\-]*")) {
					units.add(Block.read(block.substring(unitStart, endBracket + 1)));
					colonPosition = -1;
					unitStart = i + 1;
				}
				i = endBracket;
			} else if (block.charAt(i) == '$' && colonPosition == -1) {
				int colon = block.indexOf(':', i);
				int semicolon = block.indexOf(';', colon);
				units.add(new Variable(block.substring(unitStart, colon).trim(),
						block.substring(colon + 1, semicolon).trim()));
				i = semicolon;
				unitStart = i + 1;
			} else if (block.charAt(i) == ':') {
				colonPosition = i;
			} else if (block.charAt(i) == ';') {
				units.add(new Property(block.substring(unitStart, colonPosition).trim(),
						block.substring(colonPosition + 1, i).trim()));
				unitStart = i + 1;
			} else if (block.charAt(i) == '{') {
				break;
			}
			i++;
		}
		return new Block(selectors, units);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Unit> preprocess(ArrayList<Variable> variables) {
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
			String unitString = this.units.get(i).toString().replaceAll("\n", "\n\t");
			if (i == this.units.size() - 1) {
				unitString = unitString.trim();
			}
			builder.append(unitString);
			builder.append("\n");
		}
		builder.append("}");
		return builder.toString();
	}

}

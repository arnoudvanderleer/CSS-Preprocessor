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
		while (i < block.length()) {
			if (block.charAt(i) == '"' || block.charAt(i) == '\'') {
				i = Parser.searchStringEnd(i, block);
			} else if (block.charAt(i) == ',') {
				selectors.add(Selector.read(block.substring(selectorStart, i)));
				selectorStart = i + 1;
			} else if (block.charAt(i) == '{') {
				selectors.add(Selector.read(block.substring(selectorStart, i)));
				i++;
				break;
			}
			i++;
		}
		ArrayList<Unit> units = readContents(block.substring(i));
		return new Block(selectors, units);
	}

	/**
	 * Reads the contents of a CSS block into an arrayList.
	 *
	 * @param block the block string to read
	 * @return an array containing the contents of the block
	 */
	public static ArrayList<Unit> readContents(String block) {
		System.out.println(block);
		int i = 0;
		ArrayList<Unit> units = new ArrayList<Unit>();
		int unitStart = i;
		int colonPosition = -1;
		while (i < block.length()) {
			if (block.charAt(i) == '"' || block.charAt(i) == '\'') {
				i = Parser.searchStringEnd(i, block);
			} else if (block.charAt(i) == '(') {
				i = Parser.searchBracket(i, block);
			} else if (block.charAt(i) == '{') {
				int endBracket = Parser.searchBracket(i, block);
				if (!block.substring(i + 1, endBracket).matches("[a-zA-Z\\-\\|]*")) {
					units.add(Block.read(block.substring(unitStart, endBracket + 1)));
					colonPosition = -1;
					unitStart = endBracket + 1;
				}
				i = endBracket;
			} else if (block.charAt(i) == '$' && colonPosition == -1) {
				int colon = block.indexOf(':', i);
				int semicolon = block.indexOf(';', colon);
				units.add(new Variable(block.substring(i + 1, colon).trim(),
						block.substring(colon + 1, semicolon).trim()));
				i = semicolon;
				unitStart = i + 1;
			} else if (block.charAt(i) == ':') {
				colonPosition = i;
			} else if (block.charAt(i) == ';') {
				units.add(new Property(block.substring(unitStart, colonPosition).trim(),
						block.substring(colonPosition + 1, i).trim()));
				unitStart = i + 1;
			} else if (block.charAt(i) == '}') {
				break;
			}
			i++;
		}
		return units;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Unit> preprocess(ArrayList<Variable> variables) {
		ArrayList<Variable> newVariables = new ArrayList<Variable>(variables);
		ArrayList<Unit> newUnits = new ArrayList<Unit>();
		ArrayList<Block> newBlocks = new ArrayList<Block>();
		ArrayList<Unit> resultBlocks = new ArrayList<Unit>();
		for (int i = 0; i < this.units.size(); i++) {
			ArrayList<Unit> addUnits = this.units.get(i).preprocess(newVariables);
			for (int j = 0; j < addUnits.size(); j++) {
				if (addUnits.get(j) instanceof Block) {
					newBlocks.add((Block) addUnits.get(j));
				} else {
					newUnits.add(addUnits.get(j));
				}
			}
		}
		this.units = newUnits;
		resultBlocks.add(this);
		for (int i = 0; i < newBlocks.size(); i++) {
			ArrayList<Selector> blockSelectors = newBlocks.get(i).getSelectors();
			ArrayList<Selector> newBlockSelectors = new ArrayList<Selector>();
			for (int j = 0; j < this.selectors.size(); j++) {
				for (int k = 0; k < blockSelectors.size(); k++) {
					ArrayList<Selector> compoundSelectors = new ArrayList<Selector>();
					ArrayList<String> compoundRelations = new ArrayList<String>();
					Selector ownSelector = this.selectors.get(j);
					Selector blockSelector = blockSelectors.get(k);
					if (ownSelector instanceof Compound) {
						compoundSelectors.addAll(((Compound) ownSelector).getSelectors());
						compoundRelations.addAll(((Compound) ownSelector).getRelations());
					} else {
						compoundSelectors.add(ownSelector);
					}
					compoundRelations.add(" ");
					if (blockSelector instanceof Compound) {
						compoundSelectors.addAll(((Compound) blockSelector).getSelectors());
						compoundRelations.addAll(((Compound) blockSelector).getRelations());
					} else {
						compoundSelectors.add(blockSelector);
					}
					newBlockSelectors.add(new Compound(compoundSelectors, compoundRelations));
				}
			}
			resultBlocks.add(new Block(newBlockSelectors, newBlocks.get(i).getUnits()));
		}
		return resultBlocks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.selectors.size(); i++) {
			builder.append(this.selectors.get(i));
			if (i == this.selectors.size() - 1) {
				builder.append(" ");
			} else {
				builder.append(", ");
			}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (selectors == null) {
			if (other.selectors != null)
				return false;
		} else if (!selectors.equals(other.selectors))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}

}

package com.tempestasludi.java.p14_cssp.pcss.general;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;

/**
 * Document represents a CSS document.
 * 
 * @author Tempestas Ludi
 */
public class Document implements Unit {

	/**
	 * The CSS blocks of the document.
	 */
	public ArrayList<Unit> units;

	/**
	 * Class constructor.
	 *
	 * @param blocks
	 *            the CSS blocks of the document
	 */
	public Document(ArrayList<Unit> units) {
		super();
		this.units = new ArrayList<Unit>(units);
	}

	/**
	 * Get the blocks
	 *
	 * @return the blocks
	 */
	public ArrayList<Unit> getUnits() {
		return new ArrayList<Unit>(units);
	}

	/**
	 * Change the blocks
	 *
	 * @param blocks
	 *            the blocks to change to
	 */
	public void setUnits(ArrayList<Unit> units) {
		this.units = new ArrayList<Unit>(units);
	}

	/**
	 * Reads a CSS file into a Document.
	 *
	 * @param file
	 *            the file data to read
	 * @return a document containing the file
	 */
	public static Document read(String file) {
		return new Document(Block.readContents(file));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Unit> preprocess(ArrayList<Variable> variables) {
		ArrayList<Unit> newUnits = new ArrayList<Unit>();
		for (int i = 0; i < this.units.size(); i++) {
			newUnits.addAll(this.units.get(i).preprocess(variables));
		}
		ArrayList<Unit> result = new ArrayList<Unit>();
		result.add(new Document(newUnits));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.units.size(); i++) {
			builder.append(this.units.get(i).toString().trim());
			builder.append("\n");
			if (i != this.units.size() - 1 && this.units.get(i) instanceof Block) {
				builder.append("\n");
			}
		}
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
		Document other = (Document) obj;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}

}

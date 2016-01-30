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
	public ArrayList<Block> blocks;

	/**
	 * Class constructor.
	 *
	 * @param blocks
	 *            the CSS blocks of the document
	 */
	public Document(ArrayList<Block> blocks) {
		super();
		this.blocks = new ArrayList<Block>(blocks);
	}

	/**
	 * Get the blocks
	 *
	 * @return the blocks
	 */
	public ArrayList<Block> getBlocks() {
		return new ArrayList<Block>(blocks);
	}

	/**
	 * Change the blocks
	 *
	 * @param blocks
	 *            the blocks to change to
	 */
	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = new ArrayList<Block>(blocks);
	}

	/**
	 * Reads a CSS file into a Document.
	 *
	 * @param file
	 *            the file data to read
	 * @return a document containing the file
	 */
	public static Document read(String file) {
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
		for (int i = 0; i < this.blocks.size(); i++) {
			builder.append(this.blocks.get(i).toString());
			builder.append("\n");
		}
		return builder.toString();
	}

}

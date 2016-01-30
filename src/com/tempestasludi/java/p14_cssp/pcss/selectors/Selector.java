package com.tempestasludi.java.p14_cssp.pcss.selectors;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.general.Parser;

/**
 * Selector represents a CSS selector.
 *
 * @author Tempestas Ludi
 */
public abstract class Selector {

	/**
	 * The name of the selector.
	 */
	private String name;

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the selector
	 */
	public Selector(String name) {
		super();
		this.name = name;
	}

	/**
	 * Get the name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the name
	 *
	 * @param name
	 *            the name to change to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Reads a CSS selector into a Selector.
	 *
	 * @param selectorString
	 *            the selector data to read
	 * @return a document containing the file
	 */
	public static Selector read(String selectorString) {
		ArrayList<Selector> selectors = new ArrayList<Selector>();
		ArrayList<String> relations = new ArrayList<String>();
		int i = 0;
		selectorString = selectorString.trim().replaceAll("\\s?([>\\+~])\\s?", "$1");
		ArrayList<Character> delimiters = new ArrayList<Character>();
		delimiters.add(' ');
		delimiters.add('>');
		delimiters.add('+');
		delimiters.add('~');
		ArrayList<Character> selectorStarts = new ArrayList<Character>();
		selectorStarts.add('[');
		selectorStarts.add(':');
		selectorStarts.add('.');
		selectorStarts.add('#');
		while (i < selectorString.length()) {
			switch (selectorString.charAt(i)) {
			case ' ':
			case '>':
			case '+':
			case '~':
				relations.add(new Character(selectorString.charAt(i)).toString());
				i++;
				break;
			case '[':
				int secondBracket = Parser.searchBracket(i, selectorString);
				selectors.add(new Attribute(selectorString.substring(i + 1, secondBracket)));
				i = secondBracket + 1;
				break;
			default:
				int j = i + 1;
				while (j < selectorString.length() && !delimiters.contains(selectorString.charAt(j))
						&& !selectorStarts.contains(selectorString.charAt(j))) {
					if (selectorString.charAt(j) == '(') {
						j = Parser.searchBracket(j, selectorString);
					}
					j++;
				}
				String name = selectorString.substring(i + 1, j);
				switch (selectorString.charAt(i)) {
				case ':':
					selectors.add(new PseudoClass(name));
					break;
				case '.':
					selectors.add(new Class(name));
					break;
				case '#':
					selectors.add(new Id(name));
					break;
				default:
					selectors.add(new Tag(selectorString.substring(i, j)));
					break;
				}
				if (j < selectorString.length() && selectorStarts.contains(selectorString.charAt(j))) {
					relations.add("");
				}
				i = j;
				break;
			}
		}
		if (selectors.size() > 1) {
			return new Compound(selectors, relations);
		} else if (selectors.size() == 1) {
			return selectors.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract String toString();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Selector other = (Selector) obj;
		if (this.name == null) {
			if (other.getName() != null)
				return false;
		} else if (!this.name.equals(other.getName())) {
			return false;
		}
		return true;
	}

}

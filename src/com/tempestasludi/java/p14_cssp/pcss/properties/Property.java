package com.tempestasludi.java.p14_cssp.pcss.properties;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.general.Parser;
import com.tempestasludi.java.p14_cssp.pcss.general.Unit;

/**
 * Property represents a CSS property.
 *
 * @author Tempestas Ludi
 */
public class Property implements Unit {

	/**
	 * The name of the property.
	 */
	private String name;

	/**
	 * The value of the property.
	 */
	private String value;

	/**
	 * An arrayList containing browser-specific CSS prefixes.
	 */
	private static ArrayList<String> browsers = new ArrayList<String>() {
		{
			add("-moz-");
			add("-ms-");
			add("-o-");
			add("-webkit-");
			add("");
		}
	};

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the property
	 * @param value
	 *            the value of the property
	 */
	public Property(String name, String value) {
		super();
		this.name = name;
		this.value = value;
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
	 * Get the value
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
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
	 * Change the value
	 *
	 * @param value
	 *            the value to change to
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Unit> preprocess(ArrayList<Variable> variables) {
		ArrayList<Unit> result = new ArrayList<Unit>();
		if (this.name.length() >= 10 && this.name.substring(0, 10).equals("-browsers-")) {
			for (int i = 0; i < browsers.size(); i++) {
				StringBuilder nameBuilder = new StringBuilder();
				nameBuilder.append(browsers.get(i));
				nameBuilder.append(this.name.substring(10));
				result.addAll(new Property(nameBuilder.toString(), this.value).preprocess(variables));
			}
			return result;
		}
		int firstBracketIndex = this.name.indexOf('{');
		if (firstBracketIndex != -1) {
			int secondBracketIndex = Parser.searchBracket(firstBracketIndex, this.name);
			String[] forkParts = this.name.substring(firstBracketIndex + 1, secondBracketIndex).split("|");
			for (int i = 0; i < forkParts.length; i++) {
				StringBuilder nameBuilder = new StringBuilder();
				nameBuilder.append(this.name.substring(0, firstBracketIndex));
				nameBuilder.append(forkParts[i]);
				nameBuilder.append(this.name.substring(secondBracketIndex + 1));
				result.addAll(new Property(nameBuilder.toString(), this.name).preprocess(variables));
			}
			return result;
		}
		for (int i = 0; i < variables.size(); i++) {
			if (this.value.indexOf(variables.get(i).toString()) > -1) {
				StringBuilder regexBuilder = new StringBuilder();
				regexBuilder.append("\\$");
				regexBuilder.append(variables.get(i).getName());
				result.addAll(new Property(this.name, this.value.replaceAll(regexBuilder.toString(), variables.get(i).getValue())).preprocess(variables));
				return result;
			}
		}
		result.add(this);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append(this.name);
		if (this.value != null) {
			builder.append(": ").append(this.value).append(";");
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
		Property other = (Property) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}

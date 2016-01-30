package com.tempestasludi.java.p14_cssp.pcss.properties;

import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.general.Unit;

/**
 * Variable represents a PCSS variable.
 *
 * @author Tempestas Ludi
 */
public class Variable implements Unit {

	/**
	 * The name of the variable.
	 */
	private String name;

	/**
	 * The value of the variable.
	 */
	private String value = "";

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the variable
	 */
	public Variable(String name) {
		super();
		this.name = name;
	}

	/**
	 * Class constructor.
	 *
	 * @param name
	 *            the name of the variable
	 * @param value
	 *            the value of the variable
	 */
	public Variable(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
		return new StringBuilder().append("$").append(this.name).append(": ").append(this.value).append(";").toString();
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
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

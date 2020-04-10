package com.msgraph.colibo.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML Data
 * @author Christoffer
 *
 */
@XmlRootElement(name="data")
public class Data {
	private Persons persons;

	public Persons getPersons() {
		return persons;
	}

	public void setPersons(Persons persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Data [persons = "+persons+"]";
	}
}

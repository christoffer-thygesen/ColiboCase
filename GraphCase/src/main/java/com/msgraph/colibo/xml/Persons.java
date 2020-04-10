package com.msgraph.colibo.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML Person List
 * @author Christoffer
 *
 */
@XmlRootElement(name="persons")
public class Persons {
	private List<Person> person;

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Persons [person = "+person+"]";
	}
}

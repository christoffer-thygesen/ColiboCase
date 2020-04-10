package com.msgraph.colibo.graph;

public class GraphPerson {
	private String name;
	private String mobile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "GraphPerson [name=" + name + ", mobile=" + mobile + "]";
	}
	
}

package com.caricature.model;

public class ReimType {

	private int id;
	private String type;

	public ReimType()
	{
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ReimType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

}

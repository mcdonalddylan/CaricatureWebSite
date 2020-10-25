package com.caricature.model;

public class ReimStatus {

	private int id;
	private String status;

	public ReimStatus() {
		super();
	}

	public ReimStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

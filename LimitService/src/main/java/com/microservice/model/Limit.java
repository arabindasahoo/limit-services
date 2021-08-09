package com.microservice.model;

public class Limit {

	private int max;
	private int min;
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public Limit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Limit(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}
	
	
}

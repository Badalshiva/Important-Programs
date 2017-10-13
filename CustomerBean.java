package com.badal.movingaverage;

public class CustomerBean {

	private String customer;
	private int cdate;
	private double amountspent;
	private double average;
	
	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public int getCdate() {
		return cdate;
	}
	
	public void setCdate(int cdate) {
		this.cdate = cdate;
	}
	
	public double getAmountspent() {
		return amountspent;
	}
	
	public void setAmountspent(double amountspent) {
		this.amountspent = amountspent;
	}
	
	
}

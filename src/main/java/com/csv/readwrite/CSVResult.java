package com.csv.readwrite;

public class CSVResult {

	private int transaction;
	private double average;

	public CSVResult() {

	}

	public CSVResult(int transaction, double average) {
		this.transaction = transaction;
		this.average = average;
	}

	public int getTransaction() {
		return transaction;
	}

	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

}
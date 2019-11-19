package com.csv.readwrite;

public class MyUser {
	private String id;
	private String date;
	private double amount;
	private String merchant;
	private String type;
	private String transaction;

	public MyUser() {

	}

	public MyUser(String id, String date, double amount, String merchant, String type, String transaction) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.merchant = merchant;
		this.type = type;
		this.transaction = transaction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

}
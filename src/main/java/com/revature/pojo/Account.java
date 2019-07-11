package com.revature.pojo;

public class Account {
	
	private int accountId;
	private double balance;
	private int owner;
	private int accType;
	private String type;
	
	public Account() {}

	public Account(int accountId, double balance, int owner, int accType, String type) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.owner = owner;
		this.accType = accType;
		this.type = type;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getAccType() {
		return accType;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", owner=" + owner + ", accType=" + accType
				+ ", type=" + type + "]";
	}

	public void setAccType(int accType) {
		this.accType = accType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

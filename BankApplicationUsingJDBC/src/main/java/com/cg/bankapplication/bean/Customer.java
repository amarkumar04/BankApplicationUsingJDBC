package com.cg.bankapplication.bean;

import java.util.List;

public class Customer {
	private int customerId;
	private String customerName;
	private String email;
	private String mobile;
	private String address;
	private long accountNo;
	private double balance;
	private int pin;
	//private List<Transaction> transaction;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String customerName, String email, String mobile, String address, long accountNo,
			double balance, int pin) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.accountNo = accountNo;
		this.balance = balance;
		this.pin = pin;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Customer\n [CustomerId=" + customerId + "\n CustomerName=" + customerName + "\n Email=" + email
				+ "\n Mobile=" + mobile + "\n Address=" + address + "\n AccountNo=" + accountNo + "\n Balance=" + balance
				+ "\n Pin=" + pin +"]\n\n";
	}

	
	
}

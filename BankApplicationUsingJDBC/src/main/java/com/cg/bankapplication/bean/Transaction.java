package com.cg.bankapplication.bean;

import java.util.Date;

public class Transaction {
	private String transactionId;
	private String transactionType;
	private long customerId;
	private long customerAccountNo;
	private long receiverAccountNo;
	private double amount;
	private String transactionDetails;
	private String date;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(String transactionId, String transactionType, long customerId, long customerAccountNo,
			long receiverAccountNo, double amount, String transactionDetails, String date) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.customerId = customerId;
		this.customerAccountNo = customerAccountNo;
		this.receiverAccountNo = receiverAccountNo;
		this.amount = amount;
		this.transactionDetails = transactionDetails;
		this.date = date;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getCustomerAccountNo() {
		return customerAccountNo;
	}
	public void setCustomerAccountNo(long customerAccountNo) {
		this.customerAccountNo = customerAccountNo;
	}
	public long getReceiverAccountNo() {
		return receiverAccountNo;
	}
	public void setReceiverAccountNo(long receiverAccountNo) {
		this.receiverAccountNo = receiverAccountNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction \n[TransactionId=" + transactionId + "\n TransactionType=" + transactionType + "\n CustomerId="
				+ customerId + "\n AccountNo=" + customerAccountNo + "\n DestinationAccountNo=" + receiverAccountNo
				+ "\n Amount=" + amount + "\n TransactionDetails=" + transactionDetails + "\n Date=" + date + "]\n\n";
	}
	
	
	
}

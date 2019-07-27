package com.cg.bankapplication.DAO;


import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Map;


import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;

public class BankApplicationDAOImp implements BankApplicationDAO {
	
	
	public Customer createCustomerAcc(Customer customer) {
		accountDetails.put(customer.getAccountNo(), customer) ;
		return customer;
	}

	@Override
	public boolean deleteCustomerAcc(Customer customer) {
		if(accountDetails.remove(customer.getAccountNo())!=null)
			return true;
		else
			return false;
	}

	@Override
	public double debit(long accountNo, double amount) {
		Customer customer = accountDetails.get(accountNo) ;
		if(customer.getBalance() < amount)
			return -1;
		else {
			double newbalance = customer.getBalance()-amount ;
			customer.setBalance(newbalance);
			accountDetails.put(accountNo, customer);
			return customer.getBalance();
		}
	
	}

	@Override
	public boolean credit(long accountNo, double amount) {
		Customer customer = accountDetails.get(accountNo) ;
		if(customer != null) {
			double newBalance = customer.getBalance() + amount ;
			customer.setBalance(newBalance);
			return true ;
		}
		else
			return false;
	}
	@Override
	public List<Transaction> getTransactionDetails(long accountNo) {
		List<Transaction> trans = new ArrayList<>();
		for (Map.Entry<String, Transaction> entry : transactions.entrySet()) {
			if (entry.getValue().getCustomerAccountNo() == accountNo || entry.getValue().getReceiverAccountNo()==accountNo)
				trans.add(entry.getValue());
		}
		Collections.reverse(trans);
		return trans;
	}

	@Override
	public Customer getCustomerAccountDetails(long accountNo) {
		Customer customer = accountDetails.get(accountNo) ;
		
		return customer;
	}

	@Override
	public boolean addTransaction(Transaction trans) {
		if(transactions.put(trans.getTransactionId(), trans)!= null)
			return true ;
		else
			return false;
	}
}

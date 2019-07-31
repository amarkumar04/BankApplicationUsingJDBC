package com.cg.bankapplication.DAO;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;

public interface BankApplicationDAO {
	
	Map<Long,Customer> accountDetails = new HashMap<>();
	LinkedHashMap<String,Transaction> transactions = new LinkedHashMap<>();
	

	Customer createCustomerAcc(Customer customer);
	boolean deleteCustomerAcc(long accountNo) ;
	double debit(long accountNo, double amount);
	boolean credit(long accountNo, double amount);
	//boolean updateCustomerAcc(Customer customer) ;
	
	
	List<Transaction> getTransactionDetails(long accountNo);
	Customer getCustomerAccountDetails(long accountNo);
	
	boolean addTransaction(Transaction trans);
	
	
}

package com.cg.bankapplication.service;


import java.util.List;


import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;
import com.cg.bankapplication.exception.BankApplicationException;

public interface BankApplicationService {
	

	Customer createCustomerAcc(Customer customer) throws BankApplicationException;
	boolean deleteCustomerAcc(long accountNo) throws BankApplicationException;
	//boolean updateCustomerAcc(Customer customer) throws BankApplicationException;
	boolean deposite(long accountNo, double amount) throws BankApplicationException;
	double withdraw(long accountNo,double amount) throws BankApplicationException;
	boolean fundTransfer(long accountDebit,long accountCredit,double amount) throws BankApplicationException;
	List<Transaction> gettransactionsDetails(long accountNo) throws BankApplicationException;
	double showBalance(long accountNo) throws BankApplicationException;
	Customer getCustomerAccountDetails(long accountNo) throws BankApplicationException;
	//boolean validateUser(long accountNo,int pin) throws BankApplicationException;
	String createTransactionId() throws BankApplicationException;
	boolean addTransaction(Transaction trans) throws BankApplicationException;
}

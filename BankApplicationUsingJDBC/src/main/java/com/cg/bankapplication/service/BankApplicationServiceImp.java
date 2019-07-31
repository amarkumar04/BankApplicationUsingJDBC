package com.cg.bankapplication.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.bankapplication.DAO.BankApplicationDAOImp;
import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;
import com.cg.bankapplication.exception.BankApplicationException;

public class BankApplicationServiceImp implements BankApplicationService {
	
	BankApplicationDAOImp dao = new BankApplicationDAOImp();
	static long accountNo = 3340000005L ;
	static int customerId=7000005 ;
	@Override
	public Customer createCustomerAcc(Customer customer) {
		customer.setAccountNo(accountNo);
		customer.setCustomerId(customerId);
		return dao.createCustomerAcc(customer);
	}

	@Override
	public boolean deleteCustomerAcc(long accountNo) {
		return dao.deleteCustomerAcc(accountNo);
	}


	@Override
	public boolean deposite(long accountNo, double amount) {
		if(dao.credit(accountNo, amount))
			return true ;
		else
			return false;
	}

	@Override
	public double withdraw(long accountNo, double amount) {
		return dao.debit(accountNo, amount);
	}

	@Override
	public boolean fundTransfer(long accountDebit, long accountCredit, double amount) {
		if((dao.debit(accountDebit, amount)>-1) && dao.credit(accountCredit, amount) )
			return true;
		else
			return false; 
	}

	@Override
	public List<Transaction> gettransactionsDetails(long accountNo) {
		return dao.getTransactionDetails(accountNo);
	}

	@Override
	public double showBalance(long accountNo) {
		Customer customer = dao.getCustomerAccountDetails(accountNo) ;

			return customer.getBalance();
		
	}

	@Override
	public Customer getCustomerAccountDetails(long accountNo) {
		return dao.getCustomerAccountDetails(accountNo);
	}

	
	public boolean validateUserPin(long accountNo,int pin) throws BankApplicationException {
		Customer customer = dao.getCustomerAccountDetails(accountNo) ;
		if(customer!= null && customer.getPin()==pin)
			return true ;
		else 
			throw new BankApplicationException("your pin does not match.Please try again.");
	}

	
	public boolean validateAccount(long accountNo) throws BankApplicationException {
		if(dao.getCustomerAccountDetails(accountNo)!=null)
			return true;
		else
			throw new BankApplicationException("The account number entered in invalid.Please try again.");
	}
	
	
	@Override
	public String createTransactionId() {
		int transid = (int)(Math.random()*100000000) ;
		
		String txnid = "TXN"+ Integer.toString(transid) ;
		return txnid;
	}

	@Override
	public boolean addTransaction(Transaction trans) {
		return dao.addTransaction(trans);
	}
	
	
	
	public boolean isNameValid(String name) throws BankApplicationException {
		Pattern nameptn = Pattern.compile("^[A-Z]{1}[a-z ]{4,}$")  ;
		Matcher match = nameptn.matcher(name) ;
		if(!match.matches())
			throw new BankApplicationException("Name is not valid.Please try again.") ;
		else
			return true;
		
	}

	
	public boolean isEmailValid(String email) throws BankApplicationException {
		Pattern nameptn = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")  ;
		Matcher match = nameptn.matcher(email) ;
		if(!match.matches())
			throw new BankApplicationException("Email id is not valid.Please try again.") ;
		else
			return true;
		
	}
	
	
	
	public boolean isPhoneValid(String phone) throws BankApplicationException {
		String mobile = String.valueOf(phone);
		Pattern nameptn = Pattern.compile("^[7-9]{1}[0-9]{9}$")  ;
		Matcher match = nameptn.matcher(mobile) ;
		if(!match.matches())
			throw new BankApplicationException("Mobile number is not valid.Please try again.") ;
		else 
			return true;
		
	}


}

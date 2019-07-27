package com.cg.bankapplication.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Map;


import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;
import com.cg.bankapplication.utility.DBConnection;

public class BankApplicationDAOImp implements BankApplicationDAO {
	
	Connection connection=null;
	PreparedStatement statement=null;
	ResultSet resultSet=null;
	int row=-1;
	public Customer createCustomerAcc(Customer customer) {
		accountDetails.put(customer.getAccountNo(), customer) ;
		
		
		long accountNo = 0 ;
		long customerId = 0;
		try(Connection connection=DBConnection.getConnection();) {
			
			
			statement=connection.prepareStatement("select accountno.NEXTVAL from dual");
			resultSet=statement.executeQuery();
			while(resultSet.next())
				accountNo=resultSet.getInt(1);
			statement=connection.prepareStatement("select customerid.NEXTVAL from dual");
			resultSet=statement.executeQuery();
			while(resultSet.next())
				customerId=resultSet.getInt(1);
			
			
			statement=connection.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,?)");
			statement.setLong(1,customerId );
			statement.setString(2, customer.getCustomerName());
			statement.setString(3,customer.getEmail());
			statement.setLong(4, customer.getAccountNo());
			statement.setString(5, customer.getAddress());
			statement.setLong(6, customer.getAccountNo());
			statement.setDouble(7, customer.getBalance());
			statement.setInt(8, customer.getPin());
			row=statement.executeUpdate();
			System.out.println("Book inserted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
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

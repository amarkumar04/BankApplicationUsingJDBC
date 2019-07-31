package com.cg.bankapplication.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
				accountNo = resultSet.getLong(1);
			
			statement=connection.prepareStatement("select customerid.NEXTVAL from dual");
			resultSet=statement.executeQuery();
			while(resultSet.next())
				customerId=resultSet.getLong(1);
			
			customer.setAccountNo(accountNo);
			customer.setCustomerId(customerId);
			statement=connection.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,?)");
			statement.setLong(1,customer.getCustomerId());
			statement.setString(2,customer.getCustomerName()); 
			statement.setString(3,customer.getEmail()); 
			statement.setLong(4,customer.getMobile()); 
			statement.setString(5,customer.getAddress()); 
			statement.setLong(6,customer.getAccountNo());
			statement.setDouble(7,customer.getBalance()); 
			statement.setInt(8,customer.getPin()); 
			row=statement.executeUpdate();
			statement=connection.prepareStatement("commit");
			statement.executeQuery();
			System.out.println("Account Created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	@Override
	public boolean deleteCustomerAcc(long accountNo) {
		try(Connection connection=DBConnection.getConnection();) {
			statement=connection.prepareStatement("delete from customer where accountno = ?");
			statement.setLong(1,accountNo);
			resultSet=statement.executeQuery();
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public double debit(long accountNo, double amount) {
		double balance = 0; 
		try(Connection connection=DBConnection.getConnection();) {
			statement=connection.prepareStatement("select balance from customer where accountno = ?");
			statement.setLong(1,accountNo);
			resultSet=statement.executeQuery();
			if(resultSet.next()) {
				balance = resultSet.getDouble("balance");
				double newBalance = balance - amount ;
				statement=connection.prepareStatement("update customer set balance = ? where accountno = ?");
				statement.setDouble(1, newBalance);
				statement.setLong(2, accountNo);
				resultSet=statement.executeQuery();
				return newBalance;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;	
	}

	@Override
	public boolean credit(long accountNo, double amount) {
		double balance = 0; 
		try(Connection connection=DBConnection.getConnection();) {
			statement=connection.prepareStatement("select balance from customer where accountno = ?");
			statement.setLong(1,accountNo);
			resultSet=statement.executeQuery();
			if(resultSet.next()) {
				balance = resultSet.getDouble("balance");
				double newBalance = balance + amount ;
				statement=connection.prepareStatement("update customer set balance = ? where accountno = ?");
				statement.setDouble(1, newBalance);
				statement.setLong(2, accountNo);
				resultSet=statement.executeQuery();
				System.out.println("credited");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	@Override
	public List<Transaction> getTransactionDetails(long accountNo) {
		LinkedList<Transaction> trans = new LinkedList<>() ;
		try(Connection connection=DBConnection.getConnection();) {
			statement=connection.prepareStatement("select * from transaction where CUSTOMERACCOUNTNO = ?");
			statement.setLong(1,accountNo);
			resultSet=statement.executeQuery();		
			while(resultSet.next())
				trans.add(new Transaction(resultSet.getString(1), resultSet.getString(2), resultSet.getLong(3),resultSet.getLong(4), resultSet.getLong(5), resultSet.getDouble(6), resultSet.getString(7), resultSet.getString(8))) ;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return trans;
	}

	@Override
	public Customer getCustomerAccountDetails(long accountNo) {
		Customer customer = null ;
		try(Connection connection=DBConnection.getConnection();) {
			statement=connection.prepareStatement("select * from customer where accountno = ?");
			statement.setLong(1,accountNo);
			resultSet=statement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getLong("customerId"));
				customer.setCustomerName(resultSet.getString("customerName"));
				customer.setEmail(resultSet.getString("email"));
				customer.setMobile(resultSet.getLong("Mobile"));
				customer.setBalance(resultSet.getDouble("balance"));
				customer.setAccountNo(resultSet.getLong("accountNo"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPin(resultSet.getInt("pin"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customer ;
	}

	@Override
	public boolean addTransaction(Transaction trans) {
		long accountNo = 0 ;
		long customerId = 0;
		long trasactionseq = 0;
		String transactionNo ="" ;
		try(Connection connection=DBConnection.getConnection();) {
			statement=connection.prepareStatement("select transactionseq.NEXTVAL from dual");
			resultSet=statement.executeQuery();
			while(resultSet.next())
				trasactionseq = resultSet.getLong(1);
			transactionNo = "TXN"+ Long.toString(trasactionseq);
			statement=connection.prepareStatement("insert into transaction(TRANSACTIONID,TRANSACTIONTYPE,CUSTOMERID,CUSTOMERACCOUNTNO,RECEIVERACCOUNTNO,AMOUNT,TRANSACTIONDETAILS) values(?,?,?,?,?,?,?)");
			statement.setString(1,transactionNo); 
			statement.setString(2,trans.getTransactionType()); 
			statement.setLong(3,trans.getCustomerId());
			statement.setLong(4,trans.getCustomerAccountNo()); 
			statement.setLong(5,trans.getReceiverAccountNo()); 	
			statement.setDouble(6,trans.getAmount()); 
 			statement.setString(7,trans.getTransactionDetails());
			row=statement.executeUpdate();
			System.out.println("transaction Created.");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}

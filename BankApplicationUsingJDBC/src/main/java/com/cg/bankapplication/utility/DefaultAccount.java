package com.cg.bankapplication.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import com.cg.bankapplication.DAO.BankApplicationDAOImp;
import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;
import com.cg.bankapplication.service.BankApplicationServiceImp;

public class DefaultAccount {
/*	static BankApplicationDAOImp dao = new BankApplicationDAOImp();
	BankApplicationServiceImp service = new BankApplicationServiceImp();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
	public Map<Long, Customer> AccountInitialize(){
		
		dao.accountDetails.put(3340000001L, new Customer(7000001,"Sanjeev kumar","sksanjeev@gmail.com",8989898989L,"dfsdf",3340000001L,50000,1234)) ;
	
		dao.accountDetails.put(3340000002L, new Customer(7000002,"Sanjeev kumar","sksanjeev@gmail.com",8989898989L,"dfsdf",3340000002L,50000,1234) ) ;
	
		dao.accountDetails.put(3340000003L, new Customer(7000003,"Sanjeev kumar","sksanjeev@gmail.com",8989898989L,"dfsdf",3340000003L,50000,1234) ) ;
		
		dao.accountDetails.put(3340000004L, new Customer(7000004,"Sanjeev kumar","sksanjeev@gmail.com",8989898989L,"dfsdf",3340000004L,50000,1234)) ;
		
		return dao.accountDetails;
		
	}
	public Map<String, Transaction> TransactionInitialize(){
		String txn1 = service.createTransactionId();
		dao.transactions.put(txn1, new Transaction(txn1, "Credit", 7000001,3340000001L,0, 5000, "Self",dtf.format(now))) ;
		String txn2 = service.createTransactionId();
		dao.transactions.put(txn2, new Transaction(txn2, "Credit", 7000002,3340000002L,0, 20, "Self",dtf.format(now))) ;
		String txn3 = service.createTransactionId();
		dao.transactions.put(txn3, new Transaction(txn3, "Credit", 7000003,3340000003L,0, 2000, "Self",dtf.format(now))) ;
		String txn4 = service.createTransactionId();
		dao.transactions.put(txn4, new Transaction(txn4, "Credit", 7000004,3340000004L,0, 600, "Self", dtf.format(now))) ;
		return dao.transactions;
		
	}
	
*/

}

package com.cg.bankapplication.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.bankapplication.DAO.BankApplicationDAOImp;
import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;
import com.cg.bankapplication.utility.DefaultAccount;
import com.sun.xml.internal.ws.util.xml.XmlUtil;


public class BankApplicationDAOTest {
static BankApplicationDAOImp dao ;
static Customer customer ;
static DefaultAccount da;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new BankApplicationDAOImp() ;
		da = new DefaultAccount();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		da = null ;
	}

	@Before
	public void setUp() throws Exception {//new Customer(2121,"Sansjs", "sasaasa","7878877887", "saassa", 3340000006L, 10000.0, 1234) ;
		customer = new Customer(2121L, "Sadsd dsa", "sadsa@sa.com", 89989898, "sadass", 3340000006L, 45, 1221) ;
	
	}

	@After
	public void tearDown() throws Exception {
		customer = null;
	}

	@Test
	public void testCreateCustomerAcc() {
		Customer Cust = dao.createCustomerAcc(customer) ;
		assertNotNull(Cust);
		
	}

/*	@Test
	public void testDeleteCustomerAcc() {
		dao.createCustomerAcc(customer);
		assertTrue(dao.deleteCustomerAcc(customer));
		
	}*/
	/*@Test
	public void testFalseDeleteCustomerAcc() {
		assertFalse(dao.deleteCustomerAcc(customer));
	}*/
	@Test
	public void testDebit() {
		dao.createCustomerAcc(customer) ;
		assertNotNull(dao.debit(3340000006L,600) ) ;
	}

	@Test
	public void testCredit() {
		dao.createCustomerAcc(customer) ;
		assertTrue(dao.credit(3340000006L, 1000));
	}
	@Test
	public void testGetTransactionDetails() {
		assertNotNull(dao.getTransactionDetails(3340000002L));
	}
	@Test
	public void testFalseGetTransactionDetails() {
		List<Transaction> li = new ArrayList<>();
		assertEquals(li, (dao.getTransactionDetails(3340000006L)));
	}
	
	@Test
	public void testGetCustomerAccountDetails() {
		dao.createCustomerAcc(customer);
		assertNotNull(dao.getCustomerAccountDetails(customer.getAccountNo()));
	}

	@Test
	public void testFalseGetCustomerAccountDetails() {
		assertNull(dao.getCustomerAccountDetails(customer.getAccountNo()+1));
	}


}

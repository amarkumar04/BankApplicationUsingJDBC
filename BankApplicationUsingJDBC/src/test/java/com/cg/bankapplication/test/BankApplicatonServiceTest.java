package com.cg.bankapplication.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.service.BankApplicationServiceImp;

public class BankApplicatonServiceTest {
static BankApplicationServiceImp service = null;
static Customer customer = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new BankApplicationServiceImp();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	service = null;
	}
	

	/*@Before
	public void setUp() throws Exception {
	customer = new Customer(2121,"Sansjs", "sasaasa","7878877887", "saassa", 3340000006L, 10000.0, 1234) ;
	}*/

	@After
	public void tearDown() throws Exception {
		customer = null ;
		
	}

	@Test
	public void testCreateCustomerAcc() {
		assertNotNull(service.createCustomerAcc(customer) );
	}

	@Test
	public void testDeleteCustomerAcc() {
		service.createCustomerAcc(customer) ;
	assertTrue(service.deleteCustomerAcc(customer.getAccountNo()));
	}

	@Test
	public void testDeposite() {
		service.createCustomerAcc(customer) ;
		assertTrue(service.deposite(customer.getAccountNo(), 50000));
	}
	
	@Test
	public void testWithdraw() {
		service.createCustomerAcc(customer) ;
		Double s =  service.withdraw(customer.getAccountNo(), 50000) ;
		assertEquals("-1.0", s.toString());
	}

	/*@Test
	public void testFundTransfer() {
		service.createCustomerAcc(customer) ;
		System.out.println(service.fundTransfer(3340000005L,3340000002L,50.0));
		assertTrue(service.fundTransfer(customer.getAccountNo(), 3340000002L, 50.0));
	}*/


}

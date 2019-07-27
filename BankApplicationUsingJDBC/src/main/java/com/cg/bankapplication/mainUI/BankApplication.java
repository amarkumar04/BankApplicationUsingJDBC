package com.cg.bankapplication.mainUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Scanner;

import com.cg.bankapplication.DAO.BankApplicationDAOImp;
import com.cg.bankapplication.bean.Customer;
import com.cg.bankapplication.bean.Transaction;
import com.cg.bankapplication.exception.BankApplicationException;
import com.cg.bankapplication.service.BankApplicationServiceImp;
import com.cg.bankapplication.utility.DefaultAccount;

public class BankApplication {

	public static void main(String[] args) {
		BankApplicationServiceImp service = new BankApplicationServiceImp();
		DefaultAccount account = new DefaultAccount();
		account.AccountInitialize();
		account.TransactionInitialize();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
		Scanner scanner = null;
		int option = 0;
		boolean exitSubMenu = false;
		do {
			System.out.println("Wecome To XYZ BANK");
			System.out.println("********MAIN MENU********");
			System.out.println("1.Create New Account(Sign up).");
			System.out.println("2.Log in");
			System.out.println("3.Exit");
			int mainOption1 = 0;
			{
				scanner = new Scanner(System.in);
				try {
					mainOption1 = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Please enter a valid option in numeric form.");
				}
			}
			switch (mainOption1) {
			case 1: {
				System.out.println("********CREATE ACCOUNT********");
				boolean customerNameValid = false;
				String customerName = "";
				do {
					scanner = new Scanner(System.in);
					System.out.println("Enter your name:");

					try {
						customerName = scanner.nextLine();
						customerNameValid = service.isNameValid(customerName);
					} catch (BankApplicationException e) {
						System.err.println(e.getMessage());
					}
				} while (!customerNameValid);
				String customerEmail = "";
				boolean emailValid = false;
				do {
					scanner = new Scanner(System.in);
					System.out.println("Enter your emailid");
					customerEmail = scanner.next();
					try {
						emailValid = service.isEmailValid(customerEmail);
					} catch (BankApplicationException e) {
						System.err.println(e.getMessage());
					}
				} while (!emailValid);
				String customerMobile = "";
				boolean customerMobileValid = false;
				do {
					scanner = new Scanner(System.in);
					System.out.println("Enter your mobile number:");

					try {
						customerMobile = scanner.next();
						customerMobileValid = service.isPhoneValid(customerMobile);
					} catch (BankApplicationException e) {
						System.err.println(e.getMessage());
					}
				} while (!customerMobileValid);
				String customerAddress = "";
				{
					scanner = new Scanner(System.in);
					System.out.println("Enter your address:");
					customerAddress = scanner.nextLine();
				}
				int customerPIN = 0;
				boolean pinValid = false;
				do {
					scanner = new Scanner(System.in);
					boolean pinValidInput = false;

					try {
						System.out.println("Enter a PIN for your account:[4digit number]");
						customerPIN = scanner.nextInt();
						pinValidInput = true;
					} catch (InputMismatchException e) {
						System.err.println("PLEASE Enter only numbers.");
					}

					if (customerPIN > 999 && customerPIN < 10000)
						pinValid = true;
					else if (pinValidInput)
						System.err.println("Please enter a valid pin of 4Numeric values.");
				} while (!pinValid);
				Customer customer = new Customer(0, customerName, customerEmail, customerMobile, customerAddress, 0, 0,
						customerPIN);
				Customer customerAccount = service.createCustomerAcc(customer);
				if (customerAccount != null) {
					System.out.println("Your account has been created successfully.");
					int customerId = customerAccount.getCustomerId();
					long customerAccountNo = customerAccount.getAccountNo();
					System.out.println("Your Account number is:" + customerAccountNo);
					System.out.println("Your Customer id is:" + customerId + "\n");
				} else
					System.err.println("Problem while creating account. please try again.\n");
			}
				break;

			case 2: {
				boolean accountValid = false;
				boolean userPinValid = false;
				long accountNo = 0;
				System.out.println("*******Welcome to XYZ Bank*******");
				System.out.println("LOGin :");
				do {
					scanner = new Scanner(System.in);
					System.out.println("Enter your account number:");
					try {
						accountNo = scanner.nextLong();
						accountValid = service.validateAccount(accountNo);
					} catch (BankApplicationException e) {
						System.err.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.err.println("Please enter valid account number.");
					}
				} while (!accountValid);
				int pin;
				do {
					System.out.println("Enter your pin:");
					scanner = new Scanner(System.in);
					try {
						pin = scanner.nextInt();
						userPinValid = service.validateUserPin(accountNo, pin);
					} catch (BankApplicationException e) {
						System.err.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.err.println("Please enter only numberic values.");
					}
				} while (!userPinValid);

				System.out.println("Welcome to your account:");
			//	boolean userAccountFlag = false;
				do {
					System.out.println("Enter your choice:");
					System.out.println("1.Show balance.");
					System.out.println("2.Deposite");
					System.out.println("3.Withdraw");
					System.out.println("4.Fund transfer");
					System.out.println("5.Show transactions");
					System.out.println("6.Delete account");
					System.out.println("7.Exit");
					{
						scanner = new Scanner(System.in);
						try {
							option = scanner.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Please enter a valid option in numeric form.");
						}

					}
					switch (option) {
					case 1: {
						System.out.println("********SHOW BALANCE********");
						System.out.println("Your balance is " + service.showBalance(accountNo));
					}

						break;
					case 2: {
						System.out.println("********DEPOSITE MONEY********");
						boolean amountValid = false;
						double amount = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter amount to deposite:");
							try {
								amount = scanner.nextDouble();
							} catch (InputMismatchException e) {
								System.err.println(e.getMessage());
							}
							if (amount > 499 && amount < 100001)
								amountValid = true;
							else
								System.err.println(
										"You can not deopiste this amount. please enter a value in between rs.1000 and rs.100000.");
						} while (!amountValid);
						if (service.deposite(accountNo, amount)) {
							System.out.println("Money deposited.");
							LocalDateTime now = LocalDateTime.now();
							String time = dtf.format(now);

							Transaction trans = new Transaction(service.createTransactionId(), "Credit",
									service.getCustomerAccountDetails(accountNo).getCustomerId(), accountNo, 0, amount,
									"Cash Deposite", time);
							service.addTransaction(trans);
						}
					}
						break;
					case 3: {
						System.out.println("********WITHDRAW MONEY********");
						pin = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter your pin:");
							try {
								pin = scanner.nextInt();
								userPinValid = service.validateUserPin(accountNo, pin);
							} catch (BankApplicationException e) {
								System.err.println(e.getMessage());
							} catch (InputMismatchException e) {
								System.err.println("Please enter only in numberic form.");
							}
						} while (!userPinValid);
						double newBalance = 0;
						boolean balanceValid = false;
						double amount = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter amount:");

							try {
								amount = scanner.nextDouble();
								if (amount > 99 && amount < 50001) {
									newBalance = service.withdraw(accountNo, amount);
									if (newBalance == -1)
										System.err.println(
												"Your Current Account balance is less than the entered amount.");
									else
										balanceValid = true;
								} else
									System.err.println(
											"The entered amount is incorrec.Please enter values between 100 and 50000.");
							} catch (InputMismatchException e) {
								System.err.println("Please eneter only in numeric format.");
							}
						} while (!balanceValid);
						LocalDateTime now = LocalDateTime.now();
						String time = dtf.format(now);
						Transaction trans = new Transaction(service.createTransactionId(), "Debit",
								service.getCustomerAccountDetails(accountNo).getCustomerId(), accountNo, 0, amount,
								"Withdraw", time);
						service.addTransaction(trans);
						System.out.println("Your updated balance is: " + newBalance);
					}
						break;
					case 4: {
						System.out.println("********FUND TRANSFER********");
						pin = 0;
						userPinValid = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter your pin:");
							try {
								pin = scanner.nextInt();
								userPinValid = service.validateUserPin(accountNo, pin);
							} catch (BankApplicationException e) {
								System.err.println(e.getMessage());
							} catch (InputMismatchException e) {
								System.err.println("Please enter input only in numeirc form.");
							}
						} while (!userPinValid);
						boolean depositeAccountValid = false;
						long accountCredit = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter the account you want to deposite money to:");
							try {
								accountCredit = scanner.nextLong();
								depositeAccountValid = service.validateAccount(accountCredit);
								System.out.println("This account belongs to:"
										+ service.getCustomerAccountDetails(accountCredit).getCustomerName());
							} catch (BankApplicationException e) {
								System.err.println(e.getMessage());
							} catch (InputMismatchException e) {
								System.err.println("Please enter only in numberic format only.");
							}
						} while (!depositeAccountValid);
						boolean amountValid = false;
						double amount = 0;
						do {
							System.out.println("Enter amount:");
							scanner = new Scanner(System.in);

							try {
								amount = scanner.nextInt();
								amountValid = true;
							} catch (InputMismatchException e) {
								System.err.println("Please enter only in numeric form.");
							}
						} while (!accountValid);
						if (service.showBalance(accountNo) >= amount) {
							if (service.fundTransfer(accountNo, accountCredit, amount)) {
								LocalDateTime now = LocalDateTime.now();
								String time = dtf.format(now);
								Transaction trans = new Transaction(service.createTransactionId(), "Fund Transfer",
										service.getCustomerAccountDetails(accountNo).getCustomerId(), accountNo,
										accountCredit, amount, "Fund transfer", time);
								service.addTransaction(trans);
								System.out.println("Fund transfer complete.");
							} else
								System.out.println("Service unavailable try again.");
						} else
							System.out.println("Low balance.");
					}
						break;
					case 5: {
						System.out.println("********SHOW TRANSACTION********");
						userPinValid = false;
						pin = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Please enter your pin:");
							try {
								pin = scanner.nextInt();
								userPinValid = service.validateUserPin(accountNo, pin);
							} catch (BankApplicationException e) {
								System.err.println(e.getMessage());
							} catch (InputMismatchException e) {
								System.err.println("Please enter only in numeric form.");
							}
						} while (!userPinValid);
						List<Transaction> transactions = service.gettransactionsDetails(accountNo);
						if (transactions.isEmpty())
							System.out.println("There are no transactions to show.");
						else {
							System.out.println("*******YOUR TRANSACTIONS*******");
							for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
								Transaction transaction = (Transaction) iterator.next();
								System.out.println(transaction);
							}
						}
					}
						break;
					case 6: {
						System.out.println("********DELETE ACCOUNT********");
						userPinValid = false;
						pin = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter your pin:");

							try {
								pin = scanner.nextInt();
								userPinValid = service.validateUserPin(accountNo, pin);
							} catch (BankApplicationException e) {
								System.err.println(e.getMessage());
							} catch (InputMismatchException e) {
								System.err.println("Please enter only in numeric format.");
							}
						} while (!userPinValid);
						scanner = new Scanner(System.in);
						System.err.println("CONFIRM: Do you really want to delete your account?(yes/no)");
						String confirmation = scanner.next();
						if (confirmation.equalsIgnoreCase("yes"))
							if (service.deleteCustomerAcc(accountNo))
								{System.out.println("Account no:" + accountNo + " has been deleted.");
								option = 7;
					}
					}
						break;
					case 90: {
						BankApplicationDAOImp dao = new BankApplicationDAOImp();
						System.out.println(dao.transactions);
					}
						break;
					case 99: {
						BankApplicationDAOImp dao = new BankApplicationDAOImp();
						System.out.println(dao.accountDetails);
					}
						break;

					default:
						System.out.println("Please enter your options between 1 and 7.");
					}
				} while (option != 7);

			}

			default:
				break;
			}

		} while (true);
	}
}

package app;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import model.Account;
import model.Transaction;
import model.User;

public class SimpleBankingApp {
	public static Vector<User> users = new Vector<User>();
	public static Vector<Account> accounts  = new Vector<Account>();
	public static Vector<Transaction> transactions =  new Vector<Transaction>();
	
	public static void populateUserData() {
		// structure of each record: username (email address), password, first_name, last_name, mobile_number
		
		// in the ideal case (real deployment of the app), we will read from file or database, but let's hard-code for now
		User aUser = new User("mike", "my_passwd", "Mike", "Smith", "07771234567");
		users.add(aUser);
		
		aUser = new User("james.cameron@gmail.com", "angel", "James", "Cameron",  "07777654321");
		users.add(aUser);
		
		aUser = new User("julia.roberts@gmail.com", "change_me",   "Julia", "roberts",   "07770123456");
		users.add(aUser); // --> THE hidden DEFECT!
		
	}
	
	public static void printAllUsers() {
		System.out.println("There are: " + users.size() + " users in the system.");	
		//System.out.println("username (email address) | password, first_name | last_name | mobile_number");
		System.out.println(String.format("%-25s| %-15s| %-15s| %-15s| %-15s", 
				"username", "password", "first_name", "last_name", "mobile_number"));
		System.out.println("-------------------------------------------------------------------------------------------");
		for  (int i = 0; i < users.size(); i++) 
            System.out.println(users.get(i).toString());	
		System.out.println();
	}
	
	public static void populateAccountData()  {
		// structure of each record: 
		// account number, username (email) of account holder, account type (Standard or Saving), account_opening_date

		// in the ideal case, we will read from file or database, but let's hard-code for now
		Account anAccount;
		try {
			anAccount = new Account("5495-1234", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
			accounts.add(anAccount);
			
			anAccount = new Account("5495-1239", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020"));
			accounts.add(anAccount);

			anAccount = new Account("5495-1291", "mike", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019"));
			accounts.add(anAccount);

			anAccount = new Account("5495-6789", "David.McDonald@gmail.com", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
			accounts.add(anAccount);

		} catch (ParseException e) {			
			e.printStackTrace();
		}  
	}
	
	public static void printAllAccounts() {
		System.out.println("There are: " + accounts.size() + " accounts in the system.");
		//System.out.println("Account_number | username_of_account_holder | account_type | account_opening_date");
		System.out.println(String.format("%-10s| %-30s| %-10s| %-15s| %-15s", 
				"Account #", "username_of_account_holder", "type", "opening_date", "Balance"));
		System.out.println("--------------------------------------------------------------------------------");
		for  (int i = 0; i < accounts.size(); i++) 
            System.out.println(accounts.get(i).toString() + "| �" + getBalance(accounts.get(i).getAccount_number()));	
		System.out.println();
	}
	
	public static void addTransaction(String account_number, double amount) { 
		Transaction aTransaction =  new Transaction(account_number, amount, Calendar.getInstance().getTime());
		transactions.add(aTransaction);
	}
	
	
	public static double getBalance(String account_number) {
		double balance = 0.0;
		
		for  (int i = 0; i < transactions.size(); i++) 
            if 	(transactions.get(i).getAccount_number() == account_number) 
            	balance += transactions.get(i).getTransaction_amount();
		
		return balance;		
	}
	
	
	
	public static void main(String[] args) {
		
		populateUserData();
		// let's print them all to see if they have been loaded (populated) properly
		printAllUsers();
		
		populateAccountData();
		// let's print them all to see if they have been loaded (populated) properly
		printAllAccounts();
		
		// do some activities on the populated accounts, add transactions, etc.
		// Deposit: adding a transaction with a positive value
		// Withdraw: adding a transaction with a negative value
		//System.out.println("Before transaction: -50.21");
		addTransaction("5495-1234", -50.21);
		//System.out.println("After transaction: -50.21");
		printAllAccounts();
		
		// and some more activities on the accounts
		//System.out.println("Before transactions: 520.00 and 21.00");
		addTransaction("5495-1234", 520.00);
		addTransaction("9999-1111", 21.00); // it seems this account does not exist in the loaded (populated) data, 
											// but the addTransaction does not do that check, need to improve that function in future
		// let's print the accounts and their balance to see if the above transaction have impacted their balances
		//System.out.println("After transactions: 520.00 and 21.00");
		printAllAccounts();
		// TODO: TALK ABOUT THE TRANSACTION BEING DONE IN GETBALANCE METHOD
		

	}

}

package tests;

import java.time.LocalDate;
import java.util.Date;

import model.Account;
import model.User;
import model.Transaction;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// initial manual testing
		User oldTestUser = new User("mike", "my_passwd", "Mike", "Smith", "0771234567");
		Date date = new Date();
		Account testAccount = new Account("12345678", "mike", "Standard", date);
		Transaction testTransaction = new Transaction("12345678", 50.00, date);
		
		System.out.println(oldTestUser);
		System.out.println(testAccount);
		System.out.println(testTransaction);
		
		// 3.2.2 IF testing
		
		// setup user
		String test_username = "mike";
		String test_password = "my_passwd";
		String test_first_name = "Mike";
		String test_last_name = "Smith";
		String test_mobile_number = "0771234567";
		
		// exercise
		User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);
		
		// verify (assert)
		Boolean passed = true;
		if(testUser.getUsername() != test_username){
			System.out.println("TC1 failed: username did not match");
			passed = false;
		}
		
		if(testUser.getPassword() != test_password) {
			System.out.println("TC2 failed: password did not match");
			passed = false;
		}
		
		if(testUser.getFirst_name() != test_first_name) {
			System.out.println("TC3 failed: first name did not match");
			passed = false;
		}
		
		if(testUser.getLast_name() != test_last_name) {
			System.out.println("TC4 failed: last name did not match");
			passed = false;
		}
		
		if(testUser.getMobile_number() != test_mobile_number) {
			System.out.println("TC5 failed: mobile number did not match");
			passed = false;
		}
		
		if(passed) {
			System.out.println("All TC's passed");
		}
		
		assert testUser.getUsername()== test_username;
		assert testUser.getPassword()== test_password;
		assert testUser.getFirst_name()== test_first_name;
		assert testUser.getLast_name()== test_last_name;
		assert testUser.getMobile_number()== test_mobile_number;
		
		System.out.println("All Java assertions in the test suite passed (none failed).");
	}
}

package tests;

import app.SimpleBankingApp;

public class SimpleBankingAppTest {
	// system under test (SUT):
	static SimpleBankingApp mainApp = new SimpleBankingApp ();

	// this test method (test case) verifies if the data load feature of the class (unit or component) 
	// under test (UUT) works properly
	public static void testDataLoads() {
		// Reminder: the classical Four-Phase test pattern (Setup-Exercise-Verify-Teardown
		// http://xunitpatterns.com/Four%20Phase%20Test.html 
		
		// Setup phase: none
		
		// Exercise phase
		mainApp.populateUserData();

		// Verify phase
		// we see in the load function of the UUT that we have loaded 3 users, so let's verify that
		try {
			assert mainApp.users.size() == 3: "Users array size mismatch";
			System.out.println("testDataLoads: populateUserData: TC1 passed.");
		} catch(AssertionError e) {
			System.out.println("Assertion failed: " + e.getMessage());
		}
		// The above only verification is basic (simple, weak) 
		// To do STRONGER verification, we would need more assertions for user names and account balances, etc.
		
		mainApp.populateAccountData();
		try {
			assert mainApp.accounts.size() == 4 : "Accounts array size mismatch";
			System.out.println("testDataLoads: populateAccountData: TC1 passed.");
		} catch(AssertionError e) {
			System.out.println("Assertion failed: " + e.getMessage());
		}
		
		// Teardown phase: no Teardown is needed for this test case, since we have not made 
		// any changes to the system state in the test case 
	}
	
	// this test method (test case) verifies if the Deposit feature works properly
	public static void testDeposits() {
		// Setup phase
		double balanceBefore = mainApp.getBalance("5495-1234"); 
		double depositAmount = 50.21;
		
		// Exercise phase
		mainApp.addTransaction("5495-1234", depositAmount);
		double balanceAfter = mainApp.getBalance("5495-1234");
		try {
			assert balanceBefore + depositAmount == balanceAfter: "Balance after deposit incorrect";
			System.out.println("testDeposits: TC1 passed.");
		} catch(AssertionError e) {
			System.out.println("Assertion failed:: " + e.getMessage());
		}
		
		// tear-down: put the system state back in where it was
		// read more about the tear-down phase of test cases: http://xunitpatterns.com/Four%20Phase%20Test.html
		mainApp.addTransaction("5495-1234", -depositAmount);
	}

	// this test method (test case) verifies if the Withdraw feature works properly
	public static void testWithdrawals() {
		// setup phase
		double balanceBefore = mainApp.getBalance("5495-6789");
		double withdrawalAmount = -40.31;
		
		// exercise phase
		mainApp.addTransaction("5495-6789", withdrawalAmount);
		double balanceAfter = mainApp.getBalance("5495-6789");
		try {
			assert balanceBefore + withdrawalAmount == balanceAfter: "Balance after withdrawal incorrect";
		} catch(AssertionError e) {
			System.out.println("Assertion failed: " + e.getMessage());
		}
		System.out.println("testWithdrawals: TC1 passed");
		
		// tear-down
		mainApp.addTransaction("5495-6789", -withdrawalAmount);
	}
	
	public static void main(String[] args) {
		testDataLoads();
		testDeposits();
		testWithdrawals();
	}

}

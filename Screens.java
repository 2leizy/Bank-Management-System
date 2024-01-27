

public interface Screens{

	public void homeScreen();

	public void accountManagementScreen();

	public static void managingAccountScreen(Account account) {
		System.out.println("\n===== MANAGING ACCOUNT =====\n");
		System.out.println("Hello " + account.getAccName() + "! What would you like to do?");
		System.out.println("1. Check Balance\n2. Withdraw Cash\n3. Delete Account\n4. Deposit Cash\n5. Transfer money\n6. Exit");
		System.out.print("Choice: ");
	}

	public static void signUpScreen(){
		System.out.println("\n===== SIGNING UP =====\n");
	}
	
}
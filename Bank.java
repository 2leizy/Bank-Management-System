import java.util.Scanner;

public class Bank extends BankManagement implements Screens {

	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Bank bank = new Bank();

		while (true) {

			bank.homeScreen();
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					bank.accountManagementScreen();
					int option = sc.nextInt();
					sc.nextLine();
					switch (option) {
						case 1:
							System.out.print("Enter usernumber: ");
							String userNumber = sc.nextLine();
							bank.manageAccount(userNumber);
							break;
						case 2:
							System.out.println();
							break;
						default:
							System.out.println("Invalid!");
							break;
					}
					break;
				case 2:
					bank.signUp();
					break;
				case 3:
					bank.showAccounts();
					break;
				case 4:
					System.out.println("===== EXITING =====");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Choice!");
			}
		}

	}

	@Override
	public void homeScreen() {

		System.out.println("===== SIMPLE BANKING SYSTEM =====");
		System.out.println("1. Manage Account\n2. Sign-up\n3. Available accounts\n4. Exit");
		System.out.print("Enter choice: ");

	}

	@Override
	public void accountManagementScreen() {
		System.out.println("\n===== ACCOUNT MANAGEMENT =====");
		System.out.print("1. Log-in\n2. Exit\n");
		System.out.print("Choice: ");


	}



}
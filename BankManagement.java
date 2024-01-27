import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class BankManagement {

	private static Scanner sc = new Scanner(System.in);
	public static ArrayList<Account> accounts = new ArrayList<>();
	private final double MAINTAINING_BALANCE = 3000;

	public void transfer(Account targetAccount, double amount) {

	}

	public void showAccounts() {

		System.out.println("===== AVAILABLE ACCOUNTS =====");

		if (!accounts.isEmpty()) {
			for (Account account : accounts) {
				System.out.println("\nUSER INFORMATION:");
				System.out.println("Username: " + account.getAccName());
				System.out.println("Balance: " + account.getBalance());
				System.out.println();
			}
		} else {
			System.out.println("No existing accounts!\n");
		}

	}

	public void signUp() {

		Account account = new Account();
		Screens.signUpScreen();
		System.out.print("Enter Account Name: ");
		String accName = sc.nextLine();
		account.setAccName(accName);
		boolean isCreated = false;

		while (true) {
			try {
				System.out.print("Enter starting balance: ");
				double amount = sc.nextDouble();
				if (amount < 0) {
					throw new IllegalArgumentException("Negative balance are not allowed!");
				} else if (amount < MAINTAINING_BALANCE) {
					throw new IllegalArgumentException("Starting balance must be greater than minimum balance!");
				}
				account.setBalance(amount);
				sc.nextLine();
				isCreated = true;
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String accNum = generateAccNum();
		System.out.println("Your account number is: " + accNum);
		account.setAccNum(accNum);

		accounts.add(account);
		Collections.sort(accounts);

		if (isCreated) {
			System.out.println("\nAccount succesfully created!\n");
		}

	}

	public void manageAccount(String accountNumber) {

		boolean managingAccount = true;
		boolean accountExists = accNumExists(accountNumber);
		Account foundAccount = null;
		int choice;

		if (accountExists) {

			foundAccount = validateAccount(accountNumber);

			while (managingAccount) {

				Screens.managingAccountScreen(foundAccount);
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
					case 1:
						checkBalance(foundAccount);
						break;
					case 2:
						System.out.print("Withdrawal amount: ");
						double withdrawalAmount = sc.nextDouble();
						withdrawCash(foundAccount, withdrawalAmount);
						checkBalance(foundAccount);
						break;
					case 3:
						System.out.print("Delete account? (Y:N): ");
						String confirmation = sc.nextLine().toLowerCase();
						if (confirmation.equals("y")) {
							deleteAccount(foundAccount);
							System.out.println(
									"\nAccount user " + foundAccount.getAccName() + " has been succesfully deleted");
							return;
						} else {
							System.out.println("Account deletion cancelled!");
							break;
						}
					case 4:
						double depositAmount;

						do {
							System.out.print("How much would you like to deposit? : ");
							depositAmount = sc.nextDouble();
							if (depositAmount < 500) {
								System.out.println("Must be a minimum deposit of 500");
							}
						} while (depositAmount < 500);

						deposit(foundAccount, depositAmount);
						checkBalance(foundAccount);
						break;

					case 5:

						break;

					case 6:
						System.out.println("\nFinished Managing Account");
						managingAccount = false;
						break;
					default:
						System.out.println("Invalid choice!");
						break;
				}
			}
		} else {
			System.out.println("Account does not exist!");
		}
	}

	// helper methods

	public double withdrawCash(Account account, double amount) {

		double remainingBalance = account.getBalance() - amount;
		boolean withdrawn = false;

		if (remainingBalance >= MAINTAINING_BALANCE) {
			account.setBalance(remainingBalance);
			withdrawn = true;
			if (withdrawn) {
				System.out.println("Succesfully withdrawn cash!");
			} else {
				System.out.println("Unsuccessful withdrawal");
			}
		} else {
			System.out.println("Balance must not be below maintaining Balance!");
		}

		return remainingBalance;

	}

	public void deleteAccount(Account account) {
		accounts.remove(account);
	}

	public void checkBalance(Account account) {
		System.out.println("\nMaintaining Balance: " + account.getBalance());
	}

	public void deposit(Account account, Double amount) {

		account.setBalance(account.getBalance() + amount);
		System.out.println("Sucessfully deposited " + amount);

	}

	public boolean accNumExists(String accountNumber) {

		for (Account account : accounts) {
			if (account.getAccNum().equals(accountNumber)) {
				return true;
			}
		}
		return false;

	}

	public Account validateAccount(String accountNumber) {

		for (Account account : accounts) {
			if (account.getAccNum().equals(accountNumber)) {
				return account;
			}
		}
		return null;

	}

	public String generateAccNum() {

		long randomNumber = (long) (Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String accountNumber = String.valueOf(randomNumber);
		return accountNumber;

	}

}

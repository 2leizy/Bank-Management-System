

public class Account implements Comparable<Account>{

	private String accName;
	private String accNum;
	private double accBal;

	public Account(){
		this.accName = null;
		this.accNum = null;
		this.accBal = 0;
	}


	public String getAccName(){ 
		return accName; 
	}

	public String getAccNum(){ 
		return accNum; 
	}

	public double getBalance(){ 
		return accBal; 
	}

	public void setAccName(String name) {
		this.accName = name; 
	}

	public void setAccNum(String num){ 
		this.accNum = num; 
	}

	public void setBalance(double balance) {
		this.accBal = balance;
	}


	@Override
	public int compareTo(Account otherAccount) {

		return this.getAccNum().compareTo(otherAccount.getAccNum());
		
	}

}

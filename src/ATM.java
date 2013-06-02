import java.io.Serializable;
import java.util.Random;

/**
 * ATM.java
 *
 * Version: 
 *     $Id: ATM.java,v 1.1 2012-05-16 17:46:19 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: ATM.java,v $
 *     Revision 1.1  2012-05-16 17:46:19  txs6138
 *     Final
 *
 */

/**
 * ATM Class
 * @author Tyler Schoen (txs6138) May 12, 2012 at 9:24:37 PM
 * ATM Model that does the underlying work for the ATM GUI
 */
public class ATM implements Serializable {

	private Bank openBank;
	private int atmId;
	private String atmState;
	private String txtState;
	private Account selectedAccount = null;

	Random randomGenerator = new Random();

	/**
	 * ATM Constructor
	 * @param bank
	 * Takes in a bank model to base the atm off of
	 * so users can log into their accounts on that bank
	 */
	public ATM(Bank bank) {

		this.openBank = bank;
		this.atmId = randomGenerator.nextInt(10000);
		this.checkId();
		this.atmState = "Id";
		this.txtState = "Please enter your account ID number.";

	}

	/**
	 * getBank method
	 * @return bank used to instantiate this ATM
	 */
	public Bank getBank() {

		return this.openBank;
	}

	/**
	 * getId method
	 * @return Id for this ATM
	 */
	public int getId() {

		return this.atmId;
	}

	/**
	 * getState method
	 * @return current state of the ATM model
	 * this is used to keep track of where the ATM GUI should be 
	 * and how the listeners should be acting
	 */
	public String getState() {

		return this.atmState;
	}

	/**
	 * checkId method
	 * Validates the atms ID
	 */
	public void checkId() {
		boolean toggle = true;

		while (toggle) {
			if (openBank.openAtms.contains(this.atmId)) {
				this.atmId = randomGenerator.nextInt(10000);
			} else {
				toggle = false;
				openBank.openAtms.add(atmId);
			}
		}
	}

	/**
	 * getStateString method
	 * @return returns the string of what the label on the gui should say
	 */
	public String getStateString() {

		return this.txtState;
	}

	/**
	 * verifyId method
	 * @param id
	 * Checks to see if the id entered is in the bank
	 */
	public void verifyId(String id) {
		for (Account a : this.openBank.bankAccounts) {
			if (a.getId().equals(id)) {
				this.atmState = "Pin";
				this.txtState = "Please enter your pin number.";
				this.selectedAccount = a;
			}
		}
		if (this.selectedAccount == null) {
			this.txtState = "Invalid account ID, please try again.";
		}
	}

	/**
	 * verifyPin method
	 * @param pin
	 * Checks to see if the pin entered by the users is correct
	 */
	public void verifyPin(String pin) {
		if (this.selectedAccount.getPin().equals(pin)) {
			this.atmState = "Transaction";
			this.txtState = "<html> Please select a transaction.<br />" +
					" 0.Balance 1.Withdrawl 2.Deposit</html>";
		} else {
			this.txtState = "Invalid accont pin, please try again.";
		}
	}

	/**
	 * verifyTransaction method
	 * @param num
	 * Checks to see what transaction the user has entered.
	 */
	public void verifyTransaction(String num) {
		if (num.equals("0")) {
			this.atmState = "Balance";
			this.txtState = "<html>Your account balance is: $"
					+ this.selectedAccount.getBalance()
					+ ".<br /> Press OK to continue.</html>";

		} else if (num.equals("1")) {
			if (this.selectedAccount.getType().equals("CD")) {
				this.txtState = "<html>You cannot withdrawl from a CD Account." +
						"<br /> 0.Balance 1.Withdrawl 2.Deposit</html>";
			} else {
				this.atmState = "Withdrawl";
				this.txtState = "Please enter the amount you wish to withdrawl.";
			}

		} else if (num.equals("2")) {
			this.atmState = "Deposit";
			this.txtState = "Please enter the amount you wish to deposit.";
		}
	}
	
	/**
	 * returnTransaction method
	 * Resets the GUI to the transaction interface state
	 */
	public void returnTransaction() {
		this.atmState = "Transaction";
		this.txtState = "<html> Please select a transaction.<br />" +
				" 0.Balance 1.Withdrawl 2.Deposit</html>";
	}
	
	/**
	 * widthdrawl method
	 * @param amount
	 * Getter method to use the acounts withdrawl method
	 */
	public void withdrawl(int amount) {
		if (this.selectedAccount.withdrawl(amount) == -1) {
			this.txtState = "Invalid amount, press OK to retry.";
		} else {
			this.txtState = "Withdrawl successful! Press OK to continue.";
		}
	}
	
	/**
	 * deposit method
	 * @param amount
	 * Getter method to use the accounts deposit method.
	 */
	public void deposit(int amount) {
		
		this.txtState = "Deposit successful! Press OK to continue.";
		this.selectedAccount.deposit(amount);
	}
	
	/**
	 * returnLogin method
	 * Sets the state of the GUI to the login and logs off the user
	 */
	public void returnLogin() {
		this.atmState = "Id";
		this.txtState = "Please enter your account ID number.";
		this.selectedAccount = null;
	}

}

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Account.java
 *
 * Version: 
 *     $Id: Account.java,v 1.1 2012-05-16 17:46:19 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: Account.java,v $
 *     Revision 1.1  2012-05-16 17:46:19  txs6138
 *     Final
 *
 */

/**
 * Account Abstract Class
 * @author Tyler Schoen (txs6138) May 7, 2012 at 10:43:41 PM
 * Contains all the methods shared by Account subclasses
 */
public abstract class Account implements Serializable {
	final double intRate = 0.05 / 12;
	final double savingIntRate = 0.005 / 12;
	final double cdMinBal = 500.00;
	final double savingMinBal = 200.00;
	final double checkingMinBal = 50.00;
	final double savingPenRate = -0.10;
	final double savingPenFee = 10.00;
	final double checkingPenRate = -0.10;
	final double checkingPenFee = 5.00;

	int accountId;
	int accountPin;
	double accountBalance;
	AccountType accountType;

	/**
	 * Account Constructor
	 * @param id - Account id #
	 * @param pin Account pin #
	 * @param balance - Accounts balance
	 * @param accType - Type of account
	 */
	public Account(int id, int pin, double balance, AccountType accType) {
		this.accountId = id;
		this.accountPin = pin;
		this.accountBalance = balance;
		this.accountType = accType;

	}

	/**
	 * getId method
	 * @return Accounts Id
	 */
	public String getId() {
		String strId = String.valueOf(accountId);

		return strId;
	}

	/**
	 * getBalance method
	 * @return Accounts balance
	 */
	public String getBalance() {
		String strBalance = Double.toString(accountBalance);

		return strBalance;
	}

	/**
	 * applyInerest method
	 * @return String of changes to accounts
	 * Applies one month of interest to all accounts in the bank
	 */
	public String applyInterest() {
		String interest = "";

		DecimalFormat two = new DecimalFormat("#.##");

		double accumulatedInterest = 0;
		double accumulatedPenalty = 0;
		double intBalance = 0;
		double penBalance = 0;

		if (this.accountType == AccountType.CD) {
			accumulatedInterest = this.accountBalance * intRate;
			double newBalance = this.accountBalance + accumulatedInterest;

			this.accountBalance = newBalance;

			interest = this.getId() + "\t$\t"
					+ two.format(accumulatedInterest) + "\t$\t"
					+ two.format(this.accountBalance);
			System.out.println(interest);
			return interest;

		} else if (this.accountType == AccountType.Checking) {

			if (this.accountBalance > checkingMinBal) {

			} else if (this.accountBalance > checkingPenFee) {
				this.accountBalance = this.accountBalance - checkingPenFee;
				accumulatedPenalty = -checkingPenFee;
			} else {
				accumulatedPenalty = this.accountBalance * checkingPenRate;
				penBalance = this.accountBalance + accumulatedPenalty;

				this.accountBalance = penBalance;
			}

			interest = this.getId() + "\t$\t"
					+ String.valueOf(accumulatedPenalty) + "\t$\t"
					+ two.format(this.accountBalance);
			System.out.println(interest);
			return interest;

		} else if (this.accountType == AccountType.Saving) {

			if (this.accountBalance > savingMinBal) {
				accumulatedInterest = this.accountBalance * savingIntRate;
				intBalance = this.accountBalance + accumulatedInterest;

				this.accountBalance = intBalance;
			} else if (this.accountBalance > savingPenFee) {
				this.accountBalance = this.accountBalance - savingPenFee;
				accumulatedInterest = -savingPenFee;
			} else {
				accumulatedInterest = this.accountBalance * savingPenRate;
				penBalance = this.accountBalance + accumulatedInterest;

				this.accountBalance = penBalance;
			}

			interest = this.getId() + "\t$\t"
					+ String.valueOf(accumulatedInterest) + "\t$\t"
					+ two.format(this.accountBalance);
			System.out.println(interest);
			return interest;
		}
		return interest;
	}

	/**
	 * deposit method
	 * @param amount - amount to deposit into the account
	 * @return balance of the account
	 */
	public int deposit(int amount) {
		this.accountBalance += amount;
		return (int) accountBalance;
	}

	/**
	 * withdrawl method
	 * @param amount - amount to withdrawl from the account
	 * @return balance of the account
	 */
	public int withdrawl(int amount) {
		if (this.accountType == AccountType.CD) {
			return -1;
		} else if (amount > this.accountBalance) {
			return -1;
		} else {
			this.accountBalance -= amount;
			return (int) accountBalance;
		}

	}

	/**
	 * getType method
	 * @return Account type
	 */
	public String getType() {
		return accountType.toString();
	}

	/**
	 * getPin method
	 * @return Account pin
	 */
	public String getPin() {
		return String.valueOf(accountPin);
	}

	/**
	 * toString method
	 * @return String representation of the Account
	 */
	public String toString() {
		String strBank = this.getId() + this.getType() + this.getBalance();

		return strBank;
	}
}

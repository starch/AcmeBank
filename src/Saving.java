import java.io.Serializable;

/**
 * Saving.java
 *
 * Version: 
 *     $Id: Saving.java,v 1.1 2012-05-16 17:46:20 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: Saving.java,v $
 *     Revision 1.1  2012-05-16 17:46:20  txs6138
 *     Final
 *
 */

/**
 * Saving Class
 * @author Tyler Schoen (txs6138)
 * May 7, 2012 at 10:44:01 PM
 */
public class Saving extends Account implements Serializable {
	final double minBal = 200.00;
	boolean success = false;

	/**
	 * Saving Constructor
	 * @param id - Account id
	 * @param pin - Account pin
	 * @param balance - Account balance
	 */
	public Saving(int id, int pin, double balance) {
		super(id, pin, balance, AccountType.Saving);
		
		if (balance > minBal) {
			this.success = true;
		} 
		
	}
	
}

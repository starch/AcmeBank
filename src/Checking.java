import java.io.Serializable;

/**
 * Checking.java
 *
 * Version: 
 *     $Id: Checking.java,v 1.1 2012-05-16 17:46:19 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: Checking.java,v $
 *     Revision 1.1  2012-05-16 17:46:19  txs6138
 *     Final
 *
 */

/**
 * Checking Class
 * @author Tyler Schoen (txs6138)
 * May 7, 2012 at 10:43:54 PM
 */
public class Checking extends Account implements Serializable {
	final double minBal = 50.00;
	boolean success = false;
	
	/**
	 * Checking Constructor
	 * @param id - Account id
	 * @param pin - Account pin
	 * @param balance - Account balance
	 */
	public Checking(int id, int pin, double balance) {
		super(id, pin, balance, AccountType.Checking);
		
		if (balance > minBal) {
			this.success = true;
		}
	}
	
}

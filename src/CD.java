import java.io.Serializable;

/**
 * CD.java
 *
 * Version: 
 *     $Id: CD.java,v 1.1 2012-05-16 17:46:20 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: CD.java,v $
 *     Revision 1.1  2012-05-16 17:46:20  txs6138
 *     Final
 *
 */

/**
 * CD Class
 * @author Tyler Schoen (txs6138)
 * May 7, 2012 at 10:44:14 PM
 */
public class CD extends Account implements Serializable {
	final double minBal = 500.00;
	boolean success = false;
	
	/**
	 * CD Constructor
	 * @param id - Account id
	 * @param pin - Account pin
	 * @param balance - Account balance
	 */
	public CD(int id, int pin, double balance) {
		super(id, pin, balance, AccountType.CD);
		
		if (balance > minBal) {
			this.success = true;
		}
		
	}
	
}

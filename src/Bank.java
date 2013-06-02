import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Bank.java
 *
 * Version: 
 *     $Id: Bank.java,v 1.1 2012-05-16 17:46:20 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: Bank.java,v $
 *     Revision 1.1  2012-05-16 17:46:20  txs6138
 *     Final
 *
 */

/**
 * @author Tyler Schoen (txs6138) May 7, 2012 at 9:44:30 PM Bank Class main of
 *         the project
 */
public class Bank implements Serializable {


	File bankFile;
	public ArrayList<Account> bankAccounts;
	public ArrayList<Integer> openAtms;

	/**
	 * Bank Constructor Creates a bank object that manages accounts and lets
	 * the user access and update their own account.
	 * 
	 * @param bankFile
	 *            - file that contains starting bank information, if the file
	 *            is empty or does not exist it creates and starts with no 
	 *            accounts
	 * 
	 * @param batchFile
	 *            (optional) - file that contains input for the bank, makes it
	 *            so there is no required input from the user.
	 * 
	 */
	public Bank(File file) {
		this.bankFile = file;
		this.bankAccounts = new ArrayList<Account>();
		this.openAtms = new ArrayList<Integer>();

	}

	/**
	 * main method
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 *             Takes in at least 1 argument for a bank file to open an
	 *             existing bank or to create a new one and launch a GUI.
	 *             Optionally takes two arguments to laucnh batch mode.
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Bank openBank = null;

		if (args.length < 1 || args.length > 2) {
			System.err.println("Usage: java Bank bankFile [batchFile]");
			System.exit(0);
		} else if (args.length == 1) {
			String argFile = args[0];
			File filename = new File(argFile);
			if (!filename.exists()) {
				try {
					filename.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Bank bank = new Bank(filename);
				BankGUI guiBank = new BankGUI(bank);

				bank.save();

			} else {
				try {
					FileInputStream fileIn = new FileInputStream(filename);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					openBank = (Bank) in.readObject();
					openBank.initString();

					in.close();
					fileIn.close();

					BankGUI guiBank = new BankGUI(openBank);

				} catch (IOException e) {
					e.printStackTrace();
					return;

				} catch (ClassNotFoundException c) {
					c.printStackTrace();
					return;
				}

			}

		} else if (args.length == 2) {
			String argFile = args[0];
			String batchFile = args[1];

			File filename = new File(argFile);
			File batchfilename = new File(batchFile);
			if (!filename.exists()) {
				try {
					filename.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Bank bank = new Bank(filename);
				bank.save();
				Batch batch = new Batch(batchfilename, bank);
				batch.batchParse();

			} else {
				try {
					FileInputStream fileIn = new FileInputStream(filename);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					openBank = (Bank) in.readObject();
					openBank.initString();

					in.close();
					fileIn.close();

					Batch batch = new Batch(batchfilename, openBank);
					batch.batchParse();

				} catch (IOException e) {
					e.printStackTrace();
					return;

				} catch (ClassNotFoundException c) {
					c.printStackTrace();
					return;
				}
			}
		}
	}
	
	/**
	 * Save method
	 * Saves bank state and serializes it
	 */
	public void save() {

		try {
			FileOutputStream fileOut = new FileOutputStream(this.bankFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(this);
			out.close();
			fileOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * closeAcc method
	 * @param a - Account
	 * Removes the account from the list of accounts in the bank 
	 */
	public void closeAcc(Account a) {
		bankAccounts.remove(a);
	}

	/**
	 * initString method
	 * Prints the initial data for the bank when it is opened
	 */
	public void initString() {
		String initBank = "========== Initial Bank Data ==================\n\n"
				+ "Account Type    Account Balance\n"
				+ "------------    ------- -----------";
		System.out.println(initBank);

		for (Account a : this.bankAccounts) {
			System.out.println(a.getType() + "\t\t" + a.getId() + "\t$\t"
					+ a.getBalance() + "\n");
		}
		System.out.println("===============================================\n");
	}

	/**
	 * finalString method
	 * Prints the final data for the bank when it is closed
	 */
	public void finalString() {
		String finalBank = "==========   Final Bank Data ==================\n\n"
				+ "Account Type    Account Balance\n"
				+ "------------    ------- -----------";
		System.out.println(finalBank);

		for (Account a : this.bankAccounts) {
			System.out.println(a.getType() + "\t\t" + a.getId() + "\t$\t"
					+ a.getBalance() + "\n");
		}
		System.out.println("===============================================\n");
	}

	/**
	 * interestString()
	 * Prints the string that represents the changes of a month of interest
	 */
	public void intrestString() {
		String interestHeader = "============== Interest Report ==============\n"
				+ "Account Adjustment      New Balance\n"
				+ "------- -----------     -----------\n";
		System.out.println(interestHeader);

		for (Account a : this.bankAccounts) {
			a.applyInterest();
			System.out.println("\n");
		}

		System.out.println("\n=============================================");
	}
}

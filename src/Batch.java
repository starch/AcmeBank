import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Batch.java
 *
 * Version: 
 *     $Id: Batch.java,v 1.1 2012-05-16 17:46:20 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: Batch.java,v $
 *     Revision 1.1  2012-05-16 17:46:20  txs6138
 *     Final
 *
 */

/**
 * Batch Class
 * @author Tyler Schoen (txs6138) May 15, 2012 at 6:56:05 PM
 * Batch object that opens a batch file which contains commands
 * to run in the bank that will run automatically without a GUI
 */
public class Batch {
	private File batch;
	private Bank openBank;
	private ArrayList<String> commandLines = new ArrayList<String>();
	public DecimalFormat two = new DecimalFormat("#.##");

	/**
	 * Batch Constructor
	 * @param batchFile
	 * @param bank
	 * Takes in a batchFile which contains the commands to be run
	 * Takes in a bank object to run the commands on
	 */
	public Batch(File batchFile, Bank bank) {
		this.batch = batchFile;
		this.openBank = bank;
	}

	/**
	 * BatchParse method
	 * @throws FileNotFoundException
	 * Uses a scanner to scan in the batch file line by line and then
	 * send those lines to the parse method
	 */
	public void batchParse() throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader(batch));
		try {
			while (scanner.hasNextLine()) {
				commandLines.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
			this.parse();
		}
	}

	/**
	 * checkAccounts method
	 * @param id - id we're trying to execute the command on
	 * @param command - command we're trying to execute
	 * @param type - account type we're trying to execute the command on
	 * @return false if the account already exists
	 * @return true if the account doesn't exist
	 * Use this to check and see if an account already exists so we do not
	 * try and create another account with the same id
	 */
	public boolean checkAccounts(String id, String command, String type) {
		for (Account a : openBank.bankAccounts) {
			if (a.getId().equals(id)) {
				System.out.println(id + "\t" + command + "\t" + type
						+ "\tOpen: Failed");
				return false;
			}
		}
		return true;
	}

	/**
	 * parse method
	 * takes lines from batchParse() and goes through them splitting them by" "
	 * saves the split into a String array and then loops throught the array
	 * checking for what command and how to perform it and what to print
	 */
	public void parse() {
		for (String line : commandLines) {
			String[] temp = line.split(" ");
			if (temp[0].equals("o")) {
				if (checkAccounts(temp[2], temp[0], temp[1])) {
					if (temp[1].equals("x")) {
						openBank.bankAccounts.add(new Checking(Integer
								.parseInt(temp[2]), Integer.parseInt(temp[3]),
								Double.valueOf(two.format(Integer
										.parseInt(temp[4])))));
						System.out.println(temp[2]
								+ "\t"
								+ temp[0]
								+ "\t"
								+ temp[1]
								+ "\tOpen: Success\t$\t"
								+ Double.parseDouble(two.format(Integer
										.parseInt(temp[4]))));
					} else if (temp[1].equals("s")) {
						openBank.bankAccounts.add(new Saving(Integer
								.parseInt(temp[2]), Integer.parseInt(temp[3]),
								Double.valueOf(two.format(Integer
										.parseInt(temp[4])))));
						System.out.println(temp[2]
								+ "\t"
								+ temp[0]
								+ "\t"
								+ temp[1]
								+ "\tOpen: Success\t$\t"
								+ Double.parseDouble(two.format(Integer
										.parseInt(temp[4]))));
					} else if (temp[1].equals("c")) {
						if (Integer.parseInt(temp[4]) >= 500) {
							openBank.bankAccounts
									.add(new CD(Integer.parseInt(temp[2]),
											Integer.parseInt(temp[3]), Double
													.valueOf(temp[4])));
							System.out.println(temp[2]
									+ "\t"
									+ temp[0]
									+ "\t"
									+ temp[1]
									+ "\tOpen: Success\t$\t"
									+ Double.parseDouble(two.format(Integer
											.parseInt(temp[4]))));
						} else {
							System.out.println(temp[2] + "\t" + temp[0] + "\t"
									+ temp[1] + "\tOpen: Failed");
						}
					}
				}

			} else if (temp[0].equals("c")) {
				boolean closeCheck = false;
				for (Account a : openBank.bankAccounts) {
					if (a.getId().equals(temp[1])) {
						closeCheck = true;
						System.out.println(temp[1] + "\t" + temp[0]
								+ "\tClosed: Success\t$\t"
								+ two.format(Double.valueOf(a.getBalance())));
						openBank.closeAcc(a);
						break;
					}
				}
				if (closeCheck == false) {
					System.out.println(temp[1] + "\t" + temp[0]
							+ "\tClosed: Failed");
				}
			} else if (temp[0].equals("d")) {
				boolean check = false;
				for (Account a : openBank.bankAccounts) {
					if (a.getId().equals(temp[1])) {
						check = true;
						a.deposit(Integer.parseInt(temp[2]));
						System.out.println(temp[1] + "\t" + temp[0] + "\t\t$\t"
								+ temp[2] + "\t$\t"
								+ two.format(Double.valueOf(a.getBalance())));
					}
				}
				if (check == false) {
					System.out.println(temp[1] + "\t" + temp[0] + "\t\t$\t"
							+ temp[2] + "\tFailed");
				}
			} else if (temp[0].equals("w")) {
				for (Account a : openBank.bankAccounts) {
					if (a.getId().equals(temp[1])) {
						if (a.withdrawl(Integer.parseInt(temp[2])) == -1) {
							System.out.println(temp[1] + "\t" + temp[0]
									+ "\t\t$\t" + temp[2] + "\tFailed");
						} else {
							System.out
									.println(temp[1]
											+ "\t"
											+ temp[0]
											+ "\t\t$\t"
											+ temp[2]
											+ "\t$\t"
											+ two.format(Double.valueOf(a
													.getBalance())));
						}
					}
				}
			} else if (temp[0].equals("a")) {
				openBank.intrestString();
			}
		}
		openBank.save();
		openBank.finalString();
	}
}

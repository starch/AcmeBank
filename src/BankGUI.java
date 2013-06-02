import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * BankGUI.java
 *
 * Version: 
 *     $Id: BankGUI.java,v 1.1 2012-05-16 17:46:21 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: BankGUI.java,v $
 *     Revision 1.1  2012-05-16 17:46:21  txs6138
 *     Final
 *
 */

/**
 * @author Tyler Schoen (txs6138) May 11, 2012 at 7:10:38 PM BankGUI Class
 */
public class BankGUI extends JFrame {

	public Bank openBank;

	String[] columnNames = { "ID", "Account Type", "Balance" };
	String[][] data;
	private JTable accountList;

	private JFrame frame;

	private JPanel listCenter = new JPanel();

	private JButton btnAtm = new JButton("Launch ATM");
	private JButton btnUpdate = new JButton("Update");

	/**
	 * BankGUI Constructor
	 * 
	 * @param bank
	 *            Creates a GUI for the bank model opened
	 */
	public BankGUI(Bank bank) {

		// Set bank
		this.openBank = bank;

		// Set table array size
		data = new String[openBank.bankAccounts.size()][3];

		// Fill data array
		for (Account a : openBank.bankAccounts) {
			data[openBank.bankAccounts.indexOf(a)][0] = a.getId();
			data[openBank.bankAccounts.indexOf(a)][1] = a.getType();
			data[openBank.bankAccounts.indexOf(a)][2] = a.getBalance();
		}

		accountList = new JTable(data, columnNames);

		// Declare JFrame
		frame = new JFrame("Bank Application by Tyler Schoen (txs6138)");

		// Listeners
		atmListener atmListener = new atmListener();
		btnAtm.addActionListener(atmListener);

		UpdateListener updateListener = new UpdateListener();
		btnUpdate.addActionListener(updateListener);

		LayoutManager mainBorder = new BorderLayout();
		frame.setLayout(mainBorder);

		// Padding
		// PADDING
		frame.add(new JPanel(), BorderLayout.WEST);
		frame.add(new JPanel(), BorderLayout.EAST);

		// Top
		JPanel labels = new JPanel();
		frame.add(labels, BorderLayout.NORTH);

		LayoutManager topBorder = new BorderLayout();
		labels.setLayout(topBorder);

		JPanel topCenter = new JPanel();
		labels.add(topCenter, BorderLayout.NORTH);

		LayoutManager topGrid = new GridLayout(1, 3);
		topCenter.setLayout(topGrid);

		topCenter.add(new JPanel());
		topCenter.add(new JLabel("ID"), BorderLayout.CENTER);
		topCenter.add(new JPanel());
		topCenter.add(new JLabel("Type"), BorderLayout.CENTER);
		topCenter.add(new JPanel());
		topCenter.add(new JLabel("Balance"), BorderLayout.CENTER);
		topCenter.add(new JPanel());

		// Center
		JPanel center = new JPanel();
		frame.add(center, BorderLayout.CENTER);

		LayoutManager centerGrid = new GridLayout(1, 1);
		center.setLayout(centerGrid);

		LayoutManager listBorder = new BorderLayout();

		center.add(listCenter);
		listCenter.setLayout(listBorder);

		// List
		listCenter.add(accountList, BorderLayout.CENTER);

		// List Padding
		listCenter.add(new JPanel(), BorderLayout.WEST);
		listCenter.add(new JPanel(), BorderLayout.EAST);

		// Bottom
		JPanel bottom = new JPanel();
		frame.add(bottom, BorderLayout.SOUTH);

		bottom.add(btnAtm);
		bottom.add(btnUpdate);

		// Set frame properties
		frame.setSize(375, 400);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				openBank.save();
				openBank.finalString();
				frame.dispose();
			}
		});
		frame.setVisible(true);
	}

	/**
	 * atmListener Class
	 * 
	 * @author Tyler Schoen (txs6138) May 15, 2012 at 10:53:27 PM Listens for
	 *         button clicks to launch new ATMs
	 */
	class atmListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnAtm)) {
				ATM myAtm = new ATM(openBank);
				new AtmGUI(myAtm);
			}
		}
	}

	/**
	 * updateListener Class
	 * 
	 * @author Tyler Schoen (txs6138) May 15, 2012 at 10:53:59 PM Listens for
	 *         button clicks on the update button to update the table
	 */
	class UpdateListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnUpdate)) {
				for (Account a : openBank.bankAccounts) {
					data[openBank.bankAccounts.indexOf(a)][0] = a.getId();
					data[openBank.bankAccounts.indexOf(a)][1] = a.getType();
					data[openBank.bankAccounts.indexOf(a)][2] = a.getBalance();
				}
				listCenter.remove(accountList);
				JTable newTable = new JTable(data, columnNames);
				listCenter.add(newTable, BorderLayout.CENTER);
				listCenter.revalidate();
			}
		}
	}

}

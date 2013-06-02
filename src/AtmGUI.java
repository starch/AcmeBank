import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.PasswordAuthentication;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * AtmGUI.java
 *
 * Version: 
 *     $Id: AtmGUI.java,v 1.1 2012-05-16 17:46:21 txs6138 Exp $
 * 
 * Revisions: 
 *     $Log: AtmGUI.java,v $
 *     Revision 1.1  2012-05-16 17:46:21  txs6138
 *     Final
 *
 */

/**
 * AtmGUI Class
 * @author Tyler Schoen (txs6138) May 12, 2012 at 9:12:08 PM
 * Creates a GUI representation of the ATM Model
 */
public class AtmGUI extends javax.swing.JFrame {

	private ATM myAtm;
	private String strState;

	// Frames
	JFrame frame;
	JFrame pinFrame;

	// Buttons
	private JButton btn0 = new JButton("0");
	private JButton btn1 = new JButton("1");
	private JButton btn2 = new JButton("2");
	private JButton btn3 = new JButton("3");
	private JButton btn4 = new JButton("4");
	private JButton btn5 = new JButton("5");
	private JButton btn6 = new JButton("6");
	private JButton btn7 = new JButton("7");
	private JButton btn8 = new JButton("8");
	private JButton btn9 = new JButton("9");

	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnClear = new JButton("Clear");
	private JButton btnClose = new JButton("Close");

	// Text Field
	private JLabel txtLabel;
	private JPasswordField txtPin = new JPasswordField();

	public AtmGUI(ATM thisAtm) {

		this.myAtm = thisAtm;
		this.strState = myAtm.getStateString();
		txtLabel = new JLabel(strState, JLabel.CENTER);

		// Declare JFrame
		frame = new JFrame("ATM " + this.myAtm.getId()
				+ " by Tyler Schoen (txs6138)");

		// Action Listeners
		NumberListener numberListener = new NumberListener();
		OkListener okListener = new OkListener();
		CancelListener cancelListener = new CancelListener();
		CloseListener closeListener = new CloseListener();
		
		btn0.addActionListener(numberListener);
		btn1.addActionListener(numberListener);
		btn2.addActionListener(numberListener);
		btn3.addActionListener(numberListener);
		btn4.addActionListener(numberListener);
		btn5.addActionListener(numberListener);
		btn6.addActionListener(numberListener);
		btn7.addActionListener(numberListener);
		btn8.addActionListener(numberListener);
		btn9.addActionListener(numberListener);
		btnClear.addActionListener(numberListener);
		
		btnOk.addActionListener(okListener);
		
		btnCancel.addActionListener(cancelListener);
		
		btnClose.addActionListener(closeListener);

		// Set txtField
		txtPin.setEditable(false);

		LayoutManager frameBorder = new BorderLayout();
		frame.setLayout(frameBorder);

		// Padding
		frame.add(new JPanel(), BorderLayout.WEST);
		frame.add(new JPanel(), BorderLayout.EAST);

		JPanel top = new JPanel();
		frame.add(top, BorderLayout.NORTH);

		LayoutManager topBorder = new BorderLayout();
		top.setLayout(topBorder);

		// Padding
		top.add(new JPanel(), BorderLayout.NORTH);
		top.add(new JPanel(), BorderLayout.SOUTH);
		top.add(new JPanel(), BorderLayout.EAST);
		top.add(new JPanel(), BorderLayout.WEST);

		JPanel topContainer = new JPanel();
		top.add(topContainer, BorderLayout.CENTER);

		LayoutManager topContainerBorder = new BorderLayout();
		topContainer.setLayout(topContainerBorder);

		// Padding
		topContainer.add(new JPanel(), BorderLayout.SOUTH);
		topContainer.add(new JPanel(), BorderLayout.EAST);
		topContainer.add(new JPanel(), BorderLayout.WEST);

		topContainer.add(txtLabel, BorderLayout.NORTH);
		topContainer.add(txtPin, BorderLayout.CENTER);

		JPanel buttonsContainer = new JPanel();
		frame.add(buttonsContainer, BorderLayout.CENTER);

		LayoutManager buttonsBorder = new BorderLayout();
		buttonsContainer.setLayout(buttonsBorder);

		// Padding
		buttonsContainer.add(new JPanel(), BorderLayout.NORTH);
		buttonsContainer.add(new JPanel(), BorderLayout.SOUTH);
		buttonsContainer.add(new JPanel(), BorderLayout.EAST);
		buttonsContainer.add(new JPanel(), BorderLayout.WEST);

		JPanel buttons = new JPanel();
		buttonsContainer.add(buttons, BorderLayout.CENTER);

		LayoutManager buttonsGrid = new GridLayout(4, 3);
		buttons.setLayout(buttonsGrid);

		buttons.add(btn0);
		buttons.add(btn1);
		buttons.add(btn2);
		buttons.add(btn3);
		buttons.add(btn4);
		buttons.add(btn5);
		buttons.add(btn6);
		buttons.add(btn7);
		buttons.add(btn8);
		buttons.add(btn9);
		buttons.add(btnClear);

		JPanel bottom = new JPanel();
		frame.add(bottom, BorderLayout.SOUTH);

		bottom.add(btnOk);
		bottom.add(btnCancel);
		bottom.add(btnClose);

		// Set frame properties
		frame.setSize(335, 400);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * NumberListener Class
	 * @author Tyler Schoen (txs6138)
	 * May 16, 2012 at 12:19:01 PM
	 * Listens for number button clicks and clear button clicks that 
	 * allow the user to input numbers
	 */
	class NumberListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (myAtm.getState().equals("Pin")) {
				txtPin.setEchoChar('*');
			} else {
				txtPin.setEchoChar((char) 0);
			}
			if (e.getSource().equals(btn0)) {
				String newText = txtPin.getText() + "0";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn1)) {
				String newText = txtPin.getText().toString() + "1";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn2)) {
				String newText = txtPin.getText().toString() + "2";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn3)) {
				String newText = txtPin.getText().toString() + "3";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn4)) {
				String newText = txtPin.getText().toString() + "4";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn5)) {
				String newText = txtPin.getText().toString() + "5";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn6)) {
				String newText = txtPin.getText().toString() + "6";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn7)) {
				String newText = txtPin.getText().toString() + "7";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn8)) {
				String newText = txtPin.getText().toString() + "8";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btn9)) {
				String newText = txtPin.getText().toString() + "9";
				txtPin.setText(newText);
			} else if (e.getSource().equals(btnClear)) {
				txtPin.setText("");
			}
		}
	}

	/**
	 * OkListener Class
	 * @author Tyler Schoen (txs6138)
	 * May 16, 2012 at 12:20:01 PM
	 * Checks the state when the ok button is clicked and performs the action
	 * required for that state
	 */
	class OkListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnOk)) {
				if (myAtm.getState().equals("Id")) {
					myAtm.verifyId(txtPin.getText());
					txtLabel.setText(myAtm.getStateString());
					txtPin.setText("");
					
				} else if (myAtm.getState().equals("Pin")) {
					myAtm.verifyPin(txtPin.getText());
					txtLabel.setText(myAtm.getStateString());
					txtPin.setText("");
				
				} else if (myAtm.getState().equals("Transaction")) {
					myAtm.verifyTransaction(txtPin.getText());
					txtLabel.setText(myAtm.getStateString());
					txtPin.setText("");
				} else if (myAtm.getState().equals("Balance")) {
					myAtm.returnTransaction();
					txtLabel.setText(myAtm.getStateString());

				} else if (myAtm.getState().equals("Withdrawl")) {
					if (!txtPin.getText().equals("")) {
						myAtm.withdrawl(Integer.parseInt(txtPin.getText()));
						txtLabel.setText(myAtm.getStateString());
						txtPin.setText("");
						if (txtLabel.toString().equals("Invalid amount," +
								" press OK to retry.")) {
							myAtm.returnTransaction();
							
						} else {
							myAtm.returnTransaction();
						}
					} else {
						txtLabel.setText("Invalid amount, press OK to retry.");
					}


				} else if (myAtm.getState().equals("Deposit")) {
					if (!txtPin.getText().equals("")) {
						myAtm.deposit(Integer.parseInt(txtPin.getText()));
						txtLabel.setText(myAtm.getStateString());
						txtPin.setText("");
						myAtm.returnTransaction();
					} else {
						txtLabel.setText("Invalid amount, press OK to retry.");
					}
				}
			}
		}
	}
	
	/**
	 * CancelListener Class
	 * @author Tyler Schoen (txs6138)
	 * May 16, 2012 at 12:21:33 PM
	 * Listens for when the cancel button is clicked and checks the state
	 * to perform the required action
	 */
	class CancelListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnCancel)) {
				if (myAtm.getState().equals("Pin") || 
						myAtm.getState().equals("Transaction")) {
					myAtm.returnLogin();
					txtLabel.setText(myAtm.getStateString());
				} else if (myAtm.getState().equals("Deposit") || 
						myAtm.getState().equals("Withdrawl")) {
					myAtm.returnTransaction();
					txtLabel.setText(myAtm.getStateString());
				}
			}
		}
	}
	
	/**
	 * CloseListener Class
	 * @author Tyler Schoen (txs6138)
	 * May 16, 2012 at 12:22:27 PM
	 * Listens for when the close button is clicked and closes the AtmGUI
	 */
	class CloseListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnClose)) {
				frame.dispose();
				
			}
		}
	}
}

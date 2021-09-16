package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Expenses;
import com.ims.models.data.ExpensesData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.validation.DecimalKeyListener;

/**
 *
 * @author Girish
 */
public class ExpensesUI extends IFrame {

	private static final long serialVersionUID = -1108814562782327638L;

	private DateButton btnDate;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnSave;
	private javax.swing.JScrollPane jspDesc;
	private javax.swing.JLabel lblDesc;
	private javax.swing.JLabel lblExpAmount;
	private javax.swing.JLabel lblDate;
	private javax.swing.JLabel lblExpenses;
	private javax.swing.JTextArea txtDesc;
	private javax.swing.JTextField txtExpAmount;
	private javax.swing.JTextField txtExpenses;

	private ExpensesUIListen listen;

	public ExpensesUI() {
		super(Program.LANG.getProperty("mniexpenses"), 370, 290);
		initComponents();

		txtExpAmount.addKeyListener(new DecimalKeyListener());
		listen = new ExpensesUIListen();
		btnSave.addActionListener(listen);
		
		//TODO Multilanguage Text
		lblExpenses.setText(Program.LANG.getProperty("lblexpenses"));
		lblExpAmount.setText(Program.LANG.getProperty("lblexpamt"));
		lblDate.setText(Program.LANG.getProperty("lblexpdate"));
		lblDesc.setText(Program.LANG.getProperty("lblexpdesc"));
		
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnSave.setText(Program.LANG.getProperty("btnupdate"));
	}

	private void initComponents() {

		lblExpenses = new javax.swing.JLabel();
		txtExpenses = new javax.swing.JTextField();
		lblExpAmount = new javax.swing.JLabel();
		txtExpAmount = new javax.swing.JTextField();
		lblDesc = new javax.swing.JLabel();
		jspDesc = new javax.swing.JScrollPane();
		txtDesc = new javax.swing.JTextArea();
		btnSave = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		lblDate = new javax.swing.JLabel();
		btnDate = new DateButton();

		panel.setLayout(null);

		lblExpenses.setFont(Statics.NORMAL_LARGE_FONT);
		lblExpenses.setText("Expenses Title");
		panel.add(lblExpenses);
		lblExpenses.setBounds(10, 10, 190, 30);

		txtExpenses.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtExpenses);
		txtExpenses.setBounds(200, 10, 150, 30);

		lblExpAmount.setFont(Statics.NORMAL_LARGE_FONT);
		lblExpAmount.setText("Expenses Amount");
		panel.add(lblExpAmount);
		lblExpAmount.setBounds(10, 50, 190, 30);

		txtExpAmount.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtExpAmount);
		txtExpAmount.setBounds(200, 50, 150, 30);

		lblDesc.setFont(Statics.NORMAL_LARGE_FONT);
		lblDesc.setText("Expenses Description");
		panel.add(lblDesc);
		lblDesc.setBounds(10, 130, 190, 70);

		txtDesc.setColumns(1);
		txtDesc.setFont(Statics.NORMAL_LARGE_FONT);
		txtDesc.setRows(1);
		jspDesc.setViewportView(txtDesc);

		panel.add(jspDesc);
		jspDesc.setBounds(200, 130, 150, 70);

		btnSave.setFont(Statics.NORMAL_LARGE_FONT);
		btnSave.setText("Save");
		panel.add(btnSave);
		btnSave.setBounds(10, 210, 150, 50);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(200, 210, 150, 50);

		lblDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblDate.setText("Date");
		panel.add(lblDate);
		lblDate.setBounds(10, 90, 190, 30);

		btnDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(btnDate);
		btnDate.setBounds(200, 90, 150, 30);
	}

	private boolean validateFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please fill all fields!",
							JOptionPane.ERROR_MESSAGE);
					((JTextField) c).requestFocus();
					return false;
				}
			}
		}
		return !txtDesc.getText().equals("");
	}

	private void resetFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
		txtDesc.setText("");
	}

	private class ExpensesUIListen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSave) {
				if (validateFields()) {
					ExpensesData data = new ExpensesData();
					data.expdate = btnDate.getDate();
					data.expamt = Float.parseFloat(txtExpAmount.getText());
					data.expenses = txtExpenses.getText();
					data.expdesc = txtDesc.getText();
					Expenses exp = new Expenses();
					if (exp.insertExpenses(data) > 0) {
						Statics.showMessage(getContentPane(),
								"Expenses inserted successfully!",
								JOptionPane.INFORMATION_MESSAGE);
						resetFields();
					} else {
						Statics.showMessage(getContentPane(),
								"Expenses not inserted successfully!",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == btnReset) {
				resetFields();
			}
		}
	}
}
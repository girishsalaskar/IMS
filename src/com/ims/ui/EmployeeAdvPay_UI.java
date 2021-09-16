package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.EmployeeAdvPay;
import com.ims.models.EmployeeM;
import com.ims.models.data.EmployeeAdvPay_Data;
import com.ims.models.data.EmployeeM_Data;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;
import com.jdev.girish.ui.validation.AlphaSpaceKeyListener;
import com.jdev.girish.ui.validation.DecimalKeyListener;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;
import com.jdev.girish.ui.validation.NumberKeyListener;

/**
 *
 * @author Girish
 */
public class EmployeeAdvPay_UI extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8545627076028167369L;

	private JButton btnUpdate;
	private JButton btnReset;
	private DateButton dpkPpaidDate;
	private JScrollPane jspEmpPaidList;
	private JLabel lblEmpId;
	private JLabel lblEmpName;
	private JLabel lblMobNo;
	private JLabel lblNewBal;
	private JLabel lblPaidAmt;
	private JLabel lblPaidDate;
	private JLabel lblPrevBal;
	private JTable tblEmpPaidList;
	private JTextField txtEmpId;
	private JSuggestionField txtEmpName;
	private JSuggestionField txtMobNo;
	private JTextField txtNewBal;
	private JTextField txtPaidAmt;
	private JTextField txtPrevBal;
	private DefaultTableModel dtmEmppaidList;

	private ArrayList<String> arrEmpName;
	private ArrayList<String> arrEmpMo;

	private EmployeeAdvPay_UIListen listen;

	public EmployeeAdvPay_UI() {
		super(Program.LANG.getProperty("mniempadvance"), 700, 370);

		listen = new EmployeeAdvPay_UIListen();

		dtmEmppaidList = new DefaultTableModel(new Object[][] {

		}, new String[] { "Prev. Bal.", "Paid Date", "Paid Amount", "Balance" }) {
			private static final long serialVersionUID = -783343024325926438L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};

		arrEmpMo = new ArrayList<String>();
		arrEmpName = new ArrayList<String>();

		initComponents();

		txtEmpName.setSuggestionList(arrEmpName);
		txtMobNo.setSuggestionList(arrEmpMo);

		initList();

		txtEmpName.addSuggestionSelectedListener(listen);
		txtMobNo.addSuggestionSelectedListener(listen);
		txtPaidAmt.addFocusListener(listen);

		btnReset.addActionListener(listen);
		btnUpdate.addActionListener(listen);

		txtEmpName.addKeyListener(new AlphaSpaceKeyListener());
		txtMobNo.addKeyListener(new NumberKeyListener());
		txtPaidAmt.addKeyListener(new DecimalKeyListener());
		txtEmpName.addKeyListener(new LimitedTextKeyListener(60));
		txtMobNo.addKeyListener(new LimitedTextKeyListener(15));
		
		//TODO Multilanguage Strings
		
		lblEmpId.setText(Program.LANG.getProperty("lblempid"));
		lblEmpName.setText(Program.LANG.getProperty("lblempname"));
		lblMobNo.setText(Program.LANG.getProperty("lblcontactno"));
		lblPrevBal.setText(Program.LANG.getProperty("lblempprevbal"));
		lblPaidDate.setText(Program.LANG.getProperty("lblempadvpaydate"));
		lblPaidAmt.setText(Program.LANG.getProperty("lblempadvpaidamt"));
		lblNewBal.setText(Program.LANG.getProperty("lblempadvnewbal"));
		btnUpdate.setText(Program.LANG.getProperty("lblemppaynsave"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
	}

	private void initComponents() {

		lblEmpId = new JLabel();
		txtEmpId = new JTextField();
		lblEmpName = new JLabel();
		txtEmpName = new JSuggestionField();
		lblPrevBal = new JLabel();
		txtPrevBal = new JTextField();
		txtPaidAmt = new JTextField();
		lblPaidAmt = new JLabel();
		lblPaidDate = new JLabel();
		dpkPpaidDate = new DateButton();
		txtNewBal = new JTextField();
		lblNewBal = new JLabel();
		txtMobNo = new JSuggestionField();
		lblMobNo = new JLabel();
		jspEmpPaidList = new JScrollPane();
		tblEmpPaidList = new JTable();
		btnUpdate = new JButton();
		btnReset = new JButton();

		lblEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpId.setText("Employee ID");
		panel.add(lblEmpId);
		lblEmpId.setBounds(10, 20, 170, 30);

		txtEmpId.setEditable(false);
		txtEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpId);
		txtEmpId.setBounds(180, 20, 150, 30);

		lblEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpName.setText("Employee Name");
		panel.add(lblEmpName);
		lblEmpName.setBounds(10, 60, 170, 30);

		txtEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpName);
		txtEmpName.setBounds(180, 60, 150, 30);

		lblPrevBal.setFont(Statics.NORMAL_LARGE_FONT);
		lblPrevBal.setText("Previous Balance");
		panel.add(lblPrevBal);
		lblPrevBal.setBounds(10, 140, 170, 30);

		txtPrevBal.setEditable(false);
		txtPrevBal.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPrevBal);
		txtPrevBal.setBounds(180, 140, 150, 30);

		txtPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPaidAmt);
		txtPaidAmt.setBounds(180, 230, 150, 30);

		lblPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblPaidAmt.setText("Amount Paid");
		panel.add(lblPaidAmt);
		lblPaidAmt.setBounds(10, 230, 170, 30);

		lblPaidDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblPaidDate.setText("Paiment Date");
		panel.add(lblPaidDate);
		lblPaidDate.setBounds(10, 180, 170, 30);

		dpkPpaidDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkPpaidDate);
		dpkPpaidDate.setBounds(180, 180, 150, 40);

		txtNewBal.setEditable(false);
		txtNewBal.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtNewBal);
		txtNewBal.setBounds(180, 270, 150, 30);

		lblNewBal.setFont(Statics.NORMAL_LARGE_FONT);
		lblNewBal.setText("New Balance");
		panel.add(lblNewBal);
		lblNewBal.setBounds(10, 270, 170, 30);

		txtMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtMobNo);
		txtMobNo.setBounds(180, 100, 150, 30);

		lblMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblMobNo.setText("Mobile Number");
		panel.add(lblMobNo);
		lblMobNo.setBounds(10, 100, 170, 30);

		tblEmpPaidList.setModel(dtmEmppaidList);
		tblEmpPaidList.setEditingRow(0);
		jspEmpPaidList.setViewportView(tblEmpPaidList);

		panel.add(jspEmpPaidList);
		jspEmpPaidList.setBounds(340, 20, 350, 320);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Pay & Save");
		panel.add(btnUpdate);
		btnUpdate.setBounds(10, 310, 150, 50);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(180, 310, 150, 50);
	}

	private void initList() {
		arrEmpMo.clear();
		arrEmpName.clear();

		EmployeeM_Data data[] = new EmployeeM().getAllEnabledEmployees();
		if (data != null) {
			for (EmployeeM_Data d : data) {
				arrEmpName.add(d.empname);
				arrEmpMo.add(d.empmo);
			}
		}
	}

	private void initData(long empid) {
		EmployeeAdvPay_Data[] pdata = new EmployeeAdvPay()
				.getPayDetailsByEmployeeId(empid);
		if (pdata != null) {
			for (int i = 0; i < pdata.length; i++) {
				dtmEmppaidList.setRowCount(i + 1);
				tblEmpPaidList.setValueAt(pdata[i].prevbal, i, 0);
				tblEmpPaidList.setValueAt(new SimpleDateFormat("dd-MMM-yyyy")
						.format(pdata[i].paiddate), i, 1);
				tblEmpPaidList.setValueAt(pdata[i].paidamt, i, 2);
				tblEmpPaidList.setValueAt(pdata[i].balanceamt, i, 3);
			}
		}
	}

	private boolean validateFields() {
		if (txtEmpId.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please Select Employee by Using name or mobile number!",
					JOptionPane.ERROR_MESSAGE);
			txtEmpName.requestFocus();
			return false;
		} else if (txtEmpName.getText().equals("")) {
			Statics.showMessage(getContentPane(), "Please Enter Employee Name",
					JOptionPane.ERROR_MESSAGE);
			txtEmpName.requestFocus();
			return false;
		} else if (txtMobNo.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please Enter Employee Mobile Number",
					JOptionPane.ERROR_MESSAGE);
			txtMobNo.requestFocus();
			return false;
		} else if (txtPrevBal.getText().equals("")) {
			return false;
		} else if (txtPaidAmt.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please Enter amount paid to Employee",
					JOptionPane.ERROR_MESSAGE);
			txtPaidAmt.requestFocus();
			return false;
		} else if (txtNewBal.getText().equals("")) {
			return false;
		}
		return true;
	}

	private void resetFields() {
		dtmEmppaidList.setRowCount(0);
		txtEmpId.setText("");
		txtEmpName.setText("");
		txtMobNo.setText("");
		txtPrevBal.setText("");
		txtPaidAmt.setText("");
		txtNewBal.setText("");
	}

	private class EmployeeAdvPay_UIListen implements
			SuggestionSelectedListener, FocusListener, ActionListener {
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			EmployeeM_Data data = null;
			if (e.getSource() == txtEmpName) {
				data = new EmployeeM().getEmployeeByName(txtEmpName.getText());
			} else if (e.getSource() == txtMobNo) {
				data = new EmployeeM().getEmployeeByMobileNumber(txtMobNo
						.getText());
			}
			if (data != null) {
				txtEmpId.setText(data.empid + "");
				txtEmpName.setText(data.empname);
				txtMobNo.setText(data.empmo);
				txtPrevBal.setText(data.acbal + "");

				initData(data.empid);
			}
			txtPaidAmt.setText("");
			txtNewBal.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtPaidAmt) {
				if (!txtPaidAmt.getText().equals("")) {
					if (txtPrevBal.getText().equals("")) {
						Statics.showMessage(getContentPane(),
								"Please firstly select employee!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					int amt = Integer.parseInt(txtPaidAmt.getText());
					int prev = Integer.parseInt(txtPrevBal.getText());
					txtNewBal.setText((amt + prev) + "");
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnUpdate) {
				if (validateFields()) {
					EmployeeAdvPay_Data data = new EmployeeAdvPay_Data();
					data.advid = -1;
					data.empid = Long.parseLong(txtEmpId.getText());
					data.paiddate = dpkPpaidDate.getDate();
					data.prevbal = Integer.parseInt(txtPrevBal.getText());
					data.paidamt = Integer.parseInt(txtPaidAmt.getText());
					data.balanceamt = Integer.parseInt(txtNewBal.getText());
					EmployeeAdvPay pay = new EmployeeAdvPay();
					if (pay.inserPayEntry(data) > 0) {
						Statics.showMessage(getContentPane(),
								"Payment entry made successfully!",
								JOptionPane.INFORMATION_MESSAGE);
						resetFields();
					} else {
						Statics.showMessage(getContentPane(),
								"Payment entry not made successfully!",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == btnReset) {
				resetFields();
			}
		}

		@Override
		public void focusGained(FocusEvent arg0) {
		}
	}
}
package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.EmpPayment;
import com.ims.models.EmployeeAttend;
import com.ims.models.EmployeeM;
import com.ims.models.Firm;
import com.ims.models.data.EmpPayment_Data;
import com.ims.models.data.EmployeeAttend_Data;
import com.ims.models.data.EmployeeM_Data;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;
import com.jdev.girish.ui.validation.DecimalKeyListener;

/**
 *
 * @author Girish
 */
public class EmployeeSalary_UI extends IFrame {

	private static final long serialVersionUID = -481946349492066397L;

	private JButton btnPayNSave;

	private JButton btnReset;
	private JTextField txtEmpId;
	private JComboBox<String> cboSalMonth;
	private JLabel lblAttendDays;
	private JLabel lblDeduct;
	private JLabel lblEmpAdvance;
	private JLabel lblEmpId;
	private JLabel lblEmpName;
	private JLabel lblEmpSalary;
	private JLabel lblMobNo;
	private JLabel lblNetSalary;
	private JLabel lblPaidAmt;
	private JLabel lblSalMonth;
	private JLabel lblSalYear;
	private JLabel lblWorkDays;
	private JLabel lblRemAmt;
	private JLabel lblNewBal;
	private JLabel lblPrevPending;
	private JTextField txtAttendDays;
	private JTextField txtRemAmt;
	private JTextField txtNewBal;
	private JTextField txtDeduct;
	private JTextField txtEmpAdvance;
	private JSuggestionField txtEmpName;
	private JTextField txtEmpSalary;
	private JSuggestionField txtMobNo;
	private JTextField txtNetSalary;
	private JTextField txtPaidAmt;
	private JTextField txtSalYear;
	private JTextField txtWorkDays;
	private JTextField txtDeductFromAdv;
	private JTextField txtPrevPending;
	private JCheckBox chkDeductAdv;
	private JButton btnCalc;
	private EmployeeSalary_UIListen listen;
	private int prevremsal;

	public EmployeeSalary_UI() {
		super(Program.LANG.getProperty("mniempsalpayment"), 780, 440);
		listen = new EmployeeSalary_UIListen();

		initComponents();

		initSuggest();

		cboSalMonth.addItem("--Select--");
		cboSalMonth.addItem("January");
		cboSalMonth.addItem("February");
		cboSalMonth.addItem("March");
		cboSalMonth.addItem("April");
		cboSalMonth.addItem("May");
		cboSalMonth.addItem("June");
		cboSalMonth.addItem("July");
		cboSalMonth.addItem("August");
		cboSalMonth.addItem("September");
		cboSalMonth.addItem("October");
		cboSalMonth.addItem("November");
		cboSalMonth.addItem("December");

		txtRemAmt.setEditable(false);
		txtNewBal.setEditable(false);
		txtSalYear.setText(new java.text.SimpleDateFormat("yyyy")
				.format(new java.util.Date()));
		txtEmpId.setEditable(false);

		txtMobNo.addSuggestionSelectedListener(listen);
		txtEmpName.addSuggestionSelectedListener(listen);
		cboSalMonth.addFocusListener(listen);
		txtPaidAmt.addFocusListener(listen);
		txtDeduct.addFocusListener(listen);
		txtDeductFromAdv.addFocusListener(listen);

		txtPaidAmt.addKeyListener(new DecimalKeyListener());
		btnPayNSave.addActionListener(listen);
		btnCalc.addActionListener(listen);
		btnReset.addActionListener(listen);

		chkDeductAdv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chkdeductadvActionPerformed(evt);
			}
		});
	
		//TODO Multilanguage Text
		lblEmpId.setText(Program.LANG.getProperty("lblempid"));
		lblEmpName.setText(Program.LANG.getProperty("lblempname"));
		lblMobNo.setText(Program.LANG.getProperty("lblcontactno"));
		lblEmpSalary.setText(Program.LANG.getProperty("lblempsal"));
		lblEmpAdvance.setText(Program.LANG.getProperty("lblempprevbal"));
		chkDeductAdv.setText(Program.LANG.getProperty("chkempdeductfromadv"));
		lblRemAmt.setText(Program.LANG.getProperty("lblemprempay"));
		lblNetSalary.setText(Program.LANG.getProperty("lblnetsalary"));
		lblSalYear.setText(Program.LANG.getProperty("lblsalyear"));
		lblSalMonth.setText(Program.LANG.getProperty("lblsalmonth"));
		lblWorkDays.setText(Program.LANG.getProperty("lblworkdays"));
		lblAttendDays.setText(Program.LANG.getProperty("lblattenddays"));
		lblPrevPending.setText(Program.LANG.getProperty("lblprevpendingsal"));
		lblDeduct.setText(Program.LANG.getProperty("lbldeduction"));
		lblPaidAmt.setText(Program.LANG.getProperty("lblpaidamt"));
		lblNewBal.setText(Program.LANG.getProperty("lbltotaladvamt"));
		btnCalc.setText(Program.LANG.getProperty("btncalculate"));
		btnPayNSave.setText(Program.LANG.getProperty("btnpaynsave"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
	}

	// INFO chkDeductAdv Event Method
	private void chkdeductadvActionPerformed(java.awt.event.ActionEvent evt) {
		if (chkDeductAdv.isSelected()) {
			txtDeductFromAdv.setEnabled(true);
			txtDeductFromAdv.setText("");
			txtDeductFromAdv.requestFocus();
		} else {
			txtDeductFromAdv.setEnabled(false);
			txtDeductFromAdv.setText("0");
			btnPayNSave.requestFocus();
		}
	}

	private void initSuggest() {
		EmployeeM_Data[] data = new EmployeeM().getAllEnabledEmployees();
		if (data != null) {
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> mobs = new ArrayList<String>();
			for (int i = 0; i < data.length; i++) {
				// cbempid.addItem(data[i].empid+"");
				names.add(data[i].empname);
				mobs.add(data[i].empmo);
			}
			txtEmpName.setSuggestionList(names);
			txtMobNo.setSuggestionList(mobs);
		}
	}

	private void initComponents() {
		// INFO Init Components

		lblEmpId = new javax.swing.JLabel();
		txtEmpId = new javax.swing.JTextField();
		lblEmpName = new javax.swing.JLabel();
		txtEmpName = new JSuggestionField();
		lblMobNo = new javax.swing.JLabel();
		txtMobNo = new JSuggestionField();
		lblSalMonth = new javax.swing.JLabel();
		cboSalMonth = new javax.swing.JComboBox<>();
		lblSalYear = new javax.swing.JLabel();
		txtSalYear = new javax.swing.JTextField();
		lblWorkDays = new javax.swing.JLabel();
		txtWorkDays = new javax.swing.JTextField();
		lblAttendDays = new javax.swing.JLabel();
		txtAttendDays = new javax.swing.JTextField();
		lblEmpSalary = new javax.swing.JLabel();
		txtEmpSalary = new javax.swing.JTextField();
		lblPaidAmt = new javax.swing.JLabel();
		txtPaidAmt = new javax.swing.JTextField();
		lblNetSalary = new javax.swing.JLabel();
		txtNetSalary = new javax.swing.JTextField();
		lblEmpAdvance = new javax.swing.JLabel();
		txtEmpAdvance = new javax.swing.JTextField();
		lblDeduct = new javax.swing.JLabel();
		txtDeduct = new javax.swing.JTextField();
		txtDeductFromAdv = new javax.swing.JTextField();
		btnPayNSave = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		lblNewBal = new javax.swing.JLabel();
		txtNewBal = new javax.swing.JTextField();
		txtRemAmt = new javax.swing.JTextField();
		lblRemAmt = new javax.swing.JLabel();
		chkDeductAdv = new javax.swing.JCheckBox();
		btnCalc = new javax.swing.JButton();
		lblPrevPending = new javax.swing.JLabel();
		txtPrevPending = new javax.swing.JTextField();

		panel.setLayout(null);

		lblEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpId.setText("Employee ID");
		panel.add(lblEmpId);
		lblEmpId.setBounds(20, 20, 220, 30);

		txtEmpId.setEditable(false);
		txtEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpId);
		txtEmpId.setBounds(240, 20, 150, 30);

		lblEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpName.setText("Employee Name");
		panel.add(lblEmpName);
		lblEmpName.setBounds(20, 60, 220, 30);

		txtEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpName);
		txtEmpName.setBounds(240, 60, 150, 30);

		lblMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblMobNo.setText("Mobile Number");
		panel.add(lblMobNo);
		lblMobNo.setBounds(20, 100, 220, 30);

		txtMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtMobNo);
		txtMobNo.setBounds(240, 100, 150, 30);

		lblSalMonth.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalMonth.setText("Salary Month");
		panel.add(lblSalMonth);
		lblSalMonth.setBounds(400, 60, 220, 30);

		cboSalMonth.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(cboSalMonth);
		cboSalMonth.setBounds(620, 60, 150, 30);

		lblSalYear.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalYear.setText("Salary Year");
		panel.add(lblSalYear);
		lblSalYear.setBounds(400, 20, 220, 30);

		txtSalYear.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtSalYear);
		txtSalYear.setBounds(620, 20, 150, 30);

		lblWorkDays.setFont(Statics.NORMAL_LARGE_FONT);
		lblWorkDays.setText("Working Days");
		panel.add(lblWorkDays);
		lblWorkDays.setBounds(400, 100, 220, 30);

		txtWorkDays.setEditable(false);
		txtWorkDays.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtWorkDays);
		txtWorkDays.setBounds(620, 100, 150, 30);

		lblAttendDays.setFont(Statics.NORMAL_LARGE_FONT);
		lblAttendDays.setText("Attended Days");
		panel.add(lblAttendDays);
		lblAttendDays.setBounds(400, 140, 220, 30);

		txtAttendDays.setEditable(false);
		txtAttendDays.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtAttendDays);
		txtAttendDays.setBounds(620, 140, 150, 30);

		lblEmpSalary.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpSalary.setText("Employee Salary");
		panel.add(lblEmpSalary);
		lblEmpSalary.setBounds(20, 140, 220, 30);

		txtEmpSalary.setEditable(false);
		txtEmpSalary.setFont(Statics.NORMAL_LARGE_FONT);
		txtEmpSalary.setText("0");
		panel.add(txtEmpSalary);
		txtEmpSalary.setBounds(240, 140, 150, 30);

		lblPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblPaidAmt.setText("Paid Amount");
		panel.add(lblPaidAmt);
		lblPaidAmt.setBounds(400, 260, 220, 30);

		txtPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		txtPaidAmt.setText("0");
		panel.add(txtPaidAmt);
		txtPaidAmt.setBounds(620, 260, 150, 30);

		lblNetSalary.setFont(Statics.NORMAL_LARGE_FONT);
		lblNetSalary.setText("Net Salary");
		panel.add(lblNetSalary);
		lblNetSalary.setBounds(20, 300, 220, 30);

		txtNetSalary.setEditable(false);
		txtNetSalary.setFont(Statics.NORMAL_LARGE_FONT);
		txtNetSalary.setText("0");
		panel.add(txtNetSalary);
		txtNetSalary.setBounds(240, 300, 150, 30);

		lblEmpAdvance.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpAdvance.setText("Prev. Total Advance");
		panel.add(lblEmpAdvance);
		lblEmpAdvance.setBounds(20, 180, 220, 30);

		txtEmpAdvance.setEditable(false);
		txtEmpAdvance.setFont(Statics.NORMAL_LARGE_FONT);
		txtEmpAdvance.setText("0");
		panel.add(txtEmpAdvance);
		txtEmpAdvance.setBounds(240, 180, 150, 30);

		lblDeduct.setFont(Statics.NORMAL_LARGE_FONT);
		lblDeduct.setText("Deduction");
		panel.add(lblDeduct);
		lblDeduct.setBounds(400, 220, 220, 30);

		txtDeduct.setFont(Statics.NORMAL_LARGE_FONT);
		txtDeduct.setText("0");
		panel.add(txtDeduct);
		txtDeduct.setBounds(620, 220, 150, 30);

		txtDeductFromAdv.setFont(Statics.NORMAL_LARGE_FONT);
		txtDeductFromAdv.setText("0");
		txtDeductFromAdv.setEnabled(false);
		panel.add(txtDeductFromAdv);
		txtDeductFromAdv.setBounds(240, 220, 150, 30);

		btnPayNSave.setFont(Statics.NORMAL_LARGE_FONT);
		btnPayNSave.setText("Pay and Save");
		panel.add(btnPayNSave);
		btnPayNSave.setBounds(320, 340, 150, 60);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(480, 340, 150, 60);

		lblNewBal.setFont(Statics.NORMAL_LARGE_FONT);
		lblNewBal.setText("Total Advance Amt.");
		panel.add(lblNewBal);
		lblNewBal.setBounds(400, 300, 220, 30);

		txtNewBal.setEditable(false);
		txtNewBal.setFont(Statics.NORMAL_LARGE_FONT);
		txtNewBal.setText("0");
		panel.add(txtNewBal);
		txtNewBal.setBounds(620, 300, 150, 30);

		txtRemAmt.setEditable(false);
		txtRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
		txtRemAmt.setText("0");
		panel.add(txtRemAmt);
		txtRemAmt.setBounds(240, 260, 150, 30);

		lblRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblRemAmt.setText("Remaining Payment");
		panel.add(lblRemAmt);
		lblRemAmt.setBounds(20, 260, 220, 30);

		chkDeductAdv.setFont(Statics.NORMAL_LARGE_FONT);
		chkDeductAdv.setText("Deduction from Adv.");
		panel.add(chkDeductAdv);
		chkDeductAdv.setBounds(10, 220, 230, 30);

		btnCalc.setFont(Statics.NORMAL_LARGE_FONT);
		btnCalc.setText("Calculate");
		panel.add(btnCalc);
		btnCalc.setBounds(160, 340, 150, 60);

		lblPrevPending.setFont(Statics.NORMAL_LARGE_FONT);
		lblPrevPending.setText("Prev. Pending Salary");
		panel.add(lblPrevPending);
		lblPrevPending.setBounds(400, 180, 220, 30);

		txtPrevPending.setEditable(false);
		txtPrevPending.setFont(Statics.NORMAL_LARGE_FONT);
		txtPrevPending.setText("0");
		panel.add(txtPrevPending);
		txtPrevPending.setBounds(620, 180, 150, 30);
	}

	private void initData(Object obj) {
		EmployeeM_Data data = null;
		if (obj == txtEmpName) {
			data = new EmployeeM().getEmployeeByName(txtEmpName.getText());
		} else if (obj == txtMobNo) {
			data = new EmployeeM()
					.getEmployeeByMobileNumber(txtMobNo.getText());
		} else if (obj == txtEmpId) {
			data = new EmployeeM().getEmployeeById(Long.parseLong(txtEmpId
					.getText()));
		}

		if (data != null) {
			// txtEmpId.setSelectedItem(data.empid+"");
			txtEmpId.setText(data.empid + "");
			txtEmpName.setText(data.empname);
			txtMobNo.setText(data.empmo);
			txtEmpSalary.setText(data.empsal + "");
			txtEmpAdvance.setText(data.acbal + "");
			txtPrevPending.setText(data.remsalary + "");
			prevremsal = data.remsalary;
		}
	}

	private void calculateSalary() {
		int netsalary;
		int prevbal;
		int deduct;
		int advdeduct;
		int rempay;
		int prevrem;
		int newadvamt;
		int paidamt;

		txtNetSalary.setText(txtEmpSalary.getText());

		netsalary = Integer.parseInt(txtNetSalary.getText());
		prevbal = Integer.parseInt(txtEmpAdvance.getText());
		deduct = Integer.parseInt(txtDeduct.getText());
		advdeduct = Integer.parseInt(txtDeductFromAdv.getText());
		paidamt = Integer.parseInt(txtPaidAmt.getText());
		prevrem = Integer.parseInt(txtPrevPending.getText());

		netsalary = netsalary - deduct;
		netsalary = netsalary - advdeduct;
		rempay = netsalary - paidamt;
		newadvamt = prevbal - advdeduct;
		prevrem = prevremsal + rempay;

		// txtPrevPending.setText(prevrem+"");
		// txtRemAmt.setText(rempay+"");
		txtRemAmt.setText(prevrem + "");
		// txtPrevPending.setText(rempay+"");
		txtNewBal.setText(newadvamt + "");
		txtNetSalary.setText(netsalary + "");
	}

	private void resetFields() {// INFO Reset Fields
		for (Component c : panel.getComponents()) {
			if ((c instanceof JTextField) && ((JTextField) c).isEnabled()) {
				((JTextField) c).setText("");
			} else if (c instanceof JComboBox<?>) {
				((JComboBox<?>) c).setSelectedIndex(0);
				if (!((JComboBox<?>) c).getSelectedItem().toString()
						.contains("Select"))
					((JComboBox<?>) c).setSelectedIndex(-1);
			}
		}
		txtPaidAmt.setText("0");
		txtDeduct.setText("0");
		txtDeductFromAdv.setText("0");
		txtEmpAdvance.setText("0");
		txtAttendDays.setText("0");
		txtNetSalary.setText("0");
		txtNewBal.setText("0");
		txtRemAmt.setText("0");
	}

	private boolean validateFields() {// INFO Validate Fields
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("")
						&& ((JTextField) c).isEnabled()) {
					Statics.showMessage(getContentPane(),
							"Please enter all details",
							JOptionPane.ERROR_MESSAGE);
					((JTextField) c).requestFocus();
					return false;
				}
			} else if (c instanceof JComboBox<?>)
				if (((JComboBox<?>) c).getSelectedIndex() < 1) {
					Statics.showMessage(getContentPane(),
							"Please select valid option!",
							JOptionPane.ERROR_MESSAGE);
					((JComboBox<?>) c).requestFocus();
					return false;
				}
		}
		return true;
	}

	private class EmployeeSalary_UIListen implements
			SuggestionSelectedListener, FocusListener, ActionListener {
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			initData(e.getSource());
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == cboSalMonth) {
				if (cboSalMonth.getSelectedIndex() > 0) {
					if (txtSalYear.getText().equals("")) {
						Statics.showMessage(getContentPane(),
								"Please Enter Salary Year!",
								JOptionPane.ERROR_MESSAGE);
						txtSalYear.requestFocus();
						cboSalMonth.setSelectedIndex(0);
						return;
					} else if (txtEmpId.getText().equals("")) {
						Statics.showMessage(
								getContentPane(),
								"Please Select Employee by entering employee name or mobile number!",
								JOptionPane.ERROR_MESSAGE);
						txtEmpName.requestFocus();
						cboSalMonth.setSelectedIndex(0);
						return;
					}
					EmpPayment_Data payment=new EmpPayment().getPaymentDetails(Integer.parseInt(txtSalYear.getText()), cboSalMonth.getSelectedIndex(), Long.parseLong(txtEmpId.getText()));
					if(payment!=null)
					{
						Statics.showMessage(getContentPane(),
								"Payment already made for this month and year",
								JOptionPane.ERROR_MESSAGE);
						resetFields();
						return;
					}
					EmployeeAttend_Data[] data = new EmployeeAttend()
							.getMonthWiseAttendance(
									cboSalMonth.getSelectedIndex(),
									Integer.parseInt(txtSalYear.getText()),
									Long.parseLong(txtEmpId.getText()));
					if (data != null) {
						txtWorkDays.setText(data.length + "");
						int presday = 0;
						for (int i = 0; i < data.length; i++)
							if (data[i].status == 1)
								presday++;

						txtAttendDays.setText(presday + "");
						txtNetSalary.setText(txtEmpSalary.getText());
						calculateSalary();
					} else {
						Statics.showMessage(getContentPane(),
								"No attendance found at given month and year!",
								JOptionPane.ERROR_MESSAGE);
						cboSalMonth.requestFocus();
						cboSalMonth.setSelectedIndex(0);
					}
				} else {
					Statics.showMessage(getContentPane(),
							"Please select month for the salary payment!",
							JOptionPane.INFORMATION_MESSAGE);
					cboSalMonth.requestFocus();
					return;
				}
			}

			else if (e.getSource() == txtDeduct) {
				if (txtDeduct.getText().equals("")) {
					Statics.showMessage(
							getContentPane(),
							"Please enter deduction amount. (0 for no deduction",
							JOptionPane.INFORMATION_MESSAGE);
					txtDeduct.requestFocus();
					return;
				}
				calculateSalary();
			}

			else if (e.getSource() == txtDeductFromAdv) {
				if (txtDeductFromAdv.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter deduction from previous advance",
							JOptionPane.INFORMATION_MESSAGE);
					txtDeductFromAdv.requestFocus();
					return;
				}
				calculateSalary();
			}

			else if (e.getSource() == txtPaidAmt) {
				if (txtPaidAmt.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter amount paid to employee.",
							JOptionPane.INFORMATION_MESSAGE);
					txtPaidAmt.requestFocus();
					return;
				}
				calculateSalary();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == txtDeduct) {
				txtDeduct.setText("");
				txtNetSalary.setText(txtEmpSalary.getText());
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnPayNSave) {
				if (validateFields()) {
					EmpPayment_Data data = new EmpPayment_Data();
					data.empid = Long.parseLong(txtEmpId.getText());
					data.salmonth = cboSalMonth.getSelectedIndex();
					data.salyear = Integer.parseInt(txtSalYear.getText());
					data.workingdays = Integer.parseInt(txtWorkDays.getText());
					data.attenddays = Integer.parseInt(txtAttendDays.getText());
					data.empsalary = Integer.parseInt(txtEmpSalary.getText());
					data.netsalary = Integer.parseInt(txtNetSalary.getText());
					data.deduct = Integer.parseInt(txtDeduct.getText());
					data.advdeduct = Integer.parseInt(txtDeductFromAdv
							.getText());
					data.paidamt = Integer.parseInt(txtPaidAmt.getText());
					data.remamt = Integer.parseInt(txtRemAmt.getText());
					data.paydate = new java.util.Date(
							System.currentTimeMillis());

					EmpPayment pay = new EmpPayment();
					int ret = pay.makePaymentEntry(data);
					switch (ret) {
					case -1:
						Statics.showMessage(getContentPane(),
								"Payment already made for this month and year",
								JOptionPane.ERROR_MESSAGE);
						break;

					case -2:
						Statics.showMessage(getContentPane(),
								"Employee Payment not recorded successfully!",
								JOptionPane.ERROR_MESSAGE);
						break;

					case 1:
						Statics.showMessage(getContentPane(),
								"Payment made successfully!",
								JOptionPane.INFORMATION_MESSAGE);
						java.util.HashMap<String, Object> map = new java.util.HashMap<>();
						map.put("firm_id", new Firm().getFirmData().firmid);
						map.put("emp_id", Long.parseLong(txtEmpId.getText()));
						map.put("sal_month", cboSalMonth.getSelectedIndex());
						map.put("sal_year",
								Integer.parseInt(txtSalYear.getText()));
						ReportViewer view = new ReportViewer(
								"salarystate", map,
								pay.getConnectionObj());
						view.showReport();
						resetFields();
						break;
					}
				}
			} else if (e.getSource() == btnCalc) {
				calculateSalary();
			} else if (e.getSource() == btnReset) {
				resetFields();
			}
		}
	}
}
package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.EmployeeM;
import com.ims.models.data.EmployeeM_Data;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class EmployeeResignUI extends IFrame {

	private static final long serialVersionUID = -7487325455017201543L;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnResign;
	private DateButton dpkLeaveDate;
	private javax.swing.JLabel lblEmpAdv;
	private javax.swing.JLabel lblEmpId;
	private javax.swing.JLabel lblEmpName;
	private javax.swing.JLabel lblLeaveDate;
	private javax.swing.JLabel lblMobNo;
	private javax.swing.JLabel lblPayOrReceive;
	private javax.swing.JLabel lblRemPay;
	private javax.swing.JTextField txtEmpAdv;
	private javax.swing.JTextField txtEmpId;
	private JSuggestionField txtEmpName;
	private JSuggestionField txtMobNo;
	private javax.swing.JTextField txtPayOrReceive;
	private javax.swing.JTextField txtRemPay;

	private EmployeeResignUIListen listen;
	private ArrayList<String> arrEmpName;
	private ArrayList<String> arrMobNo;

	public EmployeeResignUI() {
		super(Program.LANG.getProperty("mniempsettle"), 340, 370);
		initComponents();

		arrEmpName = new ArrayList<String>();
		arrMobNo = new ArrayList<String>();
		txtEmpName.setSuggestionList(arrEmpName);
		txtMobNo.setSuggestionList(arrMobNo);

		initEmployeeData();

		listen = new EmployeeResignUIListen();
		btnResign.addActionListener(listen);
		btnReset.addActionListener(listen);
		txtEmpName.addSuggestionSelectedListener(listen);
		txtMobNo.addSuggestionSelectedListener(listen);
		
		//TODO Multilanguage Text
		lblEmpId.setText(Program.LANG.getProperty("lblempid"));
		lblEmpName.setText(Program.LANG.getProperty("lblempname"));
		lblMobNo.setText(Program.LANG.getProperty("lblcontactno"));
		lblRemPay.setText(Program.LANG.getProperty("lblemprempay"));
		lblPayOrReceive.setText(Program.LANG.getProperty("lblemppayorreceive"));
		lblEmpAdv.setText(Program.LANG.getProperty("lbltotaladvamt"));
		lblLeaveDate.setText(Program.LANG.getProperty("lblleavedate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnResign.setText(Program.LANG.getProperty("btnresign"));
	}

	private void initEmployeeData() {
		arrEmpName.clear();
		arrMobNo.clear();
		EmployeeM_Data[] data = new EmployeeM().getAllEnabledEmployees();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				arrEmpName.add(data[i].empname);
				arrMobNo.add(data[i].empmo);
			}
		}
	}

	private void initComponents() {

		txtEmpId = new javax.swing.JTextField();
		txtEmpName = new JSuggestionField();
		txtMobNo = new JSuggestionField();
		lblMobNo = new javax.swing.JLabel();
		lblEmpName = new javax.swing.JLabel();
		lblEmpId = new javax.swing.JLabel();
		lblRemPay = new javax.swing.JLabel();
		txtRemPay = new javax.swing.JTextField();
		lblEmpAdv = new javax.swing.JLabel();
		txtEmpAdv = new javax.swing.JTextField();
		lblPayOrReceive = new javax.swing.JLabel();
		txtPayOrReceive = new javax.swing.JTextField();
		lblLeaveDate = new javax.swing.JLabel();
		dpkLeaveDate = new DateButton();
		btnResign = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();

		panel.setLayout(null);

		txtEmpId.setEditable(false);
		txtEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpId);
		txtEmpId.setBounds(180, 10, 150, 30);

		txtEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpName);
		txtEmpName.setBounds(180, 50, 150, 30);

		txtMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtMobNo);
		txtMobNo.setBounds(180, 90, 150, 30);

		lblMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblMobNo.setText("Mobile Number");
		panel.add(lblMobNo);
		lblMobNo.setBounds(10, 90, 170, 30);

		lblEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpName.setText("Employee Name");
		panel.add(lblEmpName);
		lblEmpName.setBounds(10, 50, 170, 30);

		lblEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpId.setText("Employee ID");
		panel.add(lblEmpId);
		lblEmpId.setBounds(10, 10, 170, 30);

		lblRemPay.setFont(Statics.NORMAL_LARGE_FONT);
		lblRemPay.setText("Remaining Payment");
		panel.add(lblRemPay);
		lblRemPay.setBounds(10, 130, 170, 30);

		txtRemPay.setEditable(false);
		txtRemPay.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtRemPay);
		txtRemPay.setBounds(180, 130, 150, 30);

		lblEmpAdv.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpAdv.setText("Employee Advance");
		panel.add(lblEmpAdv);
		lblEmpAdv.setBounds(10, 170, 170, 30);

		txtEmpAdv.setEditable(false);
		txtEmpAdv.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpAdv);
		txtEmpAdv.setBounds(180, 170, 150, 30);

		lblPayOrReceive.setFont(Statics.NORMAL_LARGE_FONT);
		lblPayOrReceive.setText("Pay/Receive Rs.");
		panel.add(lblPayOrReceive);
		lblPayOrReceive.setBounds(10, 210, 170, 30);

		txtPayOrReceive.setEditable(false);
		txtPayOrReceive.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPayOrReceive);
		txtPayOrReceive.setBounds(180, 210, 150, 30);

		lblLeaveDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblLeaveDate.setText("Leave Date");
		panel.add(lblLeaveDate);
		lblLeaveDate.setBounds(10, 250, 170, 30);

		dpkLeaveDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkLeaveDate);
		dpkLeaveDate.setBounds(180, 250, 150, 30);

		btnResign.setFont(Statics.NORMAL_LARGE_FONT);
		btnResign.setText("Resign & Save");
		panel.add(btnResign);
		btnResign.setBounds(10, 290, 160, 60);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(170, 290, 160, 60);
	}

	private class EmployeeResignUIListen implements ActionListener,
			SuggestionSelectedListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				Statics.resetFields(panel.getComponents());
			} else if (e.getSource() == btnResign) {
				if (txtEmpId.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please select employee for settlement!",
							JOptionPane.ERROR_MESSAGE);
					txtEmpName.requestFocus();
					return;
				}
				if (JOptionPane
						.showConfirmDialog(
								getContentPane(),
								"Are you sure you want to settle employee account and remove employee details?"
										+ "\nAfter settlement, the employee details will not be longer available for use.",
								Program.APP.getProperty("APPTITLE"),
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					EmployeeM m = new EmployeeM();
					int ret = m.settleAccount(
							Long.parseLong(txtEmpId.getText()),
							dpkLeaveDate.getDate());
					if (ret > 0) {
						Statics.showMessage(
								getContentPane(),
								"Employee account settled and employee has been removed!",
								JOptionPane.INFORMATION_MESSAGE);
						Statics.resetFields(panel.getComponents());
						initEmployeeData();
						return;
					} else {
						Statics.showMessage(
								getContentPane(),
								"Employee account is not settled successfully!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			EmployeeM_Data data = null;
			if (e.getSource() == txtEmpName)
				data = new EmployeeM().getEmployeeByName(txtEmpName.getText());
			else if (e.getSource() == txtMobNo)
				data = new EmployeeM().getEmployeeByMobileNumber(txtMobNo
						.getText());
			if (data != null) {
				txtEmpId.setText(data.empid + "");
				txtEmpName.setText(data.empname);
				txtMobNo.setText(data.empmo);
				txtEmpAdv.setText(data.acbal + "");
				txtRemPay.setText(data.remsalary + "");

				if (data.acbal - data.remsalary > 0) {
					lblPayOrReceive.setText("Receive Rs.");
					txtPayOrReceive.setText((data.acbal - data.remsalary) + "");
				} else if (data.acbal - data.remsalary < 0) {
					lblPayOrReceive.setText("Pay Rs.");
					txtPayOrReceive.setText((0 - (data.acbal - data.remsalary))
							+ "");
				} else {
					lblPayOrReceive.setText("Account Setteled!");
					txtPayOrReceive.setText((data.acbal - data.remsalary) + "");
				}
			}
		}
	}
}
package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.EmployeeM;
import com.ims.models.Firm;
import com.ims.models.IMSDB;
import com.ims.models.data.EmployeeM_Data;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class EmpSalSleep extends IFrame {

	private static final long serialVersionUID = -2270389472000970025L;
	private JButton btnGetSleep;
	private JButton btnReset;
	private JComboBox<String> cboSalMonth;
	private JLabel lblEmpId;
	private JLabel lblEmpName;
	private JLabel lblMobNo;
	private JLabel lblSalMonth;
	private JLabel lblSalYear;
	private JTextField txtEmpId;
	private JSuggestionField txtEmpName;
	private JSuggestionField txtMobNo;
	private JTextField txtSalYear;
	private EmpSalSleep_Listen listen;

	public EmpSalSleep() {
		super(Program.LANG.getProperty("mniempsalslip"), 340, 295);
		listen = new EmpSalSleep_Listen();
		initComponents();

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

		initSuggest();

		txtEmpName.addSuggestionSelectedListener(listen);
		btnReset.addActionListener(listen);
		btnGetSleep.addActionListener(listen);

		//TODO Multilanguage Text
		lblEmpId.setText(Program.LANG.getProperty("lblempid"));
		lblEmpName.setText(Program.LANG.getProperty("lblempname"));
		lblMobNo.setText(Program.LANG.getProperty("lblcontactno"));
		lblSalMonth.setText(Program.LANG.getProperty("lblsalmonth"));
		lblSalYear.setText(Program.LANG.getProperty("lblsalyear"));
		btnGetSleep.setText(Program.LANG.getProperty("btngenerate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
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
			txtEmpId.setText(data.empid + "");
			txtEmpName.setText(data.empname);
			txtMobNo.setText(data.empmo);
		}
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

	private void initComponents() {

		lblEmpId = new JLabel();
		lblEmpName = new JLabel();
		lblMobNo = new JLabel();
		txtMobNo = new JSuggestionField();
		txtEmpName = new JSuggestionField();
		txtEmpId = new JTextField();
		txtSalYear = new JTextField();
		cboSalMonth = new JComboBox<String>();
		lblSalYear = new JLabel();
		lblSalMonth = new JLabel();
		btnGetSleep = new JButton();
		btnReset = new JButton();

		lblEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpId.setText("Employee ID");
		panel.add(lblEmpId);
		lblEmpId.setBounds(20, 20, 150, 30);

		lblEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpName.setText("Employee Name");
		panel.add(lblEmpName);
		lblEmpName.setBounds(20, 60, 150, 30);

		lblMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblMobNo.setText("Mobile Number");
		panel.add(lblMobNo);
		lblMobNo.setBounds(20, 100, 150, 30);

		txtMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtMobNo);
		txtMobNo.setBounds(170, 100, 140, 30);

		txtEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpName);
		txtEmpName.setBounds(170, 60, 140, 30);

		txtEmpId.setEditable(false);
		txtEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpId);
		txtEmpId.setBounds(170, 20, 140, 30);

		txtSalYear.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtSalYear);
		txtSalYear.setBounds(170, 140, 140, 30);

		cboSalMonth.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(cboSalMonth);
		cboSalMonth.setBounds(170, 180, 140, 30);

		lblSalYear.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalYear.setText("Salary Year");
		panel.add(lblSalYear);
		lblSalYear.setBounds(20, 140, 150, 30);

		lblSalMonth.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalMonth.setText("Salary Month");
		panel.add(lblSalMonth);
		lblSalMonth.setBounds(20, 180, 150, 30);

		btnGetSleep.setText("Generate");
		btnGetSleep.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(btnGetSleep);
		btnGetSleep.setBounds(20, 220, 140, 50);

		btnReset.setText("Reset");
		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(btnReset);
		btnReset.setBounds(170, 220, 140, 50);
	}

	private class EmpSalSleep_Listen implements SuggestionSelectedListener,
			ActionListener {
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent arg0) {
			initData(arg0.getSource());
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnReset) {
				resetFields();
			}

			else if (e.getSource() == btnGetSleep) {
				if (validateFields()) {
					java.util.HashMap<String, Object> map = new java.util.HashMap<>();
					map.put("firm_id", new Firm().getFirmData().firmid);
					map.put("emp_id", Long.parseLong(txtEmpId.getText()));
					map.put("sal_month", cboSalMonth.getSelectedIndex());
					map.put("sal_year", Integer.parseInt(txtSalYear.getText()));
					ReportViewer view = new ReportViewer("salarystate",
							map, new IMSDB().getConnectionObj());
					view.showReport();
				}
			}
		}
	}
}
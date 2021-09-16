package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;

/**
 *
 * @author Girish
 */
public class EmpSalReport extends IFrame {

	private javax.swing.JButton btnReset;

	private javax.swing.JButton btnShow;

	private javax.swing.JComboBox<String> cboSalMonth;
	private javax.swing.JLabel lblSalMonth;
	private javax.swing.JLabel lblSalYear;
	private javax.swing.JTextField txtSalYear;

	private EmpSalReportUIListen listen;

	public EmpSalReport() {
		super(Program.LANG.getProperty("mniempsalreport"), 320, 170);
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

		listen = new EmpSalReportUIListen();
		btnReset.addActionListener(listen);
		btnShow.addActionListener(listen);
		
		//TODO Multilanguage Text
		lblSalMonth.setText(Program.LANG.getProperty("lblsalmonth"));
		lblSalYear.setText(Program.LANG.getProperty("lblsalyear"));
		btnShow.setText(Program.LANG.getProperty("btnshow"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
	}

	private void initComponents() {

		cboSalMonth = new javax.swing.JComboBox<String>();
		txtSalYear = new javax.swing.JTextField();
		lblSalYear = new javax.swing.JLabel();
		lblSalMonth = new javax.swing.JLabel();
		btnShow = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();

		panel.setLayout(null);

		cboSalMonth.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(cboSalMonth);
		cboSalMonth.setBounds(160, 50, 140, 30);

		txtSalYear.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtSalYear);
		txtSalYear.setBounds(160, 10, 140, 30);

		lblSalYear.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalYear.setText("Salary Year");
		panel.add(lblSalYear);
		lblSalYear.setBounds(10, 10, 150, 30);

		lblSalMonth.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalMonth.setText("Salary Month");
		panel.add(lblSalMonth);
		lblSalMonth.setBounds(10, 50, 150, 30);

		btnShow.setText("Show");
		btnShow.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(btnShow);
		btnShow.setBounds(10, 90, 140, 50);

		btnReset.setText("Reset");
		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(btnReset);
		btnReset.setBounds(160, 90, 140, 50);
	}

	private class EmpSalReportUIListen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				txtSalYear.setText("");
				cboSalMonth.setSelectedIndex(0);
			} else if (e.getSource() == btnShow) {
				if (txtSalYear.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter salary year!",
							JOptionPane.ERROR_MESSAGE);
					txtSalYear.requestFocus();
					return;
				}
				int year = Integer.parseInt(txtSalYear.getText());
				int month = cboSalMonth.getSelectedIndex();

				Firm firm = new Firm();
				HashMap map = new HashMap();
				map.put("prmsalmonth", month);
				map.put("prmsalyear", year);
				map.put("prmfirmid", firm.getFirmData().firmid);
				ReportViewer view = new ReportViewer("salaryreport.jrxml", map,
						firm.getConnectionObj());
				view.showReport();
			}
		}
	}
}
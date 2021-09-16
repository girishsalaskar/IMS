package com.ims.ui;

import java.util.HashMap;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;

/**
 *
 * @author Girish
 */
public class ExpensesReportUI extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621824642129337530L;

	public ExpensesReportUI() {
		super(Program.LANG.getProperty("mniexpensesreport"), 410, 310);
		initComponents();
		
		lblStartDate.setText(Program.LANG.getProperty("lblstartdate"));
		lblEndDate.setText(Program.LANG.getProperty("lblenddate"));
		btnShowDate.setText(Program.LANG.getProperty("btnshow"));
	}

	private void initComponents() {

		lblStartDate = new javax.swing.JLabel();
		dpkStartDate = new DateButton();
		btnShowDate = new javax.swing.JButton();
		lblEndDate = new javax.swing.JLabel();
		dpkEndDate = new DateButton();

		FormListener formListener = new FormListener();

		panel.setLayout(null);

		lblStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblStartDate.setText("Starting Date");
		lblStartDate.setName("lblStartDate"); // NOI18N
		panel.add(lblStartDate);
		lblStartDate.setBounds(20, 20, 180, 40);

		dpkStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkStartDate.setName("dpkStartDate"); // NOI18N
		panel.add(dpkStartDate);
		dpkStartDate.setBounds(200, 20, 170, 40);

		btnShowDate.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowDate.setText("Show");
		btnShowDate.setName("btnShowDate"); // NOI18N
		btnShowDate.addActionListener(formListener);
		panel.add(btnShowDate);
		btnShowDate.setBounds(110, 190, 170, 60);

		lblEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndDate.setText("Ending Date");
		lblEndDate.setName("lblEndDate"); // NOI18N
		panel.add(lblEndDate);
		lblEndDate.setBounds(20, 100, 180, 40);

		dpkEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkEndDate.setName("dpkEndDate"); // NOI18N
		panel.add(dpkEndDate);
		dpkEndDate.setBounds(200, 100, 170, 40);

	}

	private class FormListener implements java.awt.event.ActionListener {
		FormListener() {
		}

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			if (evt.getSource() == btnShowDate) {
				ExpensesReportUI.this.btnShowDateActionPerformed(evt);
			}
		}
	}

	private void btnShowDateActionPerformed(java.awt.event.ActionEvent evt) {
		HashMap<String, Object> map = new HashMap<>();
		Firm firm = new Firm();
		map.put("prmstartdate", dpkStartDate.getDate());
		map.put("prmenddate", dpkEndDate.getDate());
		map.put("prmfirmid", firm.getFirmData().firmid);
		ReportViewer view = new ReportViewer("expenses",
				map, firm.getConnectionObj());
		view.showReport();
	}

	private javax.swing.JButton btnShowDate;
	private DateButton dpkEndDate;
	private DateButton dpkStartDate;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblStartDate;
}

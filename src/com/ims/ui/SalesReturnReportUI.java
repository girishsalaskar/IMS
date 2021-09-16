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
public class SalesReturnReportUI extends IFrame {

	private static final long serialVersionUID = -2833354527895453523L;
	private javax.swing.JButton btnShowDate;
	private DateButton dpkEndDate;
	private DateButton dpkStartDate;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblStartDate;

	public SalesReturnReportUI() {
		super(Program.LANG.getProperty("mnisalesreturnrpt"), 350, 200);
		initComponents();

		//TODO Multilanguage Text
		lblStartDate.setText(Program.LANG.getProperty("lblstartdate"));
		lblEndDate.setText(Program.LANG.getProperty("lblenddate"));
		btnShowDate.setText(Program.LANG.getProperty("btnshow"));
	}

	private void initComponents() {

		lblStartDate = new javax.swing.JLabel();
		lblEndDate = new javax.swing.JLabel();
		btnShowDate = new javax.swing.JButton();
		dpkStartDate = new DateButton();
		dpkEndDate = new DateButton();

		FormListener formListener = new FormListener();

		panel.setLayout(null);

		lblStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblStartDate.setText("Start Date");
		lblStartDate.setName("lblStartDate"); // NOI18N
		panel.add(lblStartDate);
		lblStartDate.setBounds(10, 10, 160, 30);

		lblEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndDate.setText("End Date");
		lblEndDate
				.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		lblEndDate.setName("lblEndDate"); // NOI18N
		panel.add(lblEndDate);
		lblEndDate.setBounds(10, 50, 160, 30);

		btnShowDate.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowDate.setText("Show");
		btnShowDate.setName("btnShowDate"); // NOI18N
		btnShowDate.addActionListener(formListener);
		panel.add(btnShowDate);
		btnShowDate.setBounds(70, 100, 170, 50);

		dpkStartDate.setName("dpkStartDate"); // NOI18N
		dpkStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkStartDate);
		dpkStartDate.setBounds(170, 10, 150, 30);

		dpkEndDate.setName("dpkEndDate"); // NOI18N
		dpkEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkEndDate);
		dpkEndDate.setBounds(170, 50, 150, 30);
	}

	private class FormListener implements java.awt.event.ActionListener {
		FormListener() {
		}

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			if (evt.getSource() == btnShowDate) {
				SalesReturnReportUI.this.btnShowActionPerformed(evt);
			}
		}
	}

	private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {
		Firm firm=new Firm();
		HashMap<String, Object> map=new HashMap<>();
		map.put("prmstartdate", dpkStartDate.getDate());
		map.put("prmenddate", dpkEndDate.getDate());
		map.put("prmfirmid", firm.getFirmData().firmid);
		ReportViewer view=new ReportViewer("salesreturnlist", map, firm.getConnectionObj());
		view.showReport();
	}
}
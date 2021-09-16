package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ShopStatus extends IFrame {

	private static final long serialVersionUID = 4928354773541134978L;

	private javax.swing.JButton btnShowDate;

	private DateButton dpkEndDate;

	private DateButton dpkStartDate;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblStartDate;

	public ShopStatus() {
		super(Program.LANG.getProperty("mnishopstat"), 400, 300);
		initComponents();
		btnShowDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showReport();
			}
		});
		
		//TODO Multilanguage Text
		lblEndDate.setText(Program.LANG.getProperty("lblenddate"));
		lblStartDate.setText(Program.LANG.getProperty("lblstartdate"));
		btnShowDate.setText(Program.LANG.getProperty("btnshow"));
	}

	private void showReport() {
		HashMap map = new HashMap();
		map.put("prmenddate", dpkEndDate.getDate());
		map.put("prmstartdate", dpkStartDate.getDate());
		Firm firm = new Firm();
		map.put("prmfirmid", firm.getFirmData().firmid);
		ReportViewer view = new ReportViewer("shopstatus.jrxml", map,
				firm.getConnectionObj());
		view.showReport();
	}

	private void initComponents() {

		dpkStartDate = new DateButton();
		dpkEndDate = new DateButton();
		btnShowDate = new javax.swing.JButton();
		lblEndDate = new javax.swing.JLabel();
		lblStartDate = new javax.swing.JLabel();

		panel.setLayout(null);

		dpkStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkStartDate);
		dpkStartDate.setBounds(200, 20, 170, 40);

		dpkEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkEndDate);
		dpkEndDate.setBounds(200, 100, 170, 40);

		btnShowDate.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowDate.setText("Show");
		panel.add(btnShowDate);
		btnShowDate.setBounds(110, 190, 170, 60);

		lblEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndDate.setText("Ending Date");
		panel.add(lblEndDate);
		lblEndDate.setBounds(20, 100, 180, 40);

		lblStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblStartDate.setText("Starting Date");
		panel.add(lblStartDate);
		lblStartDate.setBounds(20, 20, 180, 40);
	}
}
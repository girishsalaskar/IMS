package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.SupplierPayment;
import com.ims.models.Suppliers;
import com.ims.models.data.SupplierPayData;
import com.ims.models.data.SuppliersData;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class SupPayReportUI extends IFrame {

	private static final long serialVersionUID = 3580174460739364392L;

	private javax.swing.JComboBox<String> cboPayId;

	private javax.swing.JButton btnShow;
	private DateButton dpkPayDate;

	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblPayDate;
	private javax.swing.JLabel lblPayId;
	private JSuggestionField txtFullName;

	private SupPayReportUIListen listen;
	private ArrayList<String> arrFullName;

	public SupPayReportUI() {
		super(Program.LANG.getProperty("mnisuppaymentrpt"), 410, 200);
		initComponents();
		arrFullName = new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);

		listen = new SupPayReportUIListen();
		dpkPayDate.addPropertyChangeListener("date", listen);
		txtFullName.addSuggestionSelectedListener(listen);
		btnShow.addActionListener(listen);

		initDatewiseNameList();
		
		//TODO Multilanguage Text
		lblPayDate.setText(Program.LANG.getProperty("lblsuppaydate"));
		lblFullName.setText(Program.LANG.getProperty("lblsupname"));
		btnShow.setText(Program.LANG.getProperty("btnshow"));
		lblPayId.setText(Program.LANG.getProperty("lblsuppayid"));
	}

	private void initComponents() {

		txtFullName = new JSuggestionField();
		lblFullName = new javax.swing.JLabel();
		lblPayDate = new javax.swing.JLabel();
		dpkPayDate = new DateButton();
		cboPayId = new javax.swing.JComboBox<String>();
		lblPayId = new javax.swing.JLabel();
		btnShow = new javax.swing.JButton();

		panel.setLayout(null);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(190, 50, 200, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Supplier Name");
		panel.add(lblFullName);
		lblFullName.setBounds(10, 50, 180, 30);

		lblPayDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblPayDate.setText("Payment Date");
		panel.add(lblPayDate);
		lblPayDate.setBounds(10, 10, 180, 30);

		dpkPayDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkPayDate);
		dpkPayDate.setBounds(190, 10, 200, 30);

		cboPayId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(cboPayId);
		cboPayId.setBounds(190, 90, 200, 30);

		lblPayId.setFont(Statics.NORMAL_LARGE_FONT);
		lblPayId.setText("Payment ID");
		panel.add(lblPayId);
		lblPayId.setBounds(10, 90, 180, 30);

		btnShow.setFont(Statics.NORMAL_LARGE_FONT);
		btnShow.setText("Show");
		panel.add(btnShow);
		btnShow.setBounds(110, 140, 170, 50);
	}

	private void initDatewiseNameList() {
		arrFullName.clear();
		SupplierPayData[] data = new SupplierPayment()
				.getDatewisePaymentDetails(dpkPayDate.getDate());
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				SuppliersData sdata = new Suppliers()
						.getSupplierById(data[i].supid);
				if (!arrFullName.contains(sdata.supname))
					arrFullName.add(sdata.supname);
			}
		}
	}

	private class SupPayReportUIListen implements PropertyChangeListener,
			SuggestionSelectedListener, ActionListener {
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getSource() == dpkPayDate) {
				initDatewiseNameList();
			}
		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtFullName) {
				SuppliersData sdata = new Suppliers()
						.getSupplierByName(txtFullName.getText());
				SupplierPayData[] data = new SupplierPayment()
						.getDateAndNameWiseList(dpkPayDate.getDate(),
								sdata.supid);
				if (data != null) {
					cboPayId.removeAllItems();
					for (int i = 0; i < data.length; i++) {
						cboPayId.addItem(data[i].payid + "");
					}
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnShow) {
				if (cboPayId.getSelectedIndex() > -1) {
					Firm firm = new Firm();
					HashMap<String, Object> map = new HashMap<>();
					map.put("prmfirmid", firm.getFirmData().firmid);
					map.put("prmpayid", Long.parseLong(cboPayId
							.getSelectedItem().toString()));
					map.put("prmimagepath", Statics.ROOT + "img"
							+ Statics.DIR_SEP + "payslip" + Statics.DIR_SEP);
					ReportViewer view = new ReportViewer("paymentslip",
							map, firm.getConnectionObj());
					view.showReport();
				}
			}
		}
	}
}
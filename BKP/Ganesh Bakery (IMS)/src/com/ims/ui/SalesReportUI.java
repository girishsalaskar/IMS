package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Customers;
import com.ims.models.Firm;
import com.ims.models.Sales_M;
import com.ims.models.data.CustomersData;
import com.ims.models.data.Sales_MData;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class SalesReportUI extends IFrame {

	private static final long serialVersionUID = 658171125716064418L;

	private javax.swing.JButton btnShowDate;

	private javax.swing.JButton btnShowCust;
	private DateButton dpkEndDate;
	private DateButton dpkCustSales;
	private DateButton dpkStartDate;
	private javax.swing.JLabel lblCustId;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblSalesDate;
	private javax.swing.JLabel lblStartDate;
	private javax.swing.JPanel pnlCustSales;
	private javax.swing.JPanel pnlSalesList;
	private javax.swing.JTabbedPane tbpSalesReport;
	private javax.swing.JTextField txtCustId;
	private JSuggestionField txtFullName;
	private JSuggestionField txtPhone;
	private javax.swing.JLabel lblSalesBills;
	private javax.swing.JComboBox<String> cboSalesBills;
	private SalesReportUIListener listen;
	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;

	public SalesReportUI() {
		super(Program.LANG.getProperty("mnisalesrpt"), 470, 370);
		initComponents();
		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		listen = new SalesReportUIListener();
		initCustomerList();
		dpkCustSales.addPropertyChangeListener("date", listen);
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		btnShowCust.addActionListener(listen);
		btnShowDate.addActionListener(listen);
		
		//TODO Multilanguage Text
		lblStartDate.setText(Program.LANG.getProperty("lblstartdate"));
		lblEndDate.setText(Program.LANG.getProperty("lblenddate"));
		lblFullName.setText(Program.LANG.getProperty("lblcustname"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		lblSalesDate.setText(Program.LANG.getProperty("lblsalesdate"));
		lblSalesBills.setText(Program.LANG.getProperty("lblsalesid"));
		lblCustId.setText(Program.LANG.getProperty("lblcustid"));
		btnShowDate.setText(Program.LANG.getProperty("btnshow"));
		btnShowCust.setText(Program.LANG.getProperty("btnshow"));
	}

	private void initCustomerList() {
		arrFullName.clear();
		arrPhone.clear();
		CustomersData[] data = new Customers().getAllCustomers();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				arrFullName.add(data[i].custname);
				arrPhone.add(data[i].custphone);
			}
		}
	}

	private void initCustomerData(CustomersData data) {
		txtCustId.setText(data.custid + "");
		txtFullName.setText(data.custname);
		txtPhone.setText(data.custphone);
		initSalesID();
	}

	private void initSalesID() {
		cboSalesBills.removeAllItems();
		if (!txtCustId.getText().equals("")) {
			Sales_MData[] data = new Sales_M()
					.getSalesData(Long.parseLong(txtCustId.getText()),
							dpkCustSales.getDate());
			if (data != null) {
				for (int i = 0; i < data.length; i++) {
					cboSalesBills.addItem(data[i].salemid + "");
				}
			}
		}
	}

	private void initComponents() {

		tbpSalesReport = new javax.swing.JTabbedPane();
		pnlSalesList = new javax.swing.JPanel();
		lblStartDate = new javax.swing.JLabel();
		dpkStartDate = new DateButton();
		btnShowDate = new javax.swing.JButton();
		lblEndDate = new javax.swing.JLabel();
		dpkEndDate = new DateButton();
		pnlCustSales = new javax.swing.JPanel();
		lblCustId = new javax.swing.JLabel();
		txtCustId = new javax.swing.JTextField();
		txtFullName = new JSuggestionField();
		lblFullName = new javax.swing.JLabel();
		lblPhone = new javax.swing.JLabel();
		txtPhone = new JSuggestionField();
		lblSalesDate = new javax.swing.JLabel();
		dpkCustSales = new DateButton();
		btnShowCust = new javax.swing.JButton();
		lblSalesBills = new javax.swing.JLabel();
		cboSalesBills = new javax.swing.JComboBox<String>();

		panel.setLayout(null);

		tbpSalesReport
				.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
		tbpSalesReport.setFont(Statics.NORMAL_LARGE_FONT);

		pnlSalesList.setLayout(null);

		lblStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblStartDate.setText("Starting Date");
		pnlSalesList.add(lblStartDate);
		lblStartDate.setBounds(40, 40, 180, 40);

		dpkStartDate.setFont(Statics.NORMAL_LARGE_FONT);
		pnlSalesList.add(dpkStartDate);
		dpkStartDate.setBounds(220, 40, 170, 40);

		btnShowDate.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowDate.setText("Show");
		pnlSalesList.add(btnShowDate);
		btnShowDate.setBounds(130, 210, 170, 60);

		lblEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndDate.setText("Ending Date");
		pnlSalesList.add(lblEndDate);
		lblEndDate.setBounds(40, 120, 180, 40);

		dpkEndDate.setFont(Statics.NORMAL_LARGE_FONT);
		pnlSalesList.add(dpkEndDate);
		dpkEndDate.setBounds(220, 120, 170, 40);

		tbpSalesReport.addTab("Datewise", pnlSalesList);

		pnlCustSales.setLayout(null);

		lblCustId.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustId.setText("Customer ID");
		pnlCustSales.add(lblCustId);
		lblCustId.setBounds(10, 20, 220, 30);

		txtCustId.setEditable(false);
		txtCustId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(txtCustId);
		txtCustId.setBounds(230, 20, 200, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(txtFullName);
		txtFullName.setBounds(230, 60, 200, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Customer Name");
		pnlCustSales.add(lblFullName);
		lblFullName.setBounds(10, 60, 220, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		pnlCustSales.add(lblPhone);
		lblPhone.setBounds(10, 100, 220, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		pnlCustSales.add(txtPhone);
		txtPhone.setBounds(230, 100, 200, 30);

		lblSalesDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesDate.setText("Sales Date");
		pnlCustSales.add(lblSalesDate);
		lblSalesDate.setBounds(10, 140, 220, 30);

		dpkCustSales.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(dpkCustSales);
		dpkCustSales.setBounds(230, 140, 200, 30);

		btnShowCust.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowCust.setText("Show");
		pnlCustSales.add(btnShowCust);
		btnShowCust.setBounds(130, 230, 170, 60);

		lblSalesBills.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesBills.setText("Sales Bills");
		pnlCustSales.add(lblSalesBills);
		lblSalesBills.setBounds(10, 180, 220, 30);

		cboSalesBills.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(cboSalesBills);
		cboSalesBills.setBounds(230, 180, 200, 30);

		tbpSalesReport.addTab("Customerwise", pnlCustSales);

		panel.add(tbpSalesReport);
		tbpSalesReport.setBounds(10, 10, 450, 330);
		tbpSalesReport.getAccessibleContext().setAccessibleName("");
	}

	private class SalesReportUIListener implements ActionListener,
			SuggestionSelectedListener, PropertyChangeListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnShowCust) {
				if (cboSalesBills.getItemCount() > 0) {
					HashMap map = new HashMap();
					map.put("prmsalemid", Long.parseLong(cboSalesBills
							.getSelectedItem().toString()));
					Firm firm = new Firm();
					map.put("prmfirmid", firm.getFirmData().firmid);
					ReportViewer view = new ReportViewer("salesreceipt.jrxml",
							map, firm.getConnectionObj());
					view.showReport();
				} else {
					Statics.showMessage(getContentPane(),
							"Please select bill number!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else if (e.getSource() == btnShowDate) {
				HashMap map = new HashMap();
				Firm firm = new Firm();
				map.put("prmstartdate", dpkStartDate.getDate());
				map.put("prmenddate", dpkEndDate.getDate());
				map.put("prmfirmid", firm.getFirmData().firmid);
				ReportViewer view = new ReportViewer("datewisesaleslist.jrxml",
						map, firm.getConnectionObj());
				view.showReport();
			}
		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtFullName) {
				CustomersData data = new Customers()
						.getCustomerByName(txtFullName.getText());
				if (data != null)
					initCustomerData(data);
			} else if (e.getSource() == txtPhone) {
				CustomersData data = new Customers()
						.getCustomerByMobileNumber(txtPhone.getText());
				if (data != null)
					initCustomerData(data);
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getSource() == dpkCustSales) {
				initSalesID();
			}
		}
	}
}
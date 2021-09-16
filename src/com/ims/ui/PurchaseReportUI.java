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
import com.ims.models.Purchases_M;
import com.ims.models.Suppliers;
import com.ims.models.data.Purchases_MData;
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
public class PurchaseReportUI extends IFrame {

	private static final long serialVersionUID = -9488335257667782L;

	private javax.swing.JButton btnShowSuppl;

	private javax.swing.JButton btnShowDate;

	private javax.swing.JComboBox<String> cboPurchId;
	private DateButton dpkEndDate;
	private DateButton dpkPurchDate;
	private DateButton dpkStartDate;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblPurchId;
	private javax.swing.JLabel lblPurchDate;
	private javax.swing.JLabel lblStartDate;
	private javax.swing.JLabel lblSupId;
	private javax.swing.JPanel pnlCustSales;
	private javax.swing.JPanel pnlSalesList;
	private javax.swing.JTabbedPane tbpSalesReport;
	private JSuggestionField txtFullName;
	private JSuggestionField txtPhone;
	private javax.swing.JTextField txtSupId;
	private PurchaseReportUIListen listen;
	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;

	public PurchaseReportUI() {
		super(Program.LANG.getProperty("mnipurchrpt"), 480, 370);
		initComponents();
		listen = new PurchaseReportUIListen();

		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);
		initList();
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		btnShowDate.addActionListener(listen);
		btnShowSuppl.addActionListener(listen);
		dpkPurchDate.addPropertyChangeListener("date", listen);
		
		//TODO Multilanguage Text
		lblSupId.setText(Program.LANG.getProperty("lblsupid"));
		lblFullName.setText(Program.LANG.getProperty("lblsupname"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		lblPurchDate.setText(Program.LANG.getProperty("lblpurchdate"));
		btnShowSuppl.setText(Program.LANG.getProperty("btnshow"));
		lblPurchId.setText(Program.LANG.getProperty("lblpurchid"));
	}

	private void updatePurchaseID() {
		cboPurchId.removeAllItems();
		Purchases_MData[] data = new Purchases_M().getPurchasesData(
				Long.parseLong(txtSupId.getText()), dpkPurchDate.getDate());
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				cboPurchId.addItem(data[i].purmid + "");
			}
		}
	}

	private void initList() {
		arrFullName.clear();
		arrPhone.clear();
		SuppliersData[] data = new Suppliers().getAllSuppliers();
		if (data != null) {
			for (SuppliersData dt : data) {
				arrFullName.add(dt.supname);
				arrPhone.add(dt.supphone);
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
		lblSupId = new javax.swing.JLabel();
		txtSupId = new javax.swing.JTextField();
		txtFullName = new JSuggestionField();
		lblFullName = new javax.swing.JLabel();
		lblPhone = new javax.swing.JLabel();
		txtPhone = new JSuggestionField();
		lblPurchDate = new javax.swing.JLabel();
		dpkPurchDate = new DateButton();
		btnShowSuppl = new javax.swing.JButton();
		lblPurchId = new javax.swing.JLabel();
		cboPurchId = new javax.swing.JComboBox<String>();

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

		lblSupId.setFont(Statics.NORMAL_LARGE_FONT);
		lblSupId.setText("Supplier ID");
		pnlCustSales.add(lblSupId);
		lblSupId.setBounds(10, 20, 220, 30);

		txtSupId.setEditable(false);
		txtSupId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(txtSupId);
		txtSupId.setBounds(230, 20, 200, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(txtFullName);
		txtFullName.setBounds(230, 60, 200, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Supplier Name");
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

		lblPurchDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblPurchDate.setText("Purchases Date");
		pnlCustSales.add(lblPurchDate);
		lblPurchDate.setBounds(10, 140, 220, 30);

		dpkPurchDate.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(dpkPurchDate);
		dpkPurchDate.setBounds(230, 140, 200, 30);

		btnShowSuppl.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowSuppl.setText("Show");
		pnlCustSales.add(btnShowSuppl);
		btnShowSuppl.setBounds(130, 230, 170, 60);

		lblPurchId.setFont(Statics.NORMAL_LARGE_FONT);
		lblPurchId.setText("Purchases ID");
		pnlCustSales.add(lblPurchId);
		lblPurchId.setBounds(10, 180, 220, 30);

		cboPurchId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCustSales.add(cboPurchId);
		cboPurchId.setBounds(230, 180, 200, 30);

		tbpSalesReport.addTab("Supplierwise", pnlCustSales);

		panel.add(tbpSalesReport);
		tbpSalesReport.setBounds(10, 10, 450, 330);
	}

	private class PurchaseReportUIListen implements ActionListener,
			SuggestionSelectedListener, PropertyChangeListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnShowSuppl) {
				if (cboPurchId.getSelectedIndex() >= 0) {
					Firm firm = new Firm();
					HashMap<String, Object> map = new HashMap<>();
					map.put("prmfirmid", firm.getFirmData().firmid);
					map.put("prmpurmid", Long.parseLong(cboPurchId
							.getSelectedItem().toString()));
					ReportViewer view = new ReportViewer("purchreceipt",
							map, firm.getConnectionObj());
					view.showReport();
				}
			}
	
			else if (e.getSource()==btnShowDate) {
				Firm firm = new Firm();
				HashMap<String, Object> map=new HashMap<>();
				map.put("prmfirmid", firm.getFirmData().firmid);
				map.put("prmstartdate", dpkStartDate.getDate());
				map.put("prmenddate", dpkEndDate.getDate());
				ReportViewer view=new ReportViewer("datewisepurchaseslist", map, firm.getConnectionObj());
				view.showReport();
			}
		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtFullName) {
				SuppliersData data = new Suppliers()
						.getSupplierByName(txtFullName.getText());
				txtSupId.setText(data.supid + "");
				txtFullName.setText(data.supname);
				txtPhone.setText(data.supphone);
				updatePurchaseID();
			}

			else if (e.getSource() == txtPhone) {
				SuppliersData data = new Suppliers()
						.getSupplierByMobileNumber(txtPhone.getText());
				txtSupId.setText(data.supid + "");
				txtFullName.setText(data.supname);
				txtPhone.setText(data.supphone);
				updatePurchaseID();
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getSource() == dpkPurchDate) {
				updatePurchaseID();
			}
		}
	}
}
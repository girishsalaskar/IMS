package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Customers;
import com.ims.models.RouteM;
import com.ims.models.data.CustomersData;
import com.ims.models.data.RouteMData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class CustRoutesUI extends IFrame {

	private javax.swing.JButton btnReset;

	private javax.swing.JButton btnUpdate;

	private javax.swing.JLabel lblCustName;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblRouteName;
	private javax.swing.JLabel lblRouteNo;
	private JSuggestionField txtFullName;
	private JSuggestionField txtPhone;
	private JSuggestionField txtRouteName;
	private javax.swing.JComboBox cboRouteNo;

	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;
	private ArrayList<String> arrRouteName;
	private CustRoutesUIListen listen;

	private long custid;
	private int routeid;

	public CustRoutesUI() {
		super(Program.LANG.getProperty("mnicustroutes"), 450, 250);
		initComponents();

		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		arrRouteName = new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);
		txtRouteName.setSuggestionList(arrRouteName);

		initCustomerList();
		initRouteDetails();

		custid = -1;
		routeid = -1;

		listen = new CustRoutesUIListen();
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		txtRouteName.addSuggestionSelectedListener(listen);
		btnReset.addActionListener(listen);
		btnUpdate.addActionListener(listen);
		
		//TODO Multilanguage Text
		lblCustName.setText(Program.LANG.getProperty("lblcustname"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		
		lblRouteName.setText(Program.LANG.getProperty("lblroutename"));
		lblRouteNo.setText(Program.LANG.getProperty("lblrouteno"));
	}

	private void initRouteDetails() {
		arrRouteName.clear();
		cboRouteNo.removeAllItems();
		RouteMData[] data = new RouteM().getAllRouteDetails();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				arrRouteName.add(data[i].routename);
				cboRouteNo.addItem(data[i].routeno + "");
			}
		}
	}

	private void initCustomerData(CustomersData data) {
		custid = data.custid;
		txtFullName.setText(data.custname);
		txtPhone.setText(data.custphone);
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

	private void initComponents() {

		txtFullName = new JSuggestionField();
		lblCustName = new javax.swing.JLabel();
		lblPhone = new javax.swing.JLabel();
		txtPhone = new JSuggestionField();
		txtRouteName = new JSuggestionField();
		lblRouteName = new javax.swing.JLabel();
		lblRouteNo = new javax.swing.JLabel();
		btnUpdate = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		cboRouteNo = new javax.swing.JComboBox();

		panel.setLayout(null);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 10, 200, 30);

		lblCustName.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustName.setText("Customer Full Name");
		panel.add(lblCustName);
		lblCustName.setBounds(10, 10, 210, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 50, 210, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPhone);
		txtPhone.setBounds(230, 50, 200, 30);

		txtRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtRouteName);
		txtRouteName.setBounds(230, 130, 200, 30);

		lblRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		lblRouteName.setText("Route Name");
		panel.add(lblRouteName);
		lblRouteName.setBounds(10, 130, 210, 30);

		lblRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblRouteNo.setText("Route Number");
		lblRouteNo.setToolTipText("");
		panel.add(lblRouteNo);
		lblRouteNo.setBounds(10, 90, 210, 30);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(20, 170, 190, 60);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(220, 170, 200, 60);

		panel.add(cboRouteNo);
		cboRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		cboRouteNo.setBounds(230, 90, 200, 30);
	}

	private void resetFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof javax.swing.JTextField) {
				((javax.swing.JTextField) c).setText("");
			}
		}
		custid = -1;
		routeid = -1;
	}

	private class CustRoutesUIListen implements SuggestionSelectedListener,
			ActionListener {
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
			} else if (e.getSource() == txtRouteName) {
				RouteMData[] data = new RouteM()
						.getRoutesByRouteName(txtRouteName.getText());
				if (data != null) {
					cboRouteNo.removeAllItems();
					for (int i = 0; i < data.length; i++) {
						cboRouteNo.addItem(data[i].routeno + "");
					}
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetFields();
			} else if (e.getSource() == btnUpdate) {
				for (Component c : panel.getComponents()) {
					if (c instanceof javax.swing.JTextField) {
						if (((javax.swing.JTextField) c).getText().equals("")) {
							Statics.showMessage(getContentPane(),
									"Please enter all fields!",
									JOptionPane.ERROR_MESSAGE);
							((javax.swing.JTextField) c).requestFocus();
							return;
						}
					}
				}
				if (custid == -1) {
					Statics.showMessage(getContentPane(),
							"Please select customer details properly!",
							JOptionPane.ERROR_MESSAGE);
					txtFullName.requestFocus();
					return;
				}

				RouteMData data = new RouteM().getRouteByRouteNo(Integer
						.parseInt(cboRouteNo.getSelectedItem().toString()));
				routeid = data.routeid;

				Customers cust = new Customers();
				int ret = cust.updateRouteData(custid, routeid);
				if (ret > 0) {
					Statics.showMessage(getContentPane(),
							"Customer's Route Updated Successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					resetFields();
				}
			}
		}
	}
}
package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Assets;
import com.ims.models.Customers;
import com.ims.models.EmployeeM;
import com.ims.models.IMSDB;
import com.ims.models.RouteM;
import com.ims.models.RouteTrip;
import com.ims.models.data.AssetsData;
import com.ims.models.data.CustomersData;
import com.ims.models.data.EmployeeM_Data;
import com.ims.models.data.RouteMData;
import com.ims.models.data.RouteTripData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 * @author Girish
 */
public class RouteTripUI extends IFrame {

	private javax.swing.JButton btnEndReset;

	private javax.swing.JButton btnResetCrate;

	private javax.swing.JButton btnEndSave;
	private javax.swing.JButton btnCrateSave;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnSave;
	private DateButton dpkTripDate;
	private javax.swing.JComboBox cboEndRouteNo;
	private javax.swing.JComboBox cboRouteNoCrate;
	private javax.swing.JComboBox cboEndVehicle;
	private javax.swing.JComboBox cboRouteNo;
	private javax.swing.JComboBox cboVehicle;
	private DateButton dpkEndTripDate;
	private javax.swing.JScrollPane jspCustList;
	private javax.swing.JScrollPane jspCustListCr;
	private javax.swing.JTabbedPane jtpRouteTrip;
	private javax.swing.JLabel lblEmpName;
	private javax.swing.JLabel lblEndCrateRec;
	private javax.swing.JLabel lblEndCrateSent;
	private javax.swing.JLabel lblEndEmpName;
	private javax.swing.JLabel lblEndRouteNo;
	private javax.swing.JLabel lblEndRouteNo1;
	private javax.swing.JLabel lblEndTripDate;
	private javax.swing.JLabel lblEndVehicle;
	private javax.swing.JLabel lblRouteNo;
	private javax.swing.JLabel lblSentCrate;
	private javax.swing.JLabel lblTripDate;
	private javax.swing.JLabel lblVehicle;
	private javax.swing.JPanel pnlBack;
	private javax.swing.JPanel pnlCrateReceive;
	private javax.swing.JPanel pnlTripStart;
	private javax.swing.JTable tblCustList;
	private javax.swing.JTable tblCustListCr;
	private javax.swing.JTextField txtCrateSent;
	private JSuggestionField txtEmpName;
	private javax.swing.JTextField txtEndCrateRec;
	private javax.swing.JTextField txtEndCrateSent;
	private javax.swing.JTextField txtEndEmpName;

	private ArrayList<String> arrEmpName;
	private javax.swing.table.DefaultTableModel dtmCustList, dtmCustListCr;
	private long empid;
	private int routeid;

	private RouteTripUIListen listen;

	public RouteTripUI() {
		super(Program.LANG.getProperty("mniroutetrip"), 480, 490);
		initComponents();
		dtmCustList = (DefaultTableModel) tblCustList.getModel();
		dtmCustListCr = (DefaultTableModel) tblCustListCr.getModel();
		empid = -1;
		routeid = -1;
		arrEmpName = new ArrayList<String>();
		txtEmpName.setSuggestionList(arrEmpName);

		listen = new RouteTripUIListen();
		txtEmpName.addSuggestionSelectedListener(listen);
		btnSave.addActionListener(listen);
		btnReset.addActionListener(listen);
		cboRouteNo.addActionListener(listen);
		cboRouteNoCrate.addActionListener(listen);
		cboEndRouteNo.addActionListener(listen);
		btnEndSave.addActionListener(listen);
		btnEndReset.addActionListener(listen);
		btnCrateSave.addActionListener(listen);
		btnResetCrate.addActionListener(listen);

		jtpRouteTrip.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				resetSent();
				resetEnd();
				resetCrate();
			}
		});

		initData();
		
		//TODO Multilanguage Text
		lblRouteNo.setText(Program.LANG.getProperty("lblrouteno"));
		lblEmpName.setText(Program.LANG.getProperty("lblempname"));
		btnSave.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnCrateSave.setText(Program.LANG.getProperty("btnupdate"));
		btnEndReset.setText(Program.LANG.getProperty("btnreset"));
		btnEndSave.setText(Program.LANG.getProperty("btnupdate"));
		lblEndEmpName.setText(Program.LANG.getProperty("lblempname"));
		lblEndRouteNo.setText(Program.LANG.getProperty("lblrouteno"));
		lblEndRouteNo1.setText(Program.LANG.getProperty("lblrouteno"));
		lblEndTripDate.setText(Program.LANG.getProperty("lbltripdate"));
		lblEndVehicle.setText(Program.LANG.getProperty("lblvehicle"));
		lblEndCrateSent.setText(Program.LANG.getProperty("lblsentcrate"));
		
		lblVehicle.setText(Program.LANG.getProperty("lblvehicle"));
		lblSentCrate.setText(Program.LANG.getProperty("lblsentcrate"));
		lblTripDate.setText(Program.LANG.getProperty("lbltripdate"));
		lblEndCrateRec.setText(Program.LANG.getProperty("lblcraterec"));
		
	}

	private void initData() {
		cboEndRouteNo.removeAll();
		cboRouteNo.removeAll();
		cboRouteNoCrate.removeAll();

		RouteMData[] data = new RouteM().getAllRouteDetails();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				cboEndRouteNo.addItem(data[i].routeno);
				cboRouteNo.addItem(data[i].routeno);
				cboRouteNoCrate.addItem(data[i].routeno);
			}
		}

		AssetsData[] adata = new Assets().getAllAssets();
		if (adata != null) {
			for (int i = 0; i < adata.length; i++) {
				if (adata[i].astname.equalsIgnoreCase("vehicle")) {
					cboVehicle.addItem(adata[i].astvalue);
					cboEndVehicle.addItem(adata[i].astvalue);
				}
			}
		}

		EmployeeM_Data[] edata = new EmployeeM().getAllEnabledEmployees();
		if (edata != null) {
			arrEmpName.clear();
			for (int i = 0; i < edata.length; i++) {
				arrEmpName.add(edata[i].empname);
			}
		}
	}

	private void initComponents() {

		jtpRouteTrip = new javax.swing.JTabbedPane();
		pnlTripStart = new javax.swing.JPanel();
		cboRouteNo = new javax.swing.JComboBox();
		lblRouteNo = new javax.swing.JLabel();
		lblVehicle = new javax.swing.JLabel();
		cboVehicle = new javax.swing.JComboBox();
		txtEmpName = new JSuggestionField();
		lblEmpName = new javax.swing.JLabel();
		txtCrateSent = new javax.swing.JTextField();
		lblSentCrate = new javax.swing.JLabel();
		lblTripDate = new javax.swing.JLabel();
		dpkTripDate = new DateButton();
		btnReset = new javax.swing.JButton();
		btnSave = new javax.swing.JButton();
		pnlBack = new javax.swing.JPanel();
		lblEndVehicle = new javax.swing.JLabel();
		dpkEndTripDate = new DateButton();
		cboEndVehicle = new javax.swing.JComboBox();
		lblEndTripDate = new javax.swing.JLabel();
		lblEndEmpName = new javax.swing.JLabel();
		txtEndEmpName = new javax.swing.JTextField();
		txtEndCrateSent = new javax.swing.JTextField();
		lblEndCrateSent = new javax.swing.JLabel();
		lblEndRouteNo = new javax.swing.JLabel();
		cboEndRouteNo = new javax.swing.JComboBox();
		txtEndCrateRec = new javax.swing.JTextField();
		lblEndCrateRec = new javax.swing.JLabel();
		jspCustList = new javax.swing.JScrollPane();
		tblCustList = new javax.swing.JTable();
		btnEndSave = new javax.swing.JButton();
		btnEndReset = new javax.swing.JButton();
		pnlCrateReceive = new javax.swing.JPanel();
		lblEndRouteNo1 = new javax.swing.JLabel();
		cboRouteNoCrate = new javax.swing.JComboBox();
		jspCustListCr = new javax.swing.JScrollPane();
		tblCustListCr = new javax.swing.JTable();
		btnCrateSave = new javax.swing.JButton();
		btnResetCrate = new javax.swing.JButton();

		panel.setLayout(null);

		pnlTripStart.setLayout(null);

		cboRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(cboRouteNo);
		cboRouteNo.setBounds(230, 80, 200, 30);

		lblRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblRouteNo.setText("Route Number");
		lblRouteNo.setToolTipText("");
		pnlTripStart.add(lblRouteNo);
		lblRouteNo.setBounds(10, 80, 210, 30);

		lblVehicle.setFont(Statics.NORMAL_LARGE_FONT);
		lblVehicle.setText("Vehicle");
		lblVehicle.setToolTipText("");
		pnlTripStart.add(lblVehicle);
		lblVehicle.setBounds(10, 120, 210, 30);

		cboVehicle.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(cboVehicle);
		cboVehicle.setBounds(230, 120, 200, 30);

		txtEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(txtEmpName);
		txtEmpName.setBounds(230, 160, 200, 30);

		lblEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpName.setText("Employee Name");
		pnlTripStart.add(lblEmpName);
		lblEmpName.setBounds(10, 160, 210, 30);

		txtCrateSent.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(txtCrateSent);
		txtCrateSent.setBounds(230, 200, 200, 30);

		lblSentCrate.setFont(Statics.NORMAL_LARGE_FONT);
		lblSentCrate.setText("Crates Sent");
		pnlTripStart.add(lblSentCrate);
		lblSentCrate.setBounds(10, 200, 210, 30);

		lblTripDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblTripDate.setText("Trip Date");
		pnlTripStart.add(lblTripDate);
		lblTripDate.setBounds(10, 240, 210, 30);

		dpkTripDate.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(dpkTripDate);
		dpkTripDate.setBounds(230, 240, 200, 30);

		btnReset.setText("Reset");
		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(btnReset);
		btnReset.setBounds(230, 280, 200, 60);

		btnSave.setText("Save");
		btnSave.setFont(Statics.NORMAL_LARGE_FONT);
		pnlTripStart.add(btnSave);
		btnSave.setBounds(10, 280, 210, 60);

		jtpRouteTrip.addTab("Trip Start", pnlTripStart);

		pnlBack.setLayout(null);

		lblEndVehicle.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndVehicle.setText("Vehicle");
		lblEndVehicle.setToolTipText("");
		pnlBack.add(lblEndVehicle);
		lblEndVehicle.setBounds(10, 90, 210, 30);

		dpkEndTripDate.setFont(Statics.NORMAL_LARGE_FONT);
		pnlBack.add(dpkEndTripDate);
		dpkEndTripDate.setBounds(230, 50, 200, 30);

		cboEndVehicle.setFont(Statics.NORMAL_LARGE_FONT);
		pnlBack.add(cboEndVehicle);
		cboEndVehicle.setBounds(230, 90, 200, 30);

		lblEndTripDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndTripDate.setText("Trip Date");
		pnlBack.add(lblEndTripDate);
		lblEndTripDate.setBounds(10, 50, 210, 30);

		lblEndEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndEmpName.setText("Employee Name");
		pnlBack.add(lblEndEmpName);
		lblEndEmpName.setBounds(10, 130, 210, 30);

		txtEndEmpName.setEditable(false);
		txtEndEmpName.setFont(Statics.NORMAL_LARGE_FONT);
		pnlBack.add(txtEndEmpName);
		txtEndEmpName.setBounds(230, 130, 200, 30);

		txtEndCrateSent.setEditable(false);
		txtEndCrateSent.setFont(Statics.NORMAL_LARGE_FONT);
		pnlBack.add(txtEndCrateSent);
		txtEndCrateSent.setBounds(230, 170, 200, 30);

		lblEndCrateSent.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndCrateSent.setText("Crates Sent");
		pnlBack.add(lblEndCrateSent);
		lblEndCrateSent.setBounds(10, 170, 210, 30);

		lblEndRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndRouteNo.setText("Route Number");
		lblEndRouteNo.setToolTipText("");
		pnlBack.add(lblEndRouteNo);
		lblEndRouteNo.setBounds(10, 10, 210, 30);

		cboEndRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		pnlBack.add(cboEndRouteNo);
		cboEndRouteNo.setBounds(230, 10, 200, 30);

		txtEndCrateRec.setFont(Statics.NORMAL_LARGE_FONT);
		pnlBack.add(txtEndCrateRec);
		txtEndCrateRec.setBounds(230, 210, 200, 30);

		lblEndCrateRec.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndCrateRec.setText("Crates Received");
		pnlBack.add(lblEndCrateRec);
		lblEndCrateRec.setBounds(10, 210, 210, 30);

		tblCustList
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] {

						}, new String[] { "Cust. ID", "Customer Name",
								"Crates taken" }) {
					boolean[] canEdit = new boolean[] { false, false, true };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		jspCustList.setViewportView(tblCustList);

		pnlBack.add(jspCustList);
		jspCustList.setBounds(10, 250, 420, 100);

		btnEndSave.setFont(Statics.NORMAL_LARGE_FONT);
		btnEndSave.setText("Save");
		pnlBack.add(btnEndSave);
		btnEndSave.setBounds(70, 360, 140, 50);

		btnEndReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnEndReset.setText("Reset");
		pnlBack.add(btnEndReset);
		btnEndReset.setBounds(220, 360, 140, 50);

		jtpRouteTrip.addTab("Trip End", pnlBack);

		pnlCrateReceive.setLayout(null);

		lblEndRouteNo1.setFont(Statics.NORMAL_LARGE_FONT);
		lblEndRouteNo1.setText("Route Number");
		lblEndRouteNo1.setToolTipText("");
		pnlCrateReceive.add(lblEndRouteNo1);
		lblEndRouteNo1.setBounds(10, 70, 210, 30);

		cboRouteNoCrate.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCrateReceive.add(cboRouteNoCrate);
		cboRouteNoCrate.setBounds(230, 70, 200, 30);

		tblCustListCr.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Cust. ID", "Customer Name", "Crates taken",
						"Crates Return" }) {
			boolean[] canEdit = new boolean[] { false, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jspCustListCr.setViewportView(tblCustListCr);

		pnlCrateReceive.add(jspCustListCr);
		jspCustListCr.setBounds(10, 110, 420, 180);

		btnCrateSave.setFont(Statics.NORMAL_LARGE_FONT);
		btnCrateSave.setText("Save");
		pnlCrateReceive.add(btnCrateSave);
		btnCrateSave.setBounds(70, 300, 140, 50);

		btnResetCrate.setFont(Statics.NORMAL_LARGE_FONT);
		btnResetCrate.setText("Reset");
		pnlCrateReceive.add(btnResetCrate);
		btnResetCrate.setBounds(220, 300, 140, 50);

		jtpRouteTrip.addTab("Crate Received", pnlCrateReceive);

		panel.add(jtpRouteTrip);
		jtpRouteTrip.setFont(Statics.NORMAL_LARGE_FONT);
		jtpRouteTrip.setBounds(10, 10, 450, 450);
	}

	private void resetSent() {
		txtEmpName.setText("");
		txtCrateSent.setText("");
		routeid = -1;
	}

	private void resetEnd() {
		txtEndCrateRec.setText("");
		txtEndEmpName.setText("");
		txtEndCrateSent.setText("");
		cboEndVehicle.removeAllItems();
		routeid = -1;
	}

	private void resetCrate() {
		cboRouteNoCrate.setSelectedIndex(0);
	}

	private class RouteTripUIListen implements SuggestionSelectedListener,
			ActionListener {
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtEmpName) {
				EmployeeM_Data data = new EmployeeM()
						.getEmployeeByName(txtEmpName.getText());
				if (data != null) {
					empid = data.empid;
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetSent();
			} else if (e.getSource() == btnSave) {
				if (txtEmpName.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter Employee Name",
							JOptionPane.ERROR_MESSAGE);
					txtEmpName.requestFocus();
					return;
				} else if (txtCrateSent.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter crates sent!",
							JOptionPane.ERROR_MESSAGE);
					txtCrateSent.requestFocus();
					return;
				}
				RouteTripData data = new RouteTripData();
				data.cratesend = Integer.parseInt(txtCrateSent.getText());
				data.empdriver = txtEmpName.getText();
				data.routeid = routeid;
				data.tripdate = dpkTripDate.getDate();
				data.vehicleno = cboVehicle.getSelectedItem().toString();

				RouteTrip trip = new RouteTrip();
				int ret = trip.makeSentEntry(data);
				if (ret > 0) {
					Statics.showMessage(getContentPane(),
							"Route Trip saved successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					resetSent();
					return;
				} else {
					Statics.showMessage(getContentPane(),
							"Route Trip not saved successfully!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			else if (e.getSource() == cboEndRouteNo
					|| e.getSource() == cboRouteNo
					|| e.getSource() == cboRouteNoCrate) {
				RouteMData data = new RouteM().getRouteByRouteNo(Integer
						.parseInt(((javax.swing.JComboBox) e.getSource())
								.getSelectedItem().toString()));
				if (data != null) {
					routeid = data.routeid;
				}

				if (e.getSource() == cboEndRouteNo) {
					cboEndVehicle.removeAllItems();
					txtEndEmpName.setText("");
					txtEndCrateSent.setText("");
					RouteTripData tdata = new RouteTrip()
							.getRouteWiseNRTrip(routeid);
					if (tdata != null) {
						cboEndVehicle.addItem(tdata.vehicleno);
						dpkEndTripDate.setDate(tdata.tripdate);
						txtEndEmpName.setText(tdata.empdriver);
						txtEndCrateSent.setText(tdata.cratesend + "");

						CustomersData[] custdata = new Customers()
								.getRouteWiseCustomers(tdata.routeid);
						if (custdata != null) {
							for (int i = 0; i < custdata.length; i++) {
								dtmCustList.setRowCount(i + 1);

								tblCustList
										.setValueAt(custdata[i].custid, i, 0);
								tblCustList.setValueAt(custdata[i].custname, i,
										1);
								tblCustList.setValueAt(custdata[i].crate_holds,
										i, 2);
							}
						}
					}
				} else if (e.getSource() == cboRouteNoCrate) {
					dtmCustListCr.setRowCount(0);
					CustomersData[] custdata = new Customers()
							.getCustomersHavingCrates(routeid);
					if (custdata != null) {
						for (int i = 0; i < custdata.length; i++) {
							dtmCustListCr.setRowCount(i + 1);
							tblCustListCr.setValueAt(custdata[i].custid, i, 0);
							tblCustListCr
									.setValueAt(custdata[i].custname, i, 1);
							tblCustListCr.setValueAt(custdata[i].crate_holds,
									i, 2);
							tblCustListCr.setValueAt("0", i, 3);
						}
					}
				}
			}

			else if (e.getSource() == btnEndReset) {
				resetEnd();
			}

			else if (e.getSource() == btnEndSave) {
				if (txtEndCrateRec.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter crates return!",
							JOptionPane.ERROR_MESSAGE);
					txtEndCrateRec.requestFocus();
					return;
				}
				int craterec = Integer.parseInt(txtEndCrateRec.getText());
				if (craterec < Integer.parseInt(txtEndCrateSent.getText())) {
					int totcr = 0;
					for (int i = 0; i < tblCustList.getRowCount(); i++) {
						totcr += Integer.parseInt(tblCustList.getValueAt(i, 2)
								.toString());
					}
					if (totcr != craterec) {
						Statics.showMessage(getContentPane(),
								"Please enter crates taken by customers!",
								JOptionPane.ERROR_MESSAGE);
						tblCustList.requestFocus();
						return;
					}
				} else if (craterec > Integer.parseInt(txtEndCrateSent
						.getText())) {
					Statics.showMessage(getContentPane(),
							"Please update crate received!",
							JOptionPane.WARNING_MESSAGE);
				}

				IMSDB db = new IMSDB();
				try {
					db.setAutoCommit(false);
					db.executeUpdate("update route_trip set cratereceived=?"
							+ " where cratereceived is null "
							+ "and vehicleno=?", craterec, cboEndVehicle
							.getSelectedItem().toString());

					for (int i = 0; i < tblCustList.getRowCount(); i++) {
						db.executeUpdate("update customers set crate_holds=?"
								+ " where custid=?", Integer
								.parseInt(tblCustList.getValueAt(i, 2)
										.toString()), Long
								.parseLong(tblCustList.getValueAt(i, 0)
										.toString()));
					}
					db.commit();
					db.setAutoCommit(true);
					Statics.showMessage(getContentPane(),
							"Trip complete data saved successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					resetEnd();
				} catch (SQLException | DatabaseNotConnectedException e1) {
					// TODO Auto-generated catch block
					Statics.handleException(e1);
					Statics.showMessage(getContentPane(),
							"Trip complete data not saved successfully!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			else if (e.getSource() == btnCrateSave) {
				// TODO Crate Save
				IMSDB db = new IMSDB();
				try {
					db.setAutoCommit(false);
					for (int i = 0; i < tblCustListCr.getRowCount(); i++) {
						int oldcrate = Integer.parseInt(tblCustListCr
								.getValueAt(i, 2).toString());
						int ret = Integer.parseInt(tblCustListCr.getValueAt(i,
								3).toString());
						ret = oldcrate - ret;
						db.executeUpdate(
								"update customers set crate_holds=? where "
										+ "custid=?", ret, Long
										.parseLong(tblCustListCr.getValueAt(i,
												0).toString()));
					}
					db.commit();
					db.setAutoCommit(true);
					Statics.showMessage(getContentPane(),
							"Crate return updated successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					resetCrate();
				} catch (SQLException | NumberFormatException
						| DatabaseNotConnectedException e1) {
					Statics.handleException(e1);
					Statics.showMessage(getContentPane(),
							"Crate return not updated successfully!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			else if (e.getSource() == btnResetCrate) {
				// TODO Crate Reset
				resetCrate();
			}
		}
	}
}
package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.RouteM;
import com.ims.models.data.RouteMData;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class CustReportUI extends IFrame {

	private static final long serialVersionUID = 4131948131067623448L;

	private javax.swing.JButton btnShowCrate;

	private javax.swing.JButton btnShowDebit;

	private javax.swing.JComboBox<String> cboCrateRouteNo;
	private javax.swing.JComboBox<String> cboDebitRouteNo;
	private javax.swing.JTabbedPane jtpCustReports;
	private javax.swing.JLabel lblCrateRouteName;
	private javax.swing.JLabel lblCrateRouteNo;
	private javax.swing.JLabel lblDebitRouteName;
	private javax.swing.JLabel lblDebitRouteNo;
	private javax.swing.JPanel pnlCrateReport;
	private javax.swing.JPanel pnlDebitReport;
	private JSuggestionField txtCrateRouteName;
	private JSuggestionField txtDebitRouteName;

	private CustReportsUIListen listen;
	private ArrayList<String> arrRouteNames;
	private int debitrouteid;
	private int craterouteid;

	public CustReportUI() {
		super(Program.LANG.getProperty("mnicustrpt"), 450, 330);
		initComponents();
		debitrouteid = -1;
		craterouteid = -1;
		arrRouteNames = new ArrayList<String>();
		txtCrateRouteName.setSuggestionList(arrRouteNames);
		txtDebitRouteName.setSuggestionList(arrRouteNames);
		initRoutes();

		listen = new CustReportsUIListen();
		btnShowDebit.addActionListener(listen);
		btnShowCrate.addActionListener(listen);
		txtDebitRouteName.addSuggestionSelectedListener(listen);
		txtCrateRouteName.addSuggestionSelectedListener(listen);
		
		//TODO Multilanguage Text
		lblCrateRouteName.setText(Program.LANG.getProperty("lblroutename"));
		lblCrateRouteNo.setText(Program.LANG.getProperty("lblrouteno"));
		lblDebitRouteName.setText(Program.LANG.getProperty("lblroutename"));
		lblDebitRouteNo.setText(Program.LANG.getProperty("lblrouteno"));
		btnShowCrate.setText(Program.LANG.getProperty("btnshow"));
		btnShowDebit.setText(Program.LANG.getProperty("btnshow"));
	}

	private void initRoutes() {
		arrRouteNames.clear();
		cboCrateRouteNo.removeAllItems();
		cboDebitRouteNo.removeAllItems();
		RouteMData[] data = new RouteM().getAllRouteDetails();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				arrRouteNames.add(data[i].routename);
				cboCrateRouteNo.addItem(data[i].routeno + "");
				cboDebitRouteNo.addItem(data[i].routeno + "");
			}
		}
	}

	private void initComponents() {

		jtpCustReports = new javax.swing.JTabbedPane();
		pnlDebitReport = new javax.swing.JPanel();
		btnShowDebit = new javax.swing.JButton();
		cboDebitRouteNo = new javax.swing.JComboBox<String>();
		lblDebitRouteNo = new javax.swing.JLabel();
		lblDebitRouteName = new javax.swing.JLabel();
		txtDebitRouteName = new JSuggestionField();
		pnlCrateReport = new javax.swing.JPanel();
		btnShowCrate = new javax.swing.JButton();
		cboCrateRouteNo = new javax.swing.JComboBox<String>();
		lblCrateRouteNo = new javax.swing.JLabel();
		lblCrateRouteName = new javax.swing.JLabel();
		txtCrateRouteName = new JSuggestionField();

		panel.setLayout(null);

		pnlDebitReport.setLayout(null);

		btnShowDebit.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowDebit.setText("Show");
		pnlDebitReport.add(btnShowDebit);
		btnShowDebit.setBounds(110, 190, 170, 60);

		pnlDebitReport.add(cboDebitRouteNo);
		cboDebitRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		cboDebitRouteNo.setBounds(220, 20, 190, 30);

		lblDebitRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblDebitRouteNo.setText("Route Number");
		lblDebitRouteNo.setToolTipText("");
		pnlDebitReport.add(lblDebitRouteNo);
		lblDebitRouteNo.setBounds(20, 20, 200, 30);

		lblDebitRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		lblDebitRouteName.setText("Route Name");
		pnlDebitReport.add(lblDebitRouteName);
		lblDebitRouteName.setBounds(20, 100, 200, 30);

		txtDebitRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		pnlDebitReport.add(txtDebitRouteName);
		txtDebitRouteName.setBounds(220, 100, 190, 30);

		jtpCustReports.addTab("Routewise Debit Report", pnlDebitReport);

		pnlCrateReport.setLayout(null);

		btnShowCrate.setFont(Statics.NORMAL_LARGE_FONT);
		btnShowCrate.setText("Show");
		pnlCrateReport.add(btnShowCrate);
		btnShowCrate.setBounds(110, 190, 170, 60);

		pnlCrateReport.add(cboCrateRouteNo);
		cboCrateRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		cboCrateRouteNo.setBounds(220, 20, 190, 30);

		lblCrateRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblCrateRouteNo.setText("Route Number");
		lblCrateRouteNo.setToolTipText("");
		pnlCrateReport.add(lblCrateRouteNo);
		lblCrateRouteNo.setBounds(20, 20, 200, 30);

		lblCrateRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		lblCrateRouteName.setText("Route Name");
		pnlCrateReport.add(lblCrateRouteName);
		lblCrateRouteName.setBounds(20, 100, 200, 30);

		txtCrateRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		pnlCrateReport.add(txtCrateRouteName);
		txtCrateRouteName.setBounds(220, 100, 190, 30);

		jtpCustReports.addTab("Routewise Crate Report", pnlCrateReport);

		panel.add(jtpCustReports);
		jtpCustReports.setBounds(10, 10, 430, 300);
	}

	private class CustReportsUIListen implements ActionListener,
			SuggestionSelectedListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnShowDebit) {
				RouteMData data = new RouteM()
						.getRouteByRouteNo(Integer.parseInt(cboDebitRouteNo
								.getSelectedItem().toString()));
				if (data != null) {
					debitrouteid = data.routeid;
				}
				Firm firm = new Firm();
				HashMap map = new HashMap();
				map.put("prmfirmid", firm.getFirmData().firmid);
				map.put("prmrouteid", debitrouteid);
				ReportViewer view = new ReportViewer("custdebt.jrxml", map,
						firm.getConnectionObj());
				view.showReport();
			} else if (e.getSource() == btnShowCrate) {
				RouteMData data = new RouteM()
						.getRouteByRouteNo(Integer.parseInt(cboCrateRouteNo
								.getSelectedItem().toString()));
				if (data != null) {
					craterouteid = data.routeid;
				}
				Firm firm = new Firm();
				HashMap map = new HashMap();
				map.put("prmfirmid", firm.getFirmData().firmid);
				map.put("prmrouteid", craterouteid);
				ReportViewer view = new ReportViewer("custcrate.jrxml", map,
						firm.getConnectionObj());
				view.showReport();
			}
		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			RouteMData[] data = null;
			if (e.getSource() == txtCrateRouteName) {
				data = new RouteM().getRoutesByRouteName(txtCrateRouteName
						.getText());
				if (data != null) {
					cboCrateRouteNo.removeAllItems();
					for (int i = 0; i < data.length; i++) {
						cboCrateRouteNo.addItem(data[i].routeno + "");
					}
				}
			} else if (e.getSource() == txtDebitRouteName) {
				data = new RouteM().getRoutesByRouteName(txtDebitRouteName
						.getText());
				if (data != null) {
					cboDebitRouteNo.removeAllItems();
					for (int i = 0; i < data.length; i++) {
						cboDebitRouteNo.addItem(data[i].routeno + "");
					}
				}
			}
		}
	}
}
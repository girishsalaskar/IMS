package com.ims.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.TestReport;
import com.ims.models.TestReportProducts;
import com.ims.models.data.TestReportData;
import com.ims.models.data.TestReportProductsData;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.table.JButtonCellEditor;
import com.jdev.girish.ui.table.JButtonCellRenderer;

/**
 *
 * @author Girish
 */
public class TestReportEntryUI extends IFrame {
	private static final long serialVersionUID = 1L;

	private javax.swing.JButton btnAddToTable;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnResetHist;
	private javax.swing.JButton btnSaveReport;
	private javax.swing.JButton btnShowHist;
	private DateButton dpkReportDateHist;
	private javax.swing.JComboBox<String> cboReportIdHist;
	private javax.swing.JComboBox<String> cboFullNameHist;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jspSalesNote;
	private javax.swing.JScrollPane jspSalesNote1;
	private javax.swing.JTabbedPane jtpTestReport;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblFullName1;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblPhone1;
	private javax.swing.JLabel lblPhone2;
	private javax.swing.JLabel lblPhone3;
	private javax.swing.JLabel lblPhone5;
	private javax.swing.JLabel lblPhone6;
	private javax.swing.JLabel lblSalesNote;
	private javax.swing.JLabel lblSalesNote1;
	private javax.swing.JTable tblProdList;
	private javax.swing.JTextArea txtAddress;
	private javax.swing.JTextField txtFullName;
	private javax.swing.JTextField txtPhone;
	private JSuggestionField txtProdName;
	private javax.swing.JTextField txtProductStatus;
	private javax.swing.JTextArea txtReportSummary;
	private javax.swing.JTextField txtReportTitle;

	public TestReportEntryUI() {
		super(Program.LANG.getProperty("mnitestreport"), 847, 477);
		panel.setLayout(new BorderLayout());
		initComponents();
		lblFullName.setText(Program.LANG.getProperty("lblcustname"));
		lblFullName1.setText(Program.LANG.getProperty("lblcustname"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		lblPhone5.setText(Program.LANG.getProperty("lblreportdate"));
		lblSalesNote.setText(Program.LANG.getProperty("lblcustadd"));
		lblPhone1.setText(Program.LANG.getProperty("lblreporttitle"));
		lblPhone2.setText(Program.LANG.getProperty("lblprodname"));
		lblPhone3.setText(Program.LANG.getProperty("lblproductstatus"));
		lblSalesNote1.setText(Program.LANG.getProperty("lblreportsummary"));
		btnSaveReport.setText(Program.LANG.getProperty("btngenerate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnResetHist.setText(Program.LANG.getProperty("btnreset"));
		btnShowHist.setText(Program.LANG.getProperty("btnshow"));
		lblPhone6.setText(Program.LANG.getProperty("lblreportid"));
		
		dpkReportDateHist.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				TestReportData[] names=new TestReport().getDatewiseDetails(dpkReportDateHist.getDate());
				if(names!=null)
				{
					cboFullNameHist.removeAllItems();
					for(int i=0;i<names.length;i++)
						cboFullNameHist.addItem(names[i].name);
				}
			}
		});
		
		dpkReportDateHist.firePropertyChange("date", false, true);
		
		tblProdList.setDefaultEditor(Boolean.class, new JButtonCellEditor(new JCheckBox()) {
			private static final long serialVersionUID = -915589303563394164L;

			@Override
			public void btnJButtonCellEditor_actionPerformed(ActionEvent arg0) {
				((DefaultTableModel)tblProdList.getModel()).removeRow(super.row);
			}
		});
		tblProdList.setDefaultRenderer(Boolean.class, new JButtonCellRenderer());
		initList();
	}

	private void initList() {
		ArrayList<String> prodlist=new ArrayList<>();
		TestReportProductsData[] data=new TestReportProducts().getAllReportProducts();
		if(data!=null)
		{
			for(int i=0;i<data.length;i++)
			{
				prodlist.add(data[i].prodname);
			}
		}
		txtProdName.setSuggestionList(prodlist);
	}

	private void initComponents() {

		jtpTestReport = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		lblFullName = new javax.swing.JLabel();
		txtFullName = new javax.swing.JTextField();
		lblSalesNote = new javax.swing.JLabel();
		jspSalesNote = new javax.swing.JScrollPane();
		txtAddress = new javax.swing.JTextArea();
		lblPhone = new javax.swing.JLabel();
		txtPhone = new javax.swing.JTextField();
		lblPhone1 = new javax.swing.JLabel();
		txtReportTitle = new javax.swing.JTextField();
		lblPhone2 = new javax.swing.JLabel();
		txtProdName = new JSuggestionField();
		lblPhone3 = new javax.swing.JLabel();
		txtProductStatus = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblProdList = new javax.swing.JTable();
		lblSalesNote1 = new javax.swing.JLabel();
		jspSalesNote1 = new javax.swing.JScrollPane();
		txtReportSummary = new javax.swing.JTextArea();
		btnSaveReport = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		lblFullName1 = new javax.swing.JLabel();
		lblPhone5 = new javax.swing.JLabel();
		cboReportIdHist = new javax.swing.JComboBox<String>();
		cboFullNameHist=new javax.swing.JComboBox<>();
		btnShowHist = new javax.swing.JButton();
		btnResetHist = new javax.swing.JButton();
		btnAddToTable = new javax.swing.JButton();
		lblPhone6=new javax.swing.JLabel();
		dpkReportDateHist=new DateButton();

		FormListener formListener = new FormListener();

		jtpTestReport.setName("jtpTestReport"); // NOI18N

		jPanel1.setName("jPanel1"); // NOI18N
		jPanel1.setLayout(null);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Customer Name");
		lblFullName.setName("lblFullName"); // NOI18N
		jPanel1.add(lblFullName);
		lblFullName.setBounds(10, 10, 220, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		txtFullName.setName("txtFullName"); // NOI18N
		jPanel1.add(txtFullName);
		txtFullName.setBounds(230, 10, 200, 30);

		lblSalesNote.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesNote.setText("Address");
		lblSalesNote.setName("lblCustAddress"); // NOI18N
		jPanel1.add(lblSalesNote);
		lblSalesNote.setBounds(440, 10, 180, 70);

		jspSalesNote.setName("jspAddress"); // NOI18N

		txtAddress.setColumns(20);
		txtAddress.setFont(Statics.NORMAL_LARGE_FONT);
		txtAddress.setLineWrap(true);
		txtAddress.setRows(1);
		txtAddress.setName("txtAddress"); // NOI18N
		jspSalesNote.setViewportView(txtAddress);

		jPanel1.add(jspSalesNote);
		jspSalesNote.setBounds(620, 10, 200, 70);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		lblPhone.setName("lblPhone"); // NOI18N
		jPanel1.add(lblPhone);
		lblPhone.setBounds(10, 50, 220, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		txtPhone.setName("txtPhone"); // NOI18N
		jPanel1.add(txtPhone);
		txtPhone.setBounds(230, 50, 200, 30);

		lblPhone1.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone1.setText("Report Title");
		lblPhone1.setName("lblReportTitle"); // NOI18N
		jPanel1.add(lblPhone1);
		lblPhone1.setBounds(10, 90, 220, 30);

		txtReportTitle.setFont(Statics.NORMAL_LARGE_FONT);
		txtReportTitle.setToolTipText("");
		txtReportTitle.setName("txtReportTitle"); // NOI18N
		jPanel1.add(txtReportTitle);
		txtReportTitle.setBounds(230, 90, 590, 30);

		lblPhone2.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone2.setText("Product Name");
		lblPhone2.setName("lblPhone2"); // NOI18N
		jPanel1.add(lblPhone2);
		lblPhone2.setBounds(10, 130, 220, 30);

		txtProdName.setFont(Statics.NORMAL_LARGE_FONT);
		txtProdName.setToolTipText("");
		txtProdName.setName("txtProdName"); // NOI18N
		jPanel1.add(txtProdName);
		txtProdName.setBounds(230, 130, 140, 30);

		lblPhone3.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone3.setText("Product Status");
		lblPhone3.setName("lblPhone3"); // NOI18N
		jPanel1.add(lblPhone3);
		lblPhone3.setBounds(380, 130, 180, 30);

		txtProductStatus.setFont(Statics.NORMAL_LARGE_FONT);
		txtProductStatus.setToolTipText("");
		txtProductStatus.setName("txtProductStatus"); // NOI18N
		jPanel1.add(txtProductStatus);
		txtProductStatus.setBounds(560, 130, 140, 30);

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		tblProdList.setAutoCreateRowSorter(true);
		tblProdList
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] {

						}, new String[] { "Product Name", "Product Status",
								"Delete" }) {
					private static final long serialVersionUID = 1L;
					Class<?>[] types = new Class[] { java.lang.String.class,
							java.lang.String.class, java.lang.Boolean.class };

					public Class<?> getColumnClass(int columnIndex) {
						return types[columnIndex];
					}
				});
		tblProdList.setName("tblProdList"); // NOI18N
		jScrollPane1.setViewportView(tblProdList);

		jPanel1.add(jScrollPane1);
		jScrollPane1.setBounds(10, 170, 810, 150);

		lblSalesNote1.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesNote1.setText("Report Summary");
		lblSalesNote1.setName("lblSalesNote1"); // NOI18N
		jPanel1.add(lblSalesNote1);
		lblSalesNote1.setBounds(10, 330, 180, 80);

		jspSalesNote1.setName("jspSalesNote1"); // NOI18N

		txtReportSummary.setColumns(20);
		txtReportSummary.setFont(Statics.NORMAL_LARGE_FONT);
		txtReportSummary.setLineWrap(true);
		txtReportSummary.setRows(1);
		txtReportSummary.setName("txtReportSummary"); // NOI18N
		jspSalesNote1.setViewportView(txtReportSummary);

		jPanel1.add(jspSalesNote1);
		jspSalesNote1.setBounds(190, 330, 200, 80);

		btnSaveReport.setFont(Statics.NORMAL_LARGE_FONT);
		btnSaveReport.setText("Generate Report");
		btnSaveReport.setName("btnSaveReport"); // NOI18N
		btnSaveReport.addActionListener(formListener);
		jPanel1.add(btnSaveReport);
		btnSaveReport.setBounds(400, 330, 200, 80);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		btnReset.setName("btnReset"); // NOI18N
		btnReset.addActionListener(formListener);
		jPanel1.add(btnReset);
		btnReset.setBounds(620, 330, 200, 80);
		
		btnAddToTable.setFont(Statics.NORMAL_LARGE_FONT);
        btnAddToTable.setText("+");
        btnAddToTable.setName("btnAddToTable"); // NOI18N
        btnAddToTable.addActionListener(formListener);
        jPanel1.add(btnAddToTable);
        btnAddToTable.setBounds(710, 130, 110, 30);

		jtpTestReport.addTab("Test Report Entry", jPanel1);

		jPanel2.setName("jPanel2"); // NOI18N
		jPanel2.setLayout(null);

		lblFullName1.setFont(Statics.NORMAL_LARGE_FONT);
        lblFullName1.setText("Customer Name");
        lblFullName1.setName("lblFullName1"); // NOI18N
        jPanel2.add(lblFullName1);
        lblFullName1.setBounds(200, 130, 220, 30);

        lblPhone5.setFont(Statics.NORMAL_LARGE_FONT);
        lblPhone5.setText("Report Date");
        lblPhone5.setName("lblPhone5"); // NOI18N
        jPanel2.add(lblPhone5);
        lblPhone5.setBounds(200, 90, 220, 30);

        cboReportIdHist.setFont(Statics.NORMAL_LARGE_FONT);
        cboReportIdHist.setName("cboReportIdHist"); // NOI18N
        jPanel2.add(cboReportIdHist);
        cboReportIdHist.setBounds(420, 170, 200, 30);

        btnShowHist.setFont(Statics.NORMAL_LARGE_FONT);
        btnShowHist.setText("Show");
        btnShowHist.setName("btnShowHist"); // NOI18N
        btnShowHist.addActionListener(formListener);
        jPanel2.add(btnShowHist);
        btnShowHist.setBounds(260, 210, 150, 80);

        btnResetHist.setFont(Statics.NORMAL_LARGE_FONT);
        btnResetHist.setText("Reset");
        btnResetHist.setName("btnResetHist"); // NOI18N
        btnResetHist.addActionListener(formListener);
        jPanel2.add(btnResetHist);
        btnResetHist.setBounds(420, 210, 150, 80);

        dpkReportDateHist.setFont(Statics.NORMAL_LARGE_FONT);
        dpkReportDateHist.setName("btnReportDateHist"); // NOI18N
        jPanel2.add(dpkReportDateHist);
        dpkReportDateHist.setBounds(420, 90, 200, 30);

        lblPhone6.setFont(Statics.NORMAL_LARGE_FONT);
        lblPhone6.setText("Report ID");
        lblPhone6.setName("lblPhone6"); // NOI18N
        jPanel2.add(lblPhone6);
        lblPhone6.setBounds(200, 170, 220, 30);

        cboFullNameHist.setFont(Statics.NORMAL_LARGE_FONT);
        cboFullNameHist.setName("cboFullNameHist"); // NOI18N
        jPanel2.add(cboFullNameHist);
        cboFullNameHist.addActionListener(formListener);
        cboFullNameHist.setBounds(420, 130, 200, 30);

		jtpTestReport.addTab("Report History", jPanel2);
		jtpTestReport.setFont(Statics.NORMAL_LARGE_FONT);

		panel.add(jtpTestReport, java.awt.BorderLayout.CENTER);

		pack();
	}

	private class FormListener implements java.awt.event.ActionListener {

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			if (evt.getSource() == btnSaveReport) {
				TestReportEntryUI.this.btnSaveReportActionPerformed(evt);
			} else if (evt.getSource() == btnReset) {
				TestReportEntryUI.this.btnResetActionPerformed(evt);
			} else if (evt.getSource() == btnShowHist) {
				TestReportEntryUI.this.btnShowHistActionPerformed(evt);
			} else if (evt.getSource() == btnResetHist) {
				TestReportEntryUI.this.btnResetHistActionPerformed(evt);
			} else if (evt.getSource() == btnAddToTable) {
				TestReportEntryUI.this.btnAddToTableActionPerformed(evt);
			} else if (evt.getSource()==cboFullNameHist) {
				TestReportEntryUI.this.cboFullNameHistActionPerformed(evt);
			}
		}
	}

	private void btnSaveReportActionPerformed(java.awt.event.ActionEvent evt) {
		txtProdName.setText("-");
		txtProductStatus.setText("-");
		if(!validateEntry() || txtAddress.getText().equals("") || txtReportSummary.getText().equals("") || tblProdList.getRowCount()<1)
		{
			Statics.showMessage(rootPane, "Please fill all information!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		TestReportData data=new TestReportData();
		data.address=txtAddress.getText();
		data.contactno=txtPhone.getText();
		data.name=txtFullName.getText();
		data.rptsummary=txtReportSummary.getText();
		data.rptdate=new java.util.Date();
		data.rpttitle=txtReportTitle.getText();

		TestReportProductsData[] prods=new TestReportProductsData[tblProdList.getRowCount()];
		for(int i=0;i<tblProdList.getRowCount();i++)
		{
			prods[i]=new TestReportProductsData();
			prods[i].prodname=tblProdList.getValueAt(i, 0).toString();
			prods[i].prodstatus=tblProdList.getValueAt(i, 1).toString();
		}
		TestReport rpt=new TestReport();
		if(rpt.makeTestReportEntry(data, prods)>0)
		{
			Statics.showMessage(rootPane, "Test report entry made successfully!", JOptionPane.INFORMATION_MESSAGE);
			data=rpt.getLastReport();
			HashMap<String, Object> prm=new HashMap<String, Object>();
			prm.put("prmrptid", data.rptid);
			prm.put("prmfirmid", new Firm().getFirmData().firmid);
			ReportViewer view=new ReportViewer("testingreport", prm, rpt.getConnectionObj());
			view.showReport();
			btnResetActionPerformed(null);
		}
		else
		{
			Statics.showMessage(rootPane, "Failed to make test report entry!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
		Statics.resetFields(jPanel1.getComponents());
		((DefaultTableModel) tblProdList.getModel()).setRowCount(0);
		txtAddress.setText("");
		txtReportSummary.setText("");
	}

	private void btnShowHistActionPerformed(java.awt.event.ActionEvent evt) {
		if(!validateHistory() || cboReportIdHist.getItemCount()==0 || cboReportIdHist.getSelectedIndex()<0)
		{
			Statics.showMessage(rootPane, "Please fill all information!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		HashMap<String, Object> prm=new HashMap<String, Object>();
		Firm firm=new Firm();
		prm.put("prmfirmid", firm.getFirmData().firmid);
		prm.put("prmrptid", Long.valueOf(cboReportIdHist.getSelectedItem().toString()));
		ReportViewer view=new ReportViewer("testingreport", prm, firm.getConnectionObj());
		view.showReport();
		btnResetActionPerformed(null);
	}

	private void btnResetHistActionPerformed(java.awt.event.ActionEvent evt) {
		Statics.resetFields(jPanel2.getComponents());
		cboReportIdHist.removeAllItems();
		((DefaultTableModel)tblProdList.getModel()).setRowCount(0);
	}
	
	private void btnAddToTableActionPerformed(java.awt.event.ActionEvent evt) {
		int i=tblProdList.getRowCount();
        ((DefaultTableModel)tblProdList.getModel()).setRowCount(i+1);
        tblProdList.setValueAt(txtProdName.getText(), i, 0);
        tblProdList.setValueAt(txtProductStatus.getText(), i, 1);
        tblProdList.setValueAt("Delete", i, 2);
        txtProdName.setText("");
        txtProductStatus.setText("");
        txtProdName.requestFocus();
    }
	
    private void cboFullNameHistActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	TestReportData[] names=new TestReport().getReportsByDateAndName(dpkReportDateHist.getDate(), cboFullNameHist.getSelectedItem().toString());
		if(names!=null)
		{
			cboReportIdHist.removeAllItems();
			for(int i=0;i<names.length;i++)
				cboReportIdHist.addItem(names[i].rptid+"");
		}
    }
	
	private boolean validateEntry() {
		for (Component c : jPanel1.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("")) {
					return false;
				}
			} else if (c instanceof JTextArea) {
				if (((JTextArea) c).getText().equals("")) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validateHistory() {
		for (Component c : jPanel2.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("")) {
					return false;
				}
			} else if (c instanceof JTextArea) {
				if (((JTextArea) c).getText().equals("")) {
					return false;
				}
			}
		}
		return true;
	}
}
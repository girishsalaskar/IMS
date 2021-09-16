package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Customers;
import com.ims.models.Firm;
import com.ims.models.Products;
import com.ims.models.SalesReturn_M;
import com.ims.models.Sales_M;
import com.ims.models.Sales_S;
import com.ims.models.data.CustomersData;
import com.ims.models.data.ProductsData;
import com.ims.models.data.SalesReturn_MData;
import com.ims.models.data.SalesReturn_SData;
import com.ims.models.data.Sales_MData;
import com.ims.models.data.Sales_SData;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.table.JButtonCellEditor;
import com.jdev.girish.ui.table.JButtonCellRenderer;
import com.jdev.girish.ui.validation.DecimalKeyListener;

/**
 *
 * @author Girish
 */
public class SalesReturnUI extends IFrame {

	// Variables declaration - do not modify
	private javax.swing.JButton btnAddSales;
	private javax.swing.JButton btnReset;
	private javax.swing.JComboBox<String> cboBillNo;
	private javax.swing.JComboBox<String> cboCustId;
	private DateButton dpkSalesDate;
	private javax.swing.JScrollPane jspOrderList;
	private javax.swing.JScrollPane jspProdList;
	private javax.swing.JLabel lblBillNo;
	private javax.swing.JLabel lblCustId;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblPayOrReceive;
	private javax.swing.JLabel lblReturnCharges;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblPrevDr;
	private javax.swing.JLabel lblAmountToPay;
	private javax.swing.JLabel lblSalesDate;
	private javax.swing.JLabel lblSubTotal;
	private javax.swing.JPanel pnlProdList;
	private javax.swing.JTable tblOrderList;
	private javax.swing.JTable tblProdList;
	private javax.swing.JTextField txtCustName;
	private javax.swing.JTextField txtPayOrReceive;
	private javax.swing.JTextField txtReturnCharges;
	private javax.swing.JTextField txtPhone;
	private javax.swing.JTextField txtPrevDr;
	private javax.swing.JTextField txtAmountToPay;
	private javax.swing.JTextField txtSubTotal;
	// End of variables declaration

	private static final long serialVersionUID = 2096192270555019368L;

	public SalesReturnUI() {
		// INFO Constructor
		super(Program.LANG.getProperty("mnisalesreturn"), 843, 602);
		initComponents();

		// TODO Multilanguage Text
		lblCustId.setText(Program.LANG.getProperty("lblcustid"));
		lblFullName.setText(Program.LANG.getProperty("lblcustname"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblSubTotal.setText(Program.LANG.getProperty("lblsubtotal"));
		lblReturnCharges.setText(Program.LANG.getProperty("lblchargeonreturn"));
		lblAmountToPay.setText(Program.LANG.getProperty("lblreturnamtrs"));

		lblSalesDate.setText(Program.LANG.getProperty("lblsalesdate"));
		lblPrevDr.setText(Program.LANG.getProperty("lblprevrecdr"));
		lblPayOrReceive.setText(Program.LANG.getProperty("lblemppayorreceive"));
		btnAddSales.setText(Program.LANG.getProperty("btnaddreturn"));
		lblBillNo.setText(Program.LANG.getProperty("lblbillno"));

		txtReturnCharges.addKeyListener(new DecimalKeyListener());
		tblOrderList.getColumn("Add")
				.setCellRenderer(new JButtonCellRenderer());
		tblOrderList.getColumn("Add").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {
					private static final long serialVersionUID = -1877857233832574061L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent arg0) {
						if (this.row >= 0) {
							((DefaultTableModel) tblProdList.getModel())
									.setRowCount(tblProdList.getRowCount() + 1);
							DefaultTableModel dtm = (DefaultTableModel) table
									.getModel();
							tblProdList.setValueAt(table.getValueAt(row, 0),
									tblProdList.getRowCount() - 1, 0);
							tblProdList.setValueAt(table.getValueAt(row, 1),
									tblProdList.getRowCount() - 1, 1);
							tblProdList.setValueAt(table.getValueAt(row, 2),
									tblProdList.getRowCount() - 1, 2);
							tblProdList.setValueAt(table.getValueAt(row, 3),
									tblProdList.getRowCount() - 1, 3);
							if (table.getValueAt(row, 2) != null
									&& table.getValueAt(row, 3) != null)
								tblProdList.setValueAt((Float.parseFloat(table
										.getValueAt(row, 2).toString()) * Float
										.parseFloat(table.getValueAt(row, 3)
												.toString())), tblProdList
										.getRowCount() - 1, 4);
							tblProdList.setValueAt("<",
									tblProdList.getRowCount() - 1, 5);
							dtm.removeRow(this.row);
						}
					}
				});
		tblProdList.getColumn("Remove").setCellRenderer(
				new JButtonCellRenderer());
		tblProdList.getColumn("Remove").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {

					private static final long serialVersionUID = -8751644411822062531L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent arg0) {
						if (table.getValueAt(row, 5).toString().equals("<")) {
							((DefaultTableModel) tblOrderList.getModel())
									.setRowCount(tblOrderList.getRowCount() + 1);
							tblOrderList.setValueAt(table.getValueAt(row, 0),
									tblOrderList.getRowCount() - 1, 0);
							tblOrderList.setValueAt(table.getValueAt(row, 1),
									tblOrderList.getRowCount() - 1, 1);
							tblOrderList.setValueAt(table.getValueAt(row, 2),
									tblOrderList.getRowCount() - 1, 2);
							tblOrderList.setValueAt(table.getValueAt(row, 3),
									tblOrderList.getRowCount() - 1, 3);
							tblOrderList.setValueAt(">",
									tblOrderList.getRowCount() - 1, 4);
						}
						((DefaultTableModel) tblProdList.getModel())
								.removeRow(row);
					}
				});
		dpkSalesDate.firePropertyChange("date", false, true);
	}

	private void initComponents() {

		lblSalesDate = new javax.swing.JLabel();
		dpkSalesDate = new DateButton();
		lblCustId = new javax.swing.JLabel();
		lblFullName = new javax.swing.JLabel();
		txtCustName = new javax.swing.JTextField();
		txtPhone = new javax.swing.JTextField();
		lblPhone = new javax.swing.JLabel();
		pnlProdList = new javax.swing.JPanel();
		jspOrderList = new javax.swing.JScrollPane();
		tblOrderList = new javax.swing.JTable();
		jspProdList = new javax.swing.JScrollPane();
		tblProdList = new javax.swing.JTable();
		lblSubTotal = new javax.swing.JLabel();
		txtSubTotal = new javax.swing.JTextField();
		lblPayOrReceive = new javax.swing.JLabel();
		txtPayOrReceive = new javax.swing.JTextField();
		txtAmountToPay = new javax.swing.JTextField();
		lblAmountToPay = new javax.swing.JLabel();
		txtReturnCharges = new javax.swing.JTextField();
		lblReturnCharges = new javax.swing.JLabel();
		lblPrevDr = new javax.swing.JLabel();
		txtPrevDr = new javax.swing.JTextField();
		btnAddSales = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		lblBillNo = new javax.swing.JLabel();
		cboBillNo = new javax.swing.JComboBox<>();
		cboCustId = new javax.swing.JComboBox<>();

		FormListener formListener = new FormListener();

		panel.setLayout(null);

		lblSalesDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesDate.setText("Sales Date");
		lblSalesDate.setName("lblSalesDate"); // NOI18N
		panel.add(lblSalesDate);
		lblSalesDate.setBounds(10, 10, 220, 30);

		dpkSalesDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkSalesDate.setName("dpkSalesDate"); // NOI18N
		dpkSalesDate.addPropertyChangeListener("date", formListener);
		panel.add(dpkSalesDate);
		dpkSalesDate.setBounds(230, 10, 200, 30);

		lblCustId.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustId.setText("Customer ID");
		lblCustId.setName("lblCustId"); // NOI18N
		panel.add(lblCustId);
		lblCustId.setBounds(440, 10, 190, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Customer Name");
		lblFullName.setName("lblFullName"); // NOI18N
		panel.add(lblFullName);
		lblFullName.setBounds(10, 50, 220, 30);

		cboCustId.setName("cboCustId"); // NOI18N
        cboCustId.addActionListener(formListener);
        panel.add(cboCustId);
        cboCustId.setFont(Statics.NORMAL_LARGE_FONT);
        cboCustId.setBounds(630, 10, 190, 30);

		txtPhone.setEditable(false);
		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		txtPhone.setName("txtPhone"); // NOI18N
		panel.add(txtPhone);
		txtPhone.setBounds(630, 50, 190, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		lblPhone.setName("lblPhone"); // NOI18N
		panel.add(lblPhone);
		lblPhone.setBounds(440, 50, 190, 30);

		pnlProdList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Products list for Sales Return",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));
		pnlProdList.setName("pnlProdList"); // NOI18N
		pnlProdList.setLayout(null);

		jspOrderList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Previous Sold Products",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));
		jspOrderList.setName("jspOrderList"); // NOI18N

		tblOrderList.setAutoCreateRowSorter(true);
		tblOrderList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product ID", "Product Name", "Units",
						"Sales Rate", "Add" }) {
			/**
							 * 
							 */
			private static final long serialVersionUID = -8259615416924322911L;
			Class<?>[] types = new Class[] { java.lang.Long.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Float.class, java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, false, false,
					true };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblOrderList.setName("tblOrderList"); // NOI18N
		jspOrderList.setViewportView(tblOrderList);

		pnlProdList.add(jspOrderList);
		jspOrderList.setBounds(20, 30, 390, 240);

		jspProdList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Items to be Returned",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));
		jspProdList.setName("jspProdList"); // NOI18N

		tblProdList.setAutoCreateRowSorter(true);
		tblProdList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product ID", "Product Name", "Units",
						"Sales Rate", "Total", "Remove" }) {
			/**
							 * 
							 */
			private static final long serialVersionUID = -777539018586450624L;
			Class<?>[] types = new Class[] { java.lang.Long.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Float.class, java.lang.Float.class,
					java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, true, true, true,
					true };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblProdList.setName("tblProdList"); // NOI18N
		tblProdList.getModel().addTableModelListener(formListener);
		jspProdList.setViewportView(tblProdList);

		pnlProdList.add(jspProdList);
		jspProdList.setBounds(410, 30, 390, 240);

		panel.add(pnlProdList);
		pnlProdList.setBounds(10, 130, 810, 280);
		pnlProdList.getAccessibleContext().setAccessibleName(
				"Products list for Return");

		lblSubTotal.setFont(Statics.NORMAL_LARGE_FONT);
		lblSubTotal.setText("Total Amount");
		lblSubTotal.setName("lblSubTotal"); // NOI18N
		panel.add(lblSubTotal);
		lblSubTotal.setBounds(20, 420, 120, 30);

		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(Statics.NORMAL_LARGE_FONT);
		txtSubTotal.setText("0");
		txtSubTotal.setToolTipText("");
		txtSubTotal.setName("txtSubTotal"); // NOI18N
		panel.add(txtSubTotal);
		txtSubTotal.setBounds(20, 450, 120, 30);

		lblPayOrReceive.setFont(Statics.NORMAL_LARGE_FONT);
        lblPayOrReceive.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPayOrReceive.setText("New Receivable Dr.");
        lblPayOrReceive.setName("lblPayOrReceive"); // NOI18N
        panel.add(lblPayOrReceive);
        lblPayOrReceive.setBounds(630, 420, 170, 30);

        txtPayOrReceive.setEditable(false);
        txtPayOrReceive.setFont(Statics.NORMAL_LARGE_FONT);
        txtPayOrReceive.setText("0");
        txtPayOrReceive.setToolTipText("");
        txtPayOrReceive.setName("txtPayOrReceive"); // NOI18N
        panel.add(txtPayOrReceive);
        txtPayOrReceive.setBounds(630, 450, 170, 30);

        txtAmountToPay.setEditable(false);
        txtAmountToPay.setFont(Statics.NORMAL_LARGE_FONT);
        txtAmountToPay.setText("0");
        txtAmountToPay.setToolTipText("");
        txtAmountToPay.setName("txtAmountToPay"); // NOI18N
        panel.add(txtAmountToPay);
        txtAmountToPay.setBounds(480, 450, 140, 30);

        lblAmountToPay.setFont(Statics.NORMAL_LARGE_FONT);
        lblAmountToPay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAmountToPay.setText("Return Amount");
        lblAmountToPay.setName("lblAmountToPay"); // NOI18N
        panel.add(lblAmountToPay);
        lblAmountToPay.setBounds(480, 420, 140, 30);

        txtReturnCharges.setFont(Statics.NORMAL_LARGE_FONT);
        txtReturnCharges.setText("0");
        txtReturnCharges.setToolTipText("");
        txtReturnCharges.setName("txtReturnCharges"); // NOI18N
        txtReturnCharges.addFocusListener(formListener);
        panel.add(txtReturnCharges);
        txtReturnCharges.setBounds(150, 450, 130, 30);

        lblReturnCharges.setFont(Statics.NORMAL_LARGE_FONT);
        lblReturnCharges.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReturnCharges.setText("Paid Amtount");
        lblReturnCharges.setName("lblReturnCharges"); // NOI18N
        panel.add(lblReturnCharges);
        lblReturnCharges.setBounds(150, 420, 130, 30);

        lblPrevDr.setFont(Statics.NORMAL_LARGE_FONT);
        lblPrevDr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrevDr.setText("Prev. Receivable Dr.");
        lblPrevDr.setName("lblPrevDr"); // NOI18N
        panel.add(lblPrevDr);
        lblPrevDr.setBounds(290, 420, 180, 30);

        txtPrevDr.setEditable(false);
        txtPrevDr.setFont(Statics.NORMAL_LARGE_FONT);
        txtPrevDr.setText("0");
        txtPrevDr.setToolTipText("");
        txtPrevDr.setName("txtPrevDr"); // NOI18N
        panel.add(txtPrevDr);
        txtPrevDr.setBounds(290, 450, 180, 30);

		btnAddSales.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddSales.setText("Add Purchases Entry");
		btnAddSales.setName("btnAddSales"); // NOI18N
		btnAddSales.addActionListener(formListener);
		panel.add(btnAddSales);
		btnAddSales.setBounds(200, 490, 210, 80);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset All");
		btnReset.setName("btnReset"); // NOI18N
		btnReset.addActionListener(formListener);
		panel.add(btnReset);
		btnReset.setBounds(420, 490, 210, 80);

		lblBillNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblBillNo.setText("Bill Number");
		lblBillNo.setName("lblBillNo"); // NOI18N
		panel.add(lblBillNo);
		lblBillNo.setBounds(10, 90, 220, 30);

		cboBillNo.setName("cboBillNo"); // NOI18N
		cboBillNo.addActionListener(formListener);
		cboBillNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(cboBillNo);
		cboBillNo.setBounds(230, 90, 200, 30);

		txtCustName.setEditable(false);
        txtCustName.setFont(Statics.NORMAL_LARGE_FONT);
        txtCustName.setName("txtCustName"); // NOI18N
        panel.add(txtCustName);
        txtCustName.setBounds(230, 50, 200, 30);
	}

	private class FormListener implements java.awt.event.ActionListener,
			java.awt.event.FocusListener, java.beans.PropertyChangeListener, TableModelListener {
		FormListener() {
		}

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			if (evt.getSource() == cboBillNo) {
				SalesReturnUI.this.cboBillNoActionPerformed(evt);
			} else if (evt.getSource() == btnAddSales) {
				SalesReturnUI.this.btnAddSalesActionPerformed(evt);
			} else if (evt.getSource() == btnReset) {
				SalesReturnUI.this.btnResetActionPerformed(evt);
			} else if (evt.getSource() == cboCustId) {
				SalesReturnUI.this.cboCustIdActionPerformed(evt);
			}
		}

		public void focusGained(java.awt.event.FocusEvent evt) {
		}

		public void focusLost(java.awt.event.FocusEvent evt) {
			if (evt.getSource() == txtReturnCharges) {
				SalesReturnUI.this.txtReturnChargesFocusLost(evt);
			}
		}

		public void propertyChange(java.beans.PropertyChangeEvent evt) {
			if (evt.getSource() == dpkSalesDate) {
				SalesReturnUI.this.dpkSalesDatePropertyChange(evt);
			}
		}

		@Override
		public void tableChanged(TableModelEvent evt) {
			if(evt.getSource()==tblProdList.getModel()){
				SalesReturnUI.this.tblProdListTableChanged(evt);
			}
		}
	}// </editor-fold>

	private void tblProdListTableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE) {/*
			if (e.getColumn() == 0) {
				if (tblProdList.getValueAt(e.getLastRow(), 0) != null) {
					String newval = tblProdList.getValueAt(
							e.getLastRow(), 0).toString();
					for (int i = 0; i < tblProdList.getRowCount(); i++) {
						if (newval.equals(tblProdList.getValueAt(i, 0)
								.toString()) && i != e.getLastRow()) {
							Statics.showMessage(
									getContentPane(),
									"The Product Already Exists! Please do not repeat the product!",
									JOptionPane.ERROR_MESSAGE);
							((DefaultTableModel)tblProdList.getModel()).removeRow(e.getLastRow());
							return;
						}
					}
				}
			} else */if (e.getColumn() == 3 || e.getColumn() == 2) {
				if (tblProdList.getValueAt(e.getLastRow(), 3) != null
						&& tblProdList.getValueAt(e.getLastRow(), 2) != null) {
					float salesamt = 0;
					int units = 0;
					salesamt = Float.parseFloat(tblProdList.getValueAt(
							e.getLastRow(), 3).toString());
					units = Integer.parseInt(tblProdList.getValueAt(
							e.getLastRow(), 2).toString());
					salesamt = salesamt * units;
					tblProdList.setValueAt(salesamt, e.getLastRow(), 4);
				}
			}
		}
		calcTotal();
	}

	private void cboCustIdActionPerformed(ActionEvent evt) {
		if(cboCustId.getSelectedIndex()>-1)
		{
			Sales_MData[] sales=new Sales_M().getSalesData(Long.parseLong(cboCustId.getSelectedItem().toString()), dpkSalesDate.getDate());
			if(sales!=null)
			{
				CustomersData data=new Customers().getCustomerById(Long.parseLong(cboCustId.getSelectedItem().toString()));
				if(data!=null)
				{
					txtCustName.setText(data.custname);
					txtPhone.setText(data.custphone);
					txtPrevDr.setText(data.debtamt+"");
				}
				for(int i=0;i<sales.length;i++)
				{
					cboBillNo.addItem(sales[i].salemid+"");
				}
			}
		}
	}

	private void cboBillNoActionPerformed(java.awt.event.ActionEvent evt) {
		if(cboBillNo.getSelectedIndex()>-1)
		{
			updateOrderDetails();
		}
	}

	private void txtReturnChargesFocusLost(java.awt.event.FocusEvent evt) {
		if(txtReturnCharges.getText().equals(""))
		{
			txtReturnCharges.setText("0");
		}
		updateGrandTotal();
	}

	private void btnAddSalesActionPerformed(java.awt.event.ActionEvent evt) {
		if(validateFields())
		{
			SalesReturn_MData mdata=new SalesReturn_MData();
			mdata.salesmid=Long.parseLong(cboBillNo.getSelectedItem().toString());
			mdata.salesreturndate=new java.util.Date();
			mdata.custid=Long.parseLong(cboCustId.getSelectedItem().toString());
			mdata.prevdr=Double.parseDouble(txtPrevDr.getText());
			mdata.returnamount=Double.parseDouble(txtAmountToPay.getText());
			mdata.returncharge=Double.parseDouble(txtReturnCharges.getText());
			mdata.total=Double.parseDouble(txtSubTotal.getText());
			SalesReturn_SData[] sdata=new SalesReturn_SData[tblProdList.getRowCount()];
			for(int i=0;i<tblProdList.getRowCount();i++)
			{
				sdata[i]=new SalesReturn_SData();
				sdata[i].prodid=Long.parseLong(tblProdList.getValueAt(i, 0).toString());
				sdata[i].prodqty=Integer.parseInt(tblProdList.getValueAt(i, 2).toString());
				sdata[i].prodrate=Float.parseFloat(tblProdList.getValueAt(i, 3).toString());
				sdata[i].totrate=Float.parseFloat(tblProdList.getValueAt(i, 4).toString());
			}
			SalesReturn_M master=new SalesReturn_M();
			int i=master.makeSalesReturnEntry(mdata, sdata);
			if(i>0)
			{
				Statics.showMessage(rootPane, "Sales Return entry made successfully!", JOptionPane.INFORMATION_MESSAGE);
				SalesReturn_MData mrdata=new SalesReturn_M().getSalesReturnData(Long.parseLong(cboBillNo.getSelectedItem().toString()));
				if(mrdata!=null)
				{
					HashMap<String, Object> map=new HashMap<>();
					map.put("prmfirmid", new Firm().getFirmData().firmid);
					map.put("prmsalemid", mrdata.salereturnmid);
					ReportViewer view=new ReportViewer("salesreturnreceipt", map, master.getConnectionObj());
					view.showReport();
				}
				dpkSalesDate.firePropertyChange("date", false, true);
			}
			else
			{
				Statics.showMessage(rootPane, "Sales Return entry not made successfully!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
		cboCustId.removeAllItems();
		cboBillNo.removeAllItems();
		((DefaultTableModel)tblOrderList.getModel()).setRowCount(0);
		((DefaultTableModel)tblProdList.getModel()).setRowCount(0);
		Component[] c=panel.getComponents();
		for(int i=0;i<c.length;i++)
		{
			if(c[i] instanceof JTextComponent)
			{
				((JTextComponent)c[i]).setText("");
			}
		}
	}

	private void dpkSalesDatePropertyChange(java.beans.PropertyChangeEvent evt) {
		btnResetActionPerformed(null);
		Sales_MData[] mdata=new Sales_M().getSalesForReturnByDate(dpkSalesDate.getDate());
		if(mdata!=null)
		{
			for(int i=0;i<mdata.length;i++)
			{
				cboCustId.addItem(mdata[i].custid+"");
			}
		}
	}
	
	private void updateGrandTotal() {
		calcTotal();
		if (txtSubTotal.getText().equals(""))
		{
			Statics.showMessage(rootPane, "No any return amount available!", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		txtSubTotal.setText(Float.parseFloat(txtSubTotal.getText()) + "");

		float amttopay = Float.parseFloat(txtSubTotal.getText());
		float returncharges = Float.parseFloat(txtReturnCharges.getText());
		float prevbal = Float.parseFloat(txtPrevDr.getText());
		amttopay=amttopay-returncharges;
		txtAmountToPay.setText(amttopay+"");
		amttopay=amttopay-prevbal;
		if(amttopay>0)
			txtPayOrReceive.setText("PAY: "+amttopay);
		else
			txtPayOrReceive.setText("REC: "+(-amttopay));
	}
	
	private void calcTotal() {
		float total = 0;
		for (int i = 0; i < tblProdList.getRowCount(); i++) {
			if (tblProdList.getValueAt(i, 4) != null)
				total += Float.parseFloat(tblProdList.getValueAt(i, 4)
						.toString());
		}
		txtSubTotal.setText(total + "");
	}

	private void updateOrderDetails() {
		if (cboBillNo.getSelectedIndex() > -1) {

			long somid = Long
					.parseLong(cboBillNo.getSelectedItem().toString());
			Sales_MData mdata=new Sales_M().getSalesById(somid);
			Sales_SData[] sdata=new Sales_S().getSalesDetailsByMasterId(mdata.salemid);
//			SalesOrder_MData somdata = new SalesOrder_M().getSalesOrder(somid);
//			new Customers().getCustomerById(somdata.custid);
//			SalesOrder_SData[] sdata = new SalesOrder_S()
//					.getSalesOrderByMID(somid);
			if (sdata != null) {
				for (int i = 0; i < sdata.length; i++) {
					ProductsData data = new Products()
							.getProductById(sdata[i].prodid);
					((DefaultTableModel)tblOrderList.getModel()).setRowCount(i + 1);
					tblOrderList.setValueAt(sdata[i].prodid, i, 0);
					tblOrderList.setValueAt(data.prodname, i, 1);
					tblOrderList.setValueAt(sdata[i].prodqty, i, 2);
					tblOrderList.setValueAt(data.salesrate, i, 3);
					tblOrderList.setValueAt(">", i, 4);
				}
			} else
				((DefaultTableModel)tblOrderList.getModel()).setRowCount(0);
		}
	}
	
	private boolean validateFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter All Fields!",
							JOptionPane.ERROR_MESSAGE);
					((JTextField) c).requestFocus();
					return false;
				}
			} else if (c instanceof JTextArea) {
				if (((JTextArea) c).getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter All Fields",
							JOptionPane.ERROR_MESSAGE);
					((JTextArea) c).requestFocus();
					return false;
				}
			}
		}
		if(tblProdList.getRowCount()<1)
		{
			Statics.showMessage(getContentPane(), "Please selecte products that has been returned!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
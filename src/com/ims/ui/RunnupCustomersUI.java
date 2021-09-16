package com.ims.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.RunnupCustomers;
import com.ims.models.Sales_M;
import com.ims.models.data.RunnupCustomerData;
import com.ims.reports.ReportViewer;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.table.JButtonCellEditor;
import com.jdev.girish.ui.table.JButtonCellRenderer;
import com.jdev.girish.ui.validation.DecimalKeyListener;

/**
 *
 * @author Girish
 */
public class RunnupCustomersUI extends IFrame {

	private static final long serialVersionUID = 8948749199843610933L;
	
	private javax.swing.JButton btnAddToTable;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnSaveReport;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblGrandTotal;
	private javax.swing.JLabel lblProdName;
	private javax.swing.JLabel lblPrice;
	private javax.swing.JTable tblProdList;
	private javax.swing.JTextField txtFullName;
	private javax.swing.JTextField txtGrandTotal;
	private JSuggestionField txtProdName;
	private javax.swing.JTextField txtProductStatus;

	public RunnupCustomersUI() {
		super(Program.LANG.getProperty("mniquicksales"), 827, 249);
		initComponents();
		
		lblFullName.setText(Program.LANG.getProperty("lblcustname"));
		lblProdName.setText(Program.LANG.getProperty("lblprodname"));
		lblPrice.setText(Program.LANG.getProperty("lblsalesrateperunit"));
		btnAddToTable.setText(Program.LANG.getProperty("btnaddtosaleslist"));
		btnSaveReport.setText(Program.LANG.getProperty("btnaddsales"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblGrandTotal.setText(Program.LANG.getProperty("lbltotal"));
		
		txtProductStatus.addKeyListener(new DecimalKeyListener());
		txtGrandTotal.addKeyListener(new DecimalKeyListener());
		tblProdList.getColumn("Delete").setCellRenderer(new JButtonCellRenderer());
		tblProdList.getColumn("Delete").setCellEditor(new JButtonCellEditor(new JCheckBox()) {
			private static final long serialVersionUID = 8532378615266054624L;

			@Override
			public void btnJButtonCellEditor_actionPerformed(ActionEvent arg0) {
				((DefaultTableModel)tblProdList.getModel()).removeRow(row);
				updateGrandTotal();
			}
		});
		initList();
	}

	private void initList() {
		ArrayList<String> list=new ArrayList<>();
		RunnupCustomerData[] data=new RunnupCustomers().getAllData();
		if(data!=null)
		{
			for(int i=0;i<data.length;i++)
			{
				list.add(data[i].prodname);
			}
		}
		txtProdName.setSuggestionList(list);
	}

	private void initComponents() {

		lblFullName = new javax.swing.JLabel();
		txtFullName = new javax.swing.JTextField();
		lblProdName = new javax.swing.JLabel();
		txtProdName = new JSuggestionField();
		lblPrice = new javax.swing.JLabel();
		txtProductStatus = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblProdList = new javax.swing.JTable();
		btnSaveReport = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		btnAddToTable = new javax.swing.JButton();
		lblGrandTotal = new javax.swing.JLabel();
		txtGrandTotal = new javax.swing.JTextField();

		FormListener formListener = new FormListener();

		panel.setLayout(null);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Customer Name");
		lblFullName.setName("lblFullName"); // NOI18N
		panel.add(lblFullName);
		lblFullName.setBounds(10, 10, 190, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		txtFullName.setName("txtFullName"); // NOI18N
		panel.add(txtFullName);
		txtFullName.setBounds(200, 10, 200, 30);

		lblProdName.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdName.setText("Product Name");
		lblProdName.setName("lblProdName"); // NOI18N
		panel.add(lblProdName);
		lblProdName.setBounds(10, 50, 190, 30);

		txtProdName.setFont(Statics.NORMAL_LARGE_FONT);
		txtProdName.setToolTipText("");
		txtProdName.setName("txtProdName"); // NOI18N
		panel.add(txtProdName);
		txtProdName.setBounds(200, 50, 200, 30);

		lblPrice.setFont(Statics.NORMAL_LARGE_FONT);
		lblPrice.setText("Price");
		lblPrice.setName("lblPrice"); // NOI18N
		panel.add(lblPrice);
		lblPrice.setBounds(10, 90, 190, 30);

		txtProductStatus.setFont(Statics.NORMAL_LARGE_FONT);
		txtProductStatus.setToolTipText("");
		txtProductStatus.setName("txtProductStatus"); // NOI18N
		panel.add(txtProductStatus);
		txtProductStatus.setBounds(200, 90, 200, 30);

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		tblProdList.setAutoCreateRowSorter(true);
		tblProdList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product Name", "Price", "Delete" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2038861045033575406L;
			Class<?>[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.Object.class };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		tblProdList.setName("tblProdList"); // NOI18N
		jScrollPane1.setViewportView(tblProdList);

		panel.add(jScrollPane1);
		jScrollPane1.setBounds(410, 10, 390, 140);

		btnSaveReport.setFont(Statics.NORMAL_LARGE_FONT);
		btnSaveReport.setText("Save");
		btnSaveReport.setName("btnSaveReport"); // NOI18N
		btnSaveReport.addActionListener(formListener);
		panel.add(btnSaveReport);
		btnSaveReport.setBounds(410, 160, 190, 50);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		btnReset.setName("btnReset"); // NOI18N
		btnReset.addActionListener(formListener);
		panel.add(btnReset);
		btnReset.setBounds(610, 160, 190, 50);

		btnAddToTable.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddToTable.setText("+");
		btnAddToTable.setName("btnAddToTable"); // NOI18N
		btnAddToTable.addActionListener(formListener);
		panel.add(btnAddToTable);
		btnAddToTable.setBounds(200, 130, 200, 30);

		lblGrandTotal.setFont(Statics.NORMAL_LARGE_FONT);
		lblGrandTotal.setText("Grand Total");
		lblGrandTotal.setName("lblGrandTotal"); // NOI18N
		panel.add(lblGrandTotal);
		lblGrandTotal.setBounds(10, 170, 190, 30);

		txtGrandTotal.setFont(Statics.NORMAL_LARGE_FONT);
		txtGrandTotal.setToolTipText("");
		txtGrandTotal.setName("txtGrandTotal"); // NOI18N
		panel.add(txtGrandTotal);
		txtGrandTotal.setBounds(200, 170, 200, 30);
	}
	
	private void updateGrandTotal()
	{
		float total=0;
		for(int i=0;i<tblProdList.getRowCount();i++)
		{
			total+=Float.parseFloat(tblProdList.getValueAt(i, 1).toString());
		}
		txtGrandTotal.setText(total+"");
	}

	private class FormListener implements java.awt.event.ActionListener {
		FormListener() {
		}

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			if (evt.getSource() == btnAddToTable) {
				RunnupCustomersUI.this.btnAddToTableActionPerformed(evt);
			} else if (evt.getSource() == btnReset) {
				RunnupCustomersUI.this.btnResetActionPerformed(evt);
			} else if (evt.getSource() == btnSaveReport) {
				RunnupCustomersUI.this.btnSaveReportActionPerformed(evt);
			}
		}
	}

	private void btnAddToTableActionPerformed(java.awt.event.ActionEvent evt) {
		if(txtProdName.getText().equals("") || txtProductStatus.getText().equals(""))
		{
			Statics.showMessage(rootPane, "Please enter product name & Product Price!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int i=tblProdList.getRowCount();
        ((DefaultTableModel)tblProdList.getModel()).setRowCount(i+1);
        tblProdList.setValueAt(txtProdName.getText(), i, 0);
        tblProdList.setValueAt(txtProductStatus.getText(), i, 1);
        tblProdList.setValueAt("Delete", i, 2);
        txtProdName.setText("");
        txtProductStatus.setText("");
        txtProdName.requestFocus();
        updateGrandTotal();
	}

	private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
		Statics.resetFields(panel.getComponents());
		((DefaultTableModel)tblProdList.getModel()).setRowCount(0);
	}

	private void btnSaveReportActionPerformed(java.awt.event.ActionEvent evt) {
		if(txtFullName.getText().equals("") || txtGrandTotal.getText().equals("") || tblProdList.getRowCount()<1)
		{
			Statics.showMessage(rootPane, "Please enter all fields!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		RunnupCustomerData[] prods=new RunnupCustomerData[tblProdList.getRowCount()];
		for(int i=0;i<tblProdList.getRowCount();i++)
		{
			prods[i]=new RunnupCustomerData();
			prods[i].custname=txtFullName.getText();
			prods[i].grandtotal=Float.parseFloat(txtGrandTotal.getText());
			prods[i].salesdate=new java.util.Date();
			prods[i].prodname=tblProdList.getValueAt(i, 0).toString();
			prods[i].prodstatus=Double.parseDouble(tblProdList.getValueAt(i, 1).toString())+"";
		}
		RunnupCustomers cust=new RunnupCustomers();
		if(cust.makeRunnupCustomerEntry(prods)>0)
		{
			Statics.showMessage(rootPane, "Quick sales entry made successfully!", JOptionPane.INFORMATION_MESSAGE);
			HashMap<String, Object> map=new HashMap<>();
			Sales_M master=new Sales_M();
			map.put("prmfirmid", new Firm().getFirmData().firmid);
			map.put("prmcustname", txtFullName.getText());
			map.put("prmsalesdate", new java.util.Date());
			ReportViewer view=new ReportViewer("quicksalesreceipt", map, master.getConnectionObj());
			view.showReport();
			btnResetActionPerformed(null);
		}
		else
		{
			Statics.showMessage(rootPane, "Quick sales entry not made successfully!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
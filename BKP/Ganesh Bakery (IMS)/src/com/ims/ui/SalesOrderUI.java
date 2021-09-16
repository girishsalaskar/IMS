package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Customers;
import com.ims.models.ProdCategory;
import com.ims.models.Products;
import com.ims.models.SalesOrder_M;
import com.ims.models.data.CustomersData;
import com.ims.models.data.ProdCategoryData;
import com.ims.models.data.ProductsData;
import com.ims.models.data.SalesOrder_MData;
import com.ims.models.data.SalesOrder_SData;
import com.ims.ui.common.InlineWindow;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;
import com.jdev.girish.ui.table.JButtonCellEditor;
import com.jdev.girish.ui.table.JButtonCellRenderer;

/**
 *
 * @author Girish
 */
public class SalesOrderUI extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7290225457708094844L;

	private javax.swing.JButton btnAddToList;

	private javax.swing.JButton btnClearAll;
	private javax.swing.JButton btnMakeOrder;
	private javax.swing.JButton btnReset;
	private javax.swing.JComboBox<String> cboProgCatg;
	private DateButton dpkDate;
	private javax.swing.JScrollPane jspOrderNote;
	private javax.swing.JScrollPane jspProdList;
	private javax.swing.JLabel lblDate;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblOrderNote;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblProdCatg;
	private javax.swing.JLabel lblProdId;
	private javax.swing.JLabel lblProduct;
	private javax.swing.JLabel lblQty;
	private javax.swing.JLabel lblSalesRate;
	private javax.swing.JLabel lblCustId;
	private javax.swing.JPanel pnlProdList;
	private javax.swing.JTable tblProdList;
	private JSuggestionField txtFullName;
	private javax.swing.JTextArea txtOrderNote;
	private JSuggestionField txtPhone;
	private javax.swing.JTextField txtProdId;
	private JSuggestionField txtProduct;
	private javax.swing.JTextField txtQty;
	private javax.swing.JTextField txtSalesRate;
	private javax.swing.JTextField txtCustId;

	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;
	private SalesOrderUIListener listen;
	private DefaultTableModel dtmProdList;
	private ArrayList<String> arrProduct;

	public SalesOrderUI() {
		super(Program.LANG.getProperty("mnisalesorder"), 820, 500);
		initComponents();
		dtmProdList = (DefaultTableModel) tblProdList.getModel();

		tblProdList.getColumn("Remove").setCellRenderer(
				new JButtonCellRenderer());
		tblProdList.getColumn("Remove").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {

					private static final long serialVersionUID = -7708626561535042132L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent e) {
						dtmProdList.removeRow(row);
					}
				});

		listen = new SalesOrderUIListener();
		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		arrProduct = new ArrayList<String>();

		txtProduct.setSuggestionList(arrProduct);
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);

		txtProduct.addSuggestionSelectedListener(listen);
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		lblFullName.addMouseListener(listen);
		cboProgCatg.addActionListener(listen);
		btnAddToList.addActionListener(listen);
		btnClearAll.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnMakeOrder.addActionListener(listen);

		initCustomers();
		initProductCategories();

		tblProdList.getColumnModel().getColumn(0).setMinWidth(0);
		tblProdList.getColumnModel().getColumn(0).setMaxWidth(0);
		tblProdList.getColumnModel().getColumn(0).setWidth(0);

		lblSalesRate.setVisible(false);
		txtSalesRate.setVisible(false);
		
		//TODO Multilanguage Text
		lblCustId.setText(Program.LANG.getProperty("lblcustid"));
		lblFullName.setText("<html><body><u style=\"color:blue;\">"+Program.LANG.getProperty("lblsupname")+"</u></body></html>");
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		
		lblDate.setText(Program.LANG.getProperty("lblorderdate"));
		lblOrderNote.setText(Program.LANG.getProperty("lblordernote"));
		lblProdId.setText(Program.LANG.getProperty("lblprodid"));
		lblProdCatg.setText(Program.LANG.getProperty("lblprodcategory"));
		lblProduct.setText(Program.LANG.getProperty("lblprodname"));
		lblQty.setText(Program.LANG.getProperty("lblprodqty"));
		btnAddToList.setText(Program.LANG.getProperty("btnadd"));
		btnClearAll.setText(Program.LANG.getProperty("btnremoveall"));
		btnMakeOrder.setText(Program.LANG.getProperty("btncreateorder"));
	}

	private void initProductCategories() {
		cboProgCatg.removeAllItems();
		ProdCategoryData[] data = new ProdCategory().getAllProductCategories();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				cboProgCatg.addItem(data[i].catname);
			}
		}
	}

	private void initCustomers() {
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

	private void resetAllFields() {
		dtmProdList.setRowCount(0);
		txtProdId.setText("");
		txtCustId.setText("");
		txtFullName.setText("");
		txtOrderNote.setText("");
		txtPhone.setText("");
		txtProduct.setText("");
		txtQty.setText("");
		txtSalesRate.setText("");
	}

	private void initComponents() {

		btnReset = new javax.swing.JButton();
		btnMakeOrder = new javax.swing.JButton();
		jspOrderNote = new javax.swing.JScrollPane();
		txtOrderNote = new javax.swing.JTextArea();
		lblOrderNote = new javax.swing.JLabel();
		txtPhone = new JSuggestionField();
		lblPhone = new javax.swing.JLabel();
		txtFullName = new JSuggestionField();
		dpkDate = new DateButton();
		lblDate = new javax.swing.JLabel();
		txtCustId = new javax.swing.JTextField();
		lblCustId = new javax.swing.JLabel();
		lblFullName = new javax.swing.JLabel();
		pnlProdList = new javax.swing.JPanel();
		lblProduct = new javax.swing.JLabel();
		txtProduct = new JSuggestionField();
		lblQty = new javax.swing.JLabel();
		txtQty = new javax.swing.JTextField();
		btnAddToList = new javax.swing.JButton();
		lblProdId = new javax.swing.JLabel();
		txtProdId = new javax.swing.JTextField();
		jspProdList = new javax.swing.JScrollPane();
		tblProdList = new javax.swing.JTable();
		lblProdCatg = new javax.swing.JLabel();
		cboProgCatg = new javax.swing.JComboBox<String>();
		btnClearAll = new javax.swing.JButton();
		lblSalesRate = new javax.swing.JLabel();
		txtSalesRate = new javax.swing.JTextField();

		panel.setLayout(null);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(410, 420, 190, 60);

		btnMakeOrder.setFont(Statics.NORMAL_LARGE_FONT);
		btnMakeOrder.setText("Create Order");
		panel.add(btnMakeOrder);
		btnMakeOrder.setBounds(200, 420, 190, 60);

		txtOrderNote.setColumns(1);
		txtOrderNote.setFont(Statics.NORMAL_LARGE_FONT);
		txtOrderNote.setLineWrap(true);
		txtOrderNote.setRows(1);
		txtOrderNote.setTabSize(1);
		jspOrderNote.setViewportView(txtOrderNote);

		panel.add(jspOrderNote);
		jspOrderNote.setBounds(610, 50, 180, 70);

		lblOrderNote.setFont(Statics.NORMAL_LARGE_FONT);
		lblOrderNote.setText("Order Note");
		panel.add(lblOrderNote);
		lblOrderNote.setBounds(490, 50, 120, 60);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		panel.add(txtPhone);
		txtPhone.setBounds(230, 90, 250, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 90, 220, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 50, 250, 30);

		dpkDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkDate);
		dpkDate.setBounds(610, 10, 180, 30);

		lblDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblDate.setText("Order Date");
		panel.add(lblDate);
		lblDate.setBounds(490, 10, 120, 30);

		txtCustId.setEditable(false);
		txtCustId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCustId);
		txtCustId.setBounds(230, 10, 250, 30);

		lblCustId.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustId.setText("Customer ID");
		panel.add(lblCustId);
		lblCustId.setBounds(10, 10, 220, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName
				.setText("<html><u style=\"color:blue\">Customer Name</u></html>");
		lblFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(lblFullName);
		lblFullName.setBounds(10, 50, 220, 30);

		pnlProdList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Products list for Order",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));
		pnlProdList.setLayout(null);

		lblProduct.setFont(Statics.NORMAL_LARGE_FONT);
		lblProduct.setText("Product Name");
		pnlProdList.add(lblProduct);
		lblProduct.setBounds(20, 110, 170, 30);

		txtProduct.setFont(Statics.NORMAL_LARGE_FONT);
		pnlProdList.add(txtProduct);
		txtProduct.setBounds(190, 110, 170, 30);

		lblQty.setFont(Statics.NORMAL_LARGE_FONT);
		lblQty.setText("Quantity");
		pnlProdList.add(lblQty);
		lblQty.setBounds(20, 150, 170, 30);

		txtQty.setFont(Statics.NORMAL_LARGE_FONT);
		txtQty.setToolTipText("");
		pnlProdList.add(txtQty);
		txtQty.setBounds(190, 150, 170, 30);

		btnAddToList.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddToList.setText("Add");
		pnlProdList.add(btnAddToList);
		btnAddToList.setBounds(20, 230, 160, 40);

		lblProdId.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdId.setText("Product ID");
		pnlProdList.add(lblProdId);
		lblProdId.setBounds(20, 30, 170, 30);

		txtProdId.setEditable(false);
		txtProdId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlProdList.add(txtProdId);
		txtProdId.setBounds(190, 30, 170, 30);

		tblProdList.setAutoCreateRowSorter(true);
		tblProdList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product ID", "Product Name", "Units",
						"Remove" }) {
			private static final long serialVersionUID = -8275657006540900151L;
			Class<?>[] types = new Class[] { java.lang.Long.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, true, true };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblProdList
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jspProdList.setViewportView(tblProdList);

		pnlProdList.add(jspProdList);
		jspProdList.setBounds(370, 30, 410, 240);

		lblProdCatg.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdCatg.setText("Product Category");
		pnlProdList.add(lblProdCatg);
		lblProdCatg.setBounds(20, 70, 170, 30);

		cboProgCatg.setFont(Statics.NORMAL_LARGE_FONT);
		pnlProdList.add(cboProgCatg);
		cboProgCatg.setBounds(190, 70, 170, 30);

		btnClearAll.setFont(Statics.NORMAL_LARGE_FONT);
		btnClearAll.setText("Remove All");
		pnlProdList.add(btnClearAll);
		btnClearAll.setBounds(190, 230, 170, 40);

		lblSalesRate.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesRate.setText("Sales Rate");
		pnlProdList.add(lblSalesRate);
		lblSalesRate.setBounds(20, 190, 170, 30);

		txtSalesRate.setFont(Statics.NORMAL_LARGE_FONT);
		txtSalesRate.setToolTipText("");
		pnlProdList.add(txtSalesRate);
		txtSalesRate.setBounds(190, 190, 170, 30);

		panel.add(pnlProdList);
		pnlProdList.setBounds(10, 130, 790, 280);
	}

	private class SalesOrderUIListener implements SuggestionSelectedListener,
			MouseListener, ActionListener {
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtPhone || e.getSource() == txtFullName) {
				CustomersData data = null;
				if (e.getSource() == txtPhone)
					data = new Customers().getCustomerByMobileNumber(txtPhone
							.getText());
				else if (e.getSource() == txtFullName)
					data = new Customers().getCustomerByName(txtFullName
							.getText());
				if (data != null) {
					txtCustId.setText(data.custid + "");
					txtFullName.setText(data.custname);
					txtPhone.setText(data.custphone);
				}
			} else if (e.getSource() == txtProduct) {
				ProductsData data = new Products()
						.getNameWiseProducts(txtProduct.getText());
				if (data != null) {
					txtProduct.setText(data.prodname);
					txtProdId.setText(data.prodid + "");
					txtSalesRate.setText(data.salesrate + "");
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblFullName) {
				CustomersUI supui = new CustomersUI();
				InlineWindow win = new InlineWindow();
				win.setContentPane(supui.getContentPane());
				win.setVisible(true);
				initCustomers();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cboProgCatg) {
				if (cboProgCatg.getSelectedIndex() >= 0) {
					ProductsData[] products = new Products()
							.getCategoryWiseProducts(cboProgCatg
									.getSelectedItem().toString());
					if (products != null) {
						arrProduct.clear();
						for (int i = 0; i < products.length; i++) {
							arrProduct.add(products[i].prodname);
						}
					}
				}
			} else if (e.getSource() == btnAddToList) {
				if (txtProdId.getText().equals("")
						|| txtProduct.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter product details!",
							JOptionPane.ERROR_MESSAGE);
					txtProduct.requestFocus();
					return;
				} else if (txtQty.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter Product Quantities",
							JOptionPane.ERROR_MESSAGE);
					txtQty.requestFocus();
					return;
				}
				int row = tblProdList.getRowCount();
				for (int i = 0; i < row; i++) {
					if (txtProdId.getText().equals(
							tblProdList.getValueAt(i, 0).toString())) {
						Statics.showMessage(getContentPane(),
								"Product already exists!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				dtmProdList.setRowCount(row + 1);

				tblProdList.setValueAt(Long.parseLong(txtProdId.getText()),
						row, 0);
				tblProdList.setValueAt(txtProduct.getText(), row, 1);
				tblProdList.setValueAt(Integer.parseInt(txtQty.getText()), row,
						2);
				// tblProdList.setValueAt(Float.parseFloat(txtSalesRate.getText()),
				// row, 3);
				tblProdList.setValueAt("Remove", row, 3);
			} else if (e.getSource() == btnClearAll) {
				dtmProdList.setRowCount(0);
			} else if (e.getSource() == btnReset) {
				resetAllFields();
			} else if (e.getSource() == btnMakeOrder) {
				if (txtCustId.getText().equals("")
						|| txtFullName.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter customer details!",
							JOptionPane.ERROR_MESSAGE);
					txtFullName.requestFocus();
					return;
				} else if (tblProdList.getRowCount() < 1) {
					Statics.showMessage(getContentPane(),
							"Please select products for order!",
							JOptionPane.ERROR_MESSAGE);
					cboProgCatg.requestFocus();
					return;
				}
				SalesOrder_SData[] data = new SalesOrder_SData[tblProdList
						.getRowCount()];
				for (int i = 0; i < tblProdList.getRowCount(); i++) {
					data[i] = new SalesOrder_SData();
					data[i].prodid = Long.parseLong(tblProdList
							.getValueAt(i, 0).toString());
					data[i].prodqty = Integer.parseInt(tblProdList.getValueAt(
							i, 2).toString());
				}

				SalesOrder_MData mdata = new SalesOrder_MData();
				mdata.custid = Long.parseLong(txtCustId.getText());
				mdata.sodate = dpkDate.getDate();

				SalesOrder_M order = new SalesOrder_M();
				int ret = order.createSalesOrder(mdata, data);
				if (ret == tblProdList.getRowCount() + 1) {
					Statics.showMessage(getContentPane(),
							"Sales order updated successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					resetAllFields();
					return;
				} else {
					Statics.showMessage(getContentPane(),
							"Sales order not updated successfully!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
}
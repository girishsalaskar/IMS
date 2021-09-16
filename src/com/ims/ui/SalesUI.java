package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Customers;
import com.ims.models.Firm;
import com.ims.models.ProdCategory;
import com.ims.models.Products;
import com.ims.models.SalesOrder_M;
import com.ims.models.SalesOrder_S;
import com.ims.models.Sales_M;
import com.ims.models.data.CustomersData;
import com.ims.models.data.ProdCategoryData;
import com.ims.models.data.ProductsData;
import com.ims.models.data.SalesOrder_MData;
import com.ims.models.data.SalesOrder_SData;
import com.ims.models.data.Sales_MData;
import com.ims.models.data.Sales_SData;
import com.ims.reports.ReportViewer;
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
public class SalesUI extends IFrame {

	private static final long serialVersionUID = 7635025744656950286L;

	private javax.swing.JButton btnAddSales;

	private javax.swing.JButton btnAddToList;
	private javax.swing.JButton btnReset;
	private javax.swing.JComboBox<String> cboOrderId;
	private javax.swing.JComboBox<String> cboProdCatg;
	private javax.swing.JComboBox<String> cboProduct;
	private DateButton dpkOrderDate;
	private DateButton dpkSalesDate;
	private javax.swing.JScrollPane jspOrderList;
	private javax.swing.JScrollPane jspProdList;
	private javax.swing.JScrollPane jspSalesNote;
	private javax.swing.JLabel lblCustId;
	private javax.swing.JLabel lblDiscount;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblInStock;
	private javax.swing.JLabel lbvInStock;
	private javax.swing.JLabel lblNewDr;
	private javax.swing.JLabel lblOrderDate;
	private javax.swing.JLabel lblOrderId;
	private javax.swing.JLabel lblPaidAmt;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblPrevDr;
	private javax.swing.JLabel lblProdCatg;
	private javax.swing.JLabel lblProdId;
	private javax.swing.JLabel lblProduct;
	private javax.swing.JLabel lblSalesDate;
	private javax.swing.JLabel lblSalesNote;
	private javax.swing.JLabel lblQty;
	private javax.swing.JLabel lblRemAmt;
	private javax.swing.JLabel lblSalesRate;
	private javax.swing.JLabel lblSubTotal;
	private javax.swing.JLabel lblTotal;
	private javax.swing.JLabel lblVat;
	private javax.swing.JPanel pnlProdList;
	private javax.swing.JTable tblOrderList;
	private javax.swing.JTable tblProdList;
	private javax.swing.JTextField txtDiscount;
	private JSuggestionField txtFullName;
	private javax.swing.JTextField txtNewDr;
	private javax.swing.JTextField txtPaidAmt;
	private JSuggestionField txtPhone;
	private javax.swing.JTextField txtPrevDr;
	private javax.swing.JTextField txtProdId;
	private javax.swing.JTextArea txtSalesNote;
	private javax.swing.JTextField txtQty;
	private javax.swing.JTextField txtRemAmt;
	private javax.swing.JTextField txtSalesRate;
	private javax.swing.JTextField txtSubTotal;
	private javax.swing.JTextField txtCustId;
	private javax.swing.JTextField txtTotal;
	private javax.swing.JTextField txtVat;

	private SalesUIListener listen;
	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;
	private DefaultTableModel dtmOrderList;
	private DefaultTableModel dtmProdList;

	public SalesUI() {
		super(Program.LANG.getProperty("mnisales"), 850, 850);
		initComponents();
		dtmOrderList = (DefaultTableModel) tblOrderList.getModel();
		dtmProdList = (DefaultTableModel) tblProdList.getModel();
		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);

		initCustomerList();
		initProductCatg();
		updateOrderID();

		int[] orderwidths = { 60, 150, 50, 70, 70 };
		Statics.customColumnWidth(tblOrderList, orderwidths);
		int[] prodwidths = { 60, 140, 55, 65, 70, 50 };
		Statics.customColumnWidth(tblProdList, prodwidths);

		// INFO Listener code
		listen = new SalesUIListener();
		lblFullName.addMouseListener(listen);
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		cboProdCatg.addActionListener(listen);
		cboProduct.addActionListener(listen);
		cboOrderId.addActionListener(listen);
		btnReset.addActionListener(listen);
		dtmProdList.addTableModelListener(listen);
		btnAddToList.addActionListener(listen);
		btnAddSales.addActionListener(listen);
		txtVat.addFocusListener(listen);
		txtDiscount.addFocusListener(listen);
		txtPaidAmt.addFocusListener(listen);
		dpkOrderDate.addPropertyChangeListener("date", listen);

		tblProdList.getColumnModel().getColumn(0).setMinWidth(0);
		tblProdList.getColumnModel().getColumn(0).setMaxWidth(0);
		tblProdList.getColumnModel().getColumn(0).setWidth(0);

		tblOrderList.getColumnModel().getColumn(0).setMinWidth(0);
		tblOrderList.getColumnModel().getColumn(0).setMaxWidth(0);
		tblOrderList.getColumnModel().getColumn(0).setWidth(0);

		tblOrderList.getColumn("Add")
				.setCellRenderer(new JButtonCellRenderer());
		tblOrderList.getColumn("Add").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {
					private static final long serialVersionUID = -1877857233832574061L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent arg0) {
						if (this.row >= 0) {
							dtmProdList.setRowCount(tblProdList.getRowCount() + 1);
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

					/**
			 * 
			 */
					private static final long serialVersionUID = -8751644411822062531L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent arg0) {
						if (table.getValueAt(row, 5).toString().equals("<")) {
							dtmOrderList.setRowCount(tblOrderList.getRowCount() + 1);
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
						dtmProdList.removeRow(row);
					}
				});
	
	//TODO Multilanguage Text
		lblOrderDate.setText(Program.LANG.getProperty("lblorderdate"));
		lblCustId.setText(Program.LANG.getProperty("lblcustid"));
		lblFullName.setText("<html><body><u>"+Program.LANG.getProperty("lblcustname")+"</u></body></html>");
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		lblProdId.setText(Program.LANG.getProperty("lblprodid"));
		lblProdCatg.setText(Program.LANG.getProperty("lblprodcategory"));
		lblProduct.setText("<html><body><u style=\"color:blue;\">"+Program.LANG.getProperty("lblprodname")+"</u></body></html>");
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblOrderId.setText(Program.LANG.getProperty("lblorderid"));
		lblTotal.setText(Program.LANG.getProperty("lbltotal"));
		lblDiscount.setText(Program.LANG.getProperty("lbldiscount"));
		lblVat.setText(Program.LANG.getProperty("lblvat"));
		lblSubTotal.setText(Program.LANG.getProperty("lblsubtotal"));
		lblPaidAmt.setText(Program.LANG.getProperty("lblpayamount"));
		lblRemAmt.setText(Program.LANG.getProperty("lblremamt"));
		lblQty.setText(Program.LANG.getProperty("lblprodqty"));
		lblSalesRate.setText(Program.LANG.getProperty("lblsalesrateperunit"));
		
		lblSalesDate.setText(Program.LANG.getProperty("lblsalesdate"));
		lblSalesNote.setText(Program.LANG.getProperty("lblsalesnote"));
		btnAddToList.setText(Program.LANG.getProperty("btnaddtosaleslist"));
		lblPrevDr.setText(Program.LANG.getProperty("lblprevrecdr"));
		lblNewDr.setText(Program.LANG.getProperty("lblnewrecdr"));
		btnAddSales.setText(Program.LANG.getProperty("btnaddsales"));
		lblInStock.setText(Program.LANG.getProperty("lblinstock"));
	}

	private void updateOrderDetails() {
		if (cboOrderId.getSelectedIndex() > 0) {

			long somid = Long
					.parseLong(cboOrderId.getSelectedItem().toString());
			SalesOrder_MData somdata = new SalesOrder_M().getSalesOrder(somid);
			if(somdata!=null)
			{
				CustomersData cust=new Customers().getCustomerById(somdata.custid);
				if(cust!=null)
				{
					txtFullName.setText(cust.custname);
					txtPhone.setText(cust.custphone);
					txtCustId.setText(cust.custid+"");
				}
				SalesOrder_SData[] sdata = new SalesOrder_S()
						.getSalesOrderByMID(somid);
				if (sdata != null) {
					for (int i = 0; i < sdata.length; i++) {
						ProductsData data = new Products()
								.getProductById(sdata[i].prodid);
						dtmOrderList.setRowCount(i + 1);
						tblOrderList.setValueAt(sdata[i].prodid, i, 0);
						tblOrderList.setValueAt(data.prodname, i, 1);
						tblOrderList.setValueAt(sdata[i].prodqty, i, 2);
						tblOrderList.setValueAt(data.salesrate, i, 3);
						tblOrderList.setValueAt("Add", i, 4);
					}
				} else
					dtmOrderList.setRowCount(0);
			}
		}
	}

	private void updateGrandTotal() {
		if (txtSubTotal.getText().equals(""))
			txtSubTotal.setText("0");
		if (txtDiscount.getText().equals(""))
			txtDiscount.setText("0");
		if (txtVat.getText().equals(""))
			txtVat.setText("0");
		float subtotal = Float.parseFloat(txtSubTotal.getText());
		float discount = Float.parseFloat(txtDiscount.getText());
		float vat = Float.parseFloat(txtVat.getText());
		subtotal = subtotal - discount;
		subtotal = subtotal + vat;
		txtTotal.setText(subtotal + "");

		float total = Float.parseFloat(txtTotal.getText());
		float paidamt = Float.parseFloat(txtPaidAmt.getText());
		float prevbal = Float.parseFloat(txtPrevDr.getText());
		prevbal += total;
		txtRemAmt
				.setText(((total - paidamt) < 0 ? "0" : (total - paidamt) + ""));
		float newbal = prevbal - paidamt;
		txtNewDr.setText(newbal + "");
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

	private void initProductList() {
		ProductsData[] proddata = null;
		if (cboProdCatg.getSelectedIndex() >= 0)
			proddata = new Products().getCategoryWiseProducts(cboProdCatg
					.getSelectedItem().toString());

		cboProduct.removeAllItems();
		txtSalesRate.setText("");
		if (proddata != null) {
			for (int i = 0; i < proddata.length; i++) {
				cboProduct.addItem(proddata[i].prodname);
			}
		}
	}

	private void initProductData() {
		ProductsData prod = null;
		if (cboProduct.getSelectedIndex() >= 0)
			prod = new Products().getNameWiseProducts(cboProduct
					.getSelectedItem().toString());
		txtProdId.setText("");
		if (prod != null) {
			txtProdId.setText(prod.prodid + "");
			txtSalesRate.setText(prod.salesrate + "");
			lbvInStock.setText(prod.instock + "");
		}
	}

	private void initProductCatg() {
		cboProdCatg.removeAllItems();
		ProdCategoryData[] pdata = new ProdCategory().getAllProductCategories();
		if (pdata != null) {
			for (ProdCategoryData pdt : pdata) {
				cboProdCatg.addItem(pdt.catname);
			}
			cboProdCatg.setSelectedIndex(-1);
		}
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
		txtPrevDr.setText(data.debtamt + "");
	}

	private void resetOrderDetails() {
		txtPrevDr.setText("0");
		txtCustId.setText("");
		txtFullName.setText("");
		txtPhone.setText("");
		txtFullName.setEditable(true);
		txtPhone.setEditable(true);
		dtmOrderList.setRowCount(0);
	}

	private void updateOrderID() {
		cboOrderId.removeAllItems();
		SalesOrder_MData[] mdata = new SalesOrder_M()
				.getOrderByDate(dpkOrderDate.getDate());
		if (mdata != null) {
			cboOrderId.addItem("Select Order");
			for (int i = 0; i < mdata.length; i++) {
				cboOrderId.addItem(mdata[i].somid + "");
			}
		}
		else
			cboOrderId.addItem("No Prev. Order");
	}

	@SuppressWarnings("deprecation")
	private void initComponents() {

		lblOrderDate = new javax.swing.JLabel();
		dpkOrderDate = new DateButton();
		lblOrderId = new javax.swing.JLabel();
		cboOrderId = new javax.swing.JComboBox<String>();
		lblCustId = new javax.swing.JLabel();
		lblFullName = new javax.swing.JLabel();
		txtFullName = new JSuggestionField();
		txtCustId = new javax.swing.JTextField();
		txtPhone = new JSuggestionField();
		lblPhone = new javax.swing.JLabel();
		lblSalesDate = new javax.swing.JLabel();
		dpkSalesDate = new DateButton();
		jspSalesNote = new javax.swing.JScrollPane();
		txtSalesNote = new javax.swing.JTextArea();
		lblSalesNote = new javax.swing.JLabel();
		pnlProdList = new javax.swing.JPanel();
		jspOrderList = new javax.swing.JScrollPane();
		tblOrderList = new javax.swing.JTable();
		jspProdList = new javax.swing.JScrollPane();
		tblProdList = new javax.swing.JTable();
		lblProdId = new javax.swing.JLabel();
		txtProdId = new javax.swing.JTextField();
		lblProdCatg = new javax.swing.JLabel();
		cboProdCatg = new javax.swing.JComboBox<String>();
		lblProduct = new javax.swing.JLabel();
		cboProduct = new javax.swing.JComboBox<String>();
		lblQty = new javax.swing.JLabel();
		txtQty = new javax.swing.JTextField();
		lblSalesRate = new javax.swing.JLabel();
		txtSalesRate = new javax.swing.JTextField();
		btnAddToList = new javax.swing.JButton();
		lblInStock = new javax.swing.JLabel();
		lbvInStock = new javax.swing.JLabel();
		lblSubTotal = new javax.swing.JLabel();
		txtSubTotal = new javax.swing.JTextField();
		lblDiscount = new javax.swing.JLabel();
		txtDiscount = new javax.swing.JTextField();
		lblVat = new javax.swing.JLabel();
		txtVat = new javax.swing.JTextField();
		lblTotal = new javax.swing.JLabel();
		txtTotal = new javax.swing.JTextField();
		lblNewDr = new javax.swing.JLabel();
		txtNewDr = new javax.swing.JTextField();
		txtRemAmt = new javax.swing.JTextField();
		lblRemAmt = new javax.swing.JLabel();
		txtPaidAmt = new javax.swing.JTextField();
		lblPaidAmt = new javax.swing.JLabel();
		lblPrevDr = new javax.swing.JLabel();
		txtPrevDr = new javax.swing.JTextField();
		btnAddSales = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();

		panel.setLayout(null);

		lblOrderDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblOrderDate.setText("Order Date");
		panel.add(lblOrderDate);
		lblOrderDate.setBounds(10, 10, 220, 30);

		dpkOrderDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkOrderDate.setNextFocusableComponent(cboOrderId);
		panel.add(dpkOrderDate);
		dpkOrderDate.setBounds(230, 10, 200, 30);

		lblOrderId.setFont(Statics.NORMAL_LARGE_FONT);
		lblOrderId.setText("Order ID");
		panel.add(lblOrderId);
		lblOrderId.setBounds(440, 10, 180, 30);

		cboOrderId.setFont(Statics.NORMAL_LARGE_FONT);
		cboOrderId.setNextFocusableComponent(dpkSalesDate);
		panel.add(cboOrderId);
		cboOrderId.setBounds(620, 10, 200, 30);

		lblCustId.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustId.setText("Customer ID");
		panel.add(lblCustId);
		lblCustId.setBounds(10, 50, 220, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setForeground(java.awt.Color.BLUE);
		lblFullName
				.setText("<html><u style=\"color:blue;\">Customer Name</u></html>");
		lblFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(lblFullName);
		lblFullName.setBounds(10, 90, 220, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		txtFullName.setNextFocusableComponent(txtPhone);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 90, 200, 30);

		txtCustId.setEditable(false);
		txtCustId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCustId);
		txtCustId.setBounds(230, 50, 200, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		txtPhone.setNextFocusableComponent(txtSalesNote);
		panel.add(txtPhone);
		txtPhone.setBounds(230, 130, 200, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 130, 220, 30);

		lblSalesDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesDate.setText("Sales Date");
		panel.add(lblSalesDate);
		lblSalesDate.setBounds(440, 50, 180, 30);

		dpkSalesDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkSalesDate.setNextFocusableComponent(txtFullName);
		panel.add(dpkSalesDate);
		dpkSalesDate.setBounds(620, 50, 200, 30);

		txtSalesNote.setColumns(20);
		txtSalesNote.setFont(Statics.NORMAL_LARGE_FONT);
		txtSalesNote.setLineWrap(true);
		txtSalesNote.setRows(1);
		txtSalesNote.setNextFocusableComponent(tblOrderList);
		jspSalesNote.setViewportView(txtSalesNote);

		panel.add(jspSalesNote);
		jspSalesNote.setBounds(620, 90, 200, 70);

		lblSalesNote.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesNote.setText("Sales Note");
		panel.add(lblSalesNote);
		lblSalesNote.setBounds(440, 90, 180, 70);

		pnlProdList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Products list for Order",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));
		pnlProdList.setLayout(null);

		jspOrderList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Previous Order Items",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));

		tblOrderList.setAutoCreateRowSorter(true);
		tblOrderList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product ID", "Product Name", "Units",
						"Sales Rate", "Add" }) {
			/**
							 * 
							 */
							private static final long serialVersionUID = 1720165785634916129L;
			Class<?>[] types = new Class[] { java.lang.Long.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, false, false,
					true };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblOrderList.setNextFocusableComponent(pnlProdList);
		jspOrderList.setViewportView(tblOrderList);

		pnlProdList.add(jspOrderList);
		jspOrderList.setBounds(20, 30, 390, 240);

		jspProdList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Items to be Sold",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));

		tblProdList.setAutoCreateRowSorter(true);
		tblProdList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product ID", "Product Name", "Units",
						"Sales Rate", "Total", "Remove" }) {
			/**
							 * 
							 */
							private static final long serialVersionUID = 5872928149213897127L;
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
		tblProdList.setNextFocusableComponent(cboProdCatg);
		jspProdList.setViewportView(tblProdList);

		pnlProdList.add(jspProdList);
		jspProdList.setBounds(410, 30, 390, 240);

		lblProdId.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblProdId.setText("Product ID");
		pnlProdList.add(lblProdId);
		lblProdId.setBounds(80, 280, 100, 30);

		txtProdId.setEditable(false);
		txtProdId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlProdList.add(txtProdId);
		txtProdId.setBounds(80, 310, 100, 30);

		lblProdCatg.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdCatg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblProdCatg.setText("Product Category");
		pnlProdList.add(lblProdCatg);
		lblProdCatg.setBounds(210, 280, 240, 30);

		cboProdCatg.setFont(Statics.NORMAL_LARGE_FONT);
		cboProdCatg.setNextFocusableComponent(cboProduct);
		pnlProdList.add(cboProdCatg);
		cboProdCatg.setBounds(210, 310, 240, 30);

		lblProduct.setFont(Statics.NORMAL_LARGE_FONT);
		lblProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblProduct.setText("Product Name");
		pnlProdList.add(lblProduct);
		lblProduct.setBounds(470, 280, 250, 30);

		cboProduct.setFont(Statics.NORMAL_LARGE_FONT);
		cboProduct.setNextFocusableComponent(txtQty);
		pnlProdList.add(cboProduct);
		cboProduct.setBounds(470, 310, 250, 30);

		lblQty.setFont(Statics.NORMAL_LARGE_FONT);
		lblQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblQty.setText("Quantity");
		pnlProdList.add(lblQty);
		lblQty.setBounds(220, 350, 100, 30);

		txtQty.setFont(Statics.NORMAL_LARGE_FONT);
		txtQty.setToolTipText("");
		txtQty.setNextFocusableComponent(txtSalesRate);
		pnlProdList.add(txtQty);
		txtQty.setBounds(220, 380, 100, 30);

		lblSalesRate.setFont(Statics.NORMAL_LARGE_FONT);
		lblSalesRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblSalesRate.setText("Sales Rate/Unit");
		pnlProdList.add(lblSalesRate);
		lblSalesRate.setBounds(330, 350, 140, 30);

		txtSalesRate.setFont(Statics.NORMAL_LARGE_FONT);
		txtSalesRate.setToolTipText("");
		txtSalesRate.setNextFocusableComponent(btnAddToList);
		pnlProdList.add(txtSalesRate);
		txtSalesRate.setBounds(330, 380, 140, 30);

		btnAddToList.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddToList.setText("Add to Purchase List");
		btnAddToList.setNextFocusableComponent(txtDiscount);
		pnlProdList.add(btnAddToList);
		btnAddToList.setBounds(520, 350, 200, 60);

		lblInStock.setFont(Statics.NORMAL_LARGE_FONT);
		lblInStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblInStock.setText("In Stock");
		pnlProdList.add(lblInStock);
		lblInStock.setBounds(80, 350, 100, 30);

		lbvInStock.setFont(Statics.NORMAL_LARGE_FONT);
		lbvInStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbvInStock.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 1, true));
		pnlProdList.add(lbvInStock);
		lbvInStock.setBounds(80, 380, 100, 30);

		panel.add(pnlProdList);
		pnlProdList.setBounds(10, 170, 810, 430);

		lblSubTotal.setFont(Statics.NORMAL_LARGE_FONT);
		lblSubTotal.setText("Total Amount");
		panel.add(lblSubTotal);
		lblSubTotal.setBounds(20, 630, 120, 30);

		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(Statics.NORMAL_LARGE_FONT);
		txtSubTotal.setText("0");
		txtSubTotal.setToolTipText("");
		panel.add(txtSubTotal);
		txtSubTotal.setBounds(140, 630, 120, 30);

		lblDiscount.setFont(Statics.NORMAL_LARGE_FONT);
		lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDiscount.setText("Discount");
		panel.add(lblDiscount);
		lblDiscount.setBounds(260, 630, 80, 30);

		txtDiscount.setFont(Statics.NORMAL_LARGE_FONT);
		txtDiscount.setText("0");
		txtDiscount.setToolTipText("");
		txtDiscount.setNextFocusableComponent(txtVat);
		panel.add(txtDiscount);
		txtDiscount.setBounds(340, 630, 100, 30);

		lblVat.setFont(Statics.NORMAL_LARGE_FONT);
		lblVat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblVat.setText("Vat");
		panel.add(lblVat);
		lblVat.setBounds(440, 630, 50, 30);

		txtVat.setFont(Statics.NORMAL_LARGE_FONT);
		txtVat.setText("0");
		txtVat.setToolTipText("");
		txtVat.setNextFocusableComponent(txtPaidAmt);
		panel.add(txtVat);
		txtVat.setBounds(490, 630, 100, 30);

		lblTotal.setFont(Statics.NORMAL_LARGE_FONT);
		lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTotal.setText("Grand Total");
		panel.add(lblTotal);
		lblTotal.setBounds(590, 630, 110, 30);

		txtTotal.setEditable(false);
		txtTotal.setFont(Statics.NORMAL_LARGE_FONT);
		txtTotal.setText("0");
		txtTotal.setToolTipText("");
		panel.add(txtTotal);
		txtTotal.setBounds(700, 630, 120, 30);

		lblNewDr.setFont(Statics.NORMAL_LARGE_FONT);
		lblNewDr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblNewDr.setText("New Receivable Dr.");
		panel.add(lblNewDr);
		lblNewDr.setBounds(620, 670, 170, 30);

		txtNewDr.setEditable(false);
		txtNewDr.setFont(Statics.NORMAL_LARGE_FONT);
		txtNewDr.setText("0");
		txtNewDr.setToolTipText("");
		panel.add(txtNewDr);
		txtNewDr.setBounds(620, 700, 170, 30);

		txtRemAmt.setEditable(false);
		txtRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
		txtRemAmt.setText("0");
		txtRemAmt.setToolTipText("");
		panel.add(txtRemAmt);
		txtRemAmt.setBounds(400, 700, 140, 30);

		lblRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblRemAmt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblRemAmt.setText("Remaining Amt.");
		panel.add(lblRemAmt);
		lblRemAmt.setBounds(400, 670, 140, 30);

		txtPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		txtPaidAmt.setText("0");
		txtPaidAmt.setToolTipText("");
		txtPaidAmt.setNextFocusableComponent(btnAddSales);
		panel.add(txtPaidAmt);
		txtPaidAmt.setBounds(230, 700, 120, 30);

		lblPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblPaidAmt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblPaidAmt.setText("Paid Amtount");
		panel.add(lblPaidAmt);
		lblPaidAmt.setBounds(230, 670, 120, 30);

		lblPrevDr.setFont(Statics.NORMAL_LARGE_FONT);
		lblPrevDr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblPrevDr.setText("Prev. Receivable Dr.");
		panel.add(lblPrevDr);
		lblPrevDr.setBounds(10, 670, 170, 30);

		txtPrevDr.setEditable(false);
		txtPrevDr.setFont(Statics.NORMAL_LARGE_FONT);
		txtPrevDr.setText("0");
		txtPrevDr.setToolTipText("");
		panel.add(txtPrevDr);
		txtPrevDr.setBounds(10, 700, 170, 30);

		btnAddSales.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddSales.setText("Add Purchases Entry");
		btnAddSales.setNextFocusableComponent(btnReset);
		panel.add(btnAddSales);
		btnAddSales.setBounds(170, 750, 210, 80);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset All");
		panel.add(btnReset);
		btnReset.setBounds(390, 750, 210, 80);
	}

	private void resetFields()
	{
		resetOrderDetails();
		dtmProdList.setRowCount(0);
		txtSubTotal.setText("");
		txtDiscount.setText("0");
		txtVat.setText("0");
		txtPaidAmt.setText("0");
		updateGrandTotal();
		cboOrderId.setSelectedIndex(0);
		cboProdCatg.setSelectedIndex(-1);
		cboProduct.setSelectedIndex(-1);
		txtSalesNote.setText("");
	}
	private class SalesUIListener implements MouseListener, ActionListener,
			SuggestionSelectedListener, TableModelListener, FocusListener,
			PropertyChangeListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblFullName) {
				InlineWindow custui = new InlineWindow();
				custui.setContentPane(new CustomersUI().getContentPane());
				custui.setVisible(true);
				initCustomerList();
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
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cboOrderId) {
				if (cboOrderId.getSelectedIndex() > 0)
					updateOrderDetails();
				else
					resetOrderDetails();
			} else if (e.getSource() == cboProdCatg) {
				initProductList();
			} else if (e.getSource() == cboProduct) {
				initProductData();
			} else if (e.getSource() == btnReset) {
				resetFields();
			} else if (e.getSource() == btnAddToList) {
				if (txtProdId.getText().equals("")) {
					Statics.showMessage(rootPane,
							"Please Select Product from Product List",
							JOptionPane.ERROR_MESSAGE);
					cboProduct.requestFocus();
					return;
				} else if (txtQty.getText().equals("")) {
					Statics.showMessage(rootPane,
							"Please Enter Product Quantities",
							JOptionPane.ERROR_MESSAGE);
					txtQty.requestFocus();
					return;
				} else if (txtSalesRate.getText().equals("")) {
					Statics.showMessage(rootPane,
							"Please Enter Product Sales Rate!",
							JOptionPane.ERROR_MESSAGE);
					txtSalesRate.requestFocus();
					return;
				}
				int instock = Integer.parseInt(lbvInStock.getText());
				int qty = Integer.parseInt(txtQty.getText());
				if (instock == 0) {
					Statics.showMessage(rootPane,
							"The product is not available in stock!",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (qty > instock) {
					Statics.showMessage(rootPane,
							"Quantities are greater than product in stock!",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if ((instock - qty) < 20) {
					Statics.showMessage(
							rootPane,
							"Stock is less than 20 quantities! You will need to purchase soon",
							JOptionPane.WARNING_MESSAGE);
				}
				int row = tblProdList.getRowCount();
				dtmProdList.setRowCount(row + 1);
				tblProdList.setValueAt(txtProdId.getText(), row, 0);
				tblProdList.setValueAt(cboProduct.getSelectedItem(), row, 1);
				tblProdList.setValueAt(Integer.parseInt(txtQty.getText()), row,
						2);
				tblProdList.setValueAt(
						Float.parseFloat(txtSalesRate.getText()), row, 3);
				// tblProdList.setValueAt((Float.parseFloat(txtRate.getText())*Float.parseFloat(txtQty.getText())),
				// row, 5);
				tblProdList.setValueAt("X", row, 5);
			} else if (e.getSource() == btnAddSales) {
				if (txtFullName.getText().equals("")
						|| txtPhone.getText().equals("")) {
					Statics.showMessage(
							getContentPane(),
							"Customer is not selected! Please select customer Details",
							JOptionPane.ERROR_MESSAGE);
					txtFullName.requestFocus();
					return;
				} else if (tblProdList.getRowCount() < 1) {
					Statics.showMessage(
							getContentPane(),
							"Products not selected for sales! Please Select Products",
							JOptionPane.ERROR_MESSAGE);
					cboProdCatg.requestFocus();
					return;
				} else if (txtDiscount.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter discount!", JOptionPane.ERROR_MESSAGE);
					txtDiscount.requestFocus();
					return;
				} else if (txtVat.getText().equals("")) {
					Statics.showMessage(getContentPane(), "Please enter vat!",
							JOptionPane.ERROR_MESSAGE);
					txtVat.requestFocus();
					return;
				} else if (txtPaidAmt.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter paid amount",
							JOptionPane.ERROR_MESSAGE);
					txtPaidAmt.requestFocus();
				} else if (txtSalesNote.getText().equals("")) {
					txtSalesNote.setText("Sale on : " + dpkSalesDate.getText()
							+ "\n" + "Sales To : " + txtFullName.getText());
				}

				for (int i = 0; i < tblProdList.getRowCount(); i++) {
					for (int j = 0; j < tblProdList.getColumnCount(); j++) {
						if (tblProdList.getValueAt(i, j) == null) {
							Statics.showMessage(
									rootPane,
									"One of the product is not entered!\nPlease review your sold products list!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}

				updateGrandTotal();

				CustomersData custdata=null;
				if(txtCustId.getText().equals(""))
				{
					custdata=new CustomersData();
					custdata.custname=txtFullName.getText();
					custdata.custphone=txtPhone.getText().equals("")?"-":txtPhone.getText();
				}
				Sales_MData mdata = new Sales_MData();
				mdata.custid = txtCustId.getText().equals("")?-1:Long.parseLong(txtCustId.getText());
				mdata.discount = Float.parseFloat(txtDiscount.getText());
				mdata.paidamt = Float.parseFloat(txtPaidAmt.getText());
				mdata.salesdate = dpkSalesDate.getDate();
				mdata.somid = (cboOrderId.getSelectedIndex() > 0 ? Long
						.parseLong(cboOrderId.getSelectedItem().toString())
						: null);
				mdata.subtotal = Float.parseFloat(txtSubTotal.getText());
				mdata.total = Float.parseFloat(txtTotal.getText());
				mdata.vat = Float.parseFloat(txtVat.getText());

				Sales_SData[] prods = new Sales_SData[tblProdList.getRowCount()];
				for (int i = 0; i < tblProdList.getRowCount(); i++) {
					prods[i] = new Sales_SData();
					prods[i].prodid = Long.parseLong(tblProdList.getValueAt(i,
							0).toString());
					prods[i].prodqty = Integer.parseInt(tblProdList.getValueAt(
							i, 2).toString());
					prods[i].prodrate = Float.parseFloat(tblProdList
							.getValueAt(i, 3).toString());
					prods[i].totrate = Float.parseFloat(tblProdList.getValueAt(
							i, 4).toString());
				}
				float newdr = Float.parseFloat(txtNewDr.getText());
				Sales_M sales = new Sales_M();
				int ret = sales.makeSalesEntry(mdata, prods, newdr, custdata);
				if (ret < 1) {
					Statics.showMessage(getContentPane(),
							"Sales Entry Not Updated Successfully!",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					Statics.showMessage(getContentPane(),
							"Sales Entry Updated Successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					HashMap<String, Object> map = new HashMap<>();
					Sales_MData smdata = sales.getLastSalesDetails();
					map.put("prmsalemid", smdata.salemid);
					Firm firm = new Firm();
					map.put("prmfirmid", firm.getFirmData().firmid);
					ReportViewer view = new ReportViewer("salesreceipt",
							map, firm.getConnectionObj());
					view.setSubReportFileName("deliverychalan");
					view.compileAndShowSubReport();
					resetFields();
					return;
				}
			}
		}

		@Override
		public void tableChanged(TableModelEvent e) {
			if (e.getSource() == dtmProdList) {
				if (e.getType() == TableModelEvent.UPDATE) {
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
									dtmProdList.removeRow(e.getLastRow());
									return;
								}
							}
						}
					} else if (e.getColumn() == 3 || e.getColumn() == 2) {
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
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtDiscount || e.getSource() == txtVat) {
				updateGrandTotal();
			} else if (e.getSource() == txtPaidAmt) {
				updateGrandTotal();
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getSource() == dpkOrderDate) {
				updateOrderID();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void focusGained(FocusEvent arg0) {
		}
	}
}
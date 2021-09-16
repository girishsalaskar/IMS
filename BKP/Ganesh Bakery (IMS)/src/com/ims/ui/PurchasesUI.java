package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.ProdCategory;
import com.ims.models.Products;
import com.ims.models.PurchaseOrder_M;
import com.ims.models.PurchaseOrder_S;
import com.ims.models.Purchases_M;
import com.ims.models.Suppliers;
import com.ims.models.data.ProdCategoryData;
import com.ims.models.data.ProductsData;
import com.ims.models.data.PurchaseOrder_MData;
import com.ims.models.data.PurchaseOrder_SData;
import com.ims.models.data.Purchases_MData;
import com.ims.models.data.Purchases_SData;
import com.ims.models.data.SuppliersData;
import com.ims.ui.common.InlineWindow;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;
import com.jdev.girish.ui.table.JButtonCellEditor;
import com.jdev.girish.ui.table.JButtonCellRenderer;
import com.jdev.girish.ui.validation.AlphaSpaceKeyListener;
import com.jdev.girish.ui.validation.DecimalKeyListener;
import com.jdev.girish.ui.validation.NumberKeyListener;

/**
 *
 * @author Girish
 */
public class PurchasesUI extends IFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnAddPurchases;

	private JButton btnAddToList;

	private JButton btnReset;
	private JComboBox<String> cboOrderId;
	private JComboBox<String> cboProdCatg;
	private DateButton dpkOrderDate;
	private DateButton dpkPurchDate;
	private JScrollPane jspOrderList;
	private JScrollPane jspProdList;
	private JScrollPane jspPurchNote;
	private JLabel lblDiscount;
	private JLabel lblFullName;
	private JLabel lblOrderDate;
	private JLabel lblOrderId;
	private JLabel lblPhone;
	private JLabel lblProdCatg;
	private JLabel lblProdId;
	private JLabel lblProduct;
	private JLabel lblPurchDate;
	private JLabel lblPurchNote;
	private JLabel lblQty;
	private JLabel lblRate;
	private JLabel lblSubTotal;
	private JLabel lblSupplId;
	private JLabel lblTotal;
	private JLabel lblPrevCr;
	private JLabel lblNewCr;
	private JLabel lblPaidAmt;
	private JLabel lblRemAmt;
	private JTextField txtPrevCr;
	private JTextField txtNewCr;
	private JTextField txtPaidAmt;
	private JTextField txtRemAmt;
	private JLabel lblVat;
	private JPanel pnlProdList;
	private JTable tblOrderList;
	private JTable tblProdList;
	private JTextField txtDiscount;
	private JSuggestionField txtFullName;
	private JSuggestionField txtPhone;
	private JTextField txtProdId;
	// private JSuggestionField txtProduct;
	private JComboBox<String> cboProduct;
	private JTextArea txtPurchNote;
	private JTextField txtQty;
	private JTextField txtRate;
	private JTextField txtSubTotal;
	private JTextField txtSupplId;
	private JTextField txtTotal;
	private JTextField txtVat;
	private PurchasesUIListener listen;
	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;
	// private ArrayList<String> arrProduct;`
	private DefaultTableModel dtmOrderList;
	private DefaultTableModel dtmProdList;
	private JLabel lblSalesRate;
	private JTextField txtSalesRate;

	public PurchasesUI() {
		super(Program.LANG.getProperty("mnipurchases"), 840, 850);
		initComponents();

		dtmOrderList = (DefaultTableModel) tblOrderList.getModel();
		dtmProdList = (DefaultTableModel) tblProdList.getModel();

		tblOrderList.getColumn("Add")
				.setCellRenderer(new JButtonCellRenderer());
		tblOrderList.getColumn("Add").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {
					private static final long serialVersionUID = -6396381301789133086L;

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
							tblProdList.setValueAt(table.getValueAt(row, 4),
									tblProdList.getRowCount() - 1, 4);
							if (table.getValueAt(row, 2) != null
									&& table.getValueAt(row, 3) != null)
								tblProdList.setValueAt((Float.parseFloat(table
										.getValueAt(row, 2).toString()) * Float
										.parseFloat(table.getValueAt(row, 3)
												.toString())), tblProdList
										.getRowCount() - 1, 5);
							tblProdList.setValueAt("<",
									tblProdList.getRowCount() - 1, 6);
							dtm.removeRow(this.row);
						}
					}
				});

		tblProdList.getColumn("Remove").setCellRenderer(
				new JButtonCellRenderer());
		tblProdList.getColumn("Remove").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {
					private static final long serialVersionUID = 862224459734138155L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent arg0) {
						if (table.getValueAt(row, 6).toString().equals("<")) {
							dtmOrderList.setRowCount(tblOrderList.getRowCount() + 1);
							tblOrderList.setValueAt(table.getValueAt(row, 0),
									tblOrderList.getRowCount() - 1, 0);
							tblOrderList.setValueAt(table.getValueAt(row, 1),
									tblOrderList.getRowCount() - 1, 1);
							tblOrderList.setValueAt(table.getValueAt(row, 2),
									tblOrderList.getRowCount() - 1, 2);
							tblOrderList.setValueAt(">",
									tblOrderList.getRowCount() - 1, 5);
						}
						dtmProdList.removeRow(row);
					}
				});

		int[] orderwidths = { 60, 150, 50, 70, 70, 70 };
		Statics.customColumnWidth(tblOrderList, orderwidths);
		int[] prodwidths = { 60, 150, 80, 80, 80, 80 };
		Statics.customColumnWidth(tblProdList, prodwidths);

		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);
		// arrProduct=new ArrayList<String>();
		// txtProduct.setSuggestionList(arrProduct);

		listen = new PurchasesUIListener();

		lblFullName.addMouseListener(listen);
		lblProduct.addMouseListener(listen);
		dpkOrderDate.addPropertyChangeListener("date", listen);
		cboOrderId.addActionListener(listen);
		cboProdCatg.addActionListener(listen);
		cboProduct.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnAddToList.addActionListener(listen);
		dtmProdList.addTableModelListener(listen);
		btnAddPurchases.addActionListener(listen);
		txtDiscount.addFocusListener(listen);
		txtVat.addFocusListener(listen);
		tblOrderList.addKeyListener(listen);
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		txtPaidAmt.addFocusListener(listen);

		DecimalKeyListener deckey = new DecimalKeyListener();
		txtDiscount.addKeyListener(deckey);
		txtPaidAmt.addKeyListener(deckey);
		txtRate.addKeyListener(deckey);
		txtSalesRate.addKeyListener(deckey);
		txtVat.addKeyListener(deckey);

		txtFullName.addKeyListener(new AlphaSpaceKeyListener());
		txtPhone.addKeyListener(new NumberKeyListener());
		txtQty.addKeyListener(new NumberKeyListener());

		initLists();
		initSupplierList();

		updateOrderID();
		
		Statics.hideColumn(tblOrderList, 0);
		Statics.hideColumn(tblProdList, 0);
		
		//TODO Multilanguage Text
		
		lblOrderDate.setText(Program.LANG.getProperty("lblorderdate"));
		lblSupplId.setText(Program.LANG.getProperty("lblsupid"));
		lblFullName.setText("<html><body><u>"+Program.LANG.getProperty("lblsupname")+"</u></body></html>");
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		lblProdId.setText(Program.LANG.getProperty("lblprodid"));
		lblProdCatg.setText(Program.LANG.getProperty("lblprodcategory"));
		lblProduct.setText("<html><body><u style=\"color:blue;\">"+Program.LANG.getProperty("lblprodname")+"</u></body></html>");
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		
		lblOrderId.setText(Program.LANG.getProperty("lblorderid"));
		lblPurchDate.setText(Program.LANG.getProperty("lblpurchdate"));
		lblPurchNote.setText(Program.LANG.getProperty("lblpurchnote"));
		lblRate.setText(Program.LANG.getProperty("lblpurchrateperunit"));
		lblSalesRate.setText(Program.LANG.getProperty("lblsalesrateperunit"));
		btnAddToList.setText(Program.LANG.getProperty("btnaddtopurchlist"));
		lblTotal.setText(Program.LANG.getProperty("lbltotal"));
		lblDiscount.setText(Program.LANG.getProperty("lbldiscount"));
		lblVat.setText(Program.LANG.getProperty("lblvat"));
		lblSubTotal.setText(Program.LANG.getProperty("lblsubtotal"));
		lblPrevCr.setText(Program.LANG.getProperty("lblprevpaycr"));
		lblPaidAmt.setText(Program.LANG.getProperty("lblpayamount"));
		lblRemAmt.setText(Program.LANG.getProperty("lblremamt"));
		lblNewCr.setText(Program.LANG.getProperty("lblnewpaycr"));
		btnAddPurchases.setText(Program.LANG.getProperty("btnaddpurchases"));
		lblQty.setText(Program.LANG.getProperty("lblprodqty"));
	}

	private void updateOrderID() {
		cboOrderId.removeAllItems();
		cboOrderId.addItem("No Prev. Order");
		PurchaseOrder_MData data[] = new PurchaseOrder_M()
				.getOrderByDate(dpkOrderDate.getDate());
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				cboOrderId.addItem(data[i].pomid + "");
			}
		}
	}

	private void initLists() {
		cboProdCatg.removeAllItems();
		ProdCategoryData[] pdata = new ProdCategory().getAllProductCategories();
		if (pdata != null) {
			for (ProdCategoryData pdt : pdata) {
				cboProdCatg.addItem(pdt.catname);
			}
			cboProdCatg.setSelectedIndex(-1);
		}
	}

	private void initSupplierList() {
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

	private void resetOrderDetails() {
		txtPrevCr.setText("0");
		txtSupplId.setText("");
		txtFullName.setText("");
		txtPhone.setText("");
		txtFullName.setEditable(true);
		txtPhone.setEditable(true);
		dtmOrderList.setRowCount(0);
	}

	private void initProductList() {
		ProductsData[] proddata = null;
		if (cboProdCatg.getSelectedIndex() >= 0)
			proddata = new Products().getCategoryWiseProducts(cboProdCatg
					.getSelectedItem().toString());

		cboProduct.removeAllItems();
		txtRate.setText("");
		txtSalesRate.setText("");
		if (proddata != null) {
			for (int i = 0; i < proddata.length; i++) {
				cboProduct.addItem(proddata[i].prodname);
			}
		}
	}

	private void initSupplierData(SuppliersData data) {
		txtSupplId.setText(data.supid + "");
		txtFullName.setText(data.supname);
		txtPhone.setText(data.supphone);
		txtPrevCr.setText(data.supcr + "");
	}

	private void updateOrderList() {
		if (cboOrderId.getSelectedIndex() > 0) {
			PurchaseOrder_MData pomdata = new PurchaseOrder_M()
					.getOrderDetailsByID(Long.parseLong(cboOrderId
							.getSelectedItem().toString()));
			if (pomdata != null) {
				SuppliersData supdata = new Suppliers()
						.getSupplierById(pomdata.supid);
				if (supdata != null) {
					initSupplierData(supdata);
					txtFullName.setEditable(false);
					txtPhone.setEditable(false);
				} else {
					resetOrderDetails();
				}
			} else {
				resetOrderDetails();
			}
			PurchaseOrder_SData[] posdata = new PurchaseOrder_S()
					.getPurchaseOrderListByOrderMID(Long.parseLong(cboOrderId
							.getSelectedItem() + ""));
			if (posdata != null) {
				for (int i = 0; i < posdata.length; i++) {
					dtmOrderList.setRowCount(i + 1);
					tblOrderList.setValueAt(posdata[i].prodid, i, 0);
					ProductsData proddata = new Products()
							.getProductById(posdata[i].prodid);
					if (proddata == null) {
						Statics.handleException(new Exception(
								"Error in retrieving Product Information at PurchasesUI.java"));
						return;
					} else
						tblOrderList.setValueAt(proddata.prodname, i, 1);
					tblOrderList.setValueAt(posdata[i].prodqty, i, 2);
					tblOrderList.setValueAt(proddata.purchrate, i, 3);
					tblOrderList.setValueAt(proddata.salesrate, i, 4);
					tblOrderList.setValueAt(">", i, 5);
				}
			}
		} else
			resetOrderDetails();
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
		float prevbal = Float.parseFloat(txtPrevCr.getText());
		prevbal += total;
		txtRemAmt
				.setText(((total - paidamt) < 0 ? "0" : (total - paidamt) + ""));
		float newbal = prevbal - paidamt;
		txtNewCr.setText(newbal + "");
	}

	private void initProductId() {
		ProductsData prod = null;
		if (cboProduct.getSelectedIndex() >= 0)
			prod = new Products().getNameWiseProducts(cboProduct
					.getSelectedItem().toString());
		txtProdId.setText("");
		if (prod != null) {
			txtProdId.setText(prod.prodid + "");
			txtRate.setText(prod.purchrate + "");
			txtSalesRate.setText(prod.salesrate + "");
		}
	}

	private void calcTotal() {
		float total = 0;
		for (int i = 0; i < tblProdList.getRowCount(); i++) {
			if (tblProdList.getValueAt(i, 5) != null)
				total += Float.parseFloat(tblProdList.getValueAt(i, 5)
						.toString());
		}
		txtSubTotal.setText(total + "");
	}

	@SuppressWarnings("deprecation")
	private void initComponents() {
		lblOrderDate = new javax.swing.JLabel();
		dpkOrderDate = new DateButton();
		lblOrderId = new javax.swing.JLabel();
		cboOrderId = new javax.swing.JComboBox<String>();
		lblSupplId = new javax.swing.JLabel();
		lblFullName = new javax.swing.JLabel();
		txtFullName = new JSuggestionField();
		txtSupplId = new javax.swing.JTextField();
		txtPhone = new JSuggestionField();
		lblPhone = new javax.swing.JLabel();
		lblPurchDate = new javax.swing.JLabel();
		dpkPurchDate = new DateButton();
		jspPurchNote = new javax.swing.JScrollPane();
		txtPurchNote = new javax.swing.JTextArea();
		lblPurchNote = new javax.swing.JLabel();
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
		lblRate = new javax.swing.JLabel();
		txtRate = new javax.swing.JTextField();
		lblSalesRate = new javax.swing.JLabel();
		txtSalesRate = new javax.swing.JTextField();
		btnAddToList = new javax.swing.JButton();
		lblSubTotal = new javax.swing.JLabel();
		txtSubTotal = new javax.swing.JTextField();
		lblDiscount = new javax.swing.JLabel();
		txtDiscount = new javax.swing.JTextField();
		lblVat = new javax.swing.JLabel();
		txtVat = new javax.swing.JTextField();
		lblTotal = new javax.swing.JLabel();
		txtTotal = new javax.swing.JTextField();
		lblPrevCr = new javax.swing.JLabel();
		txtPrevCr = new javax.swing.JTextField();
		lblPaidAmt = new javax.swing.JLabel();
		txtPaidAmt = new javax.swing.JTextField();
		lblRemAmt = new javax.swing.JLabel();
		txtRemAmt = new javax.swing.JTextField();
		lblNewCr = new javax.swing.JLabel();
		txtNewCr = new javax.swing.JTextField();
		btnAddPurchases = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();

		panel.setLayout(null);

		lblOrderDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblOrderDate.setText("Order Date");
		panel.add(lblOrderDate);
		lblOrderDate.setBounds(10, 10, 220, 30);

		dpkOrderDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkOrderDate.setText("2015-09-20");
		dpkOrderDate.setNextFocusableComponent(cboOrderId);
		panel.add(dpkOrderDate);
		dpkOrderDate.setBounds(230, 10, 200, 30);

		lblOrderId.setFont(Statics.NORMAL_LARGE_FONT);
		lblOrderId.setText("Order ID");
		panel.add(lblOrderId);
		lblOrderId.setBounds(440, 10, 180, 30);

		cboOrderId.setFont(Statics.NORMAL_LARGE_FONT);
		cboOrderId.setNextFocusableComponent(txtFullName);
		panel.add(cboOrderId);
		cboOrderId.setBounds(620, 10, 200, 30);

		lblSupplId.setFont(Statics.NORMAL_LARGE_FONT);
		lblSupplId.setText("Supplier ID");
		panel.add(lblSupplId);
		lblSupplId.setBounds(10, 50, 220, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setForeground(java.awt.Color.BLUE);
		lblFullName.setText("<html><u>Supplier Name</u></html>");
		lblFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(lblFullName);
		lblFullName.setBounds(10, 90, 220, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		txtFullName.setNextFocusableComponent(txtPhone);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 90, 200, 30);

		txtSupplId.setEditable(false);
		txtSupplId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtSupplId);
		txtSupplId.setBounds(230, 50, 200, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		txtPhone.setNextFocusableComponent(txtPurchNote);
		panel.add(txtPhone);
		txtPhone.setBounds(230, 130, 200, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 130, 220, 30);

		lblPurchDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblPurchDate.setText("Purchases Date");
		panel.add(lblPurchDate);
		lblPurchDate.setBounds(440, 50, 180, 30);

		dpkPurchDate.setFont(Statics.NORMAL_LARGE_FONT);
		dpkPurchDate.setText("2015-09-20");
		panel.add(dpkPurchDate);
		dpkPurchDate.setBounds(620, 50, 200, 30);

		txtPurchNote.setColumns(20);
		txtPurchNote.setFont(Statics.NORMAL_LARGE_FONT);
		txtPurchNote.setLineWrap(true);
		txtPurchNote.setRows(5);
		txtPurchNote.setNextFocusableComponent(tblOrderList);
		jspPurchNote.setViewportView(txtPurchNote);

		panel.add(jspPurchNote);
		jspPurchNote.setBounds(620, 90, 200, 70);

		lblPurchNote.setFont(Statics.NORMAL_LARGE_FONT);
		lblPurchNote.setText("Purchases Note");
		panel.add(lblPurchNote);
		lblPurchNote.setBounds(440, 90, 180, 70);

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
						"Purch. Rate", "Sales Rate", "Add" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2487404290788831475L;
			Class<?>[] types = new Class[] { java.lang.Integer.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Float.class, java.lang.Float.class,
					java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, true };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblOrderList.setNextFocusableComponent(tblProdList);
		jspOrderList.setViewportView(tblOrderList);

		pnlProdList.add(jspOrderList);
		jspOrderList.setBounds(20, 30, 390, 240);

		jspProdList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0),
						1, true), "Items to be Purchased",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				Statics.NORMAL_LARGE_FONT));

		tblProdList.setAutoCreateRowSorter(true);
		tblProdList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Product ID", "Product Name", "Units",
						"Purch. Rate", "Sales Rate", "Total", "Remove" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8721628131826180072L;
			Class<?>[] types = new Class[] { java.lang.Integer.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Float.class, java.lang.Float.class,
					java.lang.Float.class, java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, true, true, true,
					true, true };

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

		lblProdId.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		lblProdId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblProdId.setText("Product ID");
		pnlProdList.add(lblProdId);
		lblProdId.setBounds(20, 280, 100, 30);

		txtProdId.setEditable(false);
		txtProdId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlProdList.add(txtProdId);
		txtProdId.setBounds(20, 310, 100, 30);

		lblProdCatg.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		lblProdCatg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblProdCatg.setText("Product Category");
		pnlProdList.add(lblProdCatg);
		lblProdCatg.setBounds(150, 280, 240, 30);

		cboProdCatg.setFont(Statics.NORMAL_LARGE_FONT);
		cboProdCatg.setNextFocusableComponent(cboProduct);
		pnlProdList.add(cboProdCatg);
		cboProdCatg.setBounds(150, 310, 240, 30);

		lblProduct.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		lblProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblProduct
				.setText("<html><u style=\"color:blue;\">Product Name</u></html>");
		pnlProdList.add(lblProduct);
		lblProduct.setBounds(410, 280, 250, 30);

		cboProduct.setFont(Statics.NORMAL_LARGE_FONT);
		cboProduct.setNextFocusableComponent(txtQty);
		pnlProdList.add(cboProduct);
		cboProduct.setBounds(410, 310, 250, 30);

		lblQty.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		lblQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblQty.setText("Quantity");
		pnlProdList.add(lblQty);
		lblQty.setBounds(690, 280, 100, 30);

		txtQty.setFont(Statics.NORMAL_LARGE_FONT);
		txtQty.setToolTipText("");
		txtQty.setNextFocusableComponent(txtRate);
		pnlProdList.add(txtQty);
		txtQty.setBounds(690, 310, 100, 30);

		lblRate.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		lblRate.setText("Purch. Rate/Unit");
		pnlProdList.add(lblRate);
		lblRate.setBounds(60, 350, 140, 30);

		txtRate.setFont(Statics.NORMAL_LARGE_FONT);
		txtRate.setToolTipText("");
		txtRate.setNextFocusableComponent(txtSalesRate);
		pnlProdList.add(txtRate);
		txtRate.setBounds(60, 380, 140, 30);

		lblSalesRate.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		lblSalesRate.setText("Sales Rate/Unit");
		pnlProdList.add(lblSalesRate);
		lblSalesRate.setBounds(330, 350, 140, 30);

		txtSalesRate.setFont(Statics.NORMAL_LARGE_FONT);
		txtSalesRate.setToolTipText("");
		txtSalesRate.setNextFocusableComponent(btnAddToList);
		pnlProdList.add(txtSalesRate);
		txtSalesRate.setBounds(330, 380, 140, 30);

		btnAddToList.setFont(Statics.NORMAL_LARGE_FONT); // NOI18N
		btnAddToList.setText("Add to Purchase List");
		btnAddToList.setNextFocusableComponent(txtDiscount);
		pnlProdList.add(btnAddToList);
		btnAddToList.setBounds(570, 350, 220, 60);

		panel.add(pnlProdList);
		pnlProdList.setBounds(10, 170, 810, 440);

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

		lblPrevCr.setFont(Statics.NORMAL_LARGE_FONT);
		lblPrevCr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblPrevCr.setText("Prev. Payable Cr.");
		panel.add(lblPrevCr);
		lblPrevCr.setBounds(20, 670, 150, 30);

		txtPrevCr.setEditable(false);
		txtPrevCr.setFont(Statics.NORMAL_LARGE_FONT);
		txtPrevCr.setText("0");
		txtPrevCr.setToolTipText("");
		panel.add(txtPrevCr);
		txtPrevCr.setBounds(20, 700, 150, 30);

		lblPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblPaidAmt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblPaidAmt.setText("Paid Amtount");
		panel.add(lblPaidAmt);
		lblPaidAmt.setBounds(230, 670, 120, 30);

		txtPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
		txtPaidAmt.setText("0");
		txtPaidAmt.setToolTipText("");
		txtPaidAmt.setNextFocusableComponent(btnAddPurchases);
		panel.add(txtPaidAmt);
		txtPaidAmt.setBounds(230, 700, 120, 30);

		lblRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
		lblRemAmt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblRemAmt.setText("Remaining Amt.");
		panel.add(lblRemAmt);
		lblRemAmt.setBounds(400, 670, 140, 30);

		txtRemAmt.setEditable(false);
		txtRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
		txtRemAmt.setText("0");
		txtRemAmt.setToolTipText("");
		txtRemAmt.setNextFocusableComponent(btnAddPurchases);
		panel.add(txtRemAmt);
		txtRemAmt.setBounds(400, 700, 140, 30);

		lblNewCr.setFont(Statics.NORMAL_LARGE_FONT);
		lblNewCr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblNewCr.setText("New Payable Cr.");
		panel.add(lblNewCr);
		lblNewCr.setBounds(640, 670, 150, 30);

		txtNewCr.setEditable(false);
		txtNewCr.setFont(Statics.NORMAL_LARGE_FONT);
		txtNewCr.setText("0");
		txtNewCr.setToolTipText("");
		panel.add(txtNewCr);
		txtNewCr.setBounds(640, 700, 150, 30);

		btnAddPurchases.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddPurchases.setText("Add Purchases Entry");
		btnAddPurchases.setNextFocusableComponent(btnReset);
		panel.add(btnAddPurchases);
		btnAddPurchases.setBounds(170, 750, 210, 80);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset All");
		panel.add(btnReset);
		btnReset.setBounds(390, 750, 210, 80);
	}

	private class PurchasesUIListener implements MouseListener,
			PropertyChangeListener, ActionListener, TableModelListener,
			FocusListener, KeyListener, SuggestionSelectedListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblFullName) {
				InlineWindow supwin = new InlineWindow();
				SuppliersUI supui = new SuppliersUI();
				supui.setTitleFont(Statics.TITLE_FONT);
				supwin.setContentPane(supui.getContentPane());
				supwin.setVisible(true);
				initSupplierList();
			} else if (e.getSource() == lblProduct) {
				InlineWindow prodwin = new InlineWindow();
				ProductsUI produi = new ProductsUI();
				produi.setTitleFont(Statics.TITLE_FONT);
				prodwin.setContentPane(produi.getContentPane());
				prodwin.setVisible(true);
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getSource() == dpkOrderDate) {
				updateOrderID();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cboOrderId) {
				if (cboOrderId.getSelectedIndex() > 0)
					updateOrderList();
				else
					resetOrderDetails();
			} else if (e.getSource() == cboProdCatg) {
				initProductList();
			} else if (e.getSource() == cboProduct) {
				initProductId();
			} else if (e.getSource() == btnReset) {
				resetOrderDetails();
				dtmProdList.setRowCount(0);
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
				} else if (txtRate.getText().equals("")) {
					Statics.showMessage(rootPane,
							"Please Enter Product Purchases Rate!",
							JOptionPane.ERROR_MESSAGE);
					txtRate.requestFocus();
					return;
				} else if (txtSalesRate.getText().equals("")) {
					Statics.showMessage(rootPane,
							"Please Enter Product Sales Rate!",
							JOptionPane.ERROR_MESSAGE);
					txtSalesRate.requestFocus();
					return;
				}
				int row = tblProdList.getRowCount();
				dtmProdList.setRowCount(row + 1);
				tblProdList.setValueAt(txtProdId.getText(), row, 0);
				tblProdList.setValueAt(cboProduct.getSelectedItem(), row, 1);
				tblProdList.setValueAt(Integer.parseInt(txtQty.getText()), row,
						2);
				tblProdList.setValueAt(Float.parseFloat(txtRate.getText()),
						row, 3);
				tblProdList.setValueAt(
						Float.parseFloat(txtSalesRate.getText()), row, 4);
				// tblProdList.setValueAt((Float.parseFloat(txtRate.getText())*Float.parseFloat(txtQty.getText())),
				// row, 5);
				tblProdList.setValueAt("X", row, 6);
			} else if (e.getSource() == btnAddPurchases) {
				if (txtSupplId.getText().equals("")) {
					Statics.showMessage(
							rootPane,
							"Please select Supplier by Entering Supplier Details!",
							JOptionPane.ERROR_MESSAGE);
					txtFullName.requestFocus();
					return;
				} else if (tblProdList.getRowCount() < 1) {
					Statics.showMessage(rootPane,
							"Please add Purchased Product Details!",
							JOptionPane.ERROR_MESSAGE);
					cboProduct.requestFocus();
					return;
				} else if (txtDiscount.getText().equals("")) {
					Statics.showMessage(rootPane, "Please Enter Discount!",
							JOptionPane.ERROR_MESSAGE);
					txtDiscount.requestFocus();
					return;
				} else if (txtVat.getText().equals("")) {
					Statics.showMessage(rootPane, "Please Enter Vat!",
							JOptionPane.ERROR_MESSAGE);
					txtVat.requestFocus();
					return;
				} else if (txtPurchNote.getText().equals("")) {
					txtPurchNote.setText("N/A");
				}
				for (int i = 0; i < tblProdList.getRowCount(); i++) {
					for (int j = 0; j < tblProdList.getColumnCount(); j++) {
						if (tblProdList.getValueAt(i, j) == null) {
							Statics.showMessage(
									rootPane,
									"One of the product is not entered!\nPlease review your purchased products list!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				updateGrandTotal();
				Purchases_SData[] prodlist = new Purchases_SData[tblProdList
						.getRowCount()];
				for (int i = 0; i < tblProdList.getRowCount(); i++) {
					prodlist[i] = new Purchases_SData();
					prodlist[i].prodid = Long.parseLong(tblProdList.getValueAt(
							i, 0).toString());
					prodlist[i].prodqty = Integer.parseInt(tblProdList
							.getValueAt(i, 2).toString());
					prodlist[i].purchrate = Float.parseFloat(tblProdList
							.getValueAt(i, 3).toString());
					prodlist[i].salesrate = Float.parseFloat(tblProdList
							.getValueAt(i, 4).toString());
				}
				Purchases_MData purdata = new Purchases_MData();
				purdata.discount = Float.parseFloat(txtDiscount.getText());
				purdata.pomid = (cboOrderId.getSelectedIndex() > 0 ? Long
						.parseLong(cboOrderId.getSelectedItem().toString())
						: null);
				purdata.purchasedate = dpkPurchDate.getDate();
				purdata.subtotal = Float.parseFloat(txtSubTotal.getText());
				purdata.supid = Long.parseLong(txtSupplId.getText());
				purdata.vat = Float.parseFloat(txtVat.getText());
				purdata.total = Float.parseFloat(txtTotal.getText());
				purdata.paidamt = Float.parseFloat(txtPaidAmt.getText());

				Purchases_M purchases = new Purchases_M();
				float newcr = Float.parseFloat(txtNewCr.getText());
				int ret = purchases.makePurchaseEntry(purdata, prodlist, newcr);
				if (ret > 1) {
					Statics.showMessage(getContentPane(),
							"Purchases Entry Made Successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					resetOrderDetails();
					dtmProdList.setRowCount(0);
				} else {
					Statics.showMessage(
							getContentPane(),
							"Purchases Entry not made Successfully!\nContact to your service provider",
							JOptionPane.ERROR_MESSAGE);
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
							float purchamt = 0;
							int units = 0;
							purchamt = Float.parseFloat(tblProdList.getValueAt(
									e.getLastRow(), 3).toString());
							units = Integer.parseInt(tblProdList.getValueAt(
									e.getLastRow(), 2).toString());
							purchamt = purchamt * units;
							tblProdList.setValueAt(purchamt, e.getLastRow(), 5);
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
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == tblOrderList) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					tblProdList.requestFocus();
				}
			} else if (e.getSource() == tblProdList) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					cboProdCatg.requestFocus();
				}
			}
		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtFullName) {
				SuppliersData data = new Suppliers()
						.getSupplierByName(txtFullName.getText());
				if (data != null)
					initSupplierData(data);
			}

			else if (e.getSource() == txtPhone) {
				SuppliersData data = new Suppliers()
						.getSupplierByMobileNumber(txtPhone.getText());
				if (data != null)
					initSupplierData(data);
			}
		}

		@Override
		public void focusGained(FocusEvent arg0) {
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

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
}
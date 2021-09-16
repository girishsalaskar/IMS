package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.ProdCategory;
import com.ims.models.Products;
import com.ims.models.PurchaseOrder_M;
import com.ims.models.Suppliers;
import com.ims.models.data.ProdCategoryData;
import com.ims.models.data.ProductsData;
import com.ims.models.data.PurchaseOrder_MData;
import com.ims.models.data.PurchaseOrder_SData;
import com.ims.models.data.SuppliersData;
import com.ims.reports.ReportViewer;
import com.ims.ui.common.InlineWindow;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;
import com.jdev.girish.ui.table.JButtonCellEditor;
import com.jdev.girish.ui.table.JButtonCellRenderer;
import com.jdev.girish.ui.validation.AlphaSpaceKeyListener;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;
import com.jdev.girish.ui.validation.NumberKeyListener;

/**
 *
 * @author Girish
 */
public class PurchaseOrderUI extends IFrame {

	private static final long serialVersionUID = 4975696391494579024L;

	private JButton btnAddToList;

	private JButton btnMakeOrder;

	private JButton btnReset;
	private DateButton dpkDate;
	private JScrollPane jspProdList;
	private JLabel lblDate;
	private JLabel lblFullName;
	private JLabel lblPhone;
	private JLabel lblProduct;
	private JLabel lblProdId;
	private JLabel lblQty;
	private JLabel lblSupplId;
	private JPanel pnlProdList;
	private JTable tblProdList;
	private JSuggestionField txtFullName;
	private JSuggestionField txtPhone;
	private JSuggestionField txtProduct;
	private JTextField txtProdId;
	private JTextField txtQty;
	private JTextField txtSupplId;
	private JComboBox<String> cboProgCatg;
	private JLabel lblProdCatg;
	private DefaultTableModel dtmProdList;
	private ArrayList<String> arrFullName;
	private ArrayList<String> arrProduct;
	private ArrayList<String> arrPhone;
	private PurchaseOrderUIListen listen;
	private JButton btnClearAll;
	private JLabel lblOrderNote;
	private JTextArea txtOrderNote;
	private JScrollPane jspOrderNote;

	public PurchaseOrderUI() {
		super(Program.LANG.getProperty("mnipurchorder"), 820, 515);

		listen = new PurchaseOrderUIListen();
		initComponents();

		dtmProdList = new DefaultTableModel(new Object[][] {

		}, new String[] { "Product ID", "Product Name", "Units", "Remove" }) {
			private static final long serialVersionUID = -5519440277768346421L;
			boolean[] canEdit = new boolean[] { false, false, true, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

			@Override
			public Class<?> getColumnClass(int arg0) {
				if (arg0 == 0 || arg0 == 2)
					return Integer.class;
				return super.getColumnClass(arg0);
			}
		};

		arrFullName = new ArrayList<String>();
		arrPhone = new ArrayList<String>();
		arrProduct = new ArrayList<String>();

		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);
		txtProduct.setSuggestionList(arrProduct);

		tblProdList.setModel(dtmProdList);

		// INFO JButton Cell Editor
		tblProdList.getColumn("Remove").setCellRenderer(
				new JButtonCellRenderer());
		tblProdList.getColumn("Remove").setCellEditor(
				new JButtonCellEditor(new JCheckBox()) {
					private static final long serialVersionUID = -6396381301789133086L;

					@Override
					public void btnJButtonCellEditor_actionPerformed(
							ActionEvent arg0) {
						if (this.row >= 0) {
							DefaultTableModel dtm = (DefaultTableModel) table
									.getModel();
							dtm.removeRow(this.row);
						}
					}
				});
		int[] widths = { 70, 200, 75, 60 };
		Statics.customColumnWidth(tblProdList, widths);

		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		txtProduct.addSuggestionSelectedListener(listen);
		cboProgCatg.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnAddToList.addActionListener(listen);
		btnMakeOrder.addActionListener(listen);
		btnClearAll.addActionListener(listen);
		tblProdList.addKeyListener(listen);
		txtPhone.addKeyListener(listen);
		lblFullName.addMouseListener(listen);

		txtFullName.addKeyListener(new AlphaSpaceKeyListener());
		txtPhone.addKeyListener(new NumberKeyListener());
		txtQty.addKeyListener(new NumberKeyListener());
		txtFullName.addKeyListener(new LimitedTextKeyListener(120));
		txtPhone.addKeyListener(new LimitedTextKeyListener(17));
		txtOrderNote.addKeyListener(new LimitedTextKeyListener(200));

		initList();
		
		Statics.hideColumn(tblProdList, 0);
		
		//TODO Multilanguage Text
		lblSupplId.setText(Program.LANG.getProperty("lblsupid"));
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

		cboProgCatg.removeAllItems();
		ProdCategoryData[] pdata = new ProdCategory().getAllProductCategories();
		if (pdata != null) {
			for (ProdCategoryData pdt : pdata) {
				cboProgCatg.addItem(pdt.catname);
			}
			cboProgCatg.setSelectedIndex(-1);
		}
	}

	private void initComponents() {

		lblFullName = new JLabel();
		txtFullName = new JSuggestionField();
		txtSupplId = new JTextField();
		lblSupplId = new JLabel();
		txtPhone = new JSuggestionField();
		lblPhone = new JLabel();
		lblDate = new JLabel();
		dpkDate = new DateButton();
		pnlProdList = new JPanel();
		lblProduct = new JLabel();
		txtProduct = new JSuggestionField();
		lblQty = new JLabel();
		txtQty = new JTextField();
		btnAddToList = new JButton();
		lblProdId = new JLabel();
		txtProdId = new JTextField();
		jspProdList = new JScrollPane();
		tblProdList = new JTable();
		lblProdCatg = new JLabel();
		cboProgCatg = new JComboBox<String>();
		btnClearAll = new JButton();
		btnMakeOrder = new JButton();
		btnReset = new JButton();
		lblOrderNote = new JLabel();
		jspOrderNote = new JScrollPane();
		txtOrderNote = new JTextArea();

		panel.setLayout(null);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("<html><body><u style=\"color:blue;\">Supplier Name</u></body></html>");
		lblFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(lblFullName);
		lblFullName.setBounds(10, 50, 220, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 50, 250, 30);

		txtSupplId.setEditable(false);
		txtSupplId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtSupplId);
		txtSupplId.setBounds(230, 10, 250, 30);

		lblSupplId.setFont(Statics.NORMAL_LARGE_FONT);
		lblSupplId.setText("Supplier ID");
		panel.add(lblSupplId);
		lblSupplId.setBounds(10, 10, 220, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		txtPhone.setToolTipText("");
		panel.add(txtPhone);
		txtPhone.setBounds(230, 90, 250, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 90, 220, 30);

		lblDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblDate.setText("Order Date");
		panel.add(lblDate);
		lblDate.setBounds(490, 10, 120, 30);

		dpkDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkDate);
		dpkDate.setBounds(610, 10, 180, 30);

		pnlProdList.setBorder(BorderFactory.createTitledBorder(new LineBorder(
				new java.awt.Color(0, 0, 0), 1, true),
				"Products list for Order", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, Statics.NORMAL_LARGE_FONT));
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
		btnAddToList.setBounds(20, 190, 160, 50);

		lblProdId.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdId.setText("Product ID");
		pnlProdList.add(lblProdId);
		lblProdId.setBounds(20, 30, 170, 30);

		txtProdId.setEditable(false);
		txtProdId.setFont(Statics.NORMAL_LARGE_FONT);
		pnlProdList.add(txtProdId);
		txtProdId.setBounds(190, 30, 170, 30);

		tblProdList.setAutoCreateRowSorter(true);
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
		btnClearAll.setBounds(190, 190, 170, 50);

		panel.add(pnlProdList);
		pnlProdList.setBounds(10, 130, 790, 280);

		btnMakeOrder.setFont(Statics.NORMAL_LARGE_FONT);
		btnMakeOrder.setText("Create Order");
		panel.add(btnMakeOrder);
		btnMakeOrder.setBounds(200, 420, 190, 60);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(410, 420, 190, 60);

		lblOrderNote.setFont(Statics.NORMAL_LARGE_FONT);
		lblOrderNote.setText("Order Note");
		panel.add(lblOrderNote);
		lblOrderNote.setBounds(490, 50, 120, 60);

		txtOrderNote.setColumns(1);
		txtOrderNote.setFont(Statics.NORMAL_LARGE_FONT);
		txtOrderNote.setLineWrap(true);
		txtOrderNote.setRows(1);
		txtOrderNote.setTabSize(1);
		jspOrderNote.setViewportView(txtOrderNote);

		panel.add(jspOrderNote);
		jspOrderNote.setBounds(610, 50, 180, 70);
	}

	private void resetFields() {
		txtSupplId.setText("");
		txtFullName.setText("");
		txtPhone.setText("");
		txtProdId.setText("");
		txtProduct.setText("");
		txtQty.setText("");
		cboProgCatg.setSelectedIndex(-1);
		dtmProdList.setRowCount(0);
		txtFullName.requestFocus();
	}

	private class PurchaseOrderUIListen implements SuggestionSelectedListener,
			ActionListener, KeyListener, MouseListener {
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtFullName) {
				SuppliersData data = new Suppliers()
						.getSupplierByName(txtFullName.getText());
				txtSupplId.setText(data.supid + "");
				txtFullName.setText(data.supname);
				txtPhone.setText(data.supphone);
			}

			else if (e.getSource() == txtPhone) {
				SuppliersData data = new Suppliers()
						.getSupplierByMobileNumber(txtPhone.getText());
				txtSupplId.setText(data.supid + "");
				txtFullName.setText(data.supname);
				txtPhone.setText(data.supphone);
			}

			else if (e.getSource() == txtProduct) {
				ProductsData data = new Products()
						.getNameWiseProducts(txtProduct.getText());
				if (data != null) {
					txtProdId.setText(data.prodid + "");
				}
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
			}

			else if (e.getSource() == btnAddToList) {
				if (txtProdId.getText().equals("")
						|| txtProduct.getText().equals("")
						|| cboProgCatg.getSelectedIndex() < 0) {
					Statics.showMessage(getContentPane(),
							"Please Select Product Category and Product",
							JOptionPane.ERROR_MESSAGE);
					cboProgCatg.requestFocus();
					return;
				} else if (txtQty.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter Quantities for Purchases!",
							JOptionPane.ERROR_MESSAGE);
					txtQty.requestFocus();
					return;
				}

				int rowcount = tblProdList.getRowCount();
				for (int i = 0; i < rowcount; i++) {
					if (tblProdList.getValueAt(i, 0).toString()
							.equals(txtProdId.getText())) {
						Statics.showMessage(getContentPane(),
								"Product alread exists in list!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				dtmProdList.setRowCount(rowcount + 1);
				tblProdList.setValueAt(txtProdId.getText(), rowcount, 0);
				tblProdList.setValueAt(txtProduct.getText(), rowcount, 1);
				tblProdList.setValueAt(txtQty.getText(), rowcount, 2);
				tblProdList.setValueAt("X", rowcount, 3);

				txtProdId.setText("");
				txtProduct.setText("");
				txtQty.setText("");
				cboProgCatg.setSelectedIndex(-1);
				cboProgCatg.requestFocus();
			}

			else if (e.getSource() == btnReset) {
				resetFields();
			}

			else if (e.getSource() == btnMakeOrder) {
				if (txtSupplId.getText().equals("")
						|| txtFullName.getText().equals("")
						|| txtPhone.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter Supplier's Details!",
							JOptionPane.ERROR_MESSAGE);
					txtFullName.requestFocus();
					return;
				} else if (tblProdList.getRowCount() < 1) {
					Statics.showMessage(getContentPane(),
							"Please Create List of Products placed in Order!",
							JOptionPane.ERROR_MESSAGE);
					txtProduct.requestFocus();
					return;
				}

				PurchaseOrder_MData pumdata = new PurchaseOrder_MData();
				pumdata.pomid = -1;
				pumdata.podate = dpkDate.getDate();
				pumdata.supid = Long.parseLong(txtSupplId.getText());
				PurchaseOrder_SData[] pusdata = new PurchaseOrder_SData[tblProdList
						.getRowCount()];
				for (int i = 0; i < tblProdList.getRowCount(); i++) {
					pusdata[i] = new PurchaseOrder_SData();
					pusdata[i].pomid = -1;
					pusdata[i].posid = -1;
					pusdata[i].prodid = Long.parseLong(tblProdList.getValueAt(
							i, 0).toString());
					pusdata[i].prodqty = Integer.parseInt(tblProdList
							.getValueAt(i, 2).toString());
				}
				PurchaseOrder_M pomdb = new PurchaseOrder_M();
				int ret = pomdb.createPurchaseOrder(pumdata, pusdata);
				if (ret == 0) {
					Statics.showMessage(getContentPane(),
							"Purchases Order Updated Successfully!",
							JOptionPane.ERROR_MESSAGE);
				} else if (ret == pusdata.length + 1) {
					Statics.showMessage(getContentPane(),
							"Purchases Order Updated Successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					Firm firm = new Firm();
					HashMap<String, Object> map = new HashMap<>();
					map.put("prmsupid", Long.parseLong(txtSupplId.getText()));
					map.put("prmorderdate", dpkDate.getDate());
					map.put("prmfirmid", firm.getFirmData().firmid);
					ReportViewer view = new ReportViewer("purchaseorder",
							map, firm.getConnectionObj());
					view.showReport();
					resetFields();
				} else {
					Statics.handleException(new Exception(
							"Purchases order not completed fully!"));
				}
			} else if (e.getSource() == btnClearAll) {
				dtmProdList.setRowCount(0);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == tblProdList) {
				if (e.getKeyCode() == KeyEvent.VK_TAB)
					cboProgCatg.requestFocus();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblFullName) {
				InlineWindow supwin = new InlineWindow();
				SuppliersUI supui = new SuppliersUI();
				supui.setTitleFont(Statics.TITLE_FONT);
				supwin.setContentPane(supui.getContentPane());
				supwin.setVisible(true);
				initList();
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
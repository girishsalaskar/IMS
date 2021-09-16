package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.ProdCategory;
import com.ims.models.Products;
import com.ims.models.data.ProdCategoryData;
import com.ims.models.data.ProductsData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;

/**
 *
 * @author Girish
 */
public class ProductsUI extends IFrame {

	private static final long serialVersionUID = 4977633121236606055L;

	private JButton btnAdd;
	private JButton btnReset;
	private JButton btnSave;
	private JComboBox<String> cboProdCat;
	private JScrollPane jspProdList;
	private JLabel lblProdCat;
	private JLabel lblProdDesc;
	private JLabel lblProdId;
	private JLabel lblProdName;
	private JLabel lblUnitName;
	private JLabel lblUnitDesc;
	private JTextField txtUnitName;
	private JTextField txtUnitDesc;
	private JTextField txtProdDesc;
	private JTextField txtProdId;
	private JTable tblProdList;
	private JTextField txtProdName;
	private DefaultTableModel dtmProdList;
	private ProductsUIListener listen;

	/**
	 * Creates new form ProductsUI
	 */
	public ProductsUI() {
		super(Program.LANG.getProperty("mniproduct"), 630, 330);
		dtmProdList = new DefaultTableModel(new Object[][] {

		}, new String[] { "Product ID", "Product Name", "Product Description",
				"Product Category", "Unit Name", "Unit Desc." }) {
			private static final long serialVersionUID = -2283773938662776555L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};

		listen = new ProductsUIListener();

		initComponents();

		tblProdList.setModel(dtmProdList);

		updateData();

		ProdCategoryData[] data = new ProdCategory().getAllProductCategories();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				cboProdCat.addItem(data[i].catname);
			}
		}
		cboProdCat.setSelectedIndex(-1);

		btnSave.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnAdd.addActionListener(listen);
		tblProdList.addMouseListener(listen);
		tblProdList.addKeyListener(listen);

		txtProdName.addKeyListener(new LimitedTextKeyListener(50));
		txtUnitName.addKeyListener(new LimitedTextKeyListener(10));
		txtUnitDesc.addKeyListener(new LimitedTextKeyListener(40));
		txtProdDesc.addKeyListener(new LimitedTextKeyListener(100));
		
		Statics.hideColumn(tblProdList, 0);
		
		//TODO Multilanguage Text
		lblProdCat.setText(Program.LANG.getProperty("lblprodcategory"));
		lblProdDesc.setText(Program.LANG.getProperty("lblproddesc"));
		lblProdId.setText(Program.LANG.getProperty("lblprodid"));
		lblProdName.setText(Program.LANG.getProperty("lblprodname"));
		lblUnitDesc.setText(Program.LANG.getProperty("lblprodunitdesc"));
		lblUnitName.setText(Program.LANG.getProperty("lblprodunitname"));
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnSave.setText(Program.LANG.getProperty("btnupdate"));
	}

	private void initComponents() {

		lblProdId = new javax.swing.JLabel();
		txtProdId = new javax.swing.JTextField();
		txtProdName = new javax.swing.JTextField();
		lblProdName = new javax.swing.JLabel();
		cboProdCat = new javax.swing.JComboBox<String>();
		lblProdCat = new javax.swing.JLabel();
		txtUnitName = new javax.swing.JTextField();
		lblUnitName = new javax.swing.JLabel();
		txtUnitDesc = new javax.swing.JTextField();
		lblUnitDesc = new javax.swing.JLabel();
		txtProdDesc = new javax.swing.JTextField();
		lblProdDesc = new javax.swing.JLabel();
		jspProdList = new javax.swing.JScrollPane();
		tblProdList = new javax.swing.JTable();
		btnAdd = new javax.swing.JButton();
		btnSave = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();

		panel.setLayout(null);

		lblProdId.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdId.setText("Product ID");
		panel.add(lblProdId);
		lblProdId.setBounds(10, 20, 120, 30);

		txtProdId.setEditable(false);
		txtProdId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtProdId);
		txtProdId.setBounds(160, 20, 200, 30);

		txtProdName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtProdName);
		txtProdName.setBounds(160, 60, 200, 30);

		lblProdName.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdName.setText("Product Name");
		panel.add(lblProdName);
		lblProdName.setBounds(10, 60, 120, 30);

		cboProdCat.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(cboProdCat);
		cboProdCat.setBounds(160, 100, 200, 30);

		lblProdCat.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdCat.setText("Product Category");
		panel.add(lblProdCat);
		lblProdCat.setBounds(10, 104, 150, 30);

		txtUnitName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtUnitName);
		txtUnitName.setBounds(160, 140, 200, 30);

		lblUnitName.setFont(Statics.NORMAL_LARGE_FONT);
		lblUnitName.setText("Unit Name");
		panel.add(lblUnitName);
		lblUnitName.setBounds(10, 140, 150, 30);

		txtUnitDesc.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtUnitDesc);
		txtUnitDesc.setBounds(160, 180, 200, 30);

		lblUnitDesc.setFont(Statics.NORMAL_LARGE_FONT);
		lblUnitDesc.setText("Unit Desc.");
		panel.add(lblUnitDesc);
		lblUnitDesc.setBounds(10, 180, 150, 30);

		txtProdDesc.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtProdDesc);
		txtProdDesc.setBounds(160, 220, 200, 30);

		lblProdDesc.setFont(Statics.NORMAL_LARGE_FONT);
		lblProdDesc.setText("Description");
		panel.add(lblProdDesc);
		lblProdDesc.setBounds(10, 220, 150, 30);

		tblProdList.setToolTipText("Products List");
		tblProdList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		jspProdList.setViewportView(tblProdList);

		panel.add(jspProdList);
		jspProdList.setBounds(370, 20, 250, 280);

		btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
		btnAdd.setText("Add New");
		panel.add(btnAdd);
		btnAdd.setBounds(10, 260, 110, 50);

		btnSave.setFont(Statics.NORMAL_LARGE_FONT);
		btnSave.setText("Save");
		panel.add(btnSave);
		btnSave.setBounds(130, 260, 110, 50);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(250, 260, 110, 50);
	}

	private void updateData() {
		// TODO Complete after database
		ProductsData[] data = new Products().getAllProducts();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmProdList.setRowCount(i + 1);
				tblProdList.setValueAt(data[i].prodid, i, 0);
				tblProdList.setValueAt(data[i].prodname, i, 1);
				tblProdList.setValueAt(data[i].proddesc, i, 2);
				tblProdList.setValueAt(new ProdCategory()
						.getProductCategoryByID(data[i].catid), i, 3);
				tblProdList.setValueAt(data[i].unitname, i, 4);
				tblProdList.setValueAt(data[i].unitdesc, i, 5);
			}
			Statics.resizeColumnWidth(tblProdList);
			int[] widths = { 50, 100, 150, 150, 70, 130 };
			Statics.customColumnWidth(tblProdList, widths);
		}
	}

	private void resetFields(boolean newp) {
		cboProdCat.setSelectedIndex(-1);
		txtProdDesc.setText("");
		if (newp)
			txtProdId.setText("");
		txtUnitName.setText("");
		txtUnitDesc.setText("");
		txtProdName.setText("");
	}

	private void initData() {
		if (tblProdList.getSelectedRowCount() > 0) {
			txtProdId.setText(tblProdList.getValueAt(
					tblProdList.getSelectedRow(), 0).toString());
			txtProdName.setText(tblProdList.getValueAt(
					tblProdList.getSelectedRow(), 1).toString());
			txtProdDesc.setText(tblProdList.getValueAt(
					tblProdList.getSelectedRow(), 2).toString());
			cboProdCat.setSelectedItem(tblProdList.getValueAt(
					tblProdList.getSelectedRow(), 3));
			txtUnitName.setText(tblProdList.getValueAt(
					tblProdList.getSelectedRow(), 4).toString());
			txtUnitDesc.setText(tblProdList.getValueAt(
					tblProdList.getSelectedRow(), 5).toString());
		}
	}

	private boolean validateFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).isEnabled()
						&& ((JTextField) c).isEditable()
						&& ((JTextField) c).getText().equals("")) {
					((JTextField) c).requestFocus();
					return false;
				}
			} else if (c instanceof JComboBox) {
				if (((JComboBox<?>) c).getSelectedIndex() < 0) {
					((JComboBox<?>) c).requestFocus();
					return false;
				}
			}
		}
		return true;
	}

	private class ProductsUIListener implements ActionListener, KeyListener,
			MouseListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetFields(false);
			} else if (e.getSource() == btnAdd) {
				resetFields(true);
			} else if (e.getSource() == btnSave) {
				if (!validateFields()) {
					Statics.showMessage(
							getContentPane(),
							"Please Enter All Fields and Select Proper Option!",
							javax.swing.JOptionPane.ERROR_MESSAGE);
					return;
				}
				ProductsData data = new ProductsData();
				data.proddesc = txtProdDesc.getText();
				data.prodname = txtProdName.getText();
				data.unitname = txtUnitName.getText();
				data.unitdesc = txtUnitDesc.getText();
				data.catid = new ProdCategory()
						.getProductCategoryIdByName(cboProdCat
								.getSelectedItem().toString());
				data.prodid = Long
						.parseLong(txtProdId.getText().equals("") ? -1 + ""
								: txtProdId.getText());
				Products prod = new Products();
				int opt = prod.updateProduct(data, (data.prodid == -1 ? false
						: true));
				if (opt > 0)
					Statics.showMessage(getContentPane(),
							"Product updated successfully!",
							javax.swing.JOptionPane.INFORMATION_MESSAGE);
				else
					Statics.showMessage(getContentPane(),
							"Product not updated successfully!",
							javax.swing.JOptionPane.ERROR_MESSAGE);
				resetFields(true);
				updateData();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblProdList) {
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN)
					initData();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblProdList) {
				initData();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == tblProdList)
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtProdName.requestFocus();
					e.consume();
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

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
}
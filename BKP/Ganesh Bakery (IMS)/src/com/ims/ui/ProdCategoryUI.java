package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.ProdCategory;
import com.ims.models.data.ProdCategoryData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;

/**
 *
 * @author Girish
 */
public class ProdCategoryUI extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3315290894796908231L;

	private JButton btnAddNew;
	private JButton btnReset;
	private JButton btnSave;
	private JScrollPane jspCatList;
	private JLabel lblCatId;
	private JLabel lblCatName;
	private JTable tblCatList;
	private JTextField txtCatId;
	private JTextField txtCatName;
	private DefaultTableModel dtmCatList;
	private ProdCategoryUIListener listen;
	private boolean updateflag;

	/**
	 * Creates new form ProdCategory
	 */
	public ProdCategoryUI() {
		super("Product Category", 600, 170);

		listen = new ProdCategoryUIListener();
		updateflag = false;
		dtmCatList = new DefaultTableModel(new Object[][] {}, new String[] {
				"Category ID", "Category Name" }) {
			private static final long serialVersionUID = -7287888699054050217L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		initComponents();

		updateTableData();

		tblCatList.addKeyListener(listen);
		tblCatList.addMouseListener(listen);
		btnReset.addActionListener(listen);
		btnSave.addActionListener(listen);
		btnAddNew.addActionListener(listen);

		txtCatName.addKeyListener(new LimitedTextKeyListener(30));
		
		//INFO Multilanguage text
		btnAddNew.setText(Program.LANG.getProperty("btnaddnew"));
		btnSave.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblCatId.setText(Program.LANG.getProperty("lblprodcatid"));
		lblCatName.setText(Program.LANG.getProperty("lblprodcategory"));
	}

	private void initComponents() {

		lblCatName = new JLabel();
		txtCatName = new JTextField();
		lblCatId = new JLabel();
		txtCatId = new JTextField();
		jspCatList = new JScrollPane();
		tblCatList = new JTable();
		btnAddNew = new JButton();
		btnSave = new JButton();
		btnReset = new JButton();

		lblCatName.setFont(Statics.NORMAL_LARGE_FONT);
		lblCatName.setText("Category Name");
		panel.add(lblCatName);
		lblCatName.setBounds(10, 60, 160, 30);

		txtCatName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCatName);
		txtCatName.setBounds(170, 60, 160, 30);

		lblCatId.setFont(Statics.NORMAL_LARGE_FONT);
		lblCatId.setText("Category ID");
		panel.add(lblCatId);
		lblCatId.setBounds(10, 20, 160, 30);

		txtCatId.setEditable(false);
		txtCatId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCatId);
		txtCatId.setBounds(170, 20, 160, 30);

		jspCatList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		tblCatList.setModel(dtmCatList);
		tblCatList.setAutoCreateRowSorter(true);
		jspCatList.setViewportView(tblCatList);

		panel.add(jspCatList);
		jspCatList.setBounds(340, 10, 250, 140);

		btnAddNew.setFont(Statics.NORMAL_LARGE_FONT);
		btnAddNew.setText("New");
		panel.add(btnAddNew);
		btnAddNew.setBounds(10, 100, 100, 50);

		btnSave.setFont(Statics.NORMAL_LARGE_FONT);
		btnSave.setText("Save");
		panel.add(btnSave);
		btnSave.setBounds(120, 100, 100, 50);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(230, 100, 100, 50);
	}

	private void updateTableData() {
		ProdCategoryData[] data = new ProdCategory().getAllProductCategories();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmCatList.setRowCount(i + 1);
				tblCatList.setValueAt(data[i].catid, i, 0);
				tblCatList.setValueAt(data[i].catname, i, 1);
			}
			Statics.resizeColumnWidth(tblCatList);
			int[] widths = { 100, 150 };
			Statics.customColumnWidth(tblCatList, widths);
		}
	}

	private void resetFields(boolean newp) {
		if (newp)
			txtCatId.setText("");
		txtCatName.setText("");
	}

	private void initData() {
		if (tblCatList.getSelectedRowCount() > 0) {
			txtCatId.setText(tblCatList.getValueAt(tblCatList.getSelectedRow(),
					0).toString());
			txtCatName.setText(tblCatList.getValueAt(
					tblCatList.getSelectedRow(), 1).toString());
		}
	}

	private class ProdCategoryUIListener implements ActionListener,
			KeyListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// INFO btnReset or btnAddNew
			if (e.getSource() == btnReset) {
				resetFields(false);
			} else if (e.getSource() == btnAddNew) {
				resetFields(true);
			}
			// INFO btnSave
			else if (e.getSource() == btnSave) {
				if (txtCatName.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please Enter Product Category Name",
							javax.swing.JOptionPane.ERROR_MESSAGE);
					txtCatName.requestFocus();
					return;
				}
				updateflag = !txtCatId.getText().equals("");
				ProdCategory cat = new ProdCategory();
				ProdCategoryData data = new ProdCategoryData(txtCatId.getText()
						.equals("") ? 0 : Long.parseLong(txtCatId.getText()),
						txtCatName.getText());
				if (cat.updateCategory(data, updateflag) > 0)
					Statics.showMessage(getContentPane(),
							"Record updated Successfully!",
							javax.swing.JOptionPane.INFORMATION_MESSAGE);
				else
					Statics.showMessage(getContentPane(),
							"Record not updated successfully!",
							javax.swing.JOptionPane.ERROR_MESSAGE);
				updateTableData();
				resetFields(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblCatList)
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN)
					initData();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblCatList)
				initData();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == tblCatList)
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtCatName.requestFocus();
					e.consume();
				}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
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
package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Suppliers;
import com.ims.models.data.SuppliersData;
import com.jdev.girish.ui.iframe.IFrame;

/**
 *
 * @author Girish
 */
public class SuppliersUI extends IFrame {

	private static final long serialVersionUID = -4722990555745974956L;

	private JButton btnAdd;

	private JButton btnReset;
	private JButton btnUpdate;
	private JScrollPane jspAddr;
	private JScrollPane jspSupplList;
	private JLabel lblAddr;
	private JLabel lblCompany;
	private JLabel lblFullName;
	private JLabel lblPhone;
	private JLabel lblSupplId;
	private JTable tblSupplList;
	private JTextArea txtAddr;
	private JTextField txtCompany;
	private JTextField txtFullName;
	private JTextField txtPhone;
	private JTextField txtSupplId;
	private DefaultTableModel dtmSupplist;
	private SuppliersUIListener listen;

	public SuppliersUI() {
		super(Program.LANG.getProperty("mnisuppdetails"), 800, 365);
		listen = new SuppliersUIListener();
		dtmSupplist = new DefaultTableModel(new Object[][] {

		}, new String[] { "Suppl. ID", "Supplier Name", "Supplier Address",
				"Company", "Contact No." }) {
			private static final long serialVersionUID = 3089526657822596872L;

			// boolean[] canEdit = new boolean [] {
			// false, true, false, false, true
			// };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// return canEdit [columnIndex];
				return false;
			}
		};

		initComponents();
		tblSupplList.setModel(dtmSupplist);

		updateTableData();

		btnReset.addActionListener(listen);
		btnAdd.addActionListener(listen);
		btnUpdate.addActionListener(listen);

		tblSupplList.addMouseListener(listen);
		tblSupplList.addKeyListener(listen);
		
		Statics.hideColumn(tblSupplList, 0);
		
		//TODO Multilanguage Text
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		
		lblSupplId.setText(Program.LANG.getProperty("lblsupid"));
		lblFullName.setText(Program.LANG.getProperty("lblsupname"));
		lblAddr.setText(Program.LANG.getProperty("lblsupaddress"));
		lblCompany.setText(Program.LANG.getProperty("lblsupcompany"));
	}

	private void initComponents() {

		txtSupplId = new JTextField();
		lblSupplId = new JLabel();
		lblFullName = new JLabel();
		txtFullName = new JTextField();
		jspAddr = new JScrollPane();
		txtAddr = new JTextArea();
		lblAddr = new JLabel();
		lblCompany = new JLabel();
		txtCompany = new JTextField();
		txtPhone = new JTextField();
		lblPhone = new JLabel();
		btnReset = new JButton();
		btnUpdate = new JButton();
		btnAdd = new JButton();
		jspSupplList = new JScrollPane();
		tblSupplList = new JTable();

		panel.setLayout(null);

		txtSupplId.setEditable(false);
		txtSupplId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtSupplId);
		txtSupplId.setBounds(230, 20, 250, 30);

		lblSupplId.setFont(Statics.NORMAL_LARGE_FONT);
		lblSupplId.setText("Supplier ID");
		panel.add(lblSupplId);
		lblSupplId.setBounds(10, 20, 220, 30);

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Supplier Full Name");
		panel.add(lblFullName);
		lblFullName.setBounds(10, 60, 220, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 60, 250, 30);

		txtAddr.setColumns(10);
		txtAddr.setFont(Statics.NORMAL_LARGE_FONT);
		txtAddr.setLineWrap(true);
		txtAddr.setRows(2);
		jspAddr.setViewportView(txtAddr);

		panel.add(jspAddr);
		jspAddr.setBounds(230, 100, 250, 90);

		lblAddr.setFont(Statics.NORMAL_LARGE_FONT);
		lblAddr.setText("Address");
		panel.add(lblAddr);
		lblAddr.setBounds(10, 100, 220, 30);

		lblCompany.setFont(Statics.NORMAL_LARGE_FONT);
		lblCompany.setText("Company");
		panel.add(lblCompany);
		lblCompany.setBounds(10, 200, 220, 30);

		txtCompany.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCompany);
		txtCompany.setBounds(230, 200, 250, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPhone);
		txtPhone.setBounds(230, 240, 250, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 240, 220, 30);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(340, 280, 140, 60);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(180, 280, 140, 60);

		btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
		btnAdd.setText("Add New");
		panel.add(btnAdd);
		btnAdd.setBounds(20, 280, 140, 60);

		tblSupplList.setAutoCreateRowSorter(true);
		jspSupplList.setViewportView(tblSupplList);

		panel.add(jspSupplList);
		jspSupplList.setBounds(490, 20, 290, 320);
	}

	private void resetEditableFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).isEditable())
					((JTextField) c).setText("");
			}
		}

		txtAddr.setText("");
	}

	private void initTableData() {
		if (tblSupplList.getSelectedRowCount() > 0) {
			int row = tblSupplList.getSelectedRow();
			txtSupplId.setText(tblSupplList.getValueAt(row, 0).toString());
			txtFullName.setText(tblSupplList.getValueAt(row, 1).toString());
			txtAddr.setText(tblSupplList.getValueAt(row, 2).toString());
			txtCompany.setText(tblSupplList.getValueAt(row, 3).toString());
			txtPhone.setText(tblSupplList.getValueAt(row, 4).toString());
		}
	}

	private void updateTableData() {
		SuppliersData[] data = new Suppliers().getAllSuppliers();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmSupplist.setRowCount(i + 1);
				tblSupplList.setValueAt(data[i].supid, i, 0);
				tblSupplList.setValueAt(data[i].supname, i, 1);
				tblSupplList.setValueAt(data[i].supadd, i, 2);
				tblSupplList.setValueAt(data[i].company, i, 3);
				tblSupplList.setValueAt(data[i].supphone, i, 4);
			}
			int[] widths = { 60, 120, 150, 120, 80 };
			Statics.customColumnWidth(tblSupplList, widths);
		}
	}

	private class SuppliersUIListener implements ActionListener, MouseListener,
			KeyListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetEditableFields();
			} else if (e.getSource() == btnAdd) {
				txtSupplId.setText("");
				resetEditableFields();
			} else if (e.getSource() == btnUpdate) {
				SuppliersData data = new SuppliersData();
				data.supid = (txtSupplId.getText().equals("") ? -1 : Long
						.parseLong(txtSupplId.getText()));
				data.supname = txtFullName.getText();
				data.supadd = txtAddr.getText();
				data.company = txtCompany.getText();
				data.supphone = txtPhone.getText();
				data.supcr = 0;

				int ret = new Suppliers().updateSupplier(data);
				switch (ret) {
				case 1:
					Statics.showMessage(getContentPane(),
							"Suppliers data updated successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					txtSupplId.setText("");
					resetEditableFields();
					break;

				case -1:
					Statics.showMessage(getContentPane(),
							"Supplier alread exists!",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				updateTableData();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == tblSupplList) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtFullName.requestFocus();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblSupplList) {
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					initTableData();
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblSupplList) {
				initTableData();
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
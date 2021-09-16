package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import com.ims.models.Customers;
import com.ims.models.data.CustomersData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.validation.AlphaSpaceKeyListener;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;
import com.jdev.girish.ui.validation.NumberKeyListener;

/**
 *
 * @author Girish
 */
public class CustomersUI extends IFrame {

	private static final long serialVersionUID = -3328305552016610445L;

	private JButton btnAdd;

	private JButton btnReset;

	private JButton btnUpdate;
	private JScrollPane jspCustAddr;
	private JScrollPane jspCustList;
	private JScrollPane jspRef;
	private JLabel lblCity;
	private JLabel lblCustAdd;
	private JLabel lblCustId;
	private JLabel lblCustName;
	private JLabel lblPhone;
	private JLabel lblRef;
	private JTable tblCustList;
	private JTextField txtCity;
	private JTextArea txtCustAddr;
	private JTextField txtCustId;
	private JTextField txtFullName;
	private JTextField txtPhone;
	private JTextArea txtRef;
	private DefaultTableModel dtmCustList;
	private CustomersUIListener listen;

	public CustomersUI() {
		super(Program.LANG.getProperty("mnicustdetails"), 850, 470);
		super.setTitleFont(Statics.TITLE_FONT);

		listen = new CustomersUIListener();
		dtmCustList = new DefaultTableModel(new Object[][] {

		}, new String[] { "Cust. ID", "Full Name", "Address", "City",
				"Contact No.", "Reference Details" }) {
			private static final long serialVersionUID = 5032294507213261829L;

			/*
			 * boolean[] canEdit = new boolean [] { true, false, true, true,
			 * false, true };
			 */

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// return canEdit [columnIndex];
				return false;
			}
		};
		initComponents();

		tblCustList.setModel(dtmCustList);

		updateTableData();

		btnAdd.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnUpdate.addActionListener(listen);

		tblCustList.addMouseListener(listen);
		tblCustList.addKeyListener(listen);
		tblCustList.addFocusListener(listen);

		AlphaSpaceKeyListener alphaSpace = new AlphaSpaceKeyListener();

		txtPhone.addKeyListener(new NumberKeyListener());
		txtFullName.addKeyListener(alphaSpace);
		txtCity.addKeyListener(alphaSpace);
		txtFullName.addKeyListener(new LimitedTextKeyListener(120));
		txtCustAddr.addKeyListener(new LimitedTextKeyListener(200));
		txtCity.addKeyListener(new LimitedTextKeyListener(40));
		txtPhone.addKeyListener(new LimitedTextKeyListener(17));
		txtRef.addKeyListener(new LimitedTextKeyListener(150));
		
		Statics.hideColumn(tblCustList, 0);
		
		//TODO Multilanguage Text
		lblCustId.setText(Program.LANG.getProperty("lblcustid"));
		lblCustName.setText(Program.LANG.getProperty("lblcustname"));
		lblCustAdd.setText(Program.LANG.getProperty("lblcustadd"));
		lblCity.setText(Program.LANG.getProperty("lblcity"));
		lblRef.setText(Program.LANG.getProperty("lblcustrefdet"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
	}

	private void updateTableData() {
		CustomersData[] data = new Customers().getAllCustomers();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmCustList.setRowCount(i + 1);
				tblCustList.setValueAt(data[i].custid, i, 0);
				tblCustList.setValueAt(data[i].custname, i, 1);
				tblCustList.setValueAt(data[i].custadd, i, 2);
				tblCustList.setValueAt(data[i].custcity, i, 3);
				tblCustList.setValueAt(data[i].custphone, i, 4);
				tblCustList.setValueAt(data[i].custref, i, 5);
			}
			int[] widths = { 0, 120, 150, 80, 100, 150 };
			Statics.customColumnWidth(tblCustList, widths);
		}
	}

	private void resetEditableFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).isEditable())
					((JTextField) c).setText("");
			}
		}
		txtCustAddr.setText("");
		txtRef.setText("");
	}

	private void initTableData() {
		if (tblCustList.getSelectedRowCount() > 0) {
			int row = tblCustList.getSelectedRow();
			txtCustId.setText(tblCustList.getValueAt(row, 0).toString());
			txtFullName.setText(tblCustList.getValueAt(row, 1).toString());
			txtCustAddr.setText(tblCustList.getValueAt(row, 2).toString());
			txtCity.setText(tblCustList.getValueAt(row, 3).toString());
			txtPhone.setText(tblCustList.getValueAt(row, 4).toString());
			txtRef.setText(tblCustList.getValueAt(row, 5).toString());
		}
	}

	private class CustomersUIListener implements ActionListener, MouseListener,
			KeyListener, FocusListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset)
				resetEditableFields();
			else if (e.getSource() == btnAdd) {
				resetEditableFields();
				txtCustId.setText("");
			} else if (e.getSource() == btnUpdate) {
				CustomersData data = new CustomersData();
				data.custid = (txtCustId.getText().equals("") ? -1 : Long
						.parseLong(txtCustId.getText()));
				data.custname = txtFullName.getText();
				data.custadd = txtCustAddr.getText();
				data.custcity = txtCity.getText();
				data.custphone = txtPhone.getText();
				data.custref = txtRef.getText();
				data.debtamt = (float) 0;
				data.crate_holds = 0;

				int ret = new Customers().updateCustomer(data);
				switch (ret) {
				case -1:
					Statics.showMessage(getContentPane(),
							"Customer already exists!",
							JOptionPane.ERROR_MESSAGE);
					break;

				case 1:
					Statics.showMessage(getContentPane(),
							"Customer data updated successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					txtCustId.setText("");
					resetEditableFields();
					break;

				case 0:
					Statics.showMessage(getContentPane(),
							"Customer data not updated successfully!",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				updateTableData();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblCustList) {
				initTableData();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblCustList) {
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					initTableData();
				}
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == tblCustList) {
				initTableData();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == tblCustList) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtFullName.requestFocus();
				}
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

		@Override
		public void focusLost(FocusEvent arg0) {
		}
	}

	private void initComponents() {

		lblCustId = new JLabel();
		txtCustId = new JTextField();
		txtFullName = new JTextField();
		lblCustName = new JLabel();
		lblCustAdd = new JLabel();
		jspCustAddr = new JScrollPane();
		txtCustAddr = new JTextArea();
		lblCity = new JLabel();
		txtCity = new JTextField();
		lblPhone = new JLabel();
		txtPhone = new JTextField();
		lblRef = new JLabel();
		jspRef = new JScrollPane();
		txtRef = new JTextArea();
		btnReset = new JButton();
		btnUpdate = new JButton();
		btnAdd = new JButton();
		jspCustList = new JScrollPane();
		tblCustList = new JTable();

		panel.setLayout(null);

		lblCustId.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustId.setText("Customer ID");
		panel.add(lblCustId);
		lblCustId.setBounds(10, 20, 210, 30);

		txtCustId.setEditable(false);
		txtCustId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCustId);
		txtCustId.setBounds(230, 20, 250, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 60, 250, 30);

		lblCustName.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustName.setText("Customer Full Name");
		panel.add(lblCustName);
		lblCustName.setBounds(10, 60, 210, 30);

		lblCustAdd.setFont(Statics.NORMAL_LARGE_FONT);
		lblCustAdd.setText("Customer Address");
		panel.add(lblCustAdd);
		lblCustAdd.setBounds(10, 100, 210, 30);

		txtCustAddr.setColumns(10);
		txtCustAddr.setFont(Statics.NORMAL_LARGE_FONT);
		txtCustAddr.setLineWrap(true);
		txtCustAddr.setRows(2);
		jspCustAddr.setViewportView(txtCustAddr);

		panel.add(jspCustAddr);
		jspCustAddr.setBounds(230, 100, 250, 90);

		lblCity.setFont(Statics.NORMAL_LARGE_FONT);
		lblCity.setText("City");
		panel.add(lblCity);
		lblCity.setBounds(10, 200, 210, 30);

		txtCity.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCity);
		txtCity.setBounds(230, 200, 250, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact Number");
		panel.add(lblPhone);
		lblPhone.setBounds(10, 240, 210, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPhone);
		txtPhone.setBounds(230, 240, 250, 30);

		lblRef.setFont(Statics.NORMAL_LARGE_FONT);
		lblRef.setText("Reference Details");
		panel.add(lblRef);
		lblRef.setBounds(10, 280, 210, 40);

		txtRef.setColumns(10);
		txtRef.setFont(Statics.NORMAL_LARGE_FONT);
		txtRef.setLineWrap(true);
		txtRef.setRows(2);
		jspRef.setViewportView(txtRef);

		panel.add(jspRef);
		jspRef.setBounds(230, 280, 250, 100);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(340, 390, 140, 60);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(180, 390, 140, 60);

		btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
		btnAdd.setText("Add New");
		panel.add(btnAdd);
		btnAdd.setBounds(20, 390, 140, 60);

		jspCustList.setViewportView(tblCustList);

		panel.add(jspCustList);
		jspCustList.setBounds(490, 20, 350, 430);
	}
}
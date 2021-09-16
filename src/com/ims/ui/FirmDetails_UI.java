package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.data.Firm_Data;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;

/**
 *
 * @author Girish
 */
public class FirmDetails_UI extends IFrame {

	private static final long serialVersionUID = 1395976435303549668L;

	private javax.swing.JButton btnNew;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jspFirmAddr;
	private javax.swing.JLabel lblFirmAddr;
	private javax.swing.JLabel lblFirmId;
	private javax.swing.JLabel lblLicNo;
	private javax.swing.JLabel lblOwnName;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblfirmname;
	private javax.swing.JTable tblFirmDetails;
	private javax.swing.JTextArea txtFirmAddr;
	private javax.swing.JTextField txtFirmId;
	private javax.swing.JTextField txtFirmName;
	private javax.swing.JTextField txtLicNo;
	private javax.swing.JTextField txtOwnName;
	private javax.swing.JTextField txtPhone;

	private FirmDetailsListener listen;

	public FirmDetails_UI() {
		super(Program.LANG.getProperty("mnifirmprof"), 737, 392);
		listen = new FirmDetailsListener();
		initComponents();

		updateFirmDetails();

		btnUpdate.addActionListener(listen);
		btnReset.addActionListener(listen);
		txtFirmName.addKeyListener(new LimitedTextKeyListener(40));
		txtLicNo.addKeyListener(new LimitedTextKeyListener(100));
		txtFirmAddr.addKeyListener(new LimitedTextKeyListener(200));
		txtOwnName.addKeyListener(new LimitedTextKeyListener(60));
		txtPhone.addKeyListener(new LimitedTextKeyListener(17));
		tblFirmDetails.getModel().addTableModelListener(listen);

		// TODO Multilanguage Text
		lblFirmAddr.setText(Program.LANG.getProperty("lblfirmaddr"));
		lblLicNo.setText(Program.LANG.getProperty("lbllicenseno"));
		lblOwnName.setText(Program.LANG.getProperty("lblownername"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblfirmname.setText(Program.LANG.getProperty("lblfirmname"));
	}

	private void updateFirmDetails() {
		Firm_Data[] data=new Firm().getAllFirms();
		((DefaultTableModel)tblFirmDetails.getModel()).setRowCount(0);
		if(data!=null)
		{
			for(int i=0;i<data.length;i++)
			{
				((DefaultTableModel)tblFirmDetails.getModel()).setRowCount(i+1);
				tblFirmDetails.setValueAt(data[i].firmid, i, 0);
				tblFirmDetails.setValueAt(data[i].firmname, i, 1);
				tblFirmDetails.setValueAt(data[i].licno, i, 2);
				tblFirmDetails.setValueAt(data[i].firmaddr, i, 3);
				tblFirmDetails.setValueAt(data[i].firmprop, i, 4);
				tblFirmDetails.setValueAt(data[i].firmphone, i, 5);
				tblFirmDetails.setValueAt((data[i].active==1), i, 6);
			}
		}
	}

	private void initData() {
		if(tblFirmDetails.getSelectedRowCount()>0)
		{
			Firm_Data data = new Firm().getFirmById(Integer.parseInt(tblFirmDetails.getValueAt(tblFirmDetails.getSelectedRow(), 0).toString()));
			if (data != null) {
				txtFirmId.setText(data.firmid+"");
				txtFirmName.setText(data.firmname);
				txtLicNo.setText(data.licno);
				txtFirmAddr.setText(data.firmaddr);
				txtOwnName.setText(data.firmprop);
				txtPhone.setText(data.firmphone);
			}
		}
	}

	private void initComponents() {
		lblfirmname = new javax.swing.JLabel();
		txtFirmName = new javax.swing.JTextField();
		lblLicNo = new javax.swing.JLabel();
		txtLicNo = new javax.swing.JTextField();
		lblFirmAddr = new javax.swing.JLabel();
		jspFirmAddr = new javax.swing.JScrollPane();
		txtFirmAddr = new javax.swing.JTextArea();
		lblOwnName = new javax.swing.JLabel();
		txtOwnName = new javax.swing.JTextField();
		lblPhone = new javax.swing.JLabel();
		txtPhone = new javax.swing.JTextField();
		btnUpdate = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		lblFirmId = new javax.swing.JLabel();
		txtFirmId = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblFirmDetails = new javax.swing.JTable();
		btnNew = new javax.swing.JButton();

		panel.setLayout(null);

		lblfirmname.setFont(Statics.NORMAL_LARGE_FONT);
		lblfirmname.setText("Firm Name");
		panel.add(lblfirmname);
		lblfirmname.setBounds(20, 50, 180, 30);

		txtFirmName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFirmName);
		txtFirmName.setBounds(200, 50, 200, 30);

		lblLicNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblLicNo.setText("License No.");
		panel.add(lblLicNo);
		lblLicNo.setBounds(20, 90, 180, 30);

		txtLicNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtLicNo);
		txtLicNo.setBounds(200, 90, 200, 30);

		lblFirmAddr.setFont(Statics.NORMAL_LARGE_FONT);
		lblFirmAddr.setText("Firm Address");
		panel.add(lblFirmAddr);
		lblFirmAddr.setBounds(20, 130, 180, 30);

		txtFirmAddr.setColumns(20);
		txtFirmAddr.setFont(Statics.NORMAL_LARGE_FONT);
		txtFirmAddr.setRows(2);
		txtFirmAddr.addKeyListener(listen);
		jspFirmAddr.setViewportView(txtFirmAddr);

		panel.add(jspFirmAddr);
		jspFirmAddr.setBounds(200, 130, 200, 80);

		lblOwnName.setFont(Statics.NORMAL_LARGE_FONT);
		lblOwnName.setText("Owner Name");
		panel.add(lblOwnName);
		lblOwnName.setBounds(20, 220, 180, 30);

		txtOwnName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtOwnName);
		txtOwnName.setBounds(200, 220, 200, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact No.");
		panel.add(lblPhone);
		lblPhone.setBounds(20, 260, 180, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPhone);
		txtPhone.setBounds(200, 260, 200, 30);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
        btnUpdate.setText("Update");
        panel.add(btnUpdate);
        btnUpdate.setBounds(150, 300, 120, 50);

        btnReset.setFont(Statics.NORMAL_LARGE_FONT);
        btnReset.setText("Reset");
        panel.add(btnReset);
        btnReset.setBounds(280, 300, 120, 50);

		lblFirmId.setFont(Statics.NORMAL_LARGE_FONT);
		lblFirmId.setText("Firm Name");
		panel.add(lblFirmId);
		lblFirmId.setBounds(20, 10, 180, 30);

		txtFirmId.setEditable(false);
		txtFirmId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFirmId);
		txtFirmId.setBounds(200, 10, 200, 30);

		tblFirmDetails.setAutoCreateRowSorter(true);
		tblFirmDetails
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][]{},
						new String[] { "Firm ID", "Firm Name", "License No.",
								"Firm Address", "Owner Name", "Contact No.",
								"Active" }) {

					private static final long serialVersionUID = 8614868233156520341L;
					Class<?>[] types = new Class[] { java.lang.Long.class,
							java.lang.String.class, java.lang.String.class,
							java.lang.String.class, java.lang.String.class,
							java.lang.String.class, java.lang.Boolean.class };
					boolean[] canEdit = new boolean[] { false, false, false,
							false, false, false, true };

					public Class<?> getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		tblFirmDetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		tblFirmDetails.addKeyListener(listen);
		tblFirmDetails.addMouseListener(listen);
		jScrollPane1.setViewportView(tblFirmDetails);

		panel.add(jScrollPane1);
		jScrollPane1.setBounds(410, 10, 300, 340);
		
		btnNew.setFont(Statics.NORMAL_LARGE_FONT);
        btnNew.setText("New");
        btnNew.addActionListener(listen);
        panel.add(btnNew);
        btnNew.setBounds(20, 300, 120, 50);
	}

	private void resetFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField && ((JTextField) c).isEditable())
				((JTextField) c).setText("");
			else if (c instanceof JTextArea)
				((JTextArea) c).setText("");
		}
		txtFirmAddr.setText("");
	}

	private boolean validateFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField && ((JTextField) c).isEditable()) {
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
		if (txtFirmAddr.getText().equals("")) {
			Statics.showMessage(getContentPane(), "Please Enter All Fields!",
					JOptionPane.ERROR_MESSAGE);
			txtFirmAddr.requestFocus();
			return false;
		}
		return true;
	}

	private class FirmDetailsListener implements ActionListener, KeyListener, MouseListener, TableModelListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetFields();
			}

			else if(e.getSource()==btnNew)
			{
				resetFields();
				txtFirmId.setText("");
			}
			else if (e.getSource() == btnUpdate) {
				if (validateFields()) {
					Firm_Data data = new Firm_Data();
					if(txtFirmId.getText().equals(""))
					{
						data.firmid=-1;
					}
					else data.firmid=Integer.parseInt(txtFirmId.getText());
					data.firmname = txtFirmName.getText();
					data.licno = txtLicNo.getText();
					data.firmaddr = txtFirmAddr.getText();
					data.firmprop = txtOwnName.getText();
					data.firmphone = txtPhone.getText();

					Firm firm = new Firm();
					data.active=(firm.getFirmData()!=null?0:1);
					int ret = firm.updateFirmData(data);
					switch (ret) {
					case -1:
						Statics.showMessage(getContentPane(),
								"Firm Data not Updated Successfully!",
								JOptionPane.ERROR_MESSAGE);
						break;

					case -2:
						Statics.showMessage(
								getContentPane(),
								"Cannot insert new Record! Firm Details alread exists!",
								JOptionPane.ERROR_MESSAGE);
						break;

					case 1:
						Statics.showMessage(getContentPane(),
								"Firm details updated successfully!",
								JOptionPane.INFORMATION_MESSAGE);
						break;

					default:
						Statics.showMessage(getContentPane(),
								"Firm details not updated successfully!"
										+ "\nContact to your service provider",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
					dispose();
					resetFields();
					FirmSwitchUI.initData();
					txtFirmId.setText("");
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent evt) {
			if (evt.getSource() == tblFirmDetails) {
				if (evt.getKeyChar() == KeyEvent.VK_TAB) {
					evt.consume();
					txtLicNo.requestFocus();
				}
			} else if (evt.getSource() == txtFirmAddr) {
				if (evt.getKeyChar() == KeyEvent.VK_TAB) {
					evt.consume();
					txtOwnName.requestFocus();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent evt) {
			if (evt.getSource() == tblFirmDetails) {
				if (evt.getKeyChar() == KeyEvent.VK_UP
						|| evt.getKeyChar() == KeyEvent.VK_DOWN) {
					initData();
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource()==tblFirmDetails)
			{
				initData();
			}
		}
		
		@Override
		public void tableChanged(TableModelEvent e) {
			if(e.getSource()==tblFirmDetails.getModel())
			{
				if(e.getType()==TableModelEvent.UPDATE)
				{
					if(e.getColumn()==6)
					{
						if(tblFirmDetails.getValueAt(e.getLastRow(), 0)!=null)
						{
							Firm firm=new Firm();
							Firm_Data data=firm.getFirmById(Integer.parseInt(tblFirmDetails.getValueAt(e.getLastRow(), 0).toString()));
							if(data!=null)
							{
								int ret=firm.activateFirm(data);
								if(ret>0)
									Statics.showMessage(rootPane, "Firm \""+tblFirmDetails.getValueAt(e.getLastRow(), 1)+"\" activated successfully!", JOptionPane.INFORMATION_MESSAGE);
								else	
									Statics.showMessage(rootPane, "Firm \""+tblFirmDetails.getValueAt(e.getLastRow(), 1)+"\" not activated successfully!", JOptionPane.ERROR_MESSAGE);
								//updateFirmDetails();
								FirmSwitchUI.initData();
								dispose();
							}
						}
					}
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
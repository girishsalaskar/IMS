package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

	private JButton btnReset;

	private JButton btnUpdate;

	private JScrollPane jspFirmAddr;
	private JLabel lblFirmAddr;
	private JLabel lblOwnName;
	private JLabel lblPhone;
	private JLabel lblfirmname;
	private JTextField txtFirmName;
	private JTextArea txtFirmAddr;
	private JTextField txtOwnName;
	private JTextField txtPhone;
	private FirmDetailsListener listen;
	private JLabel lblLicNo;
	private JTextField txtLicNo;
	private int firmid;

	public FirmDetails_UI() {
		super(Program.LANG.getProperty("mnifirmprof"), 430, 340);
		listen = new FirmDetailsListener();
		initComponents();
		firmid = -1;
		initData();

		btnUpdate.addActionListener(listen);
		btnReset.addActionListener(listen);
		txtFirmName.addKeyListener(new LimitedTextKeyListener(40));
		txtLicNo.addKeyListener(new LimitedTextKeyListener(100));
		txtFirmAddr.addKeyListener(new LimitedTextKeyListener(200));
		txtOwnName.addKeyListener(new LimitedTextKeyListener(60));
		txtPhone.addKeyListener(new LimitedTextKeyListener(17));
		
		//TODO Multilanguage Text
		lblFirmAddr.setText(Program.LANG.getProperty("lblfirmaddr"));
		lblLicNo.setText(Program.LANG.getProperty("lbllicenseno"));
		lblOwnName.setText(Program.LANG.getProperty("lblownername"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblfirmname.setText(Program.LANG.getProperty("lblfirmname"));
	}

	private void initData() {
		Firm_Data data = new Firm().getFirmData();
		if (data != null) {
			firmid = data.firmid;
			txtFirmName.setText(data.firmname);
			txtLicNo.setText(data.licno);
			txtFirmAddr.setText(data.firmaddr);
			txtOwnName.setText(data.firmprop);
			txtPhone.setText(data.firmphone);
		}
	}

	private void initComponents() {

		lblfirmname = new JLabel();
		txtFirmName = new JTextField();
		lblLicNo = new JLabel();
		txtLicNo = new JTextField();
		lblFirmAddr = new JLabel();
		jspFirmAddr = new JScrollPane();
		txtFirmAddr = new JTextArea();
		lblOwnName = new JLabel();
		txtOwnName = new JTextField();
		lblPhone = new JLabel();
		txtPhone = new JTextField();
		btnUpdate = new JButton();
		btnReset = new JButton();

		panel.setLayout(null);

		lblfirmname.setFont(Statics.NORMAL_LARGE_FONT);
		lblfirmname.setText("Firm Name");
		panel.add(lblfirmname);
		lblfirmname.setBounds(20, 20, 180, 30);

		txtFirmName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFirmName);
		txtFirmName.setBounds(200, 20, 200, 30);

		lblLicNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblLicNo.setText("License No.");
		panel.add(lblLicNo);
		lblLicNo.setBounds(20, 60, 180, 30);

		txtLicNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtLicNo);
		txtLicNo.setBounds(200, 60, 200, 30);

		lblFirmAddr.setFont(Statics.NORMAL_LARGE_FONT);
		lblFirmAddr.setText("Firm Address");
		panel.add(lblFirmAddr);
		lblFirmAddr.setBounds(20, 100, 180, 30);

		txtFirmAddr.setColumns(5);
		txtFirmAddr.setFont(Statics.NORMAL_LARGE_FONT);
		txtFirmAddr.setLineWrap(true);
		txtFirmAddr.setRows(2);
		jspFirmAddr.setViewportView(txtFirmAddr);

		panel.add(jspFirmAddr);
		jspFirmAddr.setBounds(200, 100, 200, 80);

		lblOwnName.setFont(Statics.NORMAL_LARGE_FONT);
		lblOwnName.setText("Owner Name");
		panel.add(lblOwnName);
		lblOwnName.setBounds(20, 190, 180, 30);

		txtOwnName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtOwnName);
		txtOwnName.setBounds(200, 190, 200, 30);

		lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
		lblPhone.setText("Contact No.");
		panel.add(lblPhone);
		lblPhone.setBounds(20, 230, 180, 30);

		txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtPhone);
		txtPhone.setBounds(200, 230, 200, 30);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(20, 270, 170, 50);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(230, 270, 170, 50);
	}

	public int getFirmID() {
		return firmid;
	}

	private void resetFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField)
				((JTextField) c).setText("");
			else if (c instanceof JTextArea)
				((JTextArea) c).setText("");
		}
		txtFirmAddr.setText("");
	}

	private boolean validateFields() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
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

	private class FirmDetailsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetFields();
			}

			else if (e.getSource() == btnUpdate) {
				if (validateFields()) {
					Firm_Data data = new Firm_Data();
					data.firmid = firmid;
					data.firmname = txtFirmName.getText();
					data.licno = txtLicNo.getText();
					data.firmaddr = txtFirmAddr.getText();
					data.firmprop = txtOwnName.getText();
					data.firmphone = txtPhone.getText();

					Firm firm = new Firm();
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
				}
			}
		}
	}
}
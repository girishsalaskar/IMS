package com.ims.ui;

import javax.swing.JOptionPane;

import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.data.Firm_Data;

/**
 *
 * @author Girish
 */
public class FirmSwitchUI extends javax.swing.JPanel {

	private static final long serialVersionUID = -3706842109795433613L;

	public FirmSwitchUI() {
		initComponents();
		initData();
	}

	public static void initData() {
		Firm_Data[] data = new Firm().getAllFirms();
		if (data != null) {
			cboFirmNames.removeAllItems();
			int selindex=0;
			for (int i = 0; i < data.length; i++) {
				cboFirmNames.addItem(data[i].firmid + " | " + data[i].firmname);
				if(data[i].active==1)
				{
					selindex=i;
				}
			}
			cboFirmNames.setSelectedIndex(selindex);
		}
	}

	private void initComponents() {

		cboFirmNames = new javax.swing.JComboBox<>();
		btnActivate = new javax.swing.JButton();

		FormListener formListener = new FormListener();

		setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Change Firm Details", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.TOP, Statics.NORMAL_LARGE_FONT));
		setLayout(null);

		cboFirmNames.setName("cboFirmNames"); // NOI18N
		add(cboFirmNames);
		cboFirmNames.setBounds(20, 30, 260, 40);

		btnActivate.setText("Activate");
		btnActivate.setName("btnActivate"); // NOI18N
		btnActivate.addActionListener(formListener);
		add(btnActivate);
		btnActivate.setBounds(20, 90, 260, 50);
	}

	private class FormListener implements java.awt.event.ActionListener {
		FormListener() {
		}

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			if (evt.getSource() == btnActivate) {
				FirmSwitchUI.this.btnActivateActionPerformed(evt);
			}
		}
	}

	private void btnActivateActionPerformed(java.awt.event.ActionEvent evt) {
		Firm firm=new Firm();
		Firm_Data active=firm.getFirmById(Integer.parseInt(cboFirmNames.getSelectedItem().toString().split(" | ")[0]));
		if(firm.activateFirm(active)>1)
		{
			Statics.showMessage(getParent(), "Firm switched successfully!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private javax.swing.JButton btnActivate;
	private static javax.swing.JComboBox<String> cboFirmNames;
}

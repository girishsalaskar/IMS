package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Assets;
import com.ims.models.data.AssetsData;
import com.jdev.girish.ui.iframe.IFrame;

/**
 *
 * @author Girish Salaskar
 */
public class AssetsUI extends IFrame {

	private javax.swing.JButton btnAdd;

	private javax.swing.JButton btnReset;

	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox cboAsset;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblAssetList;
	private javax.swing.JLabel lblAssetName;
	private javax.swing.JLabel lblValue;
	private javax.swing.JTable tblAssetList;
	private javax.swing.JTextField txtOther;
	private javax.swing.JTextField txtValue;
	private DefaultTableModel dtmAssetList;

	private int assetid;

	private AssetsUIListen listen;

	public AssetsUI() {
		super(Program.LANG.getProperty("mniassets"), 640, 220);
		initComponents();
		dtmAssetList = (DefaultTableModel) tblAssetList.getModel();
		txtOther.setEnabled(false);
		assetid = 0;
		listen = new AssetsUIListen();

		cboAsset.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnAdd.addActionListener(listen);
		btnUpdate.addActionListener(listen);
		tblAssetList.addMouseListener(listen);
		tblAssetList.addKeyListener(listen);

		updateAssetsTable();

		Statics.hideColumn(tblAssetList, 0);
		tblAssetList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
		//TODO Multilanguage Text
		lblAssetList.setText(Program.LANG.getProperty("lblassetlist"));
		lblAssetName.setText(Program.LANG.getProperty("lblassetname"));
		lblValue.setText(Program.LANG.getProperty("lblassetvalue"));
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
	}

	private void updateAssetsTable() {
		AssetsData[] data = new Assets().getAllAssets();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmAssetList.setRowCount(i + 1);
				tblAssetList.setValueAt(data[i].assetid, i, 0);
				tblAssetList.setValueAt(data[i].astname, i, 1);
				tblAssetList.setValueAt(data[i].astvalue, i, 2);
			}
		}
	}

	private void initComponents() {

		lblAssetName = new javax.swing.JLabel();
		txtOther = new javax.swing.JTextField();
		lblValue = new javax.swing.JLabel();
		txtValue = new javax.swing.JTextField();
		lblAssetList = new javax.swing.JLabel();
		cboAsset = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblAssetList = new javax.swing.JTable();
		btnAdd = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();

		panel.setLayout(null);

		lblAssetName.setFont(Statics.NORMAL_LARGE_FONT);
		lblAssetName.setText("Other");
		panel.add(lblAssetName);
		lblAssetName.setBounds(10, 50, 170, 30);

		txtOther.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtOther);
		txtOther.setBounds(180, 50, 160, 30);

		lblValue.setFont(Statics.NORMAL_LARGE_FONT);
		lblValue.setText("Value");
		panel.add(lblValue);
		lblValue.setBounds(10, 90, 170, 30);

		txtValue.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtValue);
		txtValue.setBounds(180, 90, 160, 30);

		lblAssetList.setFont(Statics.NORMAL_LARGE_FONT);
		lblAssetList.setText("Name");
		panel.add(lblAssetList);
		lblAssetList.setBounds(10, 10, 170, 30);

		cboAsset.setFont(Statics.NORMAL_LARGE_FONT);
		cboAsset.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Crate", "Vehicle", "Other" }));
		panel.add(cboAsset);
		cboAsset.setBounds(180, 10, 160, 30);

		tblAssetList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "ID", "Asset Name", "Value" }) {
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(tblAssetList);

		panel.add(jScrollPane1);
		jScrollPane1.setBounds(350, 10, 270, 180);

		btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
		btnAdd.setText("New");
		panel.add(btnAdd);
		btnAdd.setBounds(10, 130, 100, 60);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(120, 130, 100, 60);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(230, 130, 110, 60);
	}

	private void initTableData() {
		if (tblAssetList.getSelectedRowCount() > 0) {
			int row = tblAssetList.getSelectedRow();
			if (tblAssetList.getValueAt(row, 1).toString()
					.equalsIgnoreCase("crate")
					|| tblAssetList.getValueAt(row, 1).toString()
							.equalsIgnoreCase("vehicle")) {
				cboAsset.setSelectedItem(tblAssetList.getValueAt(row, 1)
						.toString());
				txtOther.setText("");
			} else {
				cboAsset.setSelectedIndex(2);
				txtOther.setText(tblAssetList.getValueAt(row, 1).toString());
				txtOther.setEnabled(true);
			}
			txtValue.setText(tblAssetList.getValueAt(row, 2).toString());
		}
	}

	private class AssetsUIListen implements ActionListener, MouseListener,
			KeyListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cboAsset) {
				if (cboAsset.getSelectedItem().toString()
						.equalsIgnoreCase("other"))
					txtOther.setEnabled(true);
				else
					txtOther.setEnabled(false);
			} else if (e.getSource() == btnReset) {
				cboAsset.setSelectedIndex(0);
				txtOther.setText("");
				txtValue.setText("");
			} else if (e.getSource() == btnUpdate) {
				if (txtOther.isEnabled() && txtOther.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter asset name in other!",
							JOptionPane.ERROR_MESSAGE);
					txtOther.requestFocus();
					return;
				} else if (txtValue.getText().equals("")) {
					Statics.showMessage(getContentPane(),
							"Please enter asset value!",
							JOptionPane.ERROR_MESSAGE);
					txtValue.requestFocus();
					return;
				} else if (cboAsset.getSelectedIndex() == 0) {
					try {
						int qty = Integer.parseInt(txtValue.getText());
					} catch (NumberFormatException e1) {
						Statics.showMessage(getContentPane(),
								"Please enter quantity in numbers!",
								JOptionPane.ERROR_MESSAGE);
						txtValue.setText("");
						txtValue.requestFocus();
						return;
					}
				}

				AssetsData data = new AssetsData();
				data.assetid = assetid;
				data.astname = (cboAsset.getSelectedIndex() == 2 ? txtOther
						.getText() : cboAsset.getSelectedItem().toString());
				data.astvalue = txtValue.getText();
				Assets ast = new Assets();
				int ret = ast.updateAsset(data);
				if (ret > 0) {
					Statics.showMessage(getContentPane(),
							"Asset updated successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					cboAsset.setSelectedIndex(0);
					assetid = 0;
					txtOther.setText("");
					txtValue.setText("");
				}
				updateAssetsTable();
			} else if (e.getSource() == btnAdd) {
				cboAsset.setSelectedIndex(0);
				assetid = 0;
				txtOther.setText("");
				txtValue.setText("");
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblAssetList) {
				initTableData();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblAssetList
					&& (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)) {
				initTableData();
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
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
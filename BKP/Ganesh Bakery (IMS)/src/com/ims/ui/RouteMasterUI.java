package com.ims.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.RouteM;
import com.ims.models.data.RouteMData;
import com.jdev.girish.ui.iframe.IFrame;

/**
 *
 * @author Girish
 */
public class RouteMasterUI extends IFrame {

	private javax.swing.JButton btnAdd;

	private javax.swing.JButton btnReset;

	private javax.swing.JButton btnUpdate;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblCities;
	private javax.swing.JLabel lblRouteName;
	private javax.swing.JLabel lblRouteNo;
	private javax.swing.JTable tblRouteList;
	private javax.swing.JTextField txtCities;
	private javax.swing.JTextField txtRouteName;
	private javax.swing.JTextField txtRouteNo;

	private javax.swing.table.DefaultTableModel dtmRoutList;
	private RouteMasterUIListen listen;
	private int routeid;

	public RouteMasterUI() {
		super(Program.LANG.getProperty("mniroutemaster"), 640, 220);
		initComponents();

		routeid = -1;
		listen = new RouteMasterUIListen();
		dtmRoutList = (DefaultTableModel) tblRouteList.getModel();

		btnAdd.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnUpdate.addActionListener(listen);
		tblRouteList.addMouseListener(listen);
		tblRouteList.addKeyListener(listen);

		updateRouteTable();

		int[] widths = { 0, 40, 100, 150 };
		Statics.customColumnWidth(tblRouteList, widths);

		Statics.hideColumn(tblRouteList, 0);
		
		//TODO Multilanguage Text
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		lblRouteName.setText(Program.LANG.getProperty("lblroutename"));
		lblRouteNo.setText(Program.LANG.getProperty("lblrouteno"));
		
		lblCities.setText(Program.LANG.getProperty("lblcities"));
	}

	private void updateRouteTable() {
		RouteMData[] data = new RouteM().getAllRouteDetails();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmRoutList.setRowCount(i + 1);
				tblRouteList.setValueAt(data[i].routeid, i, 0);
				tblRouteList.setValueAt(data[i].routeno, i, 1);
				tblRouteList.setValueAt(data[i].routename, i, 2);
				tblRouteList.setValueAt(data[i].locations, i, 3);
			}
		}
	}

	private void initComponents() {

		lblRouteNo = new javax.swing.JLabel();
		txtRouteNo = new javax.swing.JTextField();
		txtRouteName = new javax.swing.JTextField();
		lblRouteName = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblRouteList = new javax.swing.JTable();
		btnReset = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnAdd = new javax.swing.JButton();
		txtCities = new javax.swing.JTextField();
		lblCities = new javax.swing.JLabel();

		panel.setLayout(null);

		lblRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		lblRouteNo.setText("Route Number");
		lblRouteNo.setToolTipText("");
		panel.add(lblRouteNo);
		lblRouteNo.setBounds(10, 10, 170, 30);

		txtRouteNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtRouteNo);
		txtRouteNo.setBounds(180, 10, 160, 30);

		txtRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtRouteName);
		txtRouteName.setBounds(180, 50, 160, 30);

		lblRouteName.setFont(Statics.NORMAL_LARGE_FONT);
		lblRouteName.setText("Route Name");
		panel.add(lblRouteName);
		lblRouteName.setBounds(10, 50, 170, 30);

		tblRouteList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Route ID", "Route No", "Route Name",
						"Cities" }) {
			boolean[] canEdit = new boolean[] { false, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(tblRouteList);

		panel.add(jScrollPane1);
		jScrollPane1.setBounds(350, 10, 270, 180);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(230, 130, 110, 60);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(120, 130, 100, 60);

		btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
		btnAdd.setText("New");
		panel.add(btnAdd);
		btnAdd.setBounds(10, 130, 100, 60);

		txtCities.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtCities);
		txtCities.setBounds(180, 90, 160, 30);

		lblCities.setFont(Statics.NORMAL_LARGE_FONT);
		lblCities.setText("Cities/Villages");
		panel.add(lblCities);
		lblCities.setBounds(10, 90, 170, 30);
	}

	private void initData() {
		if (tblRouteList.getSelectedRowCount() > 0) {
			int row = tblRouteList.getSelectedRow();
			routeid = Integer.parseInt(tblRouteList.getValueAt(row, 0)
					.toString());
			txtRouteNo.setText(tblRouteList.getValueAt(row, 1).toString());
			txtRouteName.setText(tblRouteList.getValueAt(row, 2).toString());
			txtCities.setText(tblRouteList.getValueAt(row, 3).toString());
		}
	}

	private class RouteMasterUIListen implements ActionListener, MouseListener,
			KeyListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnUpdate) {
				RouteMData data = new RouteMData();
				data.routeid = routeid;
				data.routeno = Integer.parseInt(txtRouteNo.getText());
				data.routename = txtRouteName.getText();
				data.locations = txtCities.getText();

				RouteM rm = new RouteM();
				int ret = rm.updateRouteDetails(data);
				if (ret > 0) {
					Statics.showMessage(getContentPane(),
							"Route updated successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					updateRouteTable();
					for (Component com : panel.getComponents()) {
						if (com instanceof JTextField) {
							((JTextField) com).setText("");
						}
					}
					return;
				} else {
					Statics.showMessage(getContentPane(),
							"Route not updated successfully!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else if (e.getSource() == btnReset) {
				for (Component com : panel.getComponents()) {
					if (com instanceof JTextField) {
						((JTextField) com).setText("");
					}
				}
			} else if (e.getSource() == btnAdd) {
				for (Component com : panel.getComponents()) {
					if (com instanceof JTextField) {
						((JTextField) com).setText("");
					}
				}
				routeid = -1;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblRouteList) {
				initData();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblRouteList
					&& (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)) {
				initData();
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
}
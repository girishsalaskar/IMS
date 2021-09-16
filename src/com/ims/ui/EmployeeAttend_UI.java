package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.EmployeeAttend;
import com.ims.models.EmployeeM;
import com.ims.models.data.EmployeeAttend_Data;
import com.ims.models.data.EmployeeM_Data;
import com.jdev.girish.ui.iframe.IFrame;

/**
 *
 * @author Girish
 */
public class EmployeeAttend_UI extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764541070198597556L;

	private JButton btnSaveAttend;

	private DateButton dpkAttendDate;

	private JScrollPane jspEmpList;
	private JLabel lblAttendDate;
	private JTable tblEmpList;
	private DefaultTableModel dtmEmpList;
	private EmployeeAttend_UIListener listen;

	public EmployeeAttend_UI() {
		super(Program.LANG.getProperty("mniempattend"), 670, 420);

		listen = new EmployeeAttend_UIListener();

		dtmEmpList = new DefaultTableModel(new Object[][] {

		},
				new String[] { "Emp. ID", "Name", "Mobile No.", "Address",
						"Present" }) {
			private static final long serialVersionUID = -3132791957004057685L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 4)
					return btnSaveAttend.isEnabled();
				return false;
			}

			@Override
			public Class<?> getColumnClass(int arg0) {
				if (arg0 == 4)
					return Boolean.class;
				return super.getColumnClass(arg0);
			}
		};
		initComponents();

		btnSaveAttend.addActionListener(listen);
		dpkAttendDate.addPropertyChangeListener(listen);

		checkExists();
		
		Statics.hideColumn(tblEmpList, 0);
		
		//TODO Multilanguage Text
		lblAttendDate.setText(Program.LANG.getProperty("lblempattenddate"));
		btnSaveAttend.setText(Program.LANG.getProperty("btnempsaveattend"));
	}

	private void initComponents() {

		lblAttendDate = new JLabel();
		dpkAttendDate = new DateButton();
		jspEmpList = new JScrollPane();
		tblEmpList = new JTable();
		btnSaveAttend = new JButton();

		lblAttendDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblAttendDate.setText("Attendance Date");
		panel.add(lblAttendDate);
		lblAttendDate.setBounds(160, 20, 160, 40);

		dpkAttendDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkAttendDate);
		dpkAttendDate.setBounds(320, 20, 150, 40);

		tblEmpList.setModel(dtmEmpList);
		tblEmpList.setAutoCreateRowSorter(true);
		jspEmpList.setViewportView(tblEmpList);

		panel.add(jspEmpList);
		jspEmpList.setBounds(10, 70, 650, 290);

		btnSaveAttend.setFont(Statics.NORMAL_LARGE_FONT);
		btnSaveAttend.setText("Save Attendance");
		panel.add(btnSaveAttend);
		btnSaveAttend.setBounds(230, 370, 180, 40);
	}

	private void checkExists() {
		EmployeeAttend_Data[] data = new EmployeeAttend()
				.getAttendanceByDate(dpkAttendDate.getDate());
		if (data != null) {
			btnSaveAttend.setEnabled(false);
			for (int i = 0; i < data.length; i++) {
				EmployeeM_Data edata = new EmployeeM()
						.getEmployeeById(data[i].empid);
				dtmEmpList.setRowCount(i + 1);
				tblEmpList.setValueAt(edata.empid, i, 0);
				tblEmpList.setValueAt(edata.empname, i, 1);
				tblEmpList.setValueAt(edata.empmo, i, 2);
				tblEmpList.setValueAt(edata.empadd, i, 3);
				tblEmpList.setValueAt(data[i].status == 1, i, 4);
			}
			int widths[] = { 50, 170, 100, 250, 50 };
			Statics.customColumnWidth(tblEmpList, widths);
		} else {
			btnSaveAttend.setEnabled(true);
			initTable();
		}
	}

	private void initTable() {
		EmployeeM_Data[] data = new EmployeeM().getAllEnabledEmployees();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmEmpList.setRowCount(i + 1);
				tblEmpList.setValueAt(data[i].empid, i, 0);
				tblEmpList.setValueAt(data[i].empname, i, 1);
				tblEmpList.setValueAt(data[i].empmo, i, 2);
				tblEmpList.setValueAt(data[i].empadd, i, 3);
				tblEmpList.setValueAt(true, i, 4);
			}
			int widths[] = { 50, 170, 100, 250, 50 };
			Statics.customColumnWidth(tblEmpList, widths);
		}
	}

	private class EmployeeAttend_UIListener implements ActionListener,
			PropertyChangeListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSaveAttend) {
				if (tblEmpList.getRowCount() > 0) {
					if (JOptionPane
							.showConfirmDialog(
									getContentPane(),
									"Are you sure about the attendance "
											+ "given by you?\nIf not, then recheck attendance by clicking No."
											+ "\nIf confirm, then click Yes for saving attendance",
									Program.APP.getProperty("APPTITLE"),
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						EmployeeAttend_Data[] data = new EmployeeAttend_Data[tblEmpList
								.getRowCount()];
						for (int i = 0; i < tblEmpList.getRowCount(); i++) {
							data[i] = new EmployeeAttend_Data();
							data[i].attendid = -1;
							data[i].empid = Long.parseLong(tblEmpList
									.getValueAt(i, 0).toString());
							data[i].attenddate = dpkAttendDate.getDate();
							data[i].status = ((boolean) tblEmpList.getValueAt(
									i, 4) ? 1 : 0);
						}
						EmployeeAttend attend = new EmployeeAttend();
						if (attend.updateAttendance(data) == tblEmpList
								.getRowCount())
							Statics.showMessage(
									getContentPane(),
									"All employee attendance saved successfully!",
									JOptionPane.INFORMATION_MESSAGE);
						else
							Statics.showMessage(
									getContentPane(),
									"Some or no any attendance saved successfully!\n"
											+ "Please Contact to service provider.",
									JOptionPane.ERROR_MESSAGE);
						dispose();
					} else
						Statics.showMessage(
								getContentPane(),
								"Saving of attendance cancelled! Please confirm attendance and save.",
								JOptionPane.INFORMATION_MESSAGE);
				} else
					Statics.showMessage(getContentPane(),
							"No Employees Found!", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getSource() == dpkAttendDate) {
				if (e.getPropertyName().equalsIgnoreCase("date")) {
					checkExists();
				}
			}
		}
	}
}
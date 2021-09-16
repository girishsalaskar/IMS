package com.ims.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.EmployeeM;
import com.ims.models.data.EmployeeM_Data;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.validation.AlphaSpaceKeyListener;
import com.jdev.girish.ui.validation.DecimalKeyListener;
import com.jdev.girish.ui.validation.LimitedTextKeyListener;
import com.jdev.girish.ui.validation.NumberKeyListener;

/**
 *
 * @author Girish
 */
public class EmployeeM_UI extends IFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnAdd;

	private JButton btnReset;

	private JButton btnUpdate;
	private JCheckBox chkAdvance;
	private DateButton dpkJoinDate;
	private JScrollPane jspAddress;
	private JScrollPane jspEmpList;
	private JLabel lblAddress;
	private JLabel lblEmpId;
	private JLabel lblFullName;
	private JLabel lblEmpMo;
	private JLabel lblJoinDate;
	private JTextArea txtAddress;
	private JTable tblEmpList;
	private DefaultTableModel dtmEmpList;
	private JTextField txtEmpAdvance;
	private JTextField txtEmpId;
	private JTextField txtFullName;
	private JTextField txtMobNo;
	private JLabel lblEmpPic;
	private JLabel lblEmpPicV;
	private JButton btnGetImg;
	private JLabel lblEmpSal;
	private JLabel lblEmpDesgn;
	private JTextField txtEmpSal;
	private JTextField txtEmpDesgn;
	private File fleEmpPic;

	private EmployeeM_UIListener listen;

	public EmployeeM_UI() {
		super(Program.LANG.getProperty("mniempenroll"), 930, 580);
		fleEmpPic = null;

		dtmEmpList = new DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Full Name", "Address", "Contact Number",
				"Joining Date", "Designation", "Salary", "Balance",
				"Photo Name"// , "Enabled"
		}) {
			private static final long serialVersionUID = -5020433676879424525L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int cid) {
				if (cid == 0)
					return Integer.class;
				return super.getColumnClass(cid);
			}
		};

		initComponents();
		listen = new EmployeeM_UIListener();
		btnAdd.addActionListener(listen);
		btnReset.addActionListener(listen);
		btnUpdate.addActionListener(listen);
		btnGetImg.addActionListener(listen);

		tblEmpList.addKeyListener(listen);
		tblEmpList.addMouseListener(listen);
		txtMobNo.addKeyListener(new NumberKeyListener());
		txtAddress.addKeyListener(listen);

		txtAddress.addKeyListener(new LimitedTextKeyListener(120));
		txtFullName.addKeyListener(new LimitedTextKeyListener(60));
		txtMobNo.addKeyListener(new LimitedTextKeyListener(15));
		txtEmpDesgn.addKeyListener(new LimitedTextKeyListener(30));

		AlphaSpaceKeyListener alpha = new AlphaSpaceKeyListener();
		txtFullName.addKeyListener(alpha);
		txtMobNo.addKeyListener(new NumberKeyListener());
		txtEmpDesgn.addKeyListener(alpha);
		txtEmpSal.addKeyListener(new DecimalKeyListener());
		updateTableData();
		resetPic();
		
		Statics.hideColumn(tblEmpList, 0);
		
		//INFO Multilanguage Text assignment
		lblEmpId.setText(Program.LANG.getProperty("lblempid"));
		lblFullName.setText(Program.LANG.getProperty("lblempname"));
		lblAddress.setText(Program.LANG.getProperty("lblempadd"));
		lblEmpMo.setText(Program.LANG.getProperty("lblcontactno"));
		lblEmpPic.setText(Program.LANG.getProperty("lblemppic"));
		lblJoinDate.setText(Program.LANG.getProperty("lbljoindate"));
		lblEmpDesgn.setText(Program.LANG.getProperty("lbldesgn"));
		lblEmpSal.setText(Program.LANG.getProperty("lblempsal"));
		chkAdvance.setText(Program.LANG.getProperty("chkempadvance"));
		btnGetImg.setText(Program.LANG.getProperty("btnpicbrowse"));
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
	}

	private void initComponents() {

		lblFullName = new JLabel();
		txtFullName = new JTextField();
		txtMobNo = new JTextField();
		lblAddress = new JLabel();
		lblEmpMo = new JLabel();
		jspAddress = new JScrollPane();
		txtAddress = new JTextArea();
		lblJoinDate = new JLabel();
		dpkJoinDate = new DateButton();
		chkAdvance = new JCheckBox();
		txtEmpAdvance = new JTextField();
		btnAdd = new JButton();
		btnUpdate = new JButton();
		btnReset = new JButton();
		jspEmpList = new JScrollPane();
		tblEmpList = new JTable();
		txtEmpId = new JTextField();
		lblEmpId = new JLabel();
		lblEmpPic = new JLabel();
		lblEmpPicV = new JLabel();
		btnGetImg = new JButton();
		lblEmpDesgn = new JLabel();
		txtEmpDesgn = new JTextField();
		lblEmpSal = new JLabel();
		txtEmpSal = new JTextField();

		lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
		lblFullName.setText("Employee Full Name");
		panel.add(lblFullName);
		lblFullName.setBounds(10, 60, 210, 30);

		txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtFullName);
		txtFullName.setBounds(230, 60, 250, 30);

		txtMobNo.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtMobNo);
		txtMobNo.setBounds(230, 200, 250, 30);

		lblAddress.setFont(Statics.NORMAL_LARGE_FONT);
		lblAddress.setText("Employee Address");
		panel.add(lblAddress);
		lblAddress.setBounds(10, 100, 210, 30);

		lblEmpMo.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpMo.setText("Contact Number");
		panel.add(lblEmpMo);
		lblEmpMo.setBounds(10, 200, 210, 30);

		txtAddress.setColumns(10);
		txtAddress.setFont(Statics.NORMAL_LARGE_FONT);
		txtAddress.setLineWrap(true);
		txtAddress.setRows(2);
		jspAddress.setViewportView(txtAddress);

		panel.add(jspAddress);
		jspAddress.setBounds(230, 100, 250, 90);

		lblJoinDate.setFont(Statics.NORMAL_LARGE_FONT);
		lblJoinDate.setText("Joining Date");
		panel.add(lblJoinDate);
		lblJoinDate.setBounds(10, 350, 210, 30);

		dpkJoinDate.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(dpkJoinDate);
		dpkJoinDate.setBounds(230, 340, 160, 40);

		chkAdvance.setFont(Statics.NORMAL_LARGE_FONT);
		chkAdvance.setText("Advance to Employee");
		chkAdvance.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (chkAdvance.isSelected()) {
					txtEmpAdvance.setEnabled(true);
					txtEmpAdvance.setText("");
					txtEmpAdvance.requestFocus();
				} else {
					txtEmpAdvance.setEnabled(false);
					txtEmpAdvance.setText("0");
					btnUpdate.requestFocus();
				}
			}
		});
		panel.add(chkAdvance);
		chkAdvance.setBounds(10, 470, 210, 30);

		txtEmpAdvance.setFont(Statics.NORMAL_LARGE_FONT);
		txtEmpAdvance.setEnabled(false);
		txtEmpAdvance.setText("0");
		panel.add(txtEmpAdvance);
		txtEmpAdvance.setBounds(230, 470, 250, 30);

		btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
		btnAdd.setText("Add New");
		panel.add(btnAdd);
		btnAdd.setBounds(20, 510, 140, 60);

		btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
		btnUpdate.setText("Update");
		panel.add(btnUpdate);
		btnUpdate.setBounds(180, 510, 140, 60);

		btnReset.setFont(Statics.NORMAL_LARGE_FONT);
		btnReset.setText("Reset");
		panel.add(btnReset);
		btnReset.setBounds(340, 510, 140, 60);

		tblEmpList.setModel(dtmEmpList);
		tblEmpList.setAutoCreateRowSorter(true);
		jspEmpList.setViewportView(tblEmpList);

		panel.add(jspEmpList);
		jspEmpList.setBounds(490, 20, 430, 540);

		txtEmpId.setEditable(false);
		txtEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpId);
		txtEmpId.setBounds(230, 20, 250, 30);

		lblEmpId.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpId.setText("Employee ID");
		panel.add(lblEmpId);
		lblEmpId.setBounds(10, 20, 210, 30);

		lblEmpPic.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpPic.setText("Employee Picture");
		panel.add(lblEmpPic);
		lblEmpPic.setBounds(10, 270, 210, 30);

		lblEmpPicV.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panel.add(lblEmpPicV);
		lblEmpPicV.setBounds(230, 240, 90, 90);

		btnGetImg.setFont(Statics.NORMAL_LARGE_FONT);
		btnGetImg.setText("Select Picture");
		panel.add(btnGetImg);
		btnGetImg.setBounds(330, 240, 150, 90);

		lblEmpDesgn.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpDesgn.setText("Designation");
		panel.add(lblEmpDesgn);
		lblEmpDesgn.setBounds(10, 390, 210, 30);

		txtEmpDesgn.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpDesgn);
		txtEmpDesgn.setBounds(230, 390, 250, 30);

		lblEmpSal.setFont(Statics.NORMAL_LARGE_FONT);
		lblEmpSal.setText("Salary");
		panel.add(lblEmpSal);
		lblEmpSal.setBounds(10, 430, 210, 30);

		txtEmpSal.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(txtEmpSal);
		txtEmpSal.setBounds(230, 430, 250, 30);
	}

	private void updateTableData() {
		EmployeeM_Data[] data = new EmployeeM().getAllEnabledEmployees();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				dtmEmpList.setRowCount(i + 1);
				tblEmpList.setValueAt(data[i].empid, i, 0);
				tblEmpList.setValueAt(data[i].empname, i, 1);
				tblEmpList.setValueAt(data[i].empadd, i, 2);
				tblEmpList.setValueAt(data[i].empmo, i, 3);
				tblEmpList.setValueAt(new java.text.SimpleDateFormat(
						"dd-MMM-yyyy").format(data[i].joindate), i, 4);
				tblEmpList.setValueAt(data[i].desgn, i, 5);
				tblEmpList.setValueAt(data[i].empsal, i, 6);
				tblEmpList.setValueAt(data[i].acbal, i, 7);
				tblEmpList.setValueAt(data[i].emppic, i, 8);
			}
			int colwidths[] = { 50, 150, 180, 120, 120, 80 /* 50 */};
			Statics.customColumnWidth(tblEmpList, colwidths);
		}
	}

	@SuppressWarnings("deprecation")
	private void initData() {// INFO initData
		if (tblEmpList.getSelectedRowCount() > 0) {
			txtEmpId.setText(tblEmpList.getValueAt(tblEmpList.getSelectedRow(),
					0).toString());
			txtFullName.setText(tblEmpList.getValueAt(
					tblEmpList.getSelectedRow(), 1).toString());
			txtAddress.setText(tblEmpList.getValueAt(
					tblEmpList.getSelectedRow(), 2).toString());
			txtMobNo.setText(tblEmpList.getValueAt(tblEmpList.getSelectedRow(),
					3).toString());
			dpkJoinDate.setText(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date(tblEmpList.getValueAt(
							tblEmpList.getSelectedRow(), 4).toString())));
			txtEmpDesgn.setText(tblEmpList.getValueAt(
					tblEmpList.getSelectedRow(), 5).toString());
			txtEmpSal.setText(tblEmpList.getValueAt(
					tblEmpList.getSelectedRow(), 6).toString());
			txtEmpAdvance.setText(tblEmpList.getValueAt(
					tblEmpList.getSelectedRow(), 7).toString());
			Image img = null;
			try {
				File file = new File(Statics.ROOT + "img" + Statics.DIR_SEP
						+ "emp" + Statics.DIR_SEP + txtEmpId.getText() + "."
						+ tblEmpList.getValueAt(tblEmpList.getSelectedRow(), 8));
				if (file.exists())
					img = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			if (img != null) {
				lblEmpPicV.setIcon(new ImageIcon(img.getScaledInstance(
						lblEmpPicV.getWidth(), lblEmpPicV.getHeight(),
						Image.SCALE_SMOOTH)));
			} else
				resetPic();

			chkAdvance.setEnabled(false);
			chkAdvance.setSelected(false);
			chkAdvance.setText("Balance Amt.");
		}
	}

	private void resetFields(boolean newemp) {// INFO reset Fields
		if (newemp) {
			txtEmpId.setText("");
			chkAdvance.setEnabled(true);
			dpkJoinDate.setEnabled(true);
			chkAdvance.setText("Advance to Employee");
			txtEmpAdvance.setText("0");
		}

		txtFullName.setText("");
		txtAddress.setText("");
		txtMobNo.setText("");
		txtEmpSal.setText("");
		txtEmpDesgn.setText("");
		chkAdvance.setSelected(false);
		resetPic();
	}

	private boolean validateFields() {
		if (txtFullName.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please enter Employee's full name!", JOptionPane.OK_OPTION
							+ JOptionPane.ERROR_MESSAGE);
			txtFullName.requestFocus();
			return false;
		} else if (txtAddress.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please enter Employee's address!",
					JOptionPane.ERROR_MESSAGE);
			txtAddress.requestFocus();
			return false;
		} else if (txtMobNo.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please enter Employee's mobile number!",
					JOptionPane.ERROR_MESSAGE);
			txtMobNo.requestFocus();
			return false;
		} else if (txtEmpDesgn.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please Enter Employee's Designation",
					JOptionPane.ERROR_MESSAGE);
			txtEmpDesgn.requestFocus();
			return false;
		} else if (txtEmpSal.getText().equals("")) {
			Statics.showMessage(getContentPane(),
					"Please Enter Employee Salary", JOptionPane.ERROR_MESSAGE);
			txtEmpSal.requestFocus();
			return false;
		} else if (chkAdvance.isSelected()) {
			if (txtEmpAdvance.getText().equals("")) {
				Statics.showMessage(getContentPane(),
						"Please enter advance amount given to Employee!",
						JOptionPane.ERROR_MESSAGE);
				txtEmpAdvance.requestFocus();
				return false;
			}
		} else if(fleEmpPic==null)
		{
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to update worker details without his/her picture?","Inventory Mgt. System",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION)
			{
				return false;
			}
		}
		return true;
	}

	private void resetPic() {
		fleEmpPic=null;
		try {
			BufferedImage img = ImageIO.read(Statics
					.getImageStream("emptypic.png"));
			lblEmpPicV.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
	}

	private class EmployeeM_UIListener implements ActionListener, KeyListener,
			MouseListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnGetImg) {
				JFileChooser choose;
				choose = new JFileChooser(fleEmpPic);

				choose.removeChoosableFileFilter(choose
						.getAcceptAllFileFilter());
				choose.addChoosableFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "Supported Image File";
					}

					@Override
					public boolean accept(File e) {
						if (e.getAbsolutePath().endsWith(".jpg")
								|| e.getAbsolutePath().endsWith(".jpeg")
								|| e.getAbsolutePath().endsWith(".png")
								|| e.isDirectory())
							return true;
						return false;
					}
				});

				choose.setFileSelectionMode(0);
				if (choose.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
					fleEmpPic = choose.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(fleEmpPic);
						lblEmpPicV.setIcon(new ImageIcon(img.getScaledInstance(
								lblEmpPicV.getWidth(), lblEmpPicV.getHeight(),
								Image.SCALE_FAST)));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						Statics.handleException(e1);
						resetPic();
					}
				}
			} else if (e.getSource() == btnAdd) {
				resetFields(true);
			}

			else if (e.getSource() == btnReset) {
				resetFields(false);
			}

			else if (e.getSource() == btnUpdate) {
				if (validateFields()) {
					EmployeeM_Data data = new EmployeeM_Data();

					data.empid = Long
							.parseLong(txtEmpId.getText().equals("") ? -1 + ""
									: txtEmpId.getText());
					data.empname = txtFullName.getText();
					data.empmo = txtMobNo.getText();
					data.empadd = txtAddress.getText();
					data.joindate = dpkJoinDate.getDate();
					if (txtEmpAdvance.getText().equalsIgnoreCase("nill"))
						data.acbal = 0;
					else
						data.acbal = Integer.parseInt(txtEmpAdvance.getText());
					data.leavedate = null;
					data.empsal = Integer.parseInt(txtEmpSal.getText());
					data.desgn = txtEmpDesgn.getText();
					data.empenabled = (short) 1;
					if (fleEmpPic != null)
						data.emppic = fleEmpPic.getAbsolutePath()
								.substring(
										fleEmpPic.getAbsolutePath()
												.lastIndexOf(".") + 1,
										fleEmpPic.getAbsolutePath().length());
					else
						data.emppic = "N/A";

					EmployeeM emp = new EmployeeM();

					int ret = emp.updateEmployee(data, data.empid == -1 ? true
							: false);
					switch (ret) {
					case 0:
						Statics.showMessage(getContentPane(),
								"Employee Record not Updated Successfully!",
								JOptionPane.ERROR_MESSAGE);
						break;

					case -1:
						Statics.showMessage(
								getContentPane(),
								"Employee Name and Mobile already exists! Please check for repeat entry or check new Employee's Name and Mobile Number",
								JOptionPane.ERROR_MESSAGE);
						break;

					case -2:
						Statics.showMessage(
								getContentPane(),
								"Employee record updated, but advance to employee is not updated successfully!",
								JOptionPane.WARNING_MESSAGE);
						break;

					default:
						Statics.showMessage(getContentPane(),
								"Employee data updated successfully.",
								JOptionPane.INFORMATION_MESSAGE);
						updateTableData();
						if (fleEmpPic != null) {
							Statics.makeDir("img");
							Statics.makeDir("img" + Statics.DIR_SEP + "emp");
							EmployeeM_Data data1;
							if (txtEmpId.getText().equals(""))
								data1 = emp.getLasteEmployee();
							else
								data1 = emp.getEmployeeById(Long
										.parseLong(txtEmpId.getText()));
							File destfile = new File(Statics.ROOT
									+ "img"
									+ Statics.DIR_SEP
									+ "emp"
									+ Statics.DIR_SEP
									+ data1.empid
									+ "."
									+ fleEmpPic.getAbsolutePath().substring(
											fleEmpPic.getAbsolutePath()
													.lastIndexOf(".") + 1));
							if (destfile.exists())
								destfile.delete();
							Statics.imageCopy(fleEmpPic, destfile, data.emppic);
						}
						resetFields(true);
						break;
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblEmpList) {
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					initData();
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblEmpList) {
				initData();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == txtAddress) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					e.consume();
					txtMobNo.requestFocus();
				}
			} else if (e.getSource() == tblEmpList) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					e.consume();
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
	}
}
package com.ims.ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.SupplierPayment;
import com.ims.models.Suppliers;
import com.ims.models.data.SupplierPayData;
import com.ims.models.data.SuppliersData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class SupplierPaymentUI extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5371719229048543675L;

	private javax.swing.JButton btnAdd;

	private javax.swing.JButton btnReset;

	private javax.swing.JButton btnBrowse;
	private javax.swing.JButton btnUpdate;
	private DateButton dpkPayDate;
	private javax.swing.JScrollPane jspPayList;
	private javax.swing.JLabel lblAccNo;
	private javax.swing.JLabel lblBankName;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblPaidAmt;
	private javax.swing.JLabel lblPayDate;
	private javax.swing.JLabel lblPaySlip;
	private javax.swing.JLabel picPaySlip;
	private javax.swing.JTable tblPayList;
	private javax.swing.JTextField txtAccNo;
	private javax.swing.JTextField txtBankName;
	private JSuggestionField txtFullName;
	private javax.swing.JTextField txtPaidAmt;
	private javax.swing.JLabel lblBillNo;
	private javax.swing.JTextField txtBillNo;
	private javax.swing.JLabel lblRemAmt;
	private javax.swing.JLabel lblPrevBal;
	private javax.swing.JTextField txtRemAmt;
	private javax.swing.JTextField txtPrevBal;
	
	private ArrayList<String> arrFullName;
	private long supid;
	private File fleSlip;
	private SupplierPaymentUIListen listen;
	private javax.swing.table.DefaultTableModel dtmPayList;

	public SupplierPaymentUI() {
		super(Program.LANG.getProperty("mnisuppayment"), 780, 610);
		initComponents();
		supid = -1;
		arrFullName = new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);
		dtmPayList = (javax.swing.table.DefaultTableModel) tblPayList
				.getModel();
		listen = new SupplierPaymentUIListen();
		int[] widths = { 0, 100, 60, 100, 60, 60, 40, 0 };
		Statics.customColumnWidth(tblPayList, widths);

		btnReset.addActionListener(listen);
		btnAdd.addActionListener(listen);
		btnBrowse.addActionListener(listen);
		btnUpdate.addActionListener(listen);
		tblPayList.addKeyListener(listen);
		tblPayList.addMouseListener(listen);
		txtFullName.addSuggestionSelectedListener(listen);
		txtPaidAmt.addFocusListener(listen);

		fleSlip = null;
		resetPic();
		initTableData();
		Statics.hideColumn(tblPayList, 0);
		
		//TODO Multilanguage Text
		lblFullName.setText(Program.LANG.getProperty("lblsupname"));
		btnAdd.setText(Program.LANG.getProperty("btnaddnew"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		btnUpdate.setText(Program.LANG.getProperty("btnupdate"));
		
		lblPrevBal.setText(Program.LANG.getProperty("lblsuptotalpending"));
		lblBankName.setText(Program.LANG.getProperty("lblbankname"));
		lblAccNo.setText(Program.LANG.getProperty("lblaccountno"));
		lblPayDate.setText(Program.LANG.getProperty("lblsuppaydate"));
		lblPaidAmt.setText(Program.LANG.getProperty("lblsuppaidamt"));
		lblRemAmt.setText(Program.LANG.getProperty("lblsupremainamt"));
		lblBillNo.setText(Program.LANG.getProperty("lblbillno"));
		lblPaySlip.setText(Program.LANG.getProperty("lblsuppayslip"));
		btnBrowse.setText(Program.LANG.getProperty("btnbrowse"));
	}

	private void initTableData() {
		SupplierPayData[] data = new SupplierPayment().getAllPaymentDetails();
		if (data != null) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			for (int i = 0; i < data.length; i++) {
				SuppliersData sdata = new Suppliers()
						.getSupplierById(data[i].supid);
				dtmPayList.setRowCount(i + 1);
				tblPayList.setValueAt(data[i].payid, i, 0);
				tblPayList.setValueAt(sdata.supname, i, 1);
				tblPayList.setValueAt(data[i].bankname, i, 2);
				tblPayList.setValueAt(data[i].bacno, i, 3);
				tblPayList.setValueAt(format.format(data[i].paydate), i, 4);
				tblPayList.setValueAt(data[i].billno, i, 5);
				tblPayList.setValueAt(data[i].paidamt, i, 6);
				tblPayList.setValueAt(data[i].slippic, i, 7);
			}
		}

		SuppliersData[] sdata = new Suppliers().getAllSuppliers();
		if (sdata != null) {
			arrFullName.clear();
			for (int i = 0; i < sdata.length; i++) {
				arrFullName.add(sdata[i].supname);
			}
		}
	}

	private void initComponents() {

		lblFullName = new javax.swing.JLabel();
        txtFullName = new JSuggestionField();
        lblBankName = new javax.swing.JLabel();
        txtBankName = new javax.swing.JTextField();
        lblAccNo = new javax.swing.JLabel();
        txtAccNo = new javax.swing.JTextField();
        lblPayDate = new javax.swing.JLabel();
        dpkPayDate = new DateButton();
        lblPaidAmt = new javax.swing.JLabel();
        txtPaidAmt = new javax.swing.JTextField();
        lblPaySlip = new javax.swing.JLabel();
        picPaySlip = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jspPayList = new javax.swing.JScrollPane();
        tblPayList = new javax.swing.JTable();
        lblBillNo = new javax.swing.JLabel();
        txtBillNo = new javax.swing.JTextField();
        lblPrevBal = new javax.swing.JLabel();
        txtPrevBal = new javax.swing.JTextField();
        lblRemAmt = new javax.swing.JLabel();
        txtRemAmt = new javax.swing.JTextField();

        panel.setLayout(null);

        lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
        lblFullName.setText("Supplier Name");
        lblFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblFullName);
        lblFullName.setBounds(10, 10, 140, 30);

        txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtFullName);
        txtFullName.setBounds(150, 10, 280, 30);

        lblBankName.setFont(Statics.NORMAL_LARGE_FONT);
        lblBankName.setText("Bank Name");
        lblBankName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblBankName);
        lblBankName.setBounds(10, 90, 140, 30);

        txtBankName.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtBankName);
        txtBankName.setBounds(150, 90, 280, 30);

        lblAccNo.setFont(Statics.NORMAL_LARGE_FONT);
        lblAccNo.setText("Account No.");
        lblAccNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblAccNo);
        lblAccNo.setBounds(10, 130, 140, 30);

        txtAccNo.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtAccNo);
        txtAccNo.setBounds(150, 130, 280, 30);

        lblPayDate.setFont(Statics.NORMAL_LARGE_FONT);
        lblPayDate.setText("Payment Date");
        lblPayDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblPayDate);
        lblPayDate.setBounds(10, 170, 140, 30);

        dpkPayDate.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(dpkPayDate);
        dpkPayDate.setBounds(150, 170, 280, 30);

        lblPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
        lblPaidAmt.setText("Paid Amount");
        lblPaidAmt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblPaidAmt);
        lblPaidAmt.setBounds(10, 210, 140, 30);

        txtPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtPaidAmt);
        txtPaidAmt.setBounds(150, 210, 280, 30);

        lblPaySlip.setFont(Statics.NORMAL_LARGE_FONT);
        lblPaySlip.setText("Pay Slip Picture");
        lblPaySlip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblPaySlip);
        lblPaySlip.setBounds(10, 370, 140, 70);

        picPaySlip.setFont(Statics.NORMAL_LARGE_FONT);
        picPaySlip.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panel.add(picPaySlip);
        picPaySlip.setBounds(150, 330, 280, 190);

        btnBrowse.setFont(Statics.NORMAL_LARGE_FONT);
        btnBrowse.setText("Browse...");
        panel.add(btnBrowse);
        btnBrowse.setBounds(10, 460, 140, 60);

        btnReset.setFont(Statics.NORMAL_LARGE_FONT);
        btnReset.setText("Reset");
        panel.add(btnReset);
        btnReset.setBounds(300, 530, 130, 60);

        btnUpdate.setFont(Statics.NORMAL_LARGE_FONT);
        btnUpdate.setText("Update");
        panel.add(btnUpdate);
        btnUpdate.setBounds(150, 530, 140, 60);

        btnAdd.setFont(Statics.NORMAL_LARGE_FONT);
        btnAdd.setText("Add New");
        panel.add(btnAdd);
        btnAdd.setBounds(10, 530, 130, 60);

        tblPayList.setAutoCreateRowSorter(true);
        tblPayList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pay ID", "Supplier Name", "Bank Name", "A/c No.", "Pay Date", "Bill No.", "Paid Amount", "Slip Picture"
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -6653378191723732275L;
			Class<?>[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspPayList.setViewportView(tblPayList);

        panel.add(jspPayList);
        jspPayList.setBounds(440, 10, 320, 500);

        lblBillNo.setFont(Statics.NORMAL_LARGE_FONT);
        lblBillNo.setText("Bill No.");
        lblBillNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblBillNo);
        lblBillNo.setBounds(10, 290, 140, 30);

        txtBillNo.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtBillNo);
        txtBillNo.setBounds(150, 290, 280, 30);

        lblPrevBal.setFont(Statics.NORMAL_LARGE_FONT);
        lblPrevBal.setText("Total Pending");
        lblPrevBal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblPrevBal);
        lblPrevBal.setBounds(10, 50, 140, 30);

        txtPrevBal.setEditable(false);
        txtPrevBal.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtPrevBal);
        txtPrevBal.setBounds(150, 50, 280, 30);

        lblRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
        lblRemAmt.setText("New Balance");
        lblRemAmt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(lblRemAmt);
        lblRemAmt.setBounds(10, 250, 140, 30);

        txtRemAmt.setEditable(false);
        txtRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtRemAmt);
        txtRemAmt.setBounds(150, 250, 280, 30);
	}

	private void resetPic() {
		try {
			BufferedImage img = ImageIO.read(Statics
					.getImageStream("emptypic.png"));
			picPaySlip.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
	}

	private void resetFields() {
		resetPic();
		fleSlip = null;
		for (Component com : panel.getComponents()) {
			if (com instanceof javax.swing.JTextField) {
				((javax.swing.JTextField) com).setText("");
			}
		}
		supid = -1;
	}

	@SuppressWarnings("deprecation")
	private void initFields() {
		if (tblPayList.getSelectedRowCount() > 0) {
			int row = tblPayList.getSelectedRow();
			txtFullName.setText(tblPayList.getValueAt(row, 1).toString());
			txtBankName.setText(tblPayList.getValueAt(row, 2).toString());
			txtAccNo.setText(tblPayList.getValueAt(row, 3).toString());
			dpkPayDate.setDate(new java.util.Date(tblPayList.getValueAt(row, 4)
					.toString()));
			txtBillNo.setText(tblPayList.getValueAt(row, 5).toString());
			txtPaidAmt.setText(tblPayList.getValueAt(row, 6).toString());
			fleSlip = new File(Statics.ROOT + "img" + Statics.DIR_SEP
					+ "payslip" + Statics.DIR_SEP
					+ tblPayList.getValueAt(row, 7).toString());
			try {
				if(fleSlip.exists())
				{
					BufferedImage img = ImageIO.read(fleSlip);
					picPaySlip.setIcon(new ImageIcon(img.getScaledInstance(
							picPaySlip.getWidth(), picPaySlip.getHeight(),
							Image.SCALE_SMOOTH)));
				}
				else
				{
					Statics.showMessage(rootPane, "File not found!", JOptionPane.WARNING_MESSAGE);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Statics.showMessage(getContentPane(), "Unable to set Image!",
						JOptionPane.ERROR_MESSAGE);
				Statics.handleException(e1);
				resetPic();
			}
		}
	}

	private class SupplierPaymentUIListen implements ActionListener,
			KeyListener, MouseListener, SuggestionSelectedListener, FocusListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReset) {
				resetFields();
			}

			else if (e.getSource() == btnAdd) {
				resetFields();
			}

			else if (e.getSource() == btnUpdate) {
				for (Component com : panel.getComponents()) {
					if (com instanceof JTextField) {
						if (((JTextField) com).getText().equals("")) {
							Statics.showMessage(getContentPane(),
									"Please enter all fields!",
									JOptionPane.ERROR_MESSAGE);
							((JTextField) com).requestFocus();
							return;
						}
					}
				}
				if (fleSlip == null) {
					Statics.showMessage(getContentPane(),
							"Please select payment slip picture!",
							JOptionPane.ERROR_MESSAGE);
					btnBrowse.requestFocus();
					return;
				} else if (supid < 0) {
					Statics.showMessage(getContentPane(),
							"Please select supplier properly!",
							JOptionPane.ERROR_MESSAGE);
					txtFullName.getText();
					return;
				}
				SupplierPayData data = new SupplierPayData();
				data.bacno = txtAccNo.getText();
				data.bankname = txtBankName.getText();
				data.billno = txtBillNo.getText();
				data.prevbal=Float.parseFloat(txtPrevBal.getText());
				data.paidamt = Float.parseFloat(txtPaidAmt.getText());
				data.remamt=Float.parseFloat(txtRemAmt.getText());
				data.paydate = dpkPayDate.getDate();
				data.slippic = txtBillNo.getText().replace(",", "-")
						+ " "
						+ new java.text.SimpleDateFormat("dd-MM-yyyy")
								.format(dpkPayDate.getDate())
						+ fleSlip.getAbsolutePath().substring(
								fleSlip.getAbsolutePath().lastIndexOf("."));
				data.supid = supid;
				if(data.prevbal==0)
				{
					Statics.showMessage(rootPane, "Previous balance is zero! No payment entries can be made!", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(data.paidamt==0)
				{
					Statics.showMessage(rootPane, "Cannot make zero payment entry!", JOptionPane.ERROR_MESSAGE);
					txtPaidAmt.setText("");
					txtPaidAmt.requestFocus();
					return;
				}
				SupplierPayment pay = new SupplierPayment();
				if(pay.getBillWisePayment(data.billno, data.supid)!=null)
				{
					Statics.showMessage(rootPane, "Payment entry already made for given bill number and Supplier!", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int ret = pay.insertPaymentDetails(data);
				if (ret > 0) {
					Statics.makeDir("img");
					Statics.makeDir("img" + Statics.DIR_SEP + "payslip");
					File destf = new File(Statics.ROOT + "img"
							+ Statics.DIR_SEP + "payslip" + Statics.DIR_SEP
							+ data.slippic);
					Statics.imageCopy(
							fleSlip,
							destf,
							fleSlip.getAbsolutePath()
									.substring(
											fleSlip.getAbsolutePath()
													.lastIndexOf(".") + 1));
					Statics.showMessage(getContentPane(),
							"Payment entry made successfully!",
							JOptionPane.INFORMATION_MESSAGE);
					initTableData();
					resetFields();
					return;
				} else {
					Statics.showMessage(getContentPane(),
							"Payment entry not made successfully!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			else if (e.getSource() == btnBrowse) {
				JFileChooser choose;
				choose = new JFileChooser(fleSlip);

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
					fleSlip = choose.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(fleSlip);
						picPaySlip.setIcon(new ImageIcon(img.getScaledInstance(
								picPaySlip.getWidth(), picPaySlip.getHeight(),
								Image.SCALE_SMOOTH)));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						Statics.showMessage(getContentPane(),
								"Unable to set Image!",
								JOptionPane.ERROR_MESSAGE);
						Statics.handleException(e1);
						resetPic();
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == tblPayList) {
				initFields();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e) {
			if (e.getSource() == txtFullName) {
				SuppliersData data = new Suppliers()
						.getSupplierByName(txtFullName.getText());
				if (data != null) {
					supid = data.supid;
					txtPrevBal.setText(data.supcr+"");
				}
			}
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
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblPayList
					&& (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)) {
				initFields();
			}
		}
		
		@Override
		public void focusLost(FocusEvent e)
		{
			if(e.getSource()==txtPaidAmt)
			{
				float amt=Float.parseFloat(txtPaidAmt.getText());
				float prev=Float.parseFloat(txtPrevBal.getText());
				if(amt>prev)
				{
					Statics.showMessage(getContentPane(), "Paid amount is larger than previous balance!", JOptionPane.ERROR_MESSAGE);
					txtPaidAmt.setText("");
					txtPaidAmt.requestFocus();
					return;
				}
				txtRemAmt.setText((prev-amt)+"");
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

		@Override
		public void focusGained(FocusEvent arg0) {}
	}
}
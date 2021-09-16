package com.ims.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.extern.datepicker.DateButton;
import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.CustPayment;
import com.ims.models.Customers;
import com.ims.models.data.CustPaymentData;
import com.ims.models.data.CustomersData;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.jsuggestfield.JSuggestionField;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedEvent;
import com.jdev.girish.ui.jsuggestfield.event.SuggestionSelectedListener;

/**
 *
 * @author Girish
 */
public class CustPaymentUI extends IFrame {

	private static final long serialVersionUID = -5494364054284001493L;

	private javax.swing.JButton btnReset;

	private javax.swing.JButton btnSave;

	private javax.swing.JScrollPane jspPayDetails;
	private javax.swing.JLabel lblDebtAmt;
	private javax.swing.JLabel lblFullName;
	private javax.swing.JLabel lblPaidAmt;
	private javax.swing.JLabel lblPayDetails;
	private javax.swing.JLabel lblPhone;
	private javax.swing.JLabel lblRemAmt;
	private javax.swing.JTextField txtDebtAmt;
	private JSuggestionField txtFullName;
	private javax.swing.JTextField txtPaidAmt;
	private javax.swing.JTextArea txtPayDetails;
	private JSuggestionField txtPhone;
	private javax.swing.JTextField txtRemAmt;
	private javax.swing.JLabel lblPayDate;
	private DateButton dpkPayDate;
	
	private long custid;
	private CustPaymentUIListen listen;
	private ArrayList<String> arrFullName;
	private ArrayList<String> arrPhone;
	
	public CustPaymentUI() {
		super(Program.LANG.getProperty("mnicustpayment"), 440, 450);
		initComponents();
		custid=-1;
		
		arrFullName=new ArrayList<String>();
		arrPhone=new ArrayList<String>();
		txtFullName.setSuggestionList(arrFullName);
		txtPhone.setSuggestionList(arrPhone);
		
		listen=new CustPaymentUIListen();
		txtFullName.addSuggestionSelectedListener(listen);
		txtPhone.addSuggestionSelectedListener(listen);
		btnReset.addActionListener(listen);
		btnSave.addActionListener(listen);
		txtPaidAmt.addFocusListener(listen);
		
		initData();
		
		//TODO Multilanguage Text
		
		lblFullName.setText(Program.LANG.getProperty("lblcustname"));
		lblPhone.setText(Program.LANG.getProperty("lblcontactno"));
		btnReset.setText(Program.LANG.getProperty("btnreset"));
		
		lblDebtAmt.setText(Program.LANG.getProperty("lblcustdebitamt"));
		lblPaidAmt.setText(Program.LANG.getProperty("lblcustpaidamt"));
		lblPayDate.setText(Program.LANG.getProperty("lblcustpaydate"));
		lblRemAmt.setText(Program.LANG.getProperty("lblcustremainamt"));
		lblPayDetails.setText(Program.LANG.getProperty("lblcustpaydetails"));
		btnSave.setText(Program.LANG.getProperty("btncustreceivesave"));
	}
	
	private void initData()
	{
		arrFullName.clear();
		arrPhone.clear();
		
		CustomersData[] custs=new Customers().getAllCustomers();
		for(int i=0;i<custs.length;i++)
		{
			arrFullName.add(custs[i].custname);
			arrPhone.add(custs[i].custphone);
		}
	}

	private void initComponents() {

		lblFullName = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        txtFullName = new JSuggestionField();
        txtPhone = new JSuggestionField();
        lblDebtAmt = new javax.swing.JLabel();
        txtDebtAmt = new javax.swing.JTextField();
        lblPaidAmt = new javax.swing.JLabel();
        txtPaidAmt = new javax.swing.JTextField();
        lblRemAmt = new javax.swing.JLabel();
        txtRemAmt = new javax.swing.JTextField();
        lblPayDetails = new javax.swing.JLabel();
        jspPayDetails = new javax.swing.JScrollPane();
        txtPayDetails = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblPayDate = new javax.swing.JLabel();
        dpkPayDate = new DateButton();

        panel.setLayout(null);

        lblFullName.setFont(Statics.NORMAL_LARGE_FONT);
        lblFullName.setText("Customer Name");
        panel.add(lblFullName);
        lblFullName.setBounds(10, 10, 220, 30);

        lblPhone.setFont(Statics.NORMAL_LARGE_FONT);
        lblPhone.setText("Contact Number");
        panel.add(lblPhone);
        lblPhone.setBounds(10, 50, 220, 30);

        txtFullName.setFont(Statics.NORMAL_LARGE_FONT);
        panel.add(txtFullName);
        txtFullName.setBounds(230, 10, 200, 30);

        txtPhone.setFont(Statics.NORMAL_LARGE_FONT);
        txtPhone.setToolTipText("");
        panel.add(txtPhone);
        txtPhone.setBounds(230, 50, 200, 30);

        lblDebtAmt.setFont(Statics.NORMAL_LARGE_FONT);
        lblDebtAmt.setText("Debit Amount");
        panel.add(lblDebtAmt);
        lblDebtAmt.setBounds(10, 90, 220, 30);

        txtDebtAmt.setEditable(false);
        txtDebtAmt.setFont(Statics.NORMAL_LARGE_FONT);
        txtDebtAmt.setToolTipText("");
        panel.add(txtDebtAmt);
        txtDebtAmt.setBounds(230, 90, 200, 30);

        lblPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
        lblPaidAmt.setText("Paid Amount");
        panel.add(lblPaidAmt);
        lblPaidAmt.setBounds(10, 130, 220, 30);

        txtPaidAmt.setFont(Statics.NORMAL_LARGE_FONT);
        txtPaidAmt.setToolTipText("");
        panel.add(txtPaidAmt);
        txtPaidAmt.setBounds(230, 130, 200, 30);

        lblRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
        lblRemAmt.setText("Remaining Amount");
        panel.add(lblRemAmt);
        lblRemAmt.setBounds(10, 210, 220, 30);

        txtRemAmt.setFont(Statics.NORMAL_LARGE_FONT);
        txtRemAmt.setToolTipText("");
        txtRemAmt.setEditable(false);
        panel.add(txtRemAmt);
        txtRemAmt.setBounds(230, 210, 200, 30);

        lblPayDetails.setFont(Statics.NORMAL_LARGE_FONT);
        lblPayDetails.setText("Payment Details");
        panel.add(lblPayDetails);
        lblPayDetails.setBounds(10, 250, 220, 90);

        txtPayDetails.setColumns(1);
        txtPayDetails.setFont(Statics.NORMAL_LARGE_FONT);
        txtPayDetails.setLineWrap(true);
        txtPayDetails.setRows(1);
        jspPayDetails.setViewportView(txtPayDetails);

        panel.add(jspPayDetails);
        jspPayDetails.setBounds(230, 250, 200, 90);

        btnSave.setFont(Statics.NORMAL_LARGE_FONT);
        btnSave.setText("Receive & Save");
        panel.add(btnSave);
        btnSave.setBounds(10, 350, 190, 60);

        btnReset.setFont(Statics.NORMAL_LARGE_FONT);
        btnReset.setText("Reset");
        panel.add(btnReset);
        btnReset.setBounds(230, 350, 200, 60);

        lblPayDate.setFont(Statics.NORMAL_LARGE_FONT);
        lblPayDate.setText("Payment Date");
        panel.add(lblPayDate);
        lblPayDate.setBounds(10, 170, 220, 30);

        panel.add(dpkPayDate);
        dpkPayDate.setFont(Statics.NORMAL_LARGE_FONT);
        dpkPayDate.setBounds(230, 170, 200, 30);
	}
	
	private class CustPaymentUIListen implements SuggestionSelectedListener, ActionListener, FocusListener
	{
		@Override
		public void SuggestionSelected(SuggestionSelectedEvent e)
		{
			if(e.getSource()==txtFullName || e.getSource()==txtPhone)
			{
				CustomersData cust=null;
				if(e.getSource()==txtFullName)
					cust=new Customers().getCustomerByName(txtFullName.getText());
				else if(e.getSource()==txtPhone)
					cust=new Customers().getCustomerByMobileNumber(txtPhone.getText());
				if(cust!=null)
				{
					if(cust.debtamt<=0)
					{
						Statics.showMessage(getContentPane(), "No any debit remaining!", JOptionPane.INFORMATION_MESSAGE);
						Statics.resetFields(panel.getComponents());
						custid=-1;
						return;
					}
					custid=cust.custid;
					txtFullName.setText(cust.custname);
					txtPhone.setText(cust.custphone);
					txtDebtAmt.setText(cust.debtamt+"");
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnReset)
			{
				Statics.resetFields(panel.getComponents());
				custid=-1;
			}
			else if(e.getSource()==btnSave)
			{
				if(custid<1)
				{
					Statics.showMessage(getContentPane(), "Please select customer details!", JOptionPane.ERROR_MESSAGE);
					txtFullName.requestFocus();
					return;
				}
				else if(txtPaidAmt.getText().equals(""))
				{
					Statics.showMessage(getContentPane(), "Please enter paid amount!", JOptionPane.ERROR_MESSAGE);
					txtPaidAmt.requestFocus();
					return;
				}
				else if(txtPayDetails.getText().equals(""))
				{
					Statics.showMessage(getContentPane(), "Please enter payment details!", JOptionPane.ERROR_MESSAGE);
					txtPayDetails.requestFocus();
					return;
				}
				
				CustPaymentData data=new CustPaymentData();
				data.custid=custid;
				data.paydate=dpkPayDate.getDate();
				data.prevbal=Float.parseFloat(txtDebtAmt.getText());
				data.paidamt=Float.parseFloat(txtPaidAmt.getText());
				data.remamt=Float.parseFloat(txtRemAmt.getText());
				
				CustPayment pay=new CustPayment();
				int ret=pay.makePaymentEntry(data);
				if(ret>0)
				{
					Statics.showMessage(getContentPane(), "Customer Payment entry made successfully!", JOptionPane.INFORMATION_MESSAGE);
					Statics.resetFields(panel.getComponents());
					return;
				}
				else
				{
					Statics.showMessage(getContentPane(), "Customer payment entry does not made successfully!", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		
		@Override
		public void focusLost(FocusEvent e)
		{
			if(e.getSource()==txtPaidAmt)
			{
				float amt=Float.parseFloat(txtPaidAmt.getText());
				float prev=Float.parseFloat(txtDebtAmt.getText());
				if(amt>prev)
				{
					Statics.showMessage(getContentPane(), "Received amount is larger than prev. balance!", JOptionPane.ERROR_MESSAGE);
					txtPaidAmt.setText("");
					txtPaidAmt.requestFocus();
					return;
				}
				txtRemAmt.setText((prev-amt)+"");
			}
		}

		@Override
		public void focusGained(FocusEvent arg0) {}
	}
}
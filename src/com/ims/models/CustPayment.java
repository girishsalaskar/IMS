package com.ims.models;

import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.CustPaymentData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class CustPayment extends IMSDB {

	public int makePaymentEntry(CustPaymentData data)
	{
		int ret=0;
		try {
			super.setAutoCommit(false);
			ret=super.executeUpdate("insert into custpayment(custid, paydate, prevbal, paidamt,"
					+ "remamt) values(?,?,?,?,?)", data.custid, new java.sql.Date(data.paydate.getTime()),
					data.prevbal, data.paidamt, data.remamt);
			
			ret+=super.executeUpdate("update customers set debtamt=? where custid=?",
					data.remamt, data.custid);
			
			super.commit();
			super.setAutoCommit(true);
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return ret;
	}
}
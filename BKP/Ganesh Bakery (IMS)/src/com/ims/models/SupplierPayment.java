package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.SupplierPayData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class SupplierPayment extends IMSDB {

	public int insertPaymentDetails(SupplierPayData data) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret = super
					.executeUpdate(
							"insert into supppayment(supid, bankname, bacno,"
									+ " prevbal, paidamt, remamt, slippic, paydate,"
									+ " billno) values(?,?,?,?,?,?,?,?,?)",	data.supid,
									data.bankname, data.bacno, data.prevbal, data.paidamt,
									data.remamt, data.slippic, new java.sql.Date(data.paydate.getTime()),
									data.billno);
			
			super.executeUpdate("update suppliers set supcr=? where supid=?", data.remamt, data.supid);
			super.commit();
			super.setAutoCommit(true);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return ret;
	}

	public SupplierPayData[] getAllPaymentDetails() {
		SupplierPayData[] data = null;
		try {
			super.executeQuery("select * from supppayment");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new SupplierPayData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new SupplierPayData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public SupplierPayData[] getDatewisePaymentDetails(java.util.Date date) {
		SupplierPayData[] data = null;
		try {
			super.executeQuery("select * from supppayment where paydate=?",
					new java.sql.Date(date.getTime()));
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new SupplierPayData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new SupplierPayData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private void initData(SupplierPayData data, ResultSet res)
			throws SQLException {
		data.payid = res.getLong("payid");
		data.supid = res.getLong("supid");
		data.bankname = res.getString("bankname");
		data.bacno = res.getString("bacno");
		data.paidamt = res.getFloat("paidamt");
		data.slippic = res.getString("slippic");
		data.paydate = new java.util.Date(res.getDate("paydate").getTime());
		data.billno = res.getString("billno");
	}

	public SupplierPayData[] getDateAndNameWiseList(java.util.Date date,
			long supid) {
		SupplierPayData[] data = null;
		try {
			super.executeQuery(
					"select * from supppayment where paydate=? and supid=?",
					new java.sql.Date(date.getTime()), supid);
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new SupplierPayData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new SupplierPayData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}
}
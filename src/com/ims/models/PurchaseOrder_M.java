package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.PurchaseOrder_MData;
import com.ims.models.data.PurchaseOrder_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class PurchaseOrder_M extends IMSDB {

	public int createPurchaseOrder(PurchaseOrder_MData pmdata,
			PurchaseOrder_SData[] psdata) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret = super.executeUpdate(
					"insert into purchaseorder_m(podate, supid) values(?,?)",
					new java.sql.Date(pmdata.podate.getTime()), pmdata.supid);
			if (ret > 0) {
				super.executeQuery("select max(pomid) from purchaseorder_m");
				if (resultset.next()) {
					long pomid = resultset.getLong("max(pomid)");
					for (int i = 0; i < psdata.length; i++) {
						ret += super
								.executeUpdate(
										"insert into purchaseorder_s"
												+ "(pomid, prodid, prodqty) values(?,?,?)",
										pomid, psdata[i].prodid,
										psdata[i].prodqty);
					}
				}
			}
			super.commit();
			super.setAutoCommit(true);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			ret = 0;
		}
		return ret;
	}

	public PurchaseOrder_MData[] getOrderByDate(java.util.Date orderdate) {
		PurchaseOrder_MData[] data = null;
		try {
			super.executeQuery("select * from purchaseorder_m where podate=?",
					new java.sql.Date(orderdate.getTime()));
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new PurchaseOrder_MData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new PurchaseOrder_MData();
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

	public PurchaseOrder_MData getOrderDetailsByID(long pomid) {
		PurchaseOrder_MData data = null;
		try {
			super.executeQuery("select * from purchaseorder_m where pomid=?",
					pomid);
			if (resultset.next()) {
				data = new PurchaseOrder_MData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return data;
	}

	private void initData(PurchaseOrder_MData data, ResultSet res)
			throws SQLException {
		data.pomid = res.getLong("pomid");
		data.podate = new java.util.Date(res.getDate("podate").getTime());
		data.supid = res.getLong("supid");
	}
}
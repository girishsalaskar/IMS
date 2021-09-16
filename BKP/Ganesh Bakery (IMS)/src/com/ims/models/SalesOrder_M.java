package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.SalesOrder_MData;
import com.ims.models.data.SalesOrder_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class SalesOrder_M extends IMSDB {

	public int createSalesOrder(SalesOrder_MData mdata, SalesOrder_SData[] sdata) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret = super.executeUpdate(
					"insert into salesorder_m(sodate, custid) values(?,?)",
					new java.sql.Date(mdata.sodate.getTime()), mdata.custid);
			if (ret > 0) {
				super.executeQuery("select max(somid) from salesorder_m");
				if (resultset.next()) {
					long somid = resultset.getLong("max(somid)");
					for (int i = 0; i < sdata.length; i++) {
						ret += super.executeUpdate(
								"insert into salesorder_s(somid, prodid, prodqty)"
										+ " values(?,?,?)", somid,
								sdata[i].prodid, sdata[i].prodqty);
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

	public SalesOrder_MData[] getOrderByDate(java.util.Date date) {
		SalesOrder_MData[] mdata = null;
		try {
			super.executeQuery("select * from salesorder_m where sodate=?",
					new java.sql.Date(date.getTime()));
			if (resultset.next()) {
				resultset.beforeFirst();
				mdata = new SalesOrder_MData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					mdata[i] = new SalesOrder_MData();
					initData(mdata[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return mdata;
	}

	private void initData(SalesOrder_MData data, ResultSet res)
			throws SQLException {
		data.custid = res.getLong("custid");
		data.sodate = new java.util.Date(res.getDate("sodate").getTime());
		data.somid = res.getLong("somid");
	}

	public SalesOrder_MData getSalesOrder(long somid) {
		SalesOrder_MData data = null;
		try {
			super.executeQuery("select * from salesorder_m where somid=?",
					somid);
			if (resultset.next()) {
				data = new SalesOrder_MData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}
}
package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.SalesOrder_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class SalesOrder_S extends IMSDB {

	public SalesOrder_SData[] getSalesOrderByMID(long somid) {
		SalesOrder_SData[] sdata = null;
		try {
			super.executeQuery("select * from salesorder_s where somid=?",
					somid);
			if (resultset.next()) {
				resultset.beforeFirst();
				sdata = new SalesOrder_SData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					sdata[i] = new SalesOrder_SData();
					initData(sdata[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return sdata;
	}

	private void initData(SalesOrder_SData sdata, ResultSet res)
			throws SQLException {
		sdata.sosid = res.getLong("sosid");
		sdata.somid = res.getLong("somid");
		sdata.prodid = res.getLong("prodid");
		sdata.prodqty = res.getInt("prodqty");
	}
}
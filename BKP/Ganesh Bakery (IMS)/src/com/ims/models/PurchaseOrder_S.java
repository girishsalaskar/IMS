package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.PurchaseOrder_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class PurchaseOrder_S extends IMSDB {

	public PurchaseOrder_SData[] getPurchaseOrderListByOrderMID(long ordermid) {
		PurchaseOrder_SData[] data = null;
		try {
			super.executeQuery("select * from purchaseorder_s where pomid=?",
					ordermid);
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new PurchaseOrder_SData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new PurchaseOrder_SData();
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

	private void initData(PurchaseOrder_SData data, ResultSet res)
			throws SQLException {
		data.posid = res.getLong("posid");
		data.pomid = res.getLong("pomid");
		data.prodid = res.getLong("prodid");
		data.prodqty = res.getInt("prodqty");
	}
}
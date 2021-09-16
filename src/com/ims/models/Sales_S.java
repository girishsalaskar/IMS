package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.Sales_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Sales_S extends IMSDB {

	public Sales_SData[] getSalesDetailsByMasterId(long samid)
	{
		Sales_SData[] data=null;
		try {
			super.executeQuery("select * from sales_s where salemid=?", samid);
			if(resultset.next())
			{
				resultset.beforeFirst();
				data=new Sales_SData[super.getRowCount()];
				for(int i=0;resultset.next();i++)
				{
					data[i]=new Sales_SData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private void initData(Sales_SData data, ResultSet res) throws SQLException {
		data.salesid=res.getLong("salesid");
		data.salemid=res.getLong("salemid");
		data.prodid=res.getLong("prodid");
		data.prodqty=res.getInt("prodqty");
		data.prodrate=res.getFloat("prodrate");
		data.totrate=res.getFloat("totrate");
	}
}
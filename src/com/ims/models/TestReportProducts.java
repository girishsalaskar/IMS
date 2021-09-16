package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.TestReportProductsData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class TestReportProducts extends IMSDB{

	public int makeReportProductEntry(TestReportProductsData data)
	{
		int ret=0;
		try {
			ret+=super.executeUpdate("INSERT INTO `test_report_prods` (`rptid`, `prodname`, `prodstatus`) VALUES (?,?,?)",
					data.rptid, data.prodname, data.prodstatus);
		} catch (SQLException | DatabaseNotConnectedException e) {
			Statics.handleException(e);
		}
		return ret;
	}
	
	public TestReportProductsData[] getAllReportProducts() {
		TestReportProductsData[] data=null;
		try {
			super.executeQuery("select * from test_report_prods");
			if(resultset.next())
			{
				resultset.beforeFirst();
				data=new TestReportProductsData[super.getRowCount()];
				for(int i=0;resultset.next();i++)
				{
					data[i]=new TestReportProductsData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException | EmptyResultSetException e) {
			Statics.handleException(e);
		}
		return data;
	}

	private void initData(TestReportProductsData data, ResultSet res) throws SQLException
	{
		data.prodmid=res.getLong("prodmid");
		data.rptid=res.getLong("rptid");
		data.prodname=res.getString("prodname");
		data.prodstatus=res.getString("prodstatus");
	}
}
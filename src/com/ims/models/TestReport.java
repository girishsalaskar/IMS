package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.ims.misc.Statics;
import com.ims.models.data.TestReportData;
import com.ims.models.data.TestReportProductsData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class TestReport extends IMSDB {

	public int makeTestReportEntry(TestReportData data, TestReportProductsData prods[])
	{
		int ret=0;
		try {
			ret+=super.executeUpdate("INSERT INTO `test_report` (`name`, `address`, `contactno`, `rpttitle`, `rptdate`, `rptsummary`)"
					+ " VALUES(?, ?, ?, ?, ?, ?)", data.name, data.address, data.contactno, data.rpttitle, new java.sql.Date(data.rptdate.getTime()), data.rptsummary);
			data=getLastReport();
			TestReportProducts prod=new TestReportProducts();
			for(int i=0;i<prods.length;i++)
			{
				prods[i].rptid=data.rptid;
				ret+=prod.makeReportProductEntry(prods[i]);
			}
		} catch (SQLException | DatabaseNotConnectedException e) {
			Statics.handleException(e);
		}
		return ret;
	}

	public TestReportData getLastReport()
	{
		TestReportData data=null;
		try {
			super.executeQuery("select * from test_report where rptid=(select max(r.rptid) from test_report r)");
			if(resultset.next())
			{
				data=new TestReportData(); 
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return data;
	}

	private void initData(TestReportData data, ResultSet res) throws SQLException
	{
		data.rptid=res.getLong("rptid");
		data.name=res.getString("name");
		data.address=res.getString("address");
		data.contactno=res.getString("contactno");
		data.rpttitle=res.getString("rpttitle");
		data.rptdate=new java.util.Date(res.getDate("rptdate").getTime());
		data.rptsummary=res.getString("rptsummary");
	}

	public TestReportData[] getDatewiseDetails(java.util.Date date) {
		TestReportData[] data=null;
		try {
			super.executeQuery("select * from test_report where rptdate=?", new java.sql.Date(date.getTime()));
			if(resultset.next())
			{
				resultset.beforeFirst();
				data=new TestReportData[super.getRowCount()];
				for(int i=0;resultset.next();i++)
				{
					data[i]=new TestReportData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException | EmptyResultSetException e) {
			Statics.handleException(e);
		}
		return data;
	}

	public TestReportData[] getReportsByDateAndName(Date date, String string) {
		TestReportData[] data=null;
		try {
			super.executeQuery("select * from test_report where rptdate=? and name like ?", new java.sql.Date(date.getTime()), string);
			if(resultset.next())
			{
				resultset.beforeFirst();
				data=new TestReportData[super.getRowCount()];
				for(int i=0;resultset.next();i++)
				{
					data[i]=new TestReportData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException | EmptyResultSetException e) {
			Statics.handleException(e);
		}
		return data;
	}
}
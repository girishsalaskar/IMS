package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.RunnupCustomerData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class RunnupCustomers extends IMSDB {

	public int makeRunnupCustomerEntry(RunnupCustomerData[] prods)
	{
		int ret=0;
		try {
			super.setAutoCommit(false);
			
			for(int i=0;i<prods.length;i++)
			{
				ret+=super.executeUpdate("insert into runnupcustprods(custname, prodname, prodcost, grandtotal, salesdate) values(?,?,?,?,?)",
						prods[i].custname, prods[i].prodname, Double.parseDouble(prods[i].prodstatus), prods[i].grandtotal, new java.sql.Date(prods[i].salesdate.getTime()));
			}
			super.commit();
			super.setAutoCommit(true);
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return ret;
	}

	public RunnupCustomerData[] getAllData() {
		RunnupCustomerData[] data=null;
		try {
			super.executeQuery("select * from runnupcustprods where prodname in (select distinct(r.prodname) from runnupcustprods r)");
			if(resultset.next())
			{
				resultset.beforeFirst();
				data=new RunnupCustomerData[super.getRowCount()];
				for(int i=0;resultset.next();i++)
				{
					data[i]=new RunnupCustomerData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private void initData(RunnupCustomerData data,
			ResultSet res) throws SQLException {
		data.prodmid=res.getLong("prodmid");
		data.custname=res.getString("custname");
		data.salesdate=res.getDate("salesdate");
		data.prodname=res.getString("prodname");
		data.grandtotal=res.getDouble("prodcost");
		data.grandtotal=res.getDouble("grandtotal");
	}
}
package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.CustomersData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Customers extends IMSDB {

	public int updateCustomer(CustomersData data) {
		int ret = 0;
		if (data.custid == -1) {
			try {
				super.executeQuery(
						"select * from customers where custname=? and custphone=?",
						data.custname, data.custphone);
				if (resultset.next()) {
					return -1;
				}

				super.executeUpdate(
						"insert into customers(custname, custadd, custcity, custphone, "
								+ "custref, debtamt, crate_holds) values(?, ?, ?, ?, ?, ?, ?)",
						data.custname, data.custadd, data.custcity,
						data.custphone, data.custref, data.debtamt,
						data.crate_holds);
				ret = 1;
			} catch (DatabaseNotConnectedException | SQLException e) {
				// TODO Auto-generated catch block
				Statics.handleException(e);
				ret = 0;
			}
		} else {
			try {
				ret = super
						.executeUpdate(
								"update customers set custname=?, custadd=?, custcity=?, "
										+ "custphone=?, custref=?, debtamt=? where custid=?",
								data.custname, data.custadd, data.custcity,
								data.custphone, data.custref, data.debtamt,
								data.custid);
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				Statics.handleException(e);
				ret = 0;
			}
		}
		return ret;
	}

	public CustomersData[] getAllCustomers() {
		CustomersData[] data = null;
		try {
			super.executeQuery("select * from customers");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new CustomersData[super.getRowCount()];
				resultset.beforeFirst();
				for (int i = 0; resultset.next(); i++) {
					data[i] = new CustomersData();
					initData(data[i], resultset);
				}
				return data;
			} else
				data = null;
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			data = null;
		}
		return data;
	}

	public CustomersData getCustomerByName(String custname) {
		CustomersData data = null;
		try {
			super.executeQuery("select * from customers where custname=?",
					custname);
			if (resultset.next()) {
				data = new CustomersData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public CustomersData getCustomerByMobileNumber(String mobno) {
		CustomersData data = null;
		try {
			super.executeQuery("select * from customers where custphone=?",
					mobno);
			if (resultset.next()) {
				data = new CustomersData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private void initData(CustomersData data, ResultSet res) {
		try {
			data.custid = res.getLong("custid");
			data.custname = res.getString("custname");
			data.custadd = res.getString("custadd");
			data.custcity = res.getString("custcity");
			data.custphone = res.getString("custphone");
			data.custref = res.getString("custref");
			data.debtamt = res.getFloat("debtamt");
			data.crate_holds = res.getInt("crate_holds");
			data.routeid = res.getInt("routeid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
	}

	public CustomersData getCustomerById(long custid) {
		CustomersData data = null;
		try {
			super.executeQuery("select * from customers where custid=?", custid);
			if (resultset.next()) {
				data = new CustomersData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public int updateRouteData(long custid, int routeid) {
		int ret = -1;
		try {
			ret = super.executeUpdate(
					"update customers set routeid=? where custid=?", routeid,
					custid);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public CustomersData[] getRouteWiseCustomers(int routeid) {
		CustomersData[] data = null;
		try {
			super.executeQuery("select * from customers where routeid=?",
					routeid);
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new CustomersData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new CustomersData();
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

	public CustomersData[] getCustomersHavingCrates(int routeid) {
		CustomersData[] data = null;
		try {
			super.executeQuery(
					"select * from customers where crate_holds>0 and "
							+ "routeid=?", routeid);
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new CustomersData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new CustomersData();
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
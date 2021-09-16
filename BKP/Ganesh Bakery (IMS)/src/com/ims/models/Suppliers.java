package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.SuppliersData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Suppliers extends IMSDB {

	public SuppliersData getSupplierById(long supid) {
		SuppliersData data = null;
		try {
			super.executeQuery("select * from suppliers where supid=?", supid);
			if (resultset.next()) {
				data = new SuppliersData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public SuppliersData getSupplierByName(String suppliername) {
		SuppliersData data = null;
		try {
			super.executeQuery("select * from suppliers where supname=?",
					suppliername);
			if (resultset.next()) {
				data = new SuppliersData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public SuppliersData getSupplierByMobileNumber(String mobno) {
		SuppliersData data = null;
		try {
			super.executeQuery("select * from suppliers where supphone=?",
					mobno);
			if (resultset.next()) {
				data = new SuppliersData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public int updateSupplier(SuppliersData data) {
		int ret = 0;
		if (data.supid == -1) {
			try {
				super.executeQuery(
						"select * from suppliers where supname=? and supphone=?",
						data.supname, data.supphone);
				if (resultset.next()) {
					ret = -1;
				} else {
					super.executeUpdate(
							"insert into suppliers(supname, supadd, supphone, company, supcr)"
									+ " values(?, ?, ?, ?, ?)", data.supname,
							data.supadd, data.supphone, data.company, 0);
					ret = 1;
				}
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				Statics.handleException(e);
				ret = 0;
			}
		} else {
			try {
				super.executeUpdate(
						"update suppliers set supname=?, supadd=?, supphone=?, "
								+ "company=?, supcr=? where supid=?",
						data.supname, data.supadd, data.supphone, data.company,
						0, data.supid);
				ret = 1;
			} catch (DatabaseNotConnectedException | SQLException e) {
				// TODO Auto-generated catch block
				Statics.handleException(e);
				ret = 0;
			}
		}
		return ret;
	}

	public SuppliersData[] getAllSuppliers() {
		SuppliersData[] data = null;
		try {
			super.executeQuery("select * from suppliers");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new SuppliersData[super.getRowCount()];
				resultset.beforeFirst();
				for (int i = 0; resultset.next(); i++) {
					data[i] = new SuppliersData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			data = null;
		}
		return data;
	}

	private void initData(SuppliersData data, ResultSet res) {
		try {
			data.supid = res.getLong("supid");
			data.supname = res.getString("supname");
			data.supadd = res.getString("supadd");
			data.supphone = res.getString("supphone");
			data.company = res.getString("company");
			data.supcr = res.getFloat("supcr");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
	}
}
package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.EmployeeAdvPay_Data;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class EmployeeAdvPay extends IMSDB {

	public EmployeeAdvPay_Data[] getPayDetailsByEmployeeId(long empid) {
		EmployeeAdvPay_Data[] data = null;
		try {
			super.executeQuery("select * from empadvance where empid=?", empid);
			if (super.getRowCount() > 0) {
				data = new EmployeeAdvPay_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new EmployeeAdvPay_Data();
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

	public int inserPayEntry(EmployeeAdvPay_Data data) {
		int res = 0;
		try {
			res = super
					.executeUpdate(
							"insert into empadvance(empid, paiddate, prevbal, paidamt, balanceamt) values(?,?,?,?,?)",
							data.empid, data.paiddate, data.prevbal,
							data.paidamt, data.balanceamt);

			res = super.executeUpdate("update empm set acbal=? where empid=?",
					data.balanceamt, data.empid);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private void initData(EmployeeAdvPay_Data data, ResultSet res)
			throws SQLException {
		data.advid = res.getLong("advid");
		data.empid = res.getLong("empid");
		data.paiddate = new java.util.Date(res.getDate("paiddate").getTime());
		data.prevbal = res.getInt("prevbal");
		data.paidamt = res.getInt("paidamt");
		data.balanceamt = res.getInt("balanceamt");
	}
}